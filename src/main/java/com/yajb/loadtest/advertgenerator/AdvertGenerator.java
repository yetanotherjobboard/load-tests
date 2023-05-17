package com.yajb.loadtest.advertgenerator;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yajb.com.client.account.AccountApi;
import yajb.com.client.account.invoker.ApiException;
import yajb.com.client.account.model.JobAdvertDraft;
import yajb.com.client.account.model.PublishAdvertRequest;
import yajb.com.client.account.model.Subscription;
import yajb.com.client.config.ConfigApi;
import yajb.com.client.config.model.StaticConfig;

@Component
@RequiredArgsConstructor
@Slf4j
class AdvertGenerator {

  static final URI IRRELEVANT_CONTINUE_URI = URI.create("irrelevant");

  final AdvertGeneratorConfigProps cfg;
  final ConfigApi configApi;
  final AccountApi accountApi;
  final UserTokenHolder userTokenHolder;



  @SneakyThrows
  void generateAdverts() {
    testConnection();

    log.info("fetching static config");
    var staticConfig = configApi.staticConfig(cfg.tenant.token);

    log.info("{} logging in", cfg.user.username);
    userTokenHolder.login(cfg.user.username, cfg.user.password);

    log.info("fetching slots");
    var userAccount = accountApi.getOrCreateAccount(cfg.tenant.token);
    Subscription subscription = userAccount.getSubscription();
    log.info("user's subscription: {}", subscription != null ? subscription.getPlan().getName() : "none");

    var availableSlots = userAccount.getSlots().getAvailable();
    var advertsToGenerate = Math.min(availableSlots, cfg.maxAdvertsToGenerate);
    log.info("user has {} available slots. will generate {} adverts", availableSlots, advertsToGenerate);

    new GenerationLoop(staticConfig, advertsToGenerate)
        .run()
        .printSummary()
    ;
  }

  @SneakyThrows
  private void testConnection() {
    var healthCheck = HttpRequest
            .newBuilder(URI.create("%s://%s/ee-service/actuator/health".formatted(cfg.target.scheme, cfg.target.host)))
            .GET()
            .build();

    var healthCheckResponse = HttpClient
        .newHttpClient()
        .send(healthCheck, HttpResponse.BodyHandlers.ofString());

    log.info("connection check : {} : {}", healthCheckResponse, healthCheckResponse.body());
  }

  @RequiredArgsConstructor
  class GenerationLoop {
    final StaticConfig staticConfig;
    final int advertsToGenerate;

    final AtomicInteger errorCount = new AtomicInteger(0);
    final AtomicInteger generatedCount = new AtomicInteger(0);

    long startTime;
    long endTime;

    @SneakyThrows
    GenerationLoop run() {
      var executor = Executors.newFixedThreadPool(10);

      log.info("will generate {} adverts", advertsToGenerate);
      startTime = System.currentTimeMillis();
      IntStream
          .rangeClosed(1, advertsToGenerate)
          .forEach(i -> executor.submit(this::tryToPublishRandomAdvert));

      executor.shutdown();
      executor.awaitTermination(1, TimeUnit.DAYS);
      endTime = System.currentTimeMillis();
      return this;
    }

    void tryToPublishRandomAdvert() {
      try {
        publishRandomAdvert();
      } catch (Exception e) {
        log.error("exception while publishing", e);
        errorCount.incrementAndGet();
      }
    }

    void publishRandomAdvert() throws ApiException {
      JobAdvertDraft randomAdvert = AdvertBuilder.randomAd(staticConfig);
      var advertId = accountApi.createJobAdvert(cfg.tenant.token, randomAdvert).getId();
      accountApi.publishJobAdvert(
          cfg.tenant.token,
          advertId.getGuid(),
          new PublishAdvertRequest().continueUrl(IRRELEVANT_CONTINUE_URI));
      int generated = generatedCount.incrementAndGet();
      log.info("Generated [{}/{}] ads", generated, advertsToGenerate);
    }

    void printSummary() {
      log.info("Advert generation finished");
      log.info("Time spent (minutes): {}", (endTime - startTime) / 60_000);
      log.info("Error count: {}", errorCount);
    }
  }
}

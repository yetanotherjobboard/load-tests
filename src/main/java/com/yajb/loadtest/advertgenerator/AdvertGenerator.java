package com.yajb.loadtest.advertgenerator;


import java.net.URI;
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
  void run() {
    log.info("fetching static config");
    var staticConfig = configApi.staticConfig(cfg.tenant.token);

    log.info("{} logging in", cfg.user.username);
    userTokenHolder.login(cfg.user.username, cfg.user.password);

    log.info("fetching slots");
    var userAccount = accountApi.getOrCreateAccount(cfg.tenant.token);
    Subscription subscription = userAccount.getSubscription();
    log.info("user's subscription: {}", subscription != null ? subscription.getPlan().getName() : "none");

    new GenerationLoop(staticConfig, userAccount.getSlots().getAvailable())
        .run()
        .printSummary()
    ;


  }

  @RequiredArgsConstructor
  class GenerationLoop {
    final StaticConfig staticConfig;
    final int availableSlots;

    long startTime;
    long endTime;
    int errorCount;

    GenerationLoop run() {
      log.info("will generate {} adverts", availableSlots);
      startTime = System.currentTimeMillis();
      IntStream.range(1, availableSlots).forEach(i -> tryToPublishRandomAdvert(i, availableSlots));
      endTime = System.currentTimeMillis();
      return this;
    }

    void tryToPublishRandomAdvert(int counter, int total) {
      try {
        publishRandomAdvert(counter, total);
      } catch (Exception e) {
        log.error("exception while publishing", e);
        errorCount++;
      }
    }

    void publishRandomAdvert(int counter, int total) throws ApiException {
      JobAdvertDraft randomAdvert = AdvertBuilder.randomAd(staticConfig);
      log.info("Drafting [{}/{}] : '{}'", counter, total, randomAdvert.getDetails().getTitle());
      var advertId = accountApi.createJobAdvert(cfg.tenant.token, randomAdvert).getId();
      log.info("Publishing");
      accountApi.publishJobAdvert(
          cfg.tenant.token,
          advertId.getGuid(),
          new PublishAdvertRequest().continueUrl(IRRELEVANT_CONTINUE_URI));
    }

    void printSummary() {
      log.info("Advert generation finished");
      log.info("Time spent (seconds): {}", (endTime - startTime) / 1000);
      log.info("Error count: {}", errorCount);
    }
  }
}

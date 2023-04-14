package com.yajb.loadtest.advertgenerator;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import yajb.com.client.account.AccountApi;
import yajb.com.client.account.SubscriptionApi;
import yajb.com.client.config.ConfigApi;
import yajb.com.client.config.model.StaticConfig;

@Component
@RequiredArgsConstructor
@Slf4j
class AdvertGenerator {

  final AdvertGeneratorConfigProps cfg;
  final ConfigApi configApi;
  final AccountApi accountApi;
  final SubscriptionApi subscriptionApi;

  @SneakyThrows
  void run() {
    log.info("hello");

//    networkTest();

    StaticConfig staticConfig = configApi.staticConfig(cfg.tenant.token);
    log.debug("static config : {}", staticConfig);

  }

  private static void networkTest() throws IOException, InterruptedException {
    var req = HttpRequest
        .newBuilder()
        .GET()
        .uri(URI.create("https://postman-echo.com/get"))
        .build();

    var resp = HttpClient
        .newHttpClient()
        .send(req, BodyHandlers.ofString());
    System.out.println(resp);
  }

}

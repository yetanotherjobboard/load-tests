package com.yajb.loadtest.search;


import static com.yajb.loadtest.search.TrafficUtils.searchLoop;
import static com.yajb.loadtest.search.TrafficUtils.httpTarget;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;

import io.gatling.javaapi.core.Simulation;
import lombok.RequiredArgsConstructor;


@SuppressWarnings({"squid:S1171", "unused"})
public class SearchLoadSimulation extends Simulation {

  {
    var apiBaseUrl = Env.valueOf(System.getProperty("target")).apiBaseUrl;
    System.out.println("SUT : " + apiBaseUrl);

    var warmUp = searchLoop("warm up", 10)
        .injectOpen(rampUsers(10).during(10));

    var load = searchLoop("load", 10)
        .injectOpen(rampUsers(30).during(15));

    var soak = searchLoop("soak", 30)
        .injectOpen(rampUsers(100).during(100));

    var traffic = warmUp.andThen(load).andThen(soak);

    setUp(traffic).protocols(httpTarget(apiBaseUrl));
  }

  @RequiredArgsConstructor
  enum Env {
    fly("https://yajb-api-gateway.fly.dev"),
    gcp("https://api-gateway-tvexvi6w2q-uc.a.run.app");

    final String apiBaseUrl;
  }
}

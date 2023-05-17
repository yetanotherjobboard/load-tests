package com.yajb.loadtest.search;


import static com.yajb.loadtest.search.TrafficUtils.searchLoop;
import static com.yajb.loadtest.search.TrafficUtils.target;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;

import io.gatling.javaapi.core.Simulation;


@SuppressWarnings({"squid:S1171", "unused"})
public class SearchLoadSimulation extends Simulation {

  static final String GCP_API_BASE_URL = "https://api-gateway-tvexvi6w2q-uc.a.run.app";
  static final String FLY_API_BASE_URL = "https://yajb-api-gateway.fly.dev";

//  static final String API_BASE_URL = GCP_API_BASE_URL;
  static final String API_BASE_URL = FLY_API_BASE_URL;


  {
    var warmUp = searchLoop(10)
        .injectOpen(rampUsers(10).during(10));

    var load = searchLoop(10)
        .injectOpen(rampUsers(20).during(40));

    var soak = searchLoop(100)
        .injectOpen(rampUsers(100).during(100));

    var traffic = warmUp.andThen(load).andThen(soak);

    setUp(traffic).protocols(target(API_BASE_URL));
  }


}

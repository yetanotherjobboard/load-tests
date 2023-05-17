package com.yajb.loadtest.search;


import static com.yajb.loadtest.search.TrafficUtils.TARGET;
import static com.yajb.loadtest.search.TrafficUtils.searchLoop;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;

import io.gatling.javaapi.core.Simulation;


@SuppressWarnings({"squid:S1171", "unused"})
public class SearchLoadSimulation extends Simulation {

  { main(); }

  void main() {

    var warmUp = searchLoop(10)
        .injectOpen(rampUsers(10).during(10));

    var soak = searchLoop(30)
        .injectOpen(rampUsers(30).during(30));

    setUp(warmUp.andThen(soak)).protocols(TARGET);
  }


}

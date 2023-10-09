package com.yajb.loadtest;


import static com.yajb.loadtest.TrafficUtils.searchLoop;
import static com.yajb.loadtest.TrafficUtils.httpTarget;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;

import io.gatling.javaapi.core.Simulation;


@SuppressWarnings({"squid:S1171", "unused"})
public class SearchLoadSimulation extends Simulation {

  {
    var props = SimulationProps.get();
    System.out.println(props);

    var warmUp = searchLoop("warm up", 5)
        .injectOpen(rampUsers(5).during(20));

    var load = searchLoop("load", props.maxLoops())
        .injectOpen(rampUsers(props.maxUsers()).during(props.maxUsers()));

    var traffic = warmUp.andThen(load);

    setUp(traffic).protocols(httpTarget(props.apiBaseUrl()));
  }


}

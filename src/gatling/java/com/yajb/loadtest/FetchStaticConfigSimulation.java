package com.yajb.loadtest;


import static com.yajb.loadtest.TrafficUtils.fetchStaticConfigLoop;
import static com.yajb.loadtest.TrafficUtils.httpTarget;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;

import io.gatling.javaapi.core.Simulation;


@SuppressWarnings({"squid:S1171", "unused"})
public class FetchStaticConfigSimulation extends Simulation {

  {
    var props = SimulationProps.get();
    System.out.println(props);

    var warmup = fetchStaticConfigLoop("warmup", 5)
        .injectOpen(rampUsers(5).during(20));

    var load = fetchStaticConfigLoop("load", props.maxLoops())
        .injectOpen(rampUsers(props.maxUsers()).during(props.maxUsers()));

    var traffic = warmup.andThen(load);

    setUp(traffic).protocols(httpTarget(props.apiBaseUrl()));
  }


}

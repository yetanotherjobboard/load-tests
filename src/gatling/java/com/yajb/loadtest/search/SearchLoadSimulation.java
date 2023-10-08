package com.yajb.loadtest.search;


import static com.yajb.loadtest.search.TrafficUtils.searchLoop;
import static com.yajb.loadtest.search.TrafficUtils.httpTarget;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;

import io.gatling.javaapi.core.Simulation;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@SuppressWarnings({"squid:S1171", "unused"})
public class SearchLoadSimulation extends Simulation {

  {
    var props = Props.get();
    System.out.println(props);

    var warmUp = searchLoop("warm up", 5)
        .injectOpen(rampUsers(5).during(20));

    var load = searchLoop("load", 10)
        .injectOpen(rampUsers(10).during(10));

    var soak = searchLoop("soak", props.maxLoops)
        .injectOpen(rampUsers(props.maxUsers).during(props.maxUsers));

    var traffic = warmUp.andThen(load).andThen(soak);

    setUp(traffic).protocols(httpTarget(props.apiBaseUrl));
  }


  record Props(
      String apiBaseUrl,
      int maxUsers,
      int maxLoops) {

    static Props get() {
      return new Props(
          "https://api-gateway-tvexvi6w2q-uc.a.run.app",
          Optional.ofNullable(System.getenv("MAX_USERS")).map(Integer::parseInt).orElse(25),
          Optional.ofNullable(System.getenv("MAX_LOOPS")).map(Integer::parseInt).orElse(30)
      );
    }
  }
}

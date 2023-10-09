package com.yajb.loadtest;

import java.util.Optional;

record SimulationProps(
    String apiBaseUrl,
    int maxUsers,
    int maxLoops) {

  static SimulationProps get() {
    return new SimulationProps(
        "https://api-gateway-tvexvi6w2q-uc.a.run.app",
        Optional.ofNullable(System.getenv("MAX_USERS")).map(Integer::parseInt).orElse(10),
        Optional.ofNullable(System.getenv("MAX_LOOPS")).map(Integer::parseInt).orElse(50)
    );
  }
}

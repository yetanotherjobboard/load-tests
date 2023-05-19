# Performance testing results

Search functionality is expected to have the most load:
- number and activities of job seekers > number and activities of job posters
- search is computationally most expensive

Tests for other functions might be added in future

## 2023.04.14

#### system under test:
- platform: fly.io
- search service spec:
    - 512MB RAM
    - shared-cpu-1x
    - single instance
    - JVM options: `-XX:MaxRAM=384m -XX:+UseSerialGC -Xmx128m -Xss256k`
- postgres spec:
    - 1GB RAM
    - shared-cpu-1x
    - single instance

#### test setup:
- 50 000 search requests in total
- search loop : 5 sequential requests for subsequent pages fo results
``` java
var load = searchLoop("load", 100) // loop 100 times
        .injectOpen(rampUsers(100).during(50));
```

#### test results:

- details: [gatling report](gatling/2023.04.15-searchload-fly)
- summary:
    - execution time : 15 minutes
    - mean response time: 1.5 sec
    - average req/sec: 55
- conclusions:
    - all good!
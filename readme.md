# Yajb performance testing results

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

### conclusions:
- all good!

## 2023.05.18

Migration from fly.io to gcp/cloudrun in progress, so testing both.

#### system under test:
- platform: fly.io
    - specs same as before
- platform: gcp/cloudrun
    - search service spec: same as in fly.io
    - postgres spec:
        - less RAM than on fly.io (~0.6GB)

#### test setup:
- previous setup was too challenging for CloudRun (would take few hours to finish)
- introduced pause between each search request (1-2 seconds, 'user thinking time')
- dropped down to 17 000 search requests in total, divided in 3 phases with varying number of concurrent users:
    - warmup: 500 requests, 10 users
    - load: 1500 requests, 30 users
    - soak: 15 000 requests, 100 users

``` java
var warmUp = searchLoop("warm up", 10)
        .injectOpen(rampUsers(10).during(10));

    var load = searchLoop("load", 10)
        .injectOpen(rampUsers(30).during(15));

    var soak = searchLoop("soak", 30)
        .injectOpen(rampUsers(100).during(100));

    var traffic = warmUp.andThen(load).andThen(soak);
```

#### test results for warmup + load (no soak):

- fly details: [fly gatling report](gatling/2023.05.18-searchload-fly)
- gcp details: [gcp gatling report](gatling/2023.05.18-searchload-gcp)
- summary:
  - results are very similar for both platforms, and really good
  - execution time : 3.5 minutes
  - mean response time: 0.3 sec
  - average req/sec: 7 in warmup, 15 in load phase

#### test results for warmup + load + soak:

- fly:
  - details: [gatling report](gatling/2023.05.18-searchload-soak-fly)
  - summary:
    - execution time : 10 minutes
    - mean response time: 0.3 sec
    - average req/sec in soak phase: 55
- gcp/cloudrun
  - details: [gatling report](gatling/2023.05.18-searchload-soak-gcp)
  - summary:
    - system overloaded, huge latencies
    - execution time : 40 minutes
    - mean response time in soak phase: 13.5 sec
    - average req/sec in soak phase: ~5

### conclusions
- cloudrun might be fine for go-live & moderate traffic (up to 30 concurrent users)
- we have to figure out the killer difference between fly/gcp during soak phase
- i really like fly.io

### next steps

- (dis)prove hypotesis: cloudrun is choking because of difference in postgres spec
    - increase postgres RAM and rerun soak test
- (dis)prove hypotesis: cloudrun is choking because of GC pauses (JVM settings are too low to support 100 concurrent users)
    - setup prometheus&grafana (can be local) to scrape `/search-service/actuator/prometheus` and rerun soak test
    - confirm GC pauses
    - change JVM:MaxRAM setting and container RAM limit (e.g. double them) and rerun soak test
    - explain why it is working fine on fly.io (are they overprovisioning for burst usage?)
- retest with cloudrun's autoscaling
    - tune max-instances
    - tune concurrency factor
         - this is tricky. I already tried playing with it a bit, but with puny, non-conclusive results)

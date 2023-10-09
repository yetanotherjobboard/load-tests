
# generate adverts

### one-time setup

- login to stripe, create or enable 'load test' subscription with 10000 slots or more
- login to app as `trafficgenerator` user, buy `load test` subscription
- archive the subscription price in stripe, so it doesn't show up on pricing

### configure and run advert generator

- set config_github_reviews_enabled: false on ee-service in staging
- edit [application-staging.yml](src%2Fmain%2Fresources%2Fapplication-staging.yml) 
    - set `CHANGEME` secrets
    - set `max-adverts-to-generate`
- run `AdvertGeneratorMain` 

# run load tests

### spin up monitoring (optional)
- `docker compose -f yajb-monitoring/compose.yml up`
- http://localhost:3000 (creds: admin/grafana)

### run locally (requires jdk)
- `./gradlew generateSources gatlingRun-com.yajb.loadtest.SearchLoadSimulation`
- `./gradlew generateSources gatlingRun-com.yajb.loadtest.FetchStaticConfigSimulation`

### run in docker
- `docker build . -t yajb-load-tests`
- `docker run --rm -m 512m -e MAX_USERS=10 -e MAX_LOOPS=10 yajb-load-tests`



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

### run locally (requires jdk)
- fly: `./gradlew generateSources gatlingRun -Dtarget=fly` (no longer exist)
- gcp: `./gradlew generateSources gatlingRun -Dtarget=gcp`

### run in docker
- `docker pull eclipse-temurin:17-jdk`
- `docker run --rm -i -t -v $(pwd):/workspace --workdir /workspace eclipse-temurin:17-jdk bash`
- paste command(s) from 'run locally' on container's prompt


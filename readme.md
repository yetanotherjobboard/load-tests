
# generate adverts

### one-time setup

- login to stripe, create or enable 'load test' subscription with 10000 slots or more
- login to app as `trafficgenerator` user, buy `load test` subscription
- archive the subscription price in stripe, so it doesn't show up on pricing

### configure and run advert generator

- edit [application-staging.yml](src%2Fmain%2Fresources%2Fapplication-staging.yml) 
    - set `CHANGEME` secrets
    - set `max-adverts-to-generate`
- run `AdvertGeneratorMain` 

# run load tests

- verify API_BASE_URL in [SearchLoadSimulation.java](src%2Fgatling%2Fjava%2Fcom%2Fyajb%2Floadtest%2Fsearch%2FSearchLoadSimulation.java)

### run locally (requires jdk)
- `gradlew generateSources gatlingRun`

### run in docker
- `docker pull eclipse-temurin:17-jdk`
- `docker run --rm -v $(pwd):/workspace --workdir /workspace eclipse-temurin:17-jdk ./gradlew generateSources gatlingRun`


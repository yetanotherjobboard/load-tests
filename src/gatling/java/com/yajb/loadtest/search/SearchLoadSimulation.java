package com.yajb.loadtest.search;


import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Randomizer.pickOne;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Base64.getEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yajb.loadtest.advertgenerator.AdvertBuilder.GenerationData;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import yajb.com.client.search.model.ContractType;
import yajb.com.client.search.model.SearchQuery;
import yajb.com.client.search.model.WorkLocation;


@SuppressWarnings({"squid:S1171", "unused"})
public class SearchLoadSimulation extends Simulation {

  static final String API_BASE_URL = "https://api-gateway-tvexvi6w2q-uc.a.run.app";

  static final ObjectMapper jackson = new ObjectMapper();
  static final String TENANT_TOKEN = getEncoder().encodeToString("reeljobs:reeljobs".getBytes(UTF_8));

  static final HttpProtocolBuilder TARGET = http
      .baseUrl(API_BASE_URL)
      .header("yajb-tenant-token", TENANT_TOKEN)
      .contentTypeHeader("application/json");

  //verify if it matches  what's in SearchApi.java (local variable)
  static final String SEARCH_PATH = "/api/search/job-advert/query";

  static final Iterator<Map<String, Object>> RANDOM_QUERY_GENERATOR = Stream
      .generate(() -> Map.of("query", (Object) randomQuery()))
      .iterator();

  { main(); }

  void main() {

    ChainBuilder browseFewPagesOfSearchResults = CoreDsl
        .feed(RANDOM_QUERY_GENERATOR)
        .exec(callSearch(1))
        .exec(callSearch(2))
        .exec(callSearch(3))
        .exec(callSearch(4))
        .exec(callSearch(5))
        ;

    ScenarioBuilder scenario = CoreDsl
        .scenario("search loop 100")
        .repeat(100)
        .on(browseFewPagesOfSearchResults.pause(1));

    PopulationBuilder traffic = scenario
        .injectOpen(rampUsers(100).during(50));

    setUp(traffic).protocols(TARGET);
  }

  HttpRequestActionBuilder callSearch(int pageNumber) {
    return http("search")
        .post(SEARCH_PATH)
        .body(StringBody("#{query}"))
        .queryParam("pageSize", 12)
        .queryParam("pageNumber", pageNumber);
  }

  @SneakyThrows
  static String randomQuery() {
    var q = SearchType.pickRandom().applyTo(new SearchQuery());
    return jackson.writeValueAsString(q);
  }

  @RequiredArgsConstructor
  enum SearchType {

    BY_PHRASE         (q -> q.phrase(pickOne(GenerationData.jobs))),
    BY_CONTRACT_TYPE  (q -> q.contractType(pickOne(ContractType.values()))),
    BY_WORK_LOCATION  (q -> q.workLocation(pickOne(WorkLocation.values()))),

    ;

    final Function<SearchQuery, SearchQuery> f;
    SearchQuery applyTo(SearchQuery q) {
      return f.apply(q);
    }

    static SearchType pickRandom() {
      return pickOne(values());
    }

  }
}

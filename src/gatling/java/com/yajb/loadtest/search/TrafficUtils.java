package com.yajb.loadtest.search;

import static com.yajb.loadtest.advertgenerator.AdvertBuilder.Randomizer.pickOne;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.http.HttpDsl.http;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Base64.getEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yajb.loadtest.advertgenerator.AdvertBuilder.GenerationData;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
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

public interface TrafficUtils {


  ObjectMapper jackson = new ObjectMapper();
  String TENANT_TOKEN = getEncoder().encodeToString("reeljobs:reeljobs".getBytes(UTF_8));

  static HttpProtocolBuilder target(String apiBaseUrl) {
    return http
      .baseUrl(apiBaseUrl)
      .header("yajb-tenant-token", TENANT_TOKEN)
      .contentTypeHeader("application/json");
  }

  //verify if it matches  what's in SearchApi.java (local variable)
  String SEARCH_PATH = "/api/search/job-advert/query";

  Iterator<Map<String, Object>> RANDOM_QUERY_GENERATOR = Stream
      .generate(() -> Map.of("query", (Object) randomQuery()))
      .iterator();

  ChainBuilder BROWSE_FEW_PAGES_OF_SEARCH_RESULTS = CoreDsl
      .feed(RANDOM_QUERY_GENERATOR)
      .exec(callSearch(1))
      .exec(callSearch(2))
      .exec(callSearch(3))
      .exec(callSearch(4))
      .exec(callSearch(5))
      ;

  static ScenarioBuilder searchLoop(String desc, int repeatTimes) {
    return CoreDsl
        .scenario(desc)
        .repeat(repeatTimes)
        .on(BROWSE_FEW_PAGES_OF_SEARCH_RESULTS.pause(1));
  }
  static HttpRequestActionBuilder callSearch(int pageNumber) {
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

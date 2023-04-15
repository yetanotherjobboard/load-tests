package com.yajb.loadtest.advertgenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yajb.com.client.config.ConfigApi;
import yajb.com.client.config.invoker.ApiClient;

@Configuration
class ConfigApiConfig {

  @Bean
  ApiClient configApiClient(AdvertGeneratorConfigProps cfg) {
    return new ApiClient()
        .setScheme(cfg.target.scheme)
        .setHost(cfg.target.host);
  }

  @Bean
  ConfigApi configApi(ApiClient apiClient) {
    return new ConfigApi(apiClient);
  }

}

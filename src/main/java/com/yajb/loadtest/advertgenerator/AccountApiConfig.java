package com.yajb.loadtest.advertgenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yajb.com.client.account.AccountApi;
import yajb.com.client.account.invoker.ApiClient;

@Configuration
class AccountApiConfig {

  @Bean
  ApiClient accountApiCLient(AdvertGeneratorConfigProps cfg, UserTokenHolder credentials) {
    return new ApiClient()
        .setScheme(cfg.target.scheme)
        .setHost(cfg.target.host)
        .setPort(cfg.target.port)
        .setRequestInterceptor(b -> b.header("Authorization", "Bearer " + credentials.idToken))
        ;
  }

  @Bean
  AccountApi accountApi(ApiClient apiClient) {
    return new AccountApi(apiClient);
  }
}

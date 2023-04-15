package com.yajb.loadtest.advertgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvertGeneratorMain {

  public static void main(String... args) {
    SpringApplication
        .run(AdvertGeneratorMain.class, "--spring.profiles.active=staging")
//        .run(AdvertGeneratorMain.class, "--spring.profiles.active=staging")
        .getBean(AdvertGenerator.class)
        .run();
  }

  @Bean
  ResourceOwnerAuthenticationFlow roaf(AdvertGeneratorConfigProps cfg, ObjectMapper jackson) {
    return new ResourceOwnerAuthenticationFlow(
        cfg.oidc.tokenEndpoint,
        cfg.oidc.clientId,
        cfg.oidc.clientSecret,
        jackson
    );
  }


}

package com.yajb.loadtest.advertgenerator;

import static java.nio.charset.StandardCharsets.UTF_8;

import jakarta.annotation.PostConstruct;
import java.util.Base64;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("advertgenerator")
class AdvertGeneratorConfigProps {

  @Setter Target target;
  @Setter Tenant tenant;
  @Setter OidcConfig oidc;
  @Setter UserCredentials user;

  @PostConstruct
  void postConstruct() {
    tenant.encodeToken();
  }

  @ToString
  @Setter
  static class Target {
    String scheme;
    String host;
  }

  @ToString
  static class Tenant {
    @Setter String id;
    @Setter String secret;

    String token;

    void encodeToken() {
      token = Base64.getEncoder().encodeToString((id + ":" + secret).getBytes(UTF_8));
    }
  }

  @ToString
  @Setter
  static class OidcConfig {
    String tokenEndpoint;
    String clientId;
    String clientSecret;
  }

  @ToString
  @Setter
  static class UserCredentials {
    String username;
    String password;
  }

}

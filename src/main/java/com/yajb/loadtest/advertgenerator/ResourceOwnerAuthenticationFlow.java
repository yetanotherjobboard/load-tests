package com.yajb.loadtest.advertgenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Slf4j
public class ResourceOwnerAuthenticationFlow {

    final String tokenEndpoint;
    final String clientId;
    final String clientSecret;
    final ObjectMapper jackson;

    @SneakyThrows
    String idTokenFor(String username, String password) {
        var resp = new RestTemplate().postForEntity(
                    tokenEndpoint,
                    tokenReq(username, password),
                    String.class);
        log.debug("AuthServer said: " + resp);
        return jackson.readValue(resp.getBody(), TokenResponse.class).id_token;
    }

  @JsonIgnoreProperties(ignoreUnknown = true)
  record TokenResponse(String access_token, String id_token){}

    HttpEntity<MultiValueMap<String, String>> tokenReq(String username, String password) {
        var req = new LinkedMultiValueMap<String, String>();
        req.put("client_id", List.of(clientId));
        req.put("client_secret", List.of(clientSecret));
        req.put("grant_type", List.of("password"));
        req.put("username", List.of(username));
        req.put("password", List.of(password));
        req.put("scope", List.of("openid email"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(req, headers);
    }
}

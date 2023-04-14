package com.yajb.loadtest.advertgenerator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTokenHolder {

  final ResourceOwnerAuthenticationFlow roaf;
  String idToken;

  void login(String username, String password) {
    idToken = roaf.idTokenFor(username, password);
  }

}

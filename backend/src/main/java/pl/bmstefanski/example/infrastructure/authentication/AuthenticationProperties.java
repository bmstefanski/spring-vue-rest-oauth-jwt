package pl.bmstefanski.example.infrastructure.authentication;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth")
class AuthenticationProperties {

  private final OAuth oauth = new OAuth();
  private final Token token = new Token();

  static class OAuth {

    private List<String> redirectUrls = new ArrayList<>();

    List<String> getRedirectUrls() {
      return this.redirectUrls;
    }

  }

  static class Token {

    private String secret;
    private long expiration;

    String getSecret() {
      return this.secret;
    }

    long getExpiration() {
      return this.expiration;
    }

  }

  OAuth getOauth() {
    return this.oauth;
  }

  Token getToken() {
    return this.token;
  }

}

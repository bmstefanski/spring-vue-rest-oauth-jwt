package pl.bmstefanski.example.authentication;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth")
@PropertySource("classpath:auth.properties")
class AuthenticationProperties {

  private final OAuth oauth = new OAuth();
  private final Token token = new Token();

  public static class OAuth {

    private List<String> redirectUrls = new ArrayList<>();

    public List<String> getRedirectUrls() {
      return this.redirectUrls;
    }

    public void setRedirectUrls(List<String> redirectUrls) {
      this.redirectUrls = redirectUrls;
    }

  }

  public static class Token {

    private String secret;
    private long expiration;

    public String getSecret() {
      return this.secret;
    }

    public long getExpiration() {
      return this.expiration;
    }

    public void setSecret(String secret) {
      this.secret = secret;
    }

    public void setExpiration(long expiration) {
      this.expiration = expiration;
    }

  }

  public OAuth getOauth() {
    return this.oauth;
  }

  public Token getToken() {
    return this.token;
  }

}

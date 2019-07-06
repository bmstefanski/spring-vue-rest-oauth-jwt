package pl.bmstefanski.example.authentication.userdetails;

import java.util.Map;

public final class OAuth2UserDetailsFactory {

  private OAuth2UserDetailsFactory() {}

  public static OAuth2UserDetails obtainOAuth2UserDetails(String providerName, Map<String, Object> attributes) {
    if ("github".equalsIgnoreCase(providerName)) {
      return new GithubOAuth2UserDetails(attributes);
    } else {
      throw new UnsupportedOperationException();
    }
  }

}

package pl.bmstefanski.example.authentication.userdetails;

import java.util.Map;

class GithubOAuth2UserDetails implements OAuth2UserDetails {

  private final Map<String, Object> attributes;

  GithubOAuth2UserDetails(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String getProviderId() {
    return ((Integer) this.attributes.get("id")).toString();
  }

  @Override
  public String getUsername() {
    return (String) this.attributes.get("login");
  }

  @Override
  public String getName() {
    return (String) this.attributes.get("name");
  }

  @Override
  public String getEmail() {
    return (String) this.attributes.get("email");
  }

  @Override
  public String getAvatar() {
    return (String) this.attributes.get("avatar_url");
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.attributes;
  }

}

package pl.bmstefanski.example.authentication.userdetails;

import java.util.Map;

public interface OAuth2UserDetails {

  String getProviderId();

  String getUsername();

  String getName();

  String getEmail();

  String getAvatar();

  Map<String, Object> getAttributes();

}

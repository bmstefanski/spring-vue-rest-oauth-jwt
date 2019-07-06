package pl.bmstefanski.example.user;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

class UserDetails implements org.springframework.security.core.userdetails.UserDetails, OAuth2User {

  private final ObjectId id;
  private final String username;
  private final String password;
  private Map<String, Object> attributes;
  private final Collection<? extends GrantedAuthority> authorities;

  private UserDetails(ObjectId id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  static UserDetails of(User user) {
    return new UserDetails(user.getId(), user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
  }

  static UserDetails of(User user, Map<String, Object> attributes) {
    UserDetails userDetails = UserDetails.of(user);
    userDetails.setAttributes(attributes);

    return userDetails;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.attributes;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getName() {
    return this.id.toHexString();
  }

  private void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

}

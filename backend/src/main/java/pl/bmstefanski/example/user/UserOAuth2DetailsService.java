package pl.bmstefanski.example.user;

import java.util.Optional;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.bmstefanski.example.authentication.userdetails.OAuth2UserDetails;
import pl.bmstefanski.example.authentication.userdetails.OAuth2UserDetailsFactory;
import pl.bmstefanski.example.authentication.userdetails.OAuth2UserDetailsService;

class UserOAuth2DetailsService extends OAuth2UserDetailsService {

  private final UserRepository userRepository;

  UserOAuth2DetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User user = super.loadUser(userRequest);

    try {
      return this.loadUser(userRequest, user);
    } catch (AuthenticationException exception) {
      throw exception;
    } catch (Exception exception) {
      throw new InternalAuthenticationServiceException(exception.getMessage(), exception.getCause());
    }
  }

  private OAuth2User loadUser(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
    String providerName = userRequest.getClientRegistration().getRegistrationId();
    OAuth2UserDetails userDetails = OAuth2UserDetailsFactory.obtainOAuth2UserDetails(providerName, oAuth2User.getAttributes());

    if (userDetails.getEmail() == null || userDetails.getEmail().isEmpty()) {
      throw new RuntimeException("There is no user with that email in provider's system");
    }

    Optional<User> optionalUser = this.userRepository.findByUsername(userDetails.getUsername());
    User user;

    if (optionalUser.isPresent()) {
      user = optionalUser.get();
      if (!providerName.equals(user.getProvider())) {
        throw new RuntimeException("Looks like you're already signed up with " + user.getProvider() + " account.");
      }
      user = this.updateExistingUser(user, userDetails);
    } else {
      user = this.createNewUser(userRequest, userDetails);
    }

    return UserDetails.of(user, oAuth2User.getAttributes());
  }

  private User createNewUser(OAuth2UserRequest userRequest, OAuth2UserDetails userDetails) {
    User user = UserBuilder.create()
        .withProvider(userRequest.getClientRegistration().getRegistrationId())
        .withProviderId(userDetails.getProviderId())
        .withUsername(userDetails.getUsername())
        .withName(userDetails.getName())
        .withEmail(userDetails.getEmail())
        .withAvatar(userDetails.getAvatar())
        .build();

    return this.userRepository.save(user);
  }

  private User updateExistingUser(User user, OAuth2UserDetails userDetails) {
    user.setUsername(userDetails.getUsername());
    user.setName(userDetails.getName());
    user.setAvatar(userDetails.getAvatar());

    return this.userRepository.save(user);
  }


}

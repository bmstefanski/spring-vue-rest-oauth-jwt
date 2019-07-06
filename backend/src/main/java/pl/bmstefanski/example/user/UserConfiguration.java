package pl.bmstefanski.example.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.bmstefanski.example.authentication.AuthenticationUserDetailsService;
import pl.bmstefanski.example.authentication.userdetails.OAuth2UserDetailsService;

@Configuration
class UserConfiguration {

  @Bean
  AuthenticationUserDetailsService authenticationUserDetailsService(UserRepository userRepository) {
    return new UserDetailsService(userRepository);
  }

  @Bean
  OAuth2UserDetailsService defaultOAuth2UserService(UserRepository userRepository) {
    return new UserOAuth2DetailsService(userRepository);
  }

  @Bean
  UserFacade userFacade(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    return new UserFacade(passwordEncoder, userRepository);
  }

}

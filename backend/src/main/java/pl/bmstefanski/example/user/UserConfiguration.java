package pl.bmstefanski.example.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bmstefanski.example.infrastructure.authentication.AuthenticationUserDetailsService;

@Configuration
class UserConfiguration {

  @Bean
  AuthenticationUserDetailsService userDetailsOAuthService(UserRepository userRepository) {
    return new UserDetailsService(userRepository);
  }

}

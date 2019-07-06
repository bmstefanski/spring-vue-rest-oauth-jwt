package pl.bmstefanski.example.infrastructure.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bmstefanski.example.infrastructure.converter.ObjectToMapConverter;

@Configuration
class AuthenticationConfiguration {

  @Bean
  AuthenticationTokenFilter authenticationTokenFilter(AuthenticationProperties tokenProperties, ObjectToMapConverter objectToMapConverter,
      AuthenticationUserDetailsService userDetailsService) {
    AuthenticationTokenValidator tokenValidator = new AuthenticationTokenValidator();
    AuthenticationTokenProvider tokenProvider = new AuthenticationTokenProvider();
    AuthenticationTokenCreator tokenCreator = new AuthenticationTokenCreator(objectToMapConverter);

    return new AuthenticationTokenFilter(tokenProperties, tokenValidator, tokenProvider, tokenCreator, userDetailsService);
  }

}

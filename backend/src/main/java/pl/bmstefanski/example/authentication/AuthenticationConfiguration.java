package pl.bmstefanski.example.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.bmstefanski.example.infrastructure.converter.ObjectToMapConverter;

@Configuration
class AuthenticationConfiguration {

  @Bean
  AuthenticationTokenCreator authenticationTokenCreator(AuthenticationProperties authenticationProperties) {
    return new AuthenticationTokenCreator(authenticationProperties, new ObjectToMapConverter());
  }

  @Bean
  OAuth2AuthorizationRequestRepository authorizationRequestRepository() {
    return new OAuth2AuthorizationRequestRepository();
  }

  @Bean
  AuthenticationTokenFilter authenticationTokenFilter(AuthenticationProperties tokenProperties, AuthenticationUserDetailsService userDetailsService) {
    AuthenticationTokenValidator tokenValidator = new AuthenticationTokenValidator();
    AuthenticationTokenProvider tokenProvider = new AuthenticationTokenProvider();

    return new AuthenticationTokenFilter(tokenProperties, tokenValidator, tokenProvider, userDetailsService);
  }

  @Bean
  OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler(AuthenticationProperties authenticationProperties) {
    return new OAuth2AuthenticationSuccessHandler(this.authenticationTokenCreator(authenticationProperties), authenticationProperties,
        this.authorizationRequestRepository());
  }

  @Bean
  OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
    return new OAuth2AuthenticationFailureHandler(this.authorizationRequestRepository());
  }

}

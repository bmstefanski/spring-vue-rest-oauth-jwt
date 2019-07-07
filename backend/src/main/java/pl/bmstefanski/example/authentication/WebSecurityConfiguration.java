package pl.bmstefanski.example.authentication;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.bmstefanski.example.authentication.userdetails.OAuth2UserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final AuthenticationTokenFilter authenticationTokenFilter;
  private final OAuth2AuthorizationRequestRepository authorizationRequestRepository;
  private final OAuth2AuthenticationFailureHandler authenticationFailureHandler;
  private final OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;
  private final OAuth2UserDetailsService oAuth2UserService;

  WebSecurityConfiguration(@Qualifier("authenticationUserDetailsService") UserDetailsService userDetailsService, AuthenticationTokenFilter authenticationTokenFilter,
      OAuth2AuthorizationRequestRepository authorizationRequestRepository, OAuth2AuthenticationFailureHandler authenticationFailureHandler,
      OAuth2AuthenticationSuccessHandler authenticationSuccessHandler, OAuth2UserDetailsService oAuth2UserService) {
    this.userDetailsService = userDetailsService;
    this.authenticationTokenFilter = authenticationTokenFilter;
    this.authorizationRequestRepository = authorizationRequestRepository;
    this.authenticationFailureHandler = authenticationFailureHandler;
    this.authenticationSuccessHandler = authenticationSuccessHandler;
    this.oAuth2UserService = oAuth2UserService;
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder
        .userDetailsService(this.userDetailsService)
        .passwordEncoder(this.passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().and()
        .csrf().disable()
        .httpBasic().disable()
        .formLogin().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint())
        .and().authorizeRequests().antMatchers("/auth/**", "/oauth2/**").permitAll()
        .and().oauth2Login().authorizationEndpoint().baseUri("/oauth2/authorize").authorizationRequestRepository(this.authorizationRequestRepository)
        .and().redirectionEndpoint().baseUri("/oauth2/callback/*")
        .and().userInfoEndpoint().userService(this.oAuth2UserService)
        .and().successHandler(this.authenticationSuccessHandler).failureHandler(this.authenticationFailureHandler);

    http.addFilterBefore(this.authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}

package pl.bmstefanski.example.infrastructure.authentication;

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

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final AuthenticationTokenFilter authenticationTokenFilter;
  private final OAuth2AuthorizationRequestRepository authorizationRequestRepository;
  private final OAuth2AuthenticationFailureHandler authenticationFailureHandler;
  private final OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;

  WebSecurityConfig(@Qualifier("userDetailsOAuthService") UserDetailsService userDetailsService, AuthenticationTokenFilter authenticationTokenFilter,
      OAuth2AuthorizationRequestRepository authorizationRequestRepository, OAuth2AuthenticationFailureHandler authenticationFailureHandler,
      OAuth2AuthenticationSuccessHandler authenticationSuccessHandler) {
    this.userDetailsService = userDetailsService;
    this.authenticationTokenFilter = authenticationTokenFilter;
    this.authorizationRequestRepository = authorizationRequestRepository;
    this.authenticationFailureHandler = authenticationFailureHandler;
    this.authenticationSuccessHandler = authenticationSuccessHandler;
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
        .and().authorizeRequests()

        .antMatchers("/oauth2/**", "/auth/**").permitAll()
        .anyRequest().authenticated()

        .and().oauth2Login().authorizationEndpoint().baseUri("/oauth2/authorize").authorizationRequestRepository(this.authorizationRequestRepository)
        .and().redirectionEndpoint().baseUri("/oauth2/callback/*")
//        .and().userInfoEndpoint().userService(this.userDetailsService)
        .and().successHandler(this.authenticationSuccessHandler).failureHandler(this.authenticationFailureHandler);

    http.addFilterBefore(this.authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  AuthenticationTokenFilter authenticationFilter() {
    return new AuthenticationTokenFilter();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManagerBean();
  }

}

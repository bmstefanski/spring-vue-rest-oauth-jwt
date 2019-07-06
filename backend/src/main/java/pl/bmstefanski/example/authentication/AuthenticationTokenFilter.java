package pl.bmstefanski.example.authentication;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

class AuthenticationTokenFilter extends OncePerRequestFilter {

  private final AuthenticationProperties tokenProperties;
  private final AuthenticationTokenValidator tokenValidator;
  private final AuthenticationTokenProvider tokenProvider;
  private final AuthenticationUserDetailsService userDetailsService;

  AuthenticationTokenFilter(AuthenticationProperties tokenProperties, AuthenticationTokenValidator tokenValidator,
      AuthenticationTokenProvider tokenProvider, AuthenticationUserDetailsService userDetailsService) {
    this.tokenProperties = tokenProperties;
    this.tokenValidator = tokenValidator;
    this.tokenProvider = tokenProvider;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    String jwtToken = this.obtainJwtTokenFromRequest(request);
    String secretToken = this.tokenProperties.getToken().getSecret();

    if (jwtToken != null && this.tokenValidator.validate(jwtToken, secretToken)) {
      ObjectId userId = this.tokenProvider.obtainUserId(jwtToken, secretToken);
      UserDetails userDetails = this.userDetailsService.loadUserById(userId);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      authentication.setDetails(userDetails);

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String obtainJwtTokenFromRequest(HttpServletRequest servletRequest) {
    String authorizationHeaderContent = servletRequest.getHeader("Authorization");
    String bearer = "Bearer ";

    if (authorizationHeaderContent != null && authorizationHeaderContent.startsWith(bearer)) {
      return authorizationHeaderContent.substring(bearer.length());
    }

    return null;
  }

}

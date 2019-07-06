package pl.bmstefanski.example.authentication;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import pl.bmstefanski.example.infrastructure.helper.CookieHelper;

class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private AuthenticationTokenCreator tokenCreator;
  private AuthenticationProperties tokenProperties;
  private OAuth2AuthorizationRequestRepository requestRepository;

  OAuth2AuthenticationSuccessHandler(AuthenticationTokenCreator tokenCreator, AuthenticationProperties tokenProperties,
      OAuth2AuthorizationRequestRepository requestRepository) {
    this.tokenCreator = tokenCreator;
    this.tokenProperties = tokenProperties;
    this.requestRepository = requestRepository;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Authentication authentication)
      throws IOException {
    if (!servletResponse.isCommitted()) {
      String redirectUrl = this.obtainRedirectUri(authentication, servletRequest);

      this.clearAuthenticationAttributes(servletRequest, servletResponse);
      this.getRedirectStrategy().sendRedirect(servletRequest, servletResponse, redirectUrl);
    }
  }

  private String obtainRedirectUri(Authentication authentication, HttpServletRequest servletRequest) {
    Optional<String> optionalRedirectUrl = CookieHelper.obtainCookie(OAuth2AuthorizationRequestRepository.REDIRECT_URI_COOKIE_NAME, servletRequest.getCookies())
        .map(Cookie::getValue);

    if (optionalRedirectUrl.isPresent() && !this.isAuthorizedRedirectUri(optionalRedirectUrl.get())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
    }

    String redirectUrl = optionalRedirectUrl.orElse(this.getDefaultTargetUrl());
    String token = this.tokenCreator.create(authentication);

    return UriComponentsBuilder.fromUriString(redirectUrl).queryParam("token", token).build().toUriString();
  }

  private boolean isAuthorizedRedirectUri(String uri) {
    URI clientRedirectUri = URI.create(uri);

    return this.tokenProperties.getOauth().getRedirectUrls().stream()
        .anyMatch(redirectUri -> {
          URI authorizedUri = URI.create(redirectUri);
          return clientRedirectUri.getHost().equalsIgnoreCase(authorizedUri.getHost()) && authorizedUri.getPort() == clientRedirectUri.getPort();
        });
  }

  private void clearAuthenticationAttributes(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
    super.clearAuthenticationAttributes(servletRequest);
    this.requestRepository.removeAuthorizationRequestCookies(servletResponse, servletRequest);
  }

}

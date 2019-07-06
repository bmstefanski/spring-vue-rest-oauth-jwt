package pl.bmstefanski.example.authentication;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
  }

}

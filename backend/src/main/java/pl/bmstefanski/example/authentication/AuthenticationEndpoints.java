package pl.bmstefanski.example.authentication;

import java.util.Collections;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bmstefanski.example.authentication.dto.SignInDto;
import pl.bmstefanski.example.authentication.dto.SignUpDto;
import pl.bmstefanski.example.infrastructure.constant.ApiLayers;
import pl.bmstefanski.example.user.UserFacade;

@RequestMapping(ApiLayers.API_ROOT)
@RestController
class AuthenticationEndpoints {

  private final AuthenticationManager authenticationManager;
  private final AuthenticationTokenCreator authenticationTokenCreator;
  private final UserFacade userFacade;

  AuthenticationEndpoints(AuthenticationManager authenticationManager, AuthenticationTokenCreator authenticationTokenCreator, UserFacade userFacade) {
    this.authenticationManager = authenticationManager;
    this.authenticationTokenCreator = authenticationTokenCreator;
    this.userFacade = userFacade;
  }

  @PostMapping("/signin")
  ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody SignInDto dto) {
    Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return ResponseEntity.ok(Collections.singletonMap("accessToken", this.authenticationTokenCreator.create(authentication)));
  }

  @PostMapping("/signup")
  ResponseEntity<?> registerUser(@RequestBody SignUpDto dto) {
    return ResponseEntity.created(this.userFacade.register(dto)).build();
  }

}

package pl.bmstefanski.example.user;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.bmstefanski.example.authentication.dto.SignUpDto;
import pl.bmstefanski.example.infrastructure.constant.ApiLayers;

public class UserFacade {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  UserFacade(PasswordEncoder passwordEncoder, UserRepository userRepository) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public URI register(SignUpDto dto) {
    if (this.userRepository.existsByUsername(dto.getUsername()) || this.userRepository.existsByEmail(dto.getEmail())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    User user = this.userRepository.save(UserBuilder.create()
        .withUsername(dto.getUsername())
        .withName(dto.getName())
        .withEmail(dto.getEmail())
        .withPassword(this.passwordEncoder.encode(dto.getPassword()))
        .withProvider("local")
        .build());

    return ServletUriComponentsBuilder.fromCurrentContextPath().path(ApiLayers.USERS + "/{id}").buildAndExpand(user.getId()).toUri();
  }

}

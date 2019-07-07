package pl.bmstefanski.example.user;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bmstefanski.example.authentication.annotation.AuthenticatedUser;
import pl.bmstefanski.example.authentication.annotation.RequiresAuthenticated;
import pl.bmstefanski.example.infrastructure.constant.ApiLayers;

@RequestMapping(ApiLayers.USERS)
@RestController
class UserEndpoints {

  private final UserRepository userRepository;

  UserEndpoints(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @RequiresAuthenticated
  @GetMapping("me")
  ResponseEntity<User> fetchCurrentUser(@AuthenticatedUser UserDetails userDetails) {
    return ResponseEntity.ok(this.userRepository.fetchUserByUsername(userDetails.getUsername()));
  }

  @RequiresAuthenticated
  @GetMapping("{id}")
  ResponseEntity<User> fetchById(@PathVariable ObjectId id) {
    return ResponseEntity.ok(this.userRepository.fetchUserById(id));
  }

}

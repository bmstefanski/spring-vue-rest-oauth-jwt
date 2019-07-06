package pl.bmstefanski.example.user;

import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class UserDetailsService implements pl.bmstefanski.example.authentication.AuthenticationUserDetailsService {

  private final UserRepository userRepository;

  UserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserById(ObjectId id) {
    User user = this.userRepository.fetchUserById(id);
    return pl.bmstefanski.example.user.UserDetails.of(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username doesn't belong to any user"));
    return pl.bmstefanski.example.user.UserDetails.of(user);
  }

}

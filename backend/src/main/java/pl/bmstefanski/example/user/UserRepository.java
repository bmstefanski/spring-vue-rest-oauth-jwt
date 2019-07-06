package pl.bmstefanski.example.user;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.bmstefanski.example.user.exception.UserNotFoundException;

interface UserRepository extends MongoRepository<User, ObjectId> {

  default User fetchUserById(ObjectId id) {
    return this.findById(id).orElseThrow(UserNotFoundException::new);
  }

  default User fetchUserByUsername(String username) {
    return this.findByUsername(username).orElseThrow(UserNotFoundException::new);
  }

  Optional<User> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

}

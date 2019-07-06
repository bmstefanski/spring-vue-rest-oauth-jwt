package pl.bmstefanski.example.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.bmstefanski.example.infrastructure.constant.MongoCollections;

@Document(MongoCollections.USERS)
class User {

  @Id private ObjectId id;
  private String username;
  private String password;
  private String email;

  User() {}

  User(UserBuilder builder) {
    this.id = builder.id;
    this.username = builder.username;
    this.password = builder.password;
    this.email = builder.email;
  }

  ObjectId getId() {
    return this.id;
  }

  String getUsername() {
    return this.username;
  }

  String getPassword() {
    return this.password;
  }

  String getEmail() {
    return this.email;
  }

}

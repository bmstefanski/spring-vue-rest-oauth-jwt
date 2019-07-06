package pl.bmstefanski.example.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.bmstefanski.example.infrastructure.constant.MongoCollections;

@Document(MongoCollections.USERS)
class User {

  @Id private ObjectId id;
  private String username;
  private String name;
  private String password;
  private String email;
  private String provider;
  private String providerId;
  private String avatar;

  User() {}

  User(UserBuilder builder) {
    this.id = builder.id;
    this.username = builder.username;
    this.name = builder.name;
    this.password = builder.password;
    this.email = builder.email;
    this.provider = builder.provider;
    this.providerId = builder.providerId;
    this.avatar = builder.avatar;
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

  String getProvider() {
    return this.provider;
  }

  void setUsername(String username) {
    this.username = username;
  }

  void setName(String name) {
    this.name = name;
  }

  void setAvatar(String avatar) {
    this.avatar = avatar;
  }

}

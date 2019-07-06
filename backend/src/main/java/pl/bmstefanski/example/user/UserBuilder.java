package pl.bmstefanski.example.user;

import org.bson.types.ObjectId;
import pl.bmstefanski.example.infrastructure.helper.Buildable;

final class UserBuilder implements Buildable<User> {

  ObjectId id;
  String username;
  String password;
  String email;

  private UserBuilder() {}

  static UserBuilder create() {
    return new UserBuilder();
  }

  UserBuilder withId(ObjectId id) {
    this.id = id;
    return this;
  }

  UserBuilder withUsername(String username) {
    this.username = username;
    return this;
  }

  UserBuilder withPassword(String password) {
    this.password = password;
    return this;
  }

  UserBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public User build() {
    return new User(this);
  }

}

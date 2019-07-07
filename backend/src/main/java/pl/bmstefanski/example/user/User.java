package pl.bmstefanski.example.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.bmstefanski.example.infrastructure.constant.MongoCollections;

@Document(MongoCollections.USERS)
class User {

  @Id @JsonSerialize(using = ToStringSerializer.class) private ObjectId id;
  private String username;
  private String name;
  @JsonIgnore private String password;
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

  public ObjectId getId() {
    return this.id;
  }

  public String getUsername() {
    return this.username;
  }

  public String getName() {
    return this.name;
  }

  public String getPassword() {
    return this.password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getProvider() {
    return this.provider;
  }

  public String getProviderId() {
    return this.providerId;
  }

  public String getAvatar() {
    return this.avatar;
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

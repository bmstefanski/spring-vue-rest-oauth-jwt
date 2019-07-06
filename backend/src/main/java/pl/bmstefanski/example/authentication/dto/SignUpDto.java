package pl.bmstefanski.example.authentication.dto;

public class SignUpDto {

  private String username;
  private String password;
  private String email;
  private String name;

  public String getUsername() {
    return this.username;
  }

  public String getPassword() {
    return this.password;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

}

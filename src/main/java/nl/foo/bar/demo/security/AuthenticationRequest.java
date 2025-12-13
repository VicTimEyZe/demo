package nl.foo.bar.demo.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {

  @NotBlank(message = "Username must not be blank")
  private String username;

  @NotBlank(message = "Password must not be blank")
  private String password;

}

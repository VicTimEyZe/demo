package nl.foo.bar.demo.api.actor;

import java.time.LocalDate;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActorTO {

  @Schema(description = "Unique identifier of the actor",
          example = "123e4567-e89b-12d3-a456-426614174000")
  @org.hibernate.validator.constraints.UUID
  private UUID id;

  @NotBlank(message = "Name must not be blank")
  @Schema(description = "Full name of the actor",
          example = "John Doe")
  private String name;

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Email should be valid")
  @Schema(description = "Email address of the actor",
          example = "example@email.com")
  private String email;

  @Schema(description = "Birth date of the actor",
          example = "1980-01-01")
  private LocalDate dateOfBirth;

}

package nl.foo.bar.demo.actor.service;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Actor")
@Data
public class ActorEntity {

  @Id
  private UUID id;
  private String name;
  private String email;
  private LocalDate dateOfBirth;

}

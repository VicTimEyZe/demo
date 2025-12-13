package nl.foo.bar.demo.media.service;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Media")
@Data
public class MediaEntity {

  @Id
  private UUID id;
  private String title;
  private String artist;

  @Enumerated(EnumType.STRING)
  private MediaTypeEntity type;

}

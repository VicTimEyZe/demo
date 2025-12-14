package nl.foo.bar.demo.api.media;

import java.util.UUID;

import lombok.Data;

@Data
public class MediaTO {

  private UUID id;
  private String title;
  private String artist;
  private MediaTO.Type type;

  public enum Type {
    MOVIE,
    PODCAST,
    BOOK,
    GAME
  }

}

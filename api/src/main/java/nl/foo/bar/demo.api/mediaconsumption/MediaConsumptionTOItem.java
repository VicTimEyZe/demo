package nl.foo.bar.demo.api.mediaconsumption;

import lombok.Data;
import nl.foo.bar.demo.api.media.MediaTO;

@Data
public class MediaConsumptionTOItem {

  private Status status;
  private MediaTO media;

  public enum Status {
    CONSUMING,
    COMPLETED,
    PAUSED,
    DROPPED
  }

}

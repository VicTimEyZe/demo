package nl.foo.bar.demo.mediaconsumption.controller;

import lombok.Data;
import nl.foo.bar.demo.media.controller.MediaTO;

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

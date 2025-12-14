package nl.foo.bar.demo.core.mediaconsumption;

import lombok.Data;
import nl.foo.bar.demo.core.actor.ActorEntity;
import nl.foo.bar.demo.core.media.MediaEntity;

@Data
public class MediaConsumptionEntityId {

  private ActorEntity actor;
  private MediaEntity media;

}
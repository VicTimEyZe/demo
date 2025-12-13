package nl.foo.bar.demo.mediaconsumption.service;

import lombok.Data;
import nl.foo.bar.demo.actor.service.ActorEntity;
import nl.foo.bar.demo.media.service.MediaEntity;

@Data
public class MediaConsumptionEntityId {

  private ActorEntity actor;
  private MediaEntity media;

}
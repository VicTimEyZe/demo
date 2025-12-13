package nl.foo.bar.demo.mediaconsumption.controller;

import java.util.Set;

import lombok.Data;
import nl.foo.bar.demo.actor.controller.ActorTO;

@Data
public class MediaConsumptionTO {

  private ActorTO actor;
  private int total;
  private Set<MediaConsumptionTOItem> mediaConsumptions;

}

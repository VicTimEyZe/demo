package nl.foo.bar.demo.api.mediaconsumption;

import java.util.Set;

import lombok.Data;
import nl.foo.bar.demo.api.actor.ActorTO;

@Data
public class MediaConsumptionTO {

  private ActorTO actor;
  private int total;
  private Set<MediaConsumptionTOItem> mediaConsumptions;

}

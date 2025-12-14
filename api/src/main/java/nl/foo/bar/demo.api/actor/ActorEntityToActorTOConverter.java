package nl.foo.bar.demo.api.actor;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.core.actor.ActorEntity;

public class ActorEntityToActorTOConverter implements Converter<ActorEntity, ActorTO> {

  @Override
  public ActorTO convert(ActorEntity source) {
    ActorTO actorResponseTO = new ActorTO();

    actorResponseTO.setId(source.getId());
    actorResponseTO.setName(source.getName());
    actorResponseTO.setEmail(source.getEmail());
    actorResponseTO.setDateOfBirth(source.getDateOfBirth());

    return actorResponseTO;
  }

}

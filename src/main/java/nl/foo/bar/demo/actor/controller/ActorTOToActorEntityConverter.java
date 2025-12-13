package nl.foo.bar.demo.actor.controller;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.actor.service.ActorEntity;

public class ActorTOToActorEntityConverter implements Converter<ActorTO, ActorEntity> {

  @Override
  public ActorEntity convert(ActorTO source) {
    ActorEntity actorEntity = new ActorEntity();

    actorEntity.setId(source.getId() != null ? source.getId() : UUID.randomUUID());
    actorEntity.setName(source.getName());
    actorEntity.setEmail(source.getEmail());
    actorEntity.setDateOfBirth(source.getDateOfBirth());

    return actorEntity;
  }

}

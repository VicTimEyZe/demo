package nl.foo.bar.demo.actor.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

  private final ActorRepository actorRepository;

  public ActorService(ActorRepository actorRepository, ConversionService conversionService) {
    this.actorRepository = actorRepository;
  }

  public UUID createActor(ActorEntity actorEntity) {
    actorEntity = actorRepository.save(actorEntity);
    return actorEntity.getId();
  }

  public List<ActorEntity> getAllActors() {
    return actorRepository.getAllActors();
  }

  public Optional<ActorEntity> getActorById(UUID id) {
    return actorRepository.findById(id);
  }

  /**
   * Checks if an actor with the given id exists. If so, all non-ID values in the database are
   * replaced with the provided values.
   * When no actor exists a new actor is inserted in the database.
   *
   * @param actorEntityValues the actor values to create or replace in the database.
   * @return An Optional containing the existing ActorEntity if it was replaced, or an empty
   *   Optional if a new actor was created.
   */
  public Optional<ActorEntity> createOrReplaceActor(ActorEntity actorEntityValues) {
    Optional<ActorEntity> existingActorEntity = this.getActorById(actorEntityValues.getId());
    actorRepository.save(actorEntityValues);
    return existingActorEntity;
  }

}

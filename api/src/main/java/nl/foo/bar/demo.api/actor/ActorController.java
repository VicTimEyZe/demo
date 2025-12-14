package nl.foo.bar.demo.api.actor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import nl.foo.bar.demo.core.actor.ActorEntity;
import nl.foo.bar.demo.core.actor.ActorService;

@RestController
public class ActorController implements ActorControllerOpenApiDoc {

  private final ActorService actorService;
  private final ConversionService conversionService;

  public ActorController(ActorService actorService, ConversionService conversionService) {
    this.actorService = actorService;
    this.conversionService = conversionService;
  }


  @ResponseBody
  @GetMapping("/actors")
  @Override
  public ResponseEntity<List<ActorTO>> getActors() {
    return ResponseEntity.ok(actorService.getAllActors()
      .stream()
      .map(actorEntity -> conversionService.convert(actorEntity, ActorTO.class))
      .toList());
  }

  @ResponseBody
  @GetMapping("/actor/{id}")
  @Override
  public ResponseEntity<ActorTO> getActorById(@PathVariable UUID id) {
    Optional<ActorEntity> actorEntity = actorService.getActorById(id);

    if (actorEntity.isPresent()) {
      return ResponseEntity.ok(conversionService.convert(actorEntity.get(), ActorTO.class));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @ResponseBody
  @PostMapping(value = "/actor",
               consumes = MediaType.APPLICATION_JSON_VALUE,
               produces = MediaType.APPLICATION_JSON_VALUE)
  @Override
  public ResponseEntity<UUID> addActor(@RequestBody @Valid ActorTO actor) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(actorService.createActor(conversionService.convert(actor, ActorEntity.class)));
  }

  @ResponseBody
  @PutMapping(value = "/actor/{id}",
              consumes = MediaType.APPLICATION_JSON_VALUE,
              produces = MediaType.APPLICATION_JSON_VALUE)
  @Override
  public ResponseEntity<ActorTO> createOrReplaceActor(
    @PathVariable UUID id,
    @RequestBody @Valid ActorTO actor
  ) {
    actor.setId(id);
    Optional<ActorEntity> existingActor
      = actorService.createOrReplaceActor(conversionService.convert(actor, ActorEntity.class));

    if (existingActor.isPresent()) {
      return ResponseEntity.ok(actor);
    } else {
      return ResponseEntity.status(HttpStatus.CREATED).body(conversionService.convert(
        actor,
        ActorTO.class
      ));
    }
  }

}

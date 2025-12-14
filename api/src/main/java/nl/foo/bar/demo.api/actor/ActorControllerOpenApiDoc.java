package nl.foo.bar.demo.api.actor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Actor API",
     description = "API for managing actors")
public interface ActorControllerOpenApiDoc {

  @Operation(summary = "Gets all actors in the system",
             description = "Returns a list of all actors")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200",
                 description = "Successful operation",
                 content = {
                   @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActorTO.class))
                 })
  })
  ResponseEntity<List<ActorTO>> getActors();

  @Operation(summary = "Get a actor by its id")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200",
                 description = "Returns the actor with the specified id",
                 content = {
                   @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActorTO.class))
                 }),
    @ApiResponse(responseCode = "400",
                 description = "Invalid id supplied",
                 content = @Content),
    @ApiResponse(responseCode = "404",
                 description = "Actor not found",
                 content = @Content)
  })
  ResponseEntity<ActorTO> getActorById(UUID id);

  @Operation(summary = "Create a new actor")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201",
                 description = "The ID of the created actor",
                 content = {
                   @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class))
                 }),
    @ApiResponse(responseCode = "400",
                 description = "Invalid actor information supplied",
                 content = @Content)
  })
  ResponseEntity<UUID> addActor(ActorTO actor);

  @Operation(summary = "Create a new actor or replace an existing actor by its id")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200",
                 description = "The updated actor for this id",
                 content = {
                   @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActorTO.class))
                 }),
    @ApiResponse(responseCode = "201",
                 description = "The created actor",
                 content = {
                   @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActorTO.class))
                 }),
    @ApiResponse(responseCode = "400",
                 description = "Invalid actor information supplied",
                 content = @Content)
  })
  ResponseEntity<ActorTO> createOrReplaceActor(UUID id, ActorTO actor);

}

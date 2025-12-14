package nl.foo.bar.demo.core.actor;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<nl.foo.bar.demo.core.actor.ActorEntity, UUID> {

  @Query("SELECT a FROM Actor a")
  List<nl.foo.bar.demo.core.actor.ActorEntity> getAllActors();

}

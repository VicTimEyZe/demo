package nl.foo.bar.demo.core.mediaconsumption;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaConsumptionRepository
  extends CrudRepository<MediaConsumptionEntity, MediaConsumptionEntityId> {

  List<MediaConsumptionEntity> getMediaConsumptionEntitiesByActor_IdAndMedia_TypeInAndMediaConsumptionStatusIn(UUID actorId,
    List<String> mediaTypes,
    List<String> mediaConsumptionStatuses
  );

}

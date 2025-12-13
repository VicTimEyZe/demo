package nl.foo.bar.demo.mediaconsumption.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import nl.foo.bar.demo.media.controller.MediaTO;
import nl.foo.bar.demo.mediaconsumption.controller.MediaConsumptionTOItem;

@Service
public class MediaConsumptionService {

  private final MediaConsumptionRepository mediaConsumptionRepository;

  public MediaConsumptionService(MediaConsumptionRepository mediaConsumptionRepository) {
    this.mediaConsumptionRepository = mediaConsumptionRepository;
  }

  public List<MediaConsumptionEntity> getMediaConsumptionForActor(
    UUID id,
    List<MediaTO.Type> includedTypes,
    List<MediaConsumptionTOItem.Status> includedStatusses
  ) {
    List<String> includedTypesString = CollectionUtils.isEmpty(includedTypes) ? Arrays.asList(
      MediaTO.Type.values()).stream().map(Objects::toString).toList() : includedTypes.stream().map(
      Enum::name).toList();

    List<String> includedStatussesString = CollectionUtils.isEmpty(includedStatusses)
      ? Arrays.asList(MediaConsumptionTOItem.Status.values())
      .stream()
      .map(Objects::toString)
      .toList()
      : includedStatusses.stream().map(Enum::name).toList();

    return mediaConsumptionRepository.getMediaConsumptionEntitiesByActor_IdAndMedia_TypeInAndMediaConsumptionStatusIn(id,
      includedTypesString,
      includedStatussesString
    );
  }

}

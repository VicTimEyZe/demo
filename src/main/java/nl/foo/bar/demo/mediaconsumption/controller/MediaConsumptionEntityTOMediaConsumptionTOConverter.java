package nl.foo.bar.demo.mediaconsumption.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.actor.controller.ActorEntityToActorTOConverter;
import nl.foo.bar.demo.media.controller.MediaEntityToMediaTOConverter;
import nl.foo.bar.demo.media.controller.MediaTO;
import nl.foo.bar.demo.mediaconsumption.service.MediaConsumptionEntity;

public class MediaConsumptionEntityTOMediaConsumptionTOConverter
  implements Converter<List<MediaConsumptionEntity>, MediaConsumptionTO> {

  private final ActorEntityToActorTOConverter actorConverter = new ActorEntityToActorTOConverter();
  private final MediaEntityToMediaTOConverter mediaConverter = new MediaEntityToMediaTOConverter();
  private final MediaConsumptionStatusToMediaConsumptionTOStatus statusConverter
    = new MediaConsumptionStatusToMediaConsumptionTOStatus();

  @Override
  public MediaConsumptionTO convert(List<MediaConsumptionEntity> source) {
    MediaConsumptionTO mediaConsumptionTO = new MediaConsumptionTO();

    mediaConsumptionTO.setActor(actorConverter.convert(source.get(0).getActor()));

    Set<MediaConsumptionTOItem> mediaConsumptionTOItems = new HashSet<MediaConsumptionTOItem>();

    for (MediaConsumptionEntity entity : source) {
      MediaConsumptionTOItem mediaConsumptionTOItem = new MediaConsumptionTOItem();

      MediaTO media = mediaConverter.convert(entity.getMedia());
      mediaConsumptionTOItem.setMedia(media);
      mediaConsumptionTOItem.setStatus(statusConverter.convert(entity.getMediaConsumptionStatus()));

      mediaConsumptionTOItems.add(mediaConsumptionTOItem);
    }
    mediaConsumptionTO.setMediaConsumptions(mediaConsumptionTOItems);

    mediaConsumptionTO.setTotal(mediaConsumptionTOItems.size());

    return mediaConsumptionTO;
  }

}

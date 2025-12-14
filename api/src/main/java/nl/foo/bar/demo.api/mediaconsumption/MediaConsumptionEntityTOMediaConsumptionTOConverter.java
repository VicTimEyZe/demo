package nl.foo.bar.demo.api.mediaconsumption;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.api.actor.ActorEntityToActorTOConverter;
import nl.foo.bar.demo.api.media.MediaEntityToMediaTOConverter;
import nl.foo.bar.demo.api.media.MediaTO;
import nl.foo.bar.demo.core.mediaconsumption.MediaConsumptionEntity;
import nl.foo.bar.demo.api.mediaconsumption.MediaConsumptionStatusToMediaConsumptionTOStatusConverter;
import nl.foo.bar.demo.api.mediaconsumption.MediaConsumptionTO;
import nl.foo.bar.demo.api.mediaconsumption.MediaConsumptionTOItem;

public class MediaConsumptionEntityTOMediaConsumptionTOConverter
  implements Converter<List<MediaConsumptionEntity>, MediaConsumptionTO> {

  private final ActorEntityToActorTOConverter actorConverter = new ActorEntityToActorTOConverter();
  private final MediaEntityToMediaTOConverter mediaConverter = new MediaEntityToMediaTOConverter();
  private final MediaConsumptionStatusToMediaConsumptionTOStatusConverter
    statusConverter
    = new MediaConsumptionStatusToMediaConsumptionTOStatusConverter();

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

package nl.foo.bar.demo.api.mediaconsumption;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.core.mediaconsumption.MediaConsumptionStatus;

public class MediaConsumptionTOStatusToMediaConsumptionStatusConverter
  implements Converter<MediaConsumptionTOItem.Status, MediaConsumptionStatus> {

  @Override
  public MediaConsumptionStatus convert(MediaConsumptionTOItem.Status source) {
    switch (source) {
      case PAUSED:
        return MediaConsumptionStatus.PAUSED;
      case CONSUMING:
        return MediaConsumptionStatus.CONSUMING;
      case COMPLETED:
        return MediaConsumptionStatus.COMPLETED;
      case DROPPED:
        return MediaConsumptionStatus.DROPPED;
      default:
        throw new IllegalArgumentException("Unknown media consumption status: " + source);
    }
  }

}

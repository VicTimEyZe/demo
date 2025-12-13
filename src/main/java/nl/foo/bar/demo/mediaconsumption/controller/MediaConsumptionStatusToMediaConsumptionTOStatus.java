package nl.foo.bar.demo.mediaconsumption.controller;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.mediaconsumption.service.MediaConsumptionStatus;

public class MediaConsumptionStatusToMediaConsumptionTOStatus
  implements Converter<MediaConsumptionStatus, MediaConsumptionTOItem.Status> {

  @Override
  public MediaConsumptionTOItem.Status convert(MediaConsumptionStatus source) {
    switch (source) {
      case PAUSED:
        return MediaConsumptionTOItem.Status.PAUSED;
      case CONSUMING:
        return MediaConsumptionTOItem.Status.CONSUMING;
      case COMPLETED:
        return MediaConsumptionTOItem.Status.COMPLETED;
      case DROPPED:
        return MediaConsumptionTOItem.Status.DROPPED;
      default:
        throw new IllegalArgumentException("Unknown media consumption status: " + source);
    }
  }

}

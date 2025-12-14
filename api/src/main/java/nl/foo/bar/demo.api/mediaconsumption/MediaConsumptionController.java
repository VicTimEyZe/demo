package nl.foo.bar.demo.api.mediaconsumption;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nl.foo.bar.demo.core.media.MediaTypeEntity;
import nl.foo.bar.demo.core.mediaconsumption.MediaConsumptionEntity;
import nl.foo.bar.demo.core.mediaconsumption.MediaConsumptionService;
import nl.foo.bar.demo.api.media.MediaTO;
import nl.foo.bar.demo.core.mediaconsumption.MediaConsumptionStatus;

@RestController
public class MediaConsumptionController {

  private final MediaConsumptionService mediaConsumptionService;
  private final ConversionService conversionService;

  public MediaConsumptionController(
    MediaConsumptionService mediaConsumptionService,
    ConversionService conversionService
  ) {
    this.mediaConsumptionService = mediaConsumptionService;
    this.conversionService = conversionService;
  }

  @ResponseBody
  @GetMapping("/actor/{id}/media")
  public ResponseEntity<MediaConsumptionTO> getMediaForActor(
    @PathVariable UUID id,
    @RequestParam(required = false) List<MediaTO.Type> type,
    @RequestParam(required = false) List<MediaConsumptionTOItem.Status> status
  ) {
    List<MediaConsumptionEntity> mediaConsumptionEntities = mediaConsumptionService.getMediaConsumptionForActor(
      id,
      type.stream().map(t -> conversionService.convert(t, MediaTypeEntity.class)).toList(),
      status.stream().map(s -> conversionService.convert(s, MediaConsumptionStatus.class)).toList()
    );

    if (CollectionUtils.isEmpty(mediaConsumptionEntities)) {
      return ResponseEntity.notFound().build();
    }


    return ResponseEntity.ok(conversionService.convert(
      mediaConsumptionEntities,
      MediaConsumptionTO.class
    ));
  }

}

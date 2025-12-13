package nl.foo.bar.demo.mediaconsumption.controller;

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

import nl.foo.bar.demo.media.controller.MediaTO;
import nl.foo.bar.demo.mediaconsumption.service.MediaConsumptionService;

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
    var mediaConsumptionEntities = mediaConsumptionService.getMediaConsumptionForActor(
      id,
      type,
      status
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

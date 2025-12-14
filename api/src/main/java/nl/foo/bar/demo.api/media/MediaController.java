package nl.foo.bar.demo.api.media;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import nl.foo.bar.demo.core.media.MediaEntity;
import nl.foo.bar.demo.core.media.MediaService;
import nl.foo.bar.demo.core.media.MediaTypeEntity;

@RestController
public class MediaController {

  private final MediaService mediaService;
  private final ConversionService conversionService;

  public MediaController(MediaService mediaService, ConversionService conversionService) {
    this.mediaService = mediaService;
    this.conversionService = conversionService;
  }

  @ResponseBody
  @GetMapping("/media")
  public @Valid ResponseEntity<List<MediaTO>> getMedia(
    @RequestParam(required = false) List<MediaTO.Type> type
  ) {
    if (CollectionUtils.isEmpty(type)) {
      return ResponseEntity.ok(mediaService.getMedia()
        .stream()
        .map(mediaEntity -> conversionService.convert(mediaEntity, MediaTO.class))
        .toList());
    }

    List<MediaTypeEntity> filteredTypes = type.stream()
      .map(mediaTOType -> conversionService.convert(mediaTOType, MediaTypeEntity.class))
      .toList();

    return ResponseEntity.ok(mediaService.getMedia(filteredTypes)
      .stream()
      .map(mediaEntity -> conversionService.convert(mediaEntity, MediaTO.class))
      .toList());
  }

  @ResponseBody
  @GetMapping("/media/{id}")
  public @Valid ResponseEntity<MediaTO> getMediaById(@PathVariable UUID id) {
    Optional<MediaEntity> mediaEntity = mediaService.getMediaById(id);

    if (mediaEntity.isPresent()) {
      return ResponseEntity.ok(conversionService.convert(mediaEntity.get(), MediaTO.class));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @ResponseBody
  @PostMapping(value = "/media",
               consumes = MediaType.APPLICATION_JSON_VALUE,
               produces = MediaType.APPLICATION_JSON_VALUE)
  public @Valid ResponseEntity<UUID> addMedia(@RequestBody MediaTO media) {
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(mediaService.createMedia(conversionService.convert(media, MediaEntity.class)));
  }


}

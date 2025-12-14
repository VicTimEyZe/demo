package nl.foo.bar.demo.api.media;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.core.media.MediaEntity;

public class MediaEntityToMediaTOConverter implements Converter<MediaEntity, MediaTO> {

  private final MediaTypeEntityToMediaTOTypeConverter conversionService
    = new MediaTypeEntityToMediaTOTypeConverter();

  @Override
  public MediaTO convert(MediaEntity source) {
    MediaTO mediaTO = new MediaTO();

    mediaTO.setId(source.getId());
    mediaTO.setTitle(source.getTitle());
    mediaTO.setArtist(source.getArtist());
    mediaTO.setType(conversionService.convert(source.getType()));

    return mediaTO;
  }

}

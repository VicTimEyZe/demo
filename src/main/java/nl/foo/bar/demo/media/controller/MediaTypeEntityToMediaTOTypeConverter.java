package nl.foo.bar.demo.media.controller;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.media.service.MediaTypeEntity;

public class MediaTypeEntityToMediaTOTypeConverter
  implements Converter<MediaTypeEntity, MediaTO.Type> {

  @Override
  public MediaTO.Type convert(MediaTypeEntity source) {
    switch (source) {
      case MOVIE:
        return MediaTO.Type.MOVIE;
      case PODCAST:
        return MediaTO.Type.PODCAST;
      case BOOK:
        return MediaTO.Type.BOOK;
      case GAME:
        return MediaTO.Type.GAME;
      default:
        throw new IllegalArgumentException("Unknown media type: " + source);
    }
  }

}

package nl.foo.bar.demo.media.controller;

import org.springframework.core.convert.converter.Converter;

import nl.foo.bar.demo.media.service.MediaTypeEntity;

public class MediaTOTypeToMediaTypeEntityConverter
  implements Converter<MediaTO.Type, MediaTypeEntity> {

  @Override
  public MediaTypeEntity convert(MediaTO.Type source) {
    switch (source) {
      case MOVIE:
        return MediaTypeEntity.MOVIE;
      case PODCAST:
        return MediaTypeEntity.PODCAST;
      case BOOK:
        return MediaTypeEntity.BOOK;
      case GAME:
        return MediaTypeEntity.GAME;
      default:
        throw new IllegalArgumentException("Unknown media type: " + source);
    }
  }

}

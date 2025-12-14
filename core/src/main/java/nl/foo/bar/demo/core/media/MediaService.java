package nl.foo.bar.demo.core.media;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

  private final MediaRepository mediaRepository;

  public MediaService(MediaRepository mediaRepository) {
    this.mediaRepository = mediaRepository;
  }

  public List<MediaEntity> getMedia() {
    return this.getMedia(Collections.emptyList());
  }

  public List<MediaEntity> getMedia(List<MediaTypeEntity> mediaTypes) {
    List<MediaEntity> allMedia = mediaRepository.getAllMedia();

    if (CollectionUtils.isNotEmpty(mediaTypes)) {
      return allMedia.stream()
        .filter(mediaEntity -> mediaTypes.contains(mediaEntity.getType()))
        .toList();
    }

    return allMedia;
  }

  public Optional<MediaEntity> getMediaById(UUID id) {
    return mediaRepository.findById(id);
  }

  public UUID createMedia(MediaEntity mediaEntity) {
    mediaRepository.save(mediaEntity);
    return mediaEntity.getId();
  }

}

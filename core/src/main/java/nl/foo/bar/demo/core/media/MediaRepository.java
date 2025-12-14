package nl.foo.bar.demo.core.media;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends CrudRepository<MediaEntity, UUID> {

  @Query("SELECT m FROM Media m")
  List<MediaEntity> getAllMedia();

}

package warsito.musicweblibrary.repo;

import org.springframework.data.repository.CrudRepository;
import warsito.musicweblibrary.entity.Album;

public interface AlbumRepository  extends CrudRepository<Album, Long> {
}

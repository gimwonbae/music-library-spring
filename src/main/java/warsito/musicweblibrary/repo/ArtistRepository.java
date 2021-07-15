package warsito.musicweblibrary.repo;

import org.springframework.data.repository.CrudRepository;
import warsito.musicweblibrary.entity.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
}

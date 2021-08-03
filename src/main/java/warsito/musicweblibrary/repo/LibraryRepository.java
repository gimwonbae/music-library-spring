package warsito.musicweblibrary.repo;

import org.springframework.data.repository.CrudRepository;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Library;
import warsito.musicweblibrary.entity.User;

import java.util.List;

public interface LibraryRepository extends CrudRepository<Library, Long> {
    List<Library> findByUser(User user);
    Iterable<Library> findByRateBetween(int startRate, int endRate);
    Iterable<Library> findByAlbumContains(Album album);
}

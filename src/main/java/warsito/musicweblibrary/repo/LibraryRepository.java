package warsito.musicweblibrary.repo;

import org.springframework.data.repository.CrudRepository;
import warsito.musicweblibrary.Rate;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Library;
import warsito.musicweblibrary.entity.User;

public interface LibraryRepository extends CrudRepository<Library, Long> {
    Iterable<Library> findByUser(User user);
    Iterable<Library> findByRateBetween(Rate startRate, Rate endRate);
    Iterable<Library> findByAlbumContains(Album album);
}

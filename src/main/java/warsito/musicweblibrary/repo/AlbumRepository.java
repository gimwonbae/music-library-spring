package warsito.musicweblibrary.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Artist;

import java.time.LocalDate;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
    Iterable<Album> findByAlbumNameContainsAndGenreContainsAndReleaseDateBetweenAndRateBetween(String name,
                               String genre,
                               LocalDate startDate,
                               LocalDate endDate,
                               int startRate,
                               int endRate);
}

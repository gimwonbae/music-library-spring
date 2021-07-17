package warsito.musicweblibrary.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.Rate;

import java.time.LocalDate;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    Iterable<Album> findByNameContainsAndGenreContainsAndReleaseBetweenAndRateBetween(String name,
                               String genre,
                               LocalDate startDate,
                               LocalDate endDate,
                               Rate startRate,
                               Rate endRate);
}

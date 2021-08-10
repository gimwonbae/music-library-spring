package waristo.musicweblibrary.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import waristo.musicweblibrary.entity.Album;

import java.time.LocalDate;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
    Iterable<Album> findByAlbumNameContainsAndGenreContainsAndReleaseDateBetweenAndRateBetween(String name,
                               String genre,
                               LocalDate startDate,
                               LocalDate endDate,
                               int startRate,
                               int endRate);
}

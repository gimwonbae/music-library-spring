package waristo.musicweblibrary.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import waristo.musicweblibrary.entity.Artist;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {
    Page<Artist> findByNameContains(String name, Pageable pageable);
}

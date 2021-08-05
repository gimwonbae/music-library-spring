package warsito.musicweblibrary.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import warsito.musicweblibrary.entity.Artist;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Long> {
    Page<Artist> findByNameContains(String name, Pageable pageable);
}

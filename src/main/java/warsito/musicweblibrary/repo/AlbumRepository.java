package warsito.musicweblibrary.repo;

import org.springframework.data.repository.CrudRepository;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.Rate;

import java.time.LocalDate;

public interface AlbumRepository  extends CrudRepository<Album, Long> {
    Iterable<Album> findByNameContains(String name);
    Iterable<Album> findAllByArtistContains(Artist artist);
    Iterable<Album> findAllByArtistContains(String genre);
    Iterable<Album> findAllByReleaseBetween(LocalDate startDate, LocalDate endDate);
    Iterable<Album> findAllByRateBetween(Rate startRate, Rate EndRate);
}

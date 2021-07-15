package warsito.musicweblibrary.repo;

import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Rate;

import java.time.LocalDate;

public interface AlbumRepository {
    Iterable<Album> findAll();
    Album findByName(String name);
    Album findByArtistID(Long artist_id);
    Album findByGenre(String genre);
    Album findByRelease(LocalDate start, LocalDate end);
    Album findByRate(Rate rate);
}

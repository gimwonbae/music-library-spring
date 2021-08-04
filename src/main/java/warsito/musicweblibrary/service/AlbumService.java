package warsito.musicweblibrary.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import warsito.musicweblibrary.dto.AlbumDto;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.exception.CustomException;
import warsito.musicweblibrary.repo.AlbumRepository;
import warsito.musicweblibrary.repo.ArtistRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AlbumService {
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }
    public Optional<Album> searchAlbum(Long id){
        return albumRepository.findById(id);
    }
    public Iterable<Album> searchAlbums(String name,
                                        String genre,
                                        Integer startYear,
                                        Integer endYear,
                                        Integer startRate,
                                        Integer endRate){
        LocalDate startDate = LocalDate.of(startYear, 1 ,1);
        LocalDate endDate = LocalDate.of(endYear, 12, 31);

        return albumRepository.findByAlbumNameContainsAndGenreContainsAndReleaseDateBetweenAndRateBetween(name, genre, startDate, endDate, startRate, endRate);
    }

    public Optional<Album> addAlbum(AlbumDto albumDto){
        String albumName = albumDto.getAlbumName();
        if (artistRepository.findById(albumDto.getArtistId()).isPresent()){
            Artist artist = artistRepository.findById(albumDto.getArtistId()).get();

            String genre = albumDto.getGenre();
            LocalDate releaseDate = LocalDate.parse(albumDto.getReleaseDate());
            int rate = albumDto.getRate();

            Album album = new Album(albumName, artist, genre, releaseDate, rate);
            albumRepository.save(album);
            return Optional.of(album);
        } else {
            return null;
        }
    }

    public boolean deleteAlbum(Long id){
        try {
            albumRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}

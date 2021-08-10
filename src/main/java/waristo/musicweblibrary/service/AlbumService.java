package waristo.musicweblibrary.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import waristo.musicweblibrary.entity.Album;
import waristo.musicweblibrary.repo.ArtistRepository;
import waristo.musicweblibrary.dto.AlbumDto;
import waristo.musicweblibrary.entity.Artist;
import waristo.musicweblibrary.repo.AlbumRepository;

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
    public Page<Album> searchAlbums(Integer page, Integer size, String sortBy, String order){
        Sort sort;
        sort = Sort.by(sortBy);
        if (order.equals("desc")) sort = sort.descending();
        return albumRepository.findAll(PageRequest.of(page, size, sort));
    }
    public Optional<Album> searchAlbum(Long id){
        return albumRepository.findById(id);
    }
    public Iterable<Album> searchAdvanced(String name,
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

package warsito.musicweblibrary.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import warsito.musicweblibrary.dto.ArtistDto;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.repo.ArtistRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Iterable<Artist> serachArtists(String name){
        return artistRepository.findByNameContains(name);
    }

    public Optional<Artist> searchArtist(Long id){
        return artistRepository.findById(id);
    }
    public Artist saveArtist(ArtistDto artistDto){
        Artist artist = new Artist(artistDto.getName(), artistDto.getBorn(), artistDto.getDied());
        artistRepository.save(artist);
        return artist;
    }

    public boolean deleteAlbum(Long id){
        try {
            artistRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}

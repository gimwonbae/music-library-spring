package waristo.musicweblibrary.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import waristo.musicweblibrary.dto.ArtistDto;
import waristo.musicweblibrary.repo.ArtistRepository;
import waristo.musicweblibrary.entity.Artist;

import java.util.Optional;

@Service
public class ArtistService {
    private ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Page<Artist> serachArtists(String name, Integer page, Integer size){
        return artistRepository.findByNameContains(name, PageRequest.of(page, size));
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

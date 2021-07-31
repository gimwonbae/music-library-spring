package warsito.musicweblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warsito.musicweblibrary.Rate;
import warsito.musicweblibrary.dto.ArtistDto;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.repo.ArtistRepository;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/artist", produces = "application/json")
@CrossOrigin(origins = "*")
public class ArtistController {
    private ArtistRepository artistRepo;

    public ArtistController(ArtistRepository artistRepo){
        this.artistRepo = artistRepo;
    }

    @GetMapping
    public Iterable<Artist> allArtists(
            @RequestParam(value = "name", required = false, defaultValue = "") String name){
        return artistRepo.findByNameContains(name);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Artist> postAlbum(@RequestBody ArtistDto artistDto){
        Artist artist = new Artist(artistDto.getName(), artistDto.getBorn(), artistDto.getDied());
        artistRepo.save(artist);
        return new ResponseEntity<>(artist, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Artist> artistById(@PathVariable("id") Long id){
        Optional<Artist> optArtist = artistRepo.findById(id);
        if (optArtist.isPresent()) return new ResponseEntity<>(optArtist.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

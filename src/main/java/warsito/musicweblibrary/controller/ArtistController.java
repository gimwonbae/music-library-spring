package warsito.musicweblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.repo.ArtistRepository;

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
    public Iterable<Artist> allArtists(){
        return artistRepo.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Artist> artistById(@PathVariable("id") Long id){
        Optional<Artist> optArtist = artistRepo.findById(id);
        if (optArtist.isPresent()) return new ResponseEntity<>(optArtist.get(), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}

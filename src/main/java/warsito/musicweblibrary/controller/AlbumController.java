package warsito.musicweblibrary.controller;

import com.fasterxml.jackson.datatype.jsr310.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warsito.musicweblibrary.Rate;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.repo.AlbumRepository;
import warsito.musicweblibrary.repo.ArtistRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/album", produces = "application/json")
@CrossOrigin(origins = "*")
public class AlbumController {
    private AlbumRepository albumRepo;
    private ArtistRepository artistRepo;

    public AlbumController(AlbumRepository albumRepo, ArtistRepository artistRepo){
        this.albumRepo = albumRepo;
        this.artistRepo = artistRepo;
    }

    @GetMapping
    public Iterable<Album> searchAlbums(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "genre", required = false, defaultValue = "") String genre,
            @RequestParam(value = "startYear", required = false, defaultValue = "1800") Integer startYear,
            @RequestParam(value = "endYear", required = false, defaultValue = "2100") Integer endYear,
            @RequestParam(value = "startRate", required = false, defaultValue = "GOOD") Rate startRate,
            @RequestParam(value = "endRate", required = false, defaultValue = "LOVE") Rate endRate
    ){
        LocalDate startDate = LocalDate.of(startYear, 1 ,1);
        LocalDate endDate = LocalDate.of(endYear, 12, 31);
        return albumRepo.findByAlbumNameContainsAndGenreContainsAndReleaseDateBetweenAndRateBetween(name, genre, startDate, endDate, startRate, endRate);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Album> postAlbum(@RequestBody Map<String, Object> json){
        String albumName = (String) json.get("albumName");
        Artist artist = artistRepo.findById(Long.valueOf((int)json.get("artistId"))).get();
        String genre = (String) json.get("genre");
        LocalDate releaseDate = LocalDate.parse((String) json.get("releaseDate"));
        Rate rate = Rate.intToRate((Integer) json.get("rate"));

        Album album = new Album(albumName, artist, genre, releaseDate, rate);

        albumRepo.save(album);
        return new ResponseEntity<>(album, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Album> albumById(@PathVariable("id") Long id){
        Optional<Album> optAlbum = albumRepo.findById(id);
        if (optAlbum.isPresent()) return new ResponseEntity<>(optAlbum.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

package warsito.musicweblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warsito.musicweblibrary.Rate;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.repo.AlbumRepository;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(path = "/album", produces = "application/json")
@CrossOrigin(origins = "*")
public class AlbumController {
    private AlbumRepository albumRepo;

    public AlbumController(AlbumRepository albumRepo){
        this.albumRepo = albumRepo;
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
        return albumRepo.findByNameContainsAndGenreContainsAndReleaseBetweenAndRateBetween(name, genre, startDate, endDate, startRate, endRate);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Album> albumById(@PathVariable("id") Long id){
        Optional<Album> optAlbum = albumRepo.findById(id);
        if (optAlbum.isPresent()) return new ResponseEntity<>(optAlbum.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

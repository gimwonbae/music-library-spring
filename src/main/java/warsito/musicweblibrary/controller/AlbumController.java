package warsito.musicweblibrary.controller;

import com.fasterxml.jackson.datatype.jsr310.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warsito.musicweblibrary.dto.AlbumDto;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.repo.AlbumRepository;
import warsito.musicweblibrary.repo.ArtistRepository;
import warsito.musicweblibrary.service.AlbumService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/album", produces = "application/json")
@CrossOrigin(origins = "*")
public class AlbumController {
    private AlbumService albumService;

    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @GetMapping
    public Iterable<Album> getAlbums(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "genre", required = false, defaultValue = "") String genre,
            @RequestParam(value = "startYear", required = false, defaultValue = "1800") Integer startYear,
            @RequestParam(value = "endYear", required = false, defaultValue = "2100") Integer endYear,
            @RequestParam(value = "startRate", required = false, defaultValue = "0") Integer startRate,
            @RequestParam(value = "endRate", required = false, defaultValue = "5") Integer endRate
    ){
        return albumService.searchAlbums(name, genre, startYear, endYear, startRate, endRate);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Album> postAlbum(@RequestBody AlbumDto albumDto){
        Optional<Album> album = albumService.addAlbum(albumDto);
        if (album.isPresent()) return new ResponseEntity<>(album.get(), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Album> albumById(@PathVariable("id") Long id){
        Optional<Album> album = albumService.searchAlbum(id);
        if (album.isPresent()) return new ResponseEntity<>(album.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus deleteAlbum(@PathVariable("id") Long id){
        boolean flag = albumService.deleteAlbum(id);
        if (flag) return HttpStatus.NO_CONTENT;
        else return HttpStatus.BAD_REQUEST;
    }
}

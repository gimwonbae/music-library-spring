package waristo.musicweblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waristo.musicweblibrary.dto.ArtistDto;
import waristo.musicweblibrary.entity.Artist;
import waristo.musicweblibrary.service.ArtistService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/artist", produces = "application/json")
@CrossOrigin(origins = "*")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Artist>> getArtists(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ){
        return new ResponseEntity<>(artistService.serachArtists(name, page ,size), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Artist> postAlbum(@RequestBody ArtistDto artistDto){
        return new ResponseEntity<>(artistService.saveArtist(artistDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Artist> artistById(@PathVariable("id") Long id){
        Optional<Artist> artist = artistService.searchArtist(id);
        if (artist.isPresent()) return new ResponseEntity<>(artist.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteArtist(@PathVariable("id") Long id){
        boolean flag = artistService.deleteAlbum(id);
        if (flag) return new ResponseEntity(HttpStatus.NO_CONTENT);
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

package waristo.musicweblibrary.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waristo.musicweblibrary.dto.AlbumDto;
import waristo.musicweblibrary.entity.Album;
import waristo.musicweblibrary.service.AlbumService;

import java.util.Optional;

@RestController
@RequestMapping(path = "/album", produces = "application/json")
@CrossOrigin(origins = "*")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService){
        this.albumService = albumService;
    }

    @GetMapping()
    @ApiOperation(value = "Search All Albums")
    public ResponseEntity<Page<Album>> getAlbums(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "desc") String sortBy,
            @RequestParam(value = "order", required = false, defaultValue = "releaseDate") String order
    ) {
        return new ResponseEntity<>(albumService.searchAlbums(page, size, sortBy, order), HttpStatus.OK);
    }

    @GetMapping(path = "/advanced-search")
    @ApiOperation(value = "Search Album with advanced-search")
    public ResponseEntity<Iterable<Album>> getAdcancedSearch(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "genre", required = false, defaultValue = "") String genre,
            @RequestParam(value = "startYear", required = false, defaultValue = "1800") Integer startYear,
            @RequestParam(value = "endYear", required = false, defaultValue = "2100") Integer endYear,
            @RequestParam(value = "startRate", required = false, defaultValue = "0") Integer startRate,
            @RequestParam(value = "endRate", required = false, defaultValue = "5") Integer endRate
    ){
        return new ResponseEntity<>(albumService.searchAdvanced(name, genre, startYear, endYear, startRate, endRate), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @ApiOperation(value = "Post Album Info on Website")
    public ResponseEntity<Album> postAlbum(@RequestBody AlbumDto albumDto){
        Optional<Album> album = albumService.addAlbum(albumDto);
        if (album.isPresent()) return new ResponseEntity<>(album.get(), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Search Album By ID")
    public ResponseEntity<Album> albumById(@PathVariable("id") Long id){
        Optional<Album> album = albumService.searchAlbum(id);
        if (album.isPresent()) return new ResponseEntity<>(album.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete Album By ID")
    public HttpStatus deleteAlbum(@PathVariable("id") Long id){
        boolean flag = albumService.deleteAlbum(id);
        if (flag) return HttpStatus.NO_CONTENT;
        else return HttpStatus.BAD_REQUEST;
    }
}

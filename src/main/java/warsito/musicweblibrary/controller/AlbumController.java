package warsito.musicweblibrary.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.repo.AlbumRepository;

@RestController
@RequestMapping(path = "/album", produces = "application/json")
@CrossOrigin(origins = "*")
public class AlbumController {
    private AlbumRepository albumRepo;

    public AlbumController(AlbumRepository albumRepo){
        this.albumRepo = albumRepo;
    }

    @GetMapping
    public Iterable<Album> allAlbums(){
        return albumRepo.findAll();
    }
}

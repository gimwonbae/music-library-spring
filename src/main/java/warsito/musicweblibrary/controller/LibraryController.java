package warsito.musicweblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import warsito.musicweblibrary.dto.LibraryDto;
import warsito.musicweblibrary.dto.UserSignUpDto;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Library;
import warsito.musicweblibrary.entity.User;
import warsito.musicweblibrary.repo.AlbumRepository;
import warsito.musicweblibrary.repo.LibraryRepository;
import warsito.musicweblibrary.repo.UserRepository;
import warsito.musicweblibrary.security.TokenProvider;
import warsito.musicweblibrary.service.LibraryService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {
    public LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<List<Library>> getLibrary(){
        return new ResponseEntity<>(libraryService.searchLibrary(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Library> postLibrary(@RequestBody LibraryDto libraryDto){
        Library library = libraryService.saveLibrary(libraryDto);
        if (library != null) return new ResponseEntity<>(library, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<Library> patchLibrary(@RequestBody LibraryDto libraryDto){
        Library library = libraryService.updateLibrary(libraryDto);
        if (library != null) return new ResponseEntity<>(library, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteLibrary(@PathVariable("id") Long id){
        boolean flag = libraryService.deleteLibrary(id);
        if (flag) return new ResponseEntity(HttpStatus.NO_CONTENT);
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {
    private TokenProvider tokenProvider;
    private UserRepository userRepo;
    private LibraryRepository libraryRepo;
    private AlbumRepository albumRepo;

    public LibraryController(UserRepository userRepo, LibraryRepository libraryRepo, AlbumRepository albumRepo){
        this.userRepo = userRepo;
        this.libraryRepo = libraryRepo;
        this.albumRepo = albumRepo;
    }

    @GetMapping
    public ResponseEntity<List<Library>> getLibrary(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        List<Library> libraries = libraryRepo.findByUser(user);
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Library> postLibrary(@RequestBody LibraryDto libraryDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);
        int rate = libraryDto.getRate();
        Album album = albumRepo.findById(libraryDto.getAlbumId()).get();

        LocalDateTime createdAt = LocalDateTime.now();
        String comment = libraryDto.getComment();

        Library library = new Library(user, rate, album, createdAt, createdAt, comment);
        libraryRepo.save(library);

        return new ResponseEntity<>(library, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Library> patchLibrary(@RequestBody LibraryDto libraryDto){
        Library library = libraryRepo.findById(libraryDto.getAlbumId()).get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!library.getUser().getUsername().equals(authentication.getName())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (libraryDto.getComment() != null) library.setComment(libraryDto.getComment());
        if (libraryDto.getRate() != null) library.setRate(libraryDto.getRate());
        library.setModifiedAt(LocalDateTime.now());
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteLibrary(@PathVariable("id") Long id){
        Library library = libraryRepo.findById(id).get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!library.getUser().getUsername().equals(authentication.getName())){
            return;
        }
        libraryRepo.deleteById(id);
    }
}

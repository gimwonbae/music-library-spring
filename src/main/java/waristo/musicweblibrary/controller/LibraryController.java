package waristo.musicweblibrary.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import waristo.musicweblibrary.dto.LibraryDto;
import waristo.musicweblibrary.entity.Library;
import waristo.musicweblibrary.service.LibraryService;

@Controller
@RequestMapping("/library")
public class LibraryController {
    public LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<Page<Library>> getLibrary(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sortBy,
            @RequestParam String order
    ){
        return new ResponseEntity<>(libraryService.searchLibrary(page, size, sortBy, order), HttpStatus.OK);
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

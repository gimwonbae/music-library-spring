package waristo.musicweblibrary.controller;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Search Album List of User's Library")
    public ResponseEntity<Page<Library>> getLibrary(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sortBy,
            @RequestParam String order
    ){
        return new ResponseEntity<>(libraryService.searchLibrary(page, size, sortBy, order), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Add Album on User's Library")
    public ResponseEntity<Library> postLibrary(@RequestBody LibraryDto libraryDto){
        Library library = libraryService.saveLibrary(libraryDto);
        if (library != null) return new ResponseEntity<>(library, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    @ApiOperation(value = "Update Album on User's Library")
    public ResponseEntity<Library> patchLibrary(@RequestBody LibraryDto libraryDto){
        Library library = libraryService.updateLibrary(libraryDto);
        if (library != null) return new ResponseEntity<>(library, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete Album on User's Library")
    public ResponseEntity deleteLibrary(@PathVariable("id") Long id){
        boolean flag = libraryService.deleteLibrary(id);
        if (flag) return new ResponseEntity(HttpStatus.NO_CONTENT);
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

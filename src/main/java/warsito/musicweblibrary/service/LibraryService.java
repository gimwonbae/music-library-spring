package warsito.musicweblibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import warsito.musicweblibrary.dto.LibraryDto;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Library;
import warsito.musicweblibrary.entity.User;
import warsito.musicweblibrary.repo.AlbumRepository;
import warsito.musicweblibrary.repo.LibraryRepository;
import warsito.musicweblibrary.repo.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibraryService {
    private LibraryRepository libraryRepository;
    private UserRepository userRepository;
    private AlbumRepository albumRepository;

    public LibraryService(LibraryRepository libraryRepository, UserRepository userRepository, AlbumRepository albumRepository) {
        this.libraryRepository = libraryRepository;
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
    }

    public Page<Library> searchLibrary(Integer page, Integer size, String sortBy, String order){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Sort sort = Sort.by(sortBy);
        if (order.equals("desc")) sort = sort.descending();
        User user = userRepository.findByUsername(username);
        return libraryRepository.findByUser(user, PageRequest.of(page, size), sort);
    }

    public Library saveLibrary(LibraryDto libraryDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        int rate = libraryDto.getRate();

        Album album;
        if (albumRepository.findById(libraryDto.getAlbumId()).isPresent()){
            album = albumRepository.findById(libraryDto.getAlbumId()).get();
        }
        else {
            return null;
        }
        LocalDateTime createdAt = LocalDateTime.now();
        String comment = libraryDto.getComment();

        Library library = new Library(user, rate, album, createdAt, createdAt, comment);
        libraryRepository.save(library);
        return library;
    }

    public Library updateLibrary(LibraryDto libraryDto){
        Library library;
        if (libraryRepository.findById(libraryDto.getAlbumId()).isPresent()){
            library = libraryRepository.findById(libraryDto.getAlbumId()).get();
        }
        else {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!library.getUser().getUsername().equals(authentication.getName())){
            return null;
        }
        if (libraryDto.getComment() != null) library.setComment(libraryDto.getComment());
        if (libraryDto.getRate() != null) library.setRate(libraryDto.getRate());
        library.setModifiedAt(LocalDateTime.now());
        return library;
    }

    public boolean deleteLibrary(Long id){
        Library library;
        if (!libraryRepository.findById(id).isPresent()) return false;
        else {
            library = libraryRepository.findById(id).get();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!library.getUser().getUsername().equals(authentication.getName())){
            return false;
        }
        libraryRepository.deleteById(id);
        return true;
    }
}

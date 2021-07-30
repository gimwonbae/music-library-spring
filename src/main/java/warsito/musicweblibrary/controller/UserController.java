package warsito.musicweblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import warsito.musicweblibrary.entity.User;
import warsito.musicweblibrary.repo.UserRepository;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public UserController (UserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping(consumes = "application/json")
//    public ResponseEntity<User> postUser(@RequestBody Map<String, Object> json){
//        String username = (String) json.get("username");
//        String password = passwordEncoder.encode((String) json.get("password"));
//        String email = (String) json.get("email");
//        LocalDate createdAt = LocalDate.now();
//
//        User user = new User(username, password, email, createdAt, createdAt);
//        userRepo.save(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }

//    @PostMapping(path = "/login", consumes = "application/json")
//    public ResponseEntity<String> loginUser(@RequestBody Map<String, Object> json){
//
//    }
}

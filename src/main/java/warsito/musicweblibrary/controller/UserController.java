package warsito.musicweblibrary.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import warsito.musicweblibrary.dto.UserSignInDto;
import warsito.musicweblibrary.dto.UserSignUpDto;
import warsito.musicweblibrary.entity.User;
import warsito.musicweblibrary.exception.CustomException;
import warsito.musicweblibrary.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import warsito.musicweblibrary.security.TokenProvider;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;

    public UserController (UserRepository userRepo, PasswordEncoder passwordEncoder, AuthenticationManager auhtenticationManager, TokenProvider tokenProvider){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = auhtenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<User> postUser(@RequestBody UserSignUpDto userSignUpDto){
        String password = passwordEncoder.encode(userSignUpDto.getPassword());
        LocalDate createdAt = LocalDate.now();

        User user = new User(userSignUpDto.getUsername(), password, userSignUpDto.getEmail(), createdAt, createdAt);
        userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody UserSignInDto userSignInDto){
        String username = userSignInDto.getUsername();
        String password = userSignInDto.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenProvider.createToken(username, userRepo.findByUsername(username).getAuthorities());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new CustomException(e.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}

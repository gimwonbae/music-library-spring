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
import org.springframework.web.bind.annotation.*;
import warsito.musicweblibrary.dto.UserSignInDto;
import warsito.musicweblibrary.dto.UserSignUpDto;
import warsito.musicweblibrary.entity.User;
import warsito.musicweblibrary.exception.CustomException;
import warsito.musicweblibrary.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import warsito.musicweblibrary.security.TokenProvider;
import warsito.musicweblibrary.security.UserRepositoryUserDetailsService;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserRepositoryUserDetailsService userService;

    public UserController(UserRepositoryUserDetailsService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<User> postUser(@RequestBody UserSignUpDto userSignUpDto){
        return new ResponseEntity<>(userService.signUp(userSignUpDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody UserSignInDto userSignInDto){
        String token = userService.logIn(userSignInDto);
        if (token == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        boolean flag = userService.deleteUser(id);
        if (flag) return new ResponseEntity(HttpStatus.NO_CONTENT);
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

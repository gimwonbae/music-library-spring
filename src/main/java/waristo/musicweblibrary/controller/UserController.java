package waristo.musicweblibrary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import waristo.musicweblibrary.dto.UserSignInDto;
import waristo.musicweblibrary.dto.UserSignUpDto;
import waristo.musicweblibrary.entity.User;
import waristo.musicweblibrary.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
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

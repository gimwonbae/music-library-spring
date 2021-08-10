package waristo.musicweblibrary.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import waristo.musicweblibrary.dto.UserSignInDto;
import waristo.musicweblibrary.dto.UserSignUpDto;
import waristo.musicweblibrary.entity.User;
import waristo.musicweblibrary.repo.UserRepository;
import waristo.musicweblibrary.security.TokenProvider;

import java.time.LocalDate;

@Service
public class UserService {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;

    public UserService(UserRepository userRepo,
                                            PasswordEncoder passwordEncoder,
                                            AuthenticationManager authenticationManager,
                                            TokenProvider tokenProvider) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public User signUp(UserSignUpDto userSignUpDto){
        String password = passwordEncoder.encode(userSignUpDto.getPassword());
        LocalDate createdAt = LocalDate.now();
        User user = new User(userSignUpDto.getUsername(), password, userSignUpDto.getEmail(), createdAt, createdAt, "ROLE_USER");
        userRepo.save(user);
        return user;
    }

    public String logIn(UserSignInDto userSignInDto){
        String username = userSignInDto.getUsername();
        String password = userSignInDto.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenProvider.createToken(username, userRepo.findByUsername(username).getAuthorities());
            return token;
        } catch (AuthenticationException e) {
            return null;
        }
    }

    public boolean deleteUser(Long id){
        try {
            userRepo.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}

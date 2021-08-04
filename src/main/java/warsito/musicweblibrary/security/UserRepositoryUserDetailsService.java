package warsito.musicweblibrary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import warsito.musicweblibrary.dto.UserSignInDto;
import warsito.musicweblibrary.dto.UserSignUpDto;
import warsito.musicweblibrary.entity.User;
import warsito.musicweblibrary.exception.CustomException;
import warsito.musicweblibrary.repo.UserRepository;

import java.time.LocalDate;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;

    public UserRepositoryUserDetailsService(UserRepository userRepo,
                                            PasswordEncoder passwordEncoder,
                                            AuthenticationManager authenticationManager,
                                            TokenProvider tokenProvider) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) return user;
        throw new UsernameNotFoundException("User '" + username + "' not found");
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

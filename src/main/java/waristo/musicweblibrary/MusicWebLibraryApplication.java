package waristo.musicweblibrary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import waristo.musicweblibrary.entity.User;
import waristo.musicweblibrary.repo.UserRepository;

import java.time.LocalDate;

@SpringBootApplication
public class MusicWebLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicWebLibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				String password = passwordEncoder.encode("1234");
				LocalDate createdAt = LocalDate.now();

				User user = new User("wonbae", password, "wo@naver.com", createdAt, createdAt, "ROLE_ADMIN");
				userRepo.save(user);
			}
		};
	}
}

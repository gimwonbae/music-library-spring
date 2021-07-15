package warsito.musicweblibrary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import warsito.musicweblibrary.entity.Album;
import warsito.musicweblibrary.entity.Artist;
import warsito.musicweblibrary.repo.AlbumRepository;
import warsito.musicweblibrary.repo.ArtistRepository;
import warsito.musicweblibrary.repo.LibraryRepository;

import java.time.LocalDate;

@SpringBootApplication
public class MusicWebLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicWebLibraryApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner dataLoader(AlbumRepository albumRepo, ArtistRepository artRepo, LibraryRepository libRepo) {
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
//			}
//		}
//	}
}

package warsito.musicweblibrary.entity;

import lombok.Data;
import warsito.musicweblibrary.Rate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 40, message = "size of album name: 1 to 40")
    private String albumName;

    @ManyToOne(targetEntity = Artist.class)
    private Artist artist;

    private String genre;
    private LocalDate releaseDate;

    @Enumerated
    private Rate rate;
}

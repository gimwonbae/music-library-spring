package warsito.musicweblibrary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    final private String albumName;

    @ManyToOne(targetEntity = Artist.class)
    final private Artist artist;

    final private String genre;
    final private LocalDate releaseDate;

    final private int rate;
}

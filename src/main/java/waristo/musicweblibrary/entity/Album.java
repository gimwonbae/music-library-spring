package waristo.musicweblibrary.entity;

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

    private final String albumName;

    @ManyToOne(targetEntity = Artist.class)
    private final Artist artist;

    private final String genre;
    private final LocalDate releaseDate;

    private final int rate;
}

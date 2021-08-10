package waristo.musicweblibrary.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 20, message = "size of artist name : 1 to 20")
    final private String name;

    final private LocalDate born;
    final private LocalDate died;

    @OneToMany(targetEntity = Album.class)
    private List<Album> albums = new ArrayList<>();
}
package warsito.musicweblibrary.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 20, message = "size of artist name : 1 to 20")
    private String name;

    private LocalDate born;
    private LocalDate died;


//    @OneToMany(targetEntity = Album.class)
//    private List<Album> albums = new ArrayList<>();
}
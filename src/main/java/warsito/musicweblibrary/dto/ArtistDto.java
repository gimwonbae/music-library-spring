package warsito.musicweblibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ArtistDto {
    private String name;
    private LocalDate born;
    private LocalDate died;
}
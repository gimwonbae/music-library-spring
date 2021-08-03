package warsito.musicweblibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
// albumName, artist, genre, releaseDate, rate
public class AlbumDto {
    private String albumName;
    private Long artistId;
    private String genre;
    private String releaseDate;
    private int rate;
}

package waristo.musicweblibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class LibraryDto {
    private Integer rate;
    private Long albumId;
    private String comment;
}
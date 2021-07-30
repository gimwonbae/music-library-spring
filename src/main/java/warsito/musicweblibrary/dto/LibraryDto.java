package warsito.musicweblibrary.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import warsito.musicweblibrary.Rate;
import warsito.musicweblibrary.entity.Album;

import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class LibraryDto {
    private int rateInt;
    private Long albumId;
    private String comment;
}
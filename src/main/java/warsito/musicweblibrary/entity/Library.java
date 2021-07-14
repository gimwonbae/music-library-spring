package warsito.musicweblibrary.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Library {
    private final int user_id;
    private Rate rate;
    private final int album_id;
    private LocalDateTime time;
    private String comment;
}

package warsito.musicweblibrary.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Album {
    private final int id;
    private final String name;
    private final int artist_id;
    private final String genre;
    private final LocalDate release;
    private Rate rate;
}

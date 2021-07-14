package warsito.musicweblibrary;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Album {
    private final int id;
    private final String name;
    private final String artist;
    private final String genre;
    private final LocalDate release;
}

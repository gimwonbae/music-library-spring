package warsito.musicweblibrary.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Artist {
    private final int id;
    private final String name;
    private final LocalDate born;
    private final LocalDate died;
}

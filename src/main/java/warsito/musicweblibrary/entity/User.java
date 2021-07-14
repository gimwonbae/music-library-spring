package warsito.musicweblibrary.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private final String id;
    private final String username;
    private final LocalDateTime created;
    private LocalDateTime modified;
    private String password;
    private int level;
}

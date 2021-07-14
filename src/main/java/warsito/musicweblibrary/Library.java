package warsito.musicweblibrary;

import lombok.Data;

@Data
public class Library {
    private Rate rate;


    public static enum Rate {
        LOVE, LIKE, GOOD
    }
}

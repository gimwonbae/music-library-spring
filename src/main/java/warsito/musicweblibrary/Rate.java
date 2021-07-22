package warsito.musicweblibrary;

public enum Rate {
    GOOD, LIKE, LOVE;

    public static Rate intToRate(int value){
        if (value == 0) return GOOD;
        else if (value == 1) return LIKE;
        else if (value == 2) return LOVE;
        return null;
    }
}

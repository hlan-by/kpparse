package ru.kinopoisk.models;

public class FilmNote {

    private String message;

    public FilmNote() {
        this.message = Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    public FilmNote(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

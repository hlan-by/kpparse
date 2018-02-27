package ru.kinopoisk.utils.search.enums;

public enum FilmGenre {
    ANIME("аниме"), HITMAN("боевик"), DRAMA("драма"), CARTOON("мультфильм"), THRILLER("ужасы"), FANTASY("фэнтези"),
    NONE ("-");

    private final String val;

    FilmGenre(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

}

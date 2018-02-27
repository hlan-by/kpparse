package ru.kinopoisk.utils.search.enums;

public enum  FilmTypes {
    FILM("фильм"), PHOTO("фотографии"), TV_SERIES("сериал"), DVD("DVD"), NONE("-");

    private final String val;

    FilmTypes(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

}

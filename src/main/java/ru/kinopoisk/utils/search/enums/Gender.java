package ru.kinopoisk.utils.search.enums;

public enum Gender {
    FEMALE("женщина"), MALE("мужчина"), NONE("-");

    private final String val;

    Gender(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

}

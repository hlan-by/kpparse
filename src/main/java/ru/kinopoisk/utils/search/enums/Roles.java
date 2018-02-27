package ru.kinopoisk.utils.search.enums;

public enum Roles {
    ACTOR("Актер"), STAGE_DIRECTOR("Режиссер"), SCREENWRITER("Сценарист"), PRODUCER("Продюсер"), NONE("-");

    private final String val;

    Roles(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}

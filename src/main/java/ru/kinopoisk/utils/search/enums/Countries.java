package ru.kinopoisk.utils.search.enums;

public enum Countries {
    USA("США"), RUSSIA("Россия"), GB("Великобритания"), USSR("СССР"), NONE("-");

    private final String val;

    Countries(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

}

package ru.kinopoisk.enums;

public enum TabNames {
    AFISHA("Афиша"), JOURNAL("Журнал"), FIMLS("Фильмы"), RATINGS("Рейтинги");

    private String rusName;

    TabNames(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}

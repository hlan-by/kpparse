package ru.kinopoisk.utils.search.data;

import ru.kinopoisk.utils.search.enums.Roles;

public class FilmCreatorsForSearch {

    private Roles firstRole = Roles.SCREENWRITER;
    private String firstRoleName = "Джордж Р.Р. Мартин";
    private Roles secondRole = Roles.ACTOR;
    private String secondRoleName = "Эмилия Кларк";

    public String getFirstRole() {
        return firstRole.toString();
    }

    public void setFirstRole(Roles firstRole) {
        this.firstRole = firstRole;
    }

    public String getSecondRole() {
        return secondRole.toString();
    }

    public void setSecondRole(Roles secondRole) {
        this.secondRole = secondRole;
    }

    public String getFirstRoleName() {
        return firstRoleName;

    }

    public void setFirstRoleName(String firstRoleName) {
        this.firstRoleName = firstRoleName;
    }

    public String getSecondRoleName() {
        return secondRoleName;
    }

    public void setSecondRoleName(String secondRoleName) {
        this.secondRoleName = secondRoleName;
    }

    @Override
    public String toString() {
        return "FilmCreatorsForSearch{" +
                "firstRole=" + firstRole +
                ", firstRoleName='" + firstRoleName + '\'' +
                ", secondRole=" + secondRole +
                ", secondRoleName='" + secondRoleName + '\'' +
                '}';
    }
}

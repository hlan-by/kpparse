package ru.kinopoisk.utils.search.data;

public class UserForSearch {
    private String userLogin = "ATwarrior";
    private String userName = "Вася";
    private String userSurname = "Пупкин";
    private String userLink = "https://www.kinopoisk.ru/user/14746989/";

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    @Override
    public String toString() {
        return "UserForSearch{" +
                "userLogin='" + userLogin + '\'' +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userLink='" + userLink + '\'' +
                '}';
    }
}

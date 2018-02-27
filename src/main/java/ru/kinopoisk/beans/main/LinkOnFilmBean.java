package ru.kinopoisk.beans.main;

public class LinkOnFilmBean {
    private String rusFilmName;
    private String href;

    public String getRusFilmName() {
        return rusFilmName;
    }

    public void setRusFilmName(String rusFilmName) {
        this.rusFilmName = rusFilmName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Link: rus name - [" + rusFilmName + "], href - [" + href + "]";
    }

    public boolean equals(LinkOnFilmBean linkForEquals) {
        if (this.href.equals(linkForEquals.getHref()) && this.rusFilmName.equals(linkForEquals.getRusFilmName())) {
            return true;
        }
        return false;
    }
}

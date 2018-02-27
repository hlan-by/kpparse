package ru.kinopoisk.utils.search.data;

import ru.kinopoisk.utils.search.enums.Countries;
import ru.kinopoisk.utils.search.enums.FilmGenre;
import ru.kinopoisk.utils.search.enums.FilmTypes;

import java.util.Arrays;
import java.util.List;

public class FilmForSearch {

    private String filmName = "Игра престолов";
    private String filmReleaseYear = "2011";
    private String filmPath = "/film/igra-prestolov-2011-464963/";

    private FilmGenre filmGenre = FilmGenre.FANTASY;
    private Countries filmCountry = Countries.USA;
    private FilmTypes filmType = FilmTypes.TV_SERIES;
    private List<String> labelList = Arrays.asList("Некромантия", "Меч и магия", "Узурпатор");

    private FilmCreatorsForSearch filmCreatorsForSearch = new FilmCreatorsForSearch();

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmReleaseYear() {
        return filmReleaseYear;
    }

    public void setFilmReleaseYear(String filmReleaseYear) {
        this.filmReleaseYear = filmReleaseYear;
    }

    public String getFilmPath() {
        return filmPath;
    }

    public void setFilmPath(String filmPath) {
        this.filmPath = filmPath;
    }

    public String getFilmGenre() {
        return filmGenre.toString();
    }

    public void setFilmGenre(FilmGenre filmGenre) {
        this.filmGenre = filmGenre;
    }

    public String getFilmCountry() {
        return filmCountry.toString();
    }

    public void setFilmCountry(Countries filmCountry) {
        this.filmCountry = filmCountry;
    }

    public String getFilmType() {
        return filmType.toString();
    }

    public void setFilmType(FilmTypes filmType) {
        this.filmType = filmType;
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        if (labelList.size() != 2) {
            this.labelList = Arrays.asList("Некромантия", "Меч и магия", "Узурпатор");
            System.out.println("Error, label list size should be 3. Values were chosen by default.");
        } else {
            this.labelList = labelList;
        }
    }

    public FilmCreatorsForSearch getFilmCreatorsForSearch() {
        return filmCreatorsForSearch;
    }

    public void setFilmCreatorsForSearch(FilmCreatorsForSearch filmCreatorsForSearch) {
        this.filmCreatorsForSearch = filmCreatorsForSearch;
    }

    @Override
    public String toString() {
        return "FilmForSearch{" +
                "filmName='" + filmName + '\'' +
                ", filmReleaseYear='" + filmReleaseYear + '\'' +
                ", filmPath='" + filmPath + '\'' +
                ", filmGenre=" + filmGenre +
                ", filmCountry=" + filmCountry +
                ", filmType=" + filmType +
                ", labelList=" + labelList +
                ", filmCreatorsForSearch=" + filmCreatorsForSearch +
                '}';
    }
}

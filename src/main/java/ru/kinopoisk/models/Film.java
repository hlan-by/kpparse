package ru.kinopoisk.models;

import java.util.List;
import java.util.regex.Pattern;

public class Film {
    public enum MPAAType {G, PG, PG_13, NC_17, R, NAN}

    public enum AgeType {AGE_ANY, AGE_0_PLUS, AGE_6_PLUS, AGE_12_PLUS, AGE_16_PLUS, AGE_18_PLUS}

    public enum FilmType {ANY, MOVIE, SERIAL, CARTOON}

    public enum City {MINSK, MOSCOW, MOGILEV}

    private String filmName;
    private FilmType filmType;
    private List<String> filmGenres;
    private List<String> filmCountries;
    private int year;
    private double kinopoiskRating;
    private double IMDBRating;
    private int worldwideCriticsPercentRating;
    private int numberPositiveReviews;
    private MPAAType MPAARating;
    private AgeType ageRating;
    private long budget;
    private long boxOffice;
    private boolean isDVDAvailable;
    private boolean isBluRayAvailable;
    private boolean is3DAvailable;

    public Film(String filmName, FilmType filmType, List<String> filmGenres, List<String> filmCountries, int year, double kinopoiskRating,
                double IMDBRating, int criticsRating, int numberPositiveReviews, MPAAType MPAARating, AgeType ageRating,
                long budget, long boxOffice, boolean isDVDAvaiable, boolean isBluRayAvaiable, boolean is3DAvaiable) {
        this.filmName = filmName;
        this.filmType = filmType;
        this.filmGenres = filmGenres;
        this.filmCountries = filmCountries;
        this.year = year;
        this.kinopoiskRating = kinopoiskRating;
        this.IMDBRating = IMDBRating;
        this.worldwideCriticsPercentRating = criticsRating;
        this.numberPositiveReviews = numberPositiveReviews;
        this.MPAARating = MPAARating;
        this.ageRating = ageRating;
        this.budget = budget;
        this.boxOffice = boxOffice;
        this.isDVDAvailable = isDVDAvaiable;
        this.isBluRayAvailable = isBluRayAvaiable;
        this.is3DAvailable = is3DAvaiable;
    }

    public String getFilmName() {
        return filmName;
    }

    public FilmType getFilmType() {
        return filmType;
    }

    public List<String> getFilmGenres() {
        return filmGenres;
    }

    public List<String> getFilmCountries() {
        return filmCountries;
    }

    public int getYear() {
        return year;
    }

    public double getKinopoiskRating() {
        return kinopoiskRating;
    }

    public double getIMDBRating() {
        return IMDBRating;
    }

    public int getWorldwideCriticsPercentRating() {
        return worldwideCriticsPercentRating;
    }

    public int getNumberPositiveReviews() {
        return numberPositiveReviews;
    }

    public long getBudget() {
        return budget;
    }

    public long getBoxOffice() {
        return boxOffice;
    }

    public boolean isDVDAvailable() {
        return isDVDAvailable;
    }

    public boolean isBluRayAvailable() {
        return isBluRayAvailable;
    }

    public boolean isIs3DAvailable() {
        return is3DAvailable;
    }

    public AgeType getAgeRating() {
        return ageRating;
    }

    public MPAAType getMPAARating() {
        return MPAARating;
    }

    public boolean isFilmNameContains(String filmName) {
        return Pattern.compile(Pattern.quote(filmName), Pattern.CASE_INSENSITIVE).matcher(filmName).find();
    }

    public boolean hasGenre(String genre) {
        return filmGenres.contains(genre);
    }

    public boolean hasCountry(String country) {
        return filmCountries.contains(country);
    }

    public boolean isBudgetInBoundaries(long minInMillionsDollars, long maxInMillionsDollars) {
        return budget >= minInMillionsDollars * 1000_000 && budget <= maxInMillionsDollars * 1000_000;
    }

    public boolean isPositiveReviewPercentInBoundaries(int min, int max) {
        return numberPositiveReviews >= min && numberPositiveReviews <= max;
    }

    public boolean isCriticsRateInBoundaries(int minRate, int maxRate) {
        return worldwideCriticsPercentRating >= minRate && worldwideCriticsPercentRating <= maxRate;
    }

    public boolean isIMDbRateInBoundaries(double minRate, double maxRate) {
        return IMDBRating >= minRate && IMDBRating <= maxRate;
    }

    public boolean isYearInBoundaries(int minYear, int maxYear) {
        return year >= minYear && year <= maxYear;
    }

    public boolean isKinopoiskRatingInBoundaries(double minRate, double maxRate) {
        return getKinopoiskRating() >= minRate && getKinopoiskRating() <= maxRate;
    }

    public boolean isBoxofficeInBoundaries(int min, int max){
        return getBoxOffice()>=(min*1_000_000) && getBoxOffice()<=(max*1_000_000);
    }

}

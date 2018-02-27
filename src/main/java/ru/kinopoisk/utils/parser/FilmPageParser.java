package ru.kinopoisk.utils.parser;

import ru.kinopoisk.models.Film;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilmPageParser extends ParserUtils {

    private WebElement mainElement;

    // LOCATORS
    private static final String FILMNAME_BIG_LOCATOR = "moviename-big";
    private static final String GET_NESSECARY_ROW_LOCATOR = "//table[@class='info']//td[@class='type' and text()='%s']//following-sibling::td";
    private static final String THREE_D_LOCATOR = "//table[@class='info']//span[@class='threeD']";
    private static final String KINOPOISK_RATING_LOCATOR = "//form[@class='rating_stars']//div[@class='block_2']//span[@class='rating_ball']";
    private static final String POSITIVE_REVIEWS_COUNT_LOCATOR = "//form[@class='rating_stars']//div[@class='block_2']//span[@class='ratingCount']";
    private static final String IMDB_RATING_LOCATOR = "//form[@class='rating_stars']//div[@class='block_2']//div[contains(text(),'IMDb')]";
    private static final String WORLDWIDE_CRITICS_RATING_LOCATOR = "//div[contains(@class,'criticsRating')]//div[@class='ratingNum']//span";

    public FilmPageParser(WebElement element) {
        this.mainElement = element;
    }

    private String getParsedFilmName() {
        String filmName = null;
        if (isElementPresent(mainElement, By.className(FILMNAME_BIG_LOCATOR))) {
            filmName = deleteTextInBrackets(mainElement.findElement(By.className(FILMNAME_BIG_LOCATOR)).getText());
        }
        return filmName;
    }

    private int getParsedYear() {
        int year = -1;
        if (isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "год")))) {
            String yearString = mainElement.findElement(By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "год"))).getText();
            year = Integer.parseInt(deleteWhiteSpaces(deleteTextInBrackets(yearString)));
        }
        return year;
    }

    private List<String> getParsedCountries() {
        List<String> countries = null;
        if (isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "страна") + "//div"))) {
            WebElement countriesTable = mainElement.findElement(By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "страна") + "//div"));
            List<WebElement> countryElements = countriesTable.findElements(By.tagName("a"));
            if (!countryElements.isEmpty()) {
                countries = new ArrayList<>();
                for (WebElement currentCountry : countryElements) {
                    countries.add(currentCountry.getText());
                }
            }
        }
        return countries;
    }

    private double getParsedKPRating() {
        double kinopoiskRating = -1;
        if (isElementPresent(mainElement, By.xpath(KINOPOISK_RATING_LOCATOR))) {
            String kinopoiskRatingString = mainElement.findElement(By.xpath(KINOPOISK_RATING_LOCATOR)).getText();
            kinopoiskRating = Double.parseDouble(deleteWhiteSpaces(kinopoiskRatingString));
        }
        return kinopoiskRating;
    }

    private int getParsedPositiveReviewsCount() {
        int numberPositiveReviews = -1;
        if (isElementPresent(mainElement, By.xpath(POSITIVE_REVIEWS_COUNT_LOCATOR))) {
            String numberPsitiveReviewsString = mainElement.findElement(By.xpath(POSITIVE_REVIEWS_COUNT_LOCATOR)).getText();
            numberPositiveReviews = Integer.parseInt(deleteWhiteSpaces(numberPsitiveReviewsString));
        }
        return numberPositiveReviews;
    }

    private double getParsedIMDBRating() {
        double IMDBRating = -1;
        if (isElementPresent(mainElement, By.xpath(IMDB_RATING_LOCATOR))) {
            String IMDBRatingString = mainElement.findElement(By.xpath(IMDB_RATING_LOCATOR)).getText();
            Pattern pattern = Pattern.compile("[^:]+(?=\\()");
            Matcher matcher = pattern.matcher(IMDBRatingString);
            if (matcher.find()) {
                IMDBRating = Double.parseDouble(deleteWhiteSpaces(matcher.group(0)));
            }
        }
        return IMDBRating;
    }

    private int getParsedWorldCriticsPercent() {
        int worldwideCriticsPercentRating = -1;
        if (isElementPresent(mainElement, By.xpath(WORLDWIDE_CRITICS_RATING_LOCATOR))) {
            String criticsRatingString = mainElement.findElement(By.xpath(WORLDWIDE_CRITICS_RATING_LOCATOR)).getText();
            worldwideCriticsPercentRating = Integer.parseInt(criticsRatingString);
        }
        return worldwideCriticsPercentRating;
    }

    private Film.MPAAType getParsedMPAARating() {
        Film.MPAAType MPAARating = Film.MPAAType.NAN;
        String MPAARatingString;
        if (isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "рейтинг MPAA")))) {
            MPAARatingString = mainElement.findElement(By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "рейтинг MPAA"))).getAttribute("class");
            switch (MPAARatingString) {
                case "rate_g":
                    MPAARating = Film.MPAAType.G;
                    break;
                case "rate_pg":
                    MPAARating = Film.MPAAType.PG;
                    break;
                case "rate_pg13":
                    MPAARating = Film.MPAAType.PG_13;
                    break;
                case "rate_nc17":
                    MPAARating = Film.MPAAType.NC_17;
                    break;
                case "rate_r":
                    MPAARating = Film.MPAAType.R;
                    break;
                default:
                    MPAARating = Film.MPAAType.NAN;
            }
        }
        return MPAARating;
    }

    private Film.AgeType getParsedAgeRating() {
        String ageRatingString;
        Film.AgeType ageRating = Film.AgeType.AGE_ANY;
        if (isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "возраст")
                + "//div[contains(@class,'ageLimit')]"))) {
            ageRatingString = mainElement.findElement(By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "возраст") +
                    "//div[contains(@class,'ageLimit')]")).getAttribute("class");
            ageRatingString = ageRatingString.substring(ageRatingString.lastIndexOf("age"));
            switch (ageRatingString) {
                case "age0":
                    ageRating = Film.AgeType.AGE_0_PLUS;
                    break;
                case "age6":
                    ageRating = Film.AgeType.AGE_6_PLUS;
                    break;
                case "age12":
                    ageRating = Film.AgeType.AGE_12_PLUS;
                    break;
                case "age16":
                    ageRating = Film.AgeType.AGE_16_PLUS;
                    break;
                case "age18":
                    ageRating = Film.AgeType.AGE_18_PLUS;
                    break;
                default:
                    ageRating = Film.AgeType.AGE_ANY;
            }
        }
        return ageRating;
    }

    private Film.FilmType getParsedFilmType() {
        Film.FilmType filmType = Film.FilmType.ANY;
        if (isElementPresent(mainElement, By.className(FILMNAME_BIG_LOCATOR))) {
            String filmTypeString = mainElement.findElement(By.className(FILMNAME_BIG_LOCATOR)).getText();
            if (filmTypeString.contains("сериал")) filmType = Film.FilmType.SERIAL;
            else if(getParsedFilmGenres().contains("мультфильм")) filmType = Film.FilmType.CARTOON;
            else filmType = Film.FilmType.MOVIE;
        }
        return filmType;
    }

    private List<String> getParsedFilmGenres() {
        List<String> genres = null;
        if (isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "жанр") + "//span"))) {
            WebElement genresTable = mainElement.findElement(By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "жанр") + "//span"));
            List<WebElement> genreElements = genresTable.findElements(By.tagName("a"));
            if (!genreElements.isEmpty()) {
                genres = new ArrayList<>();
                for (WebElement currentGenre : genreElements) {
                    genres.add(currentGenre.getText());
                }
            }
        }
        return genres;
    }

    private boolean isParsedDVDAvailable() {
        return isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "релиз на DVD")));
    }

    private boolean isParsedBluRayDVDAvailable() {
        return isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "релиз на Blu-ray")));
    }

    private boolean isParsed3DAvailable() {
        return isElementPresent(mainElement, By.xpath(THREE_D_LOCATOR));
    }

    private long getParsedBudget() {
        long budget = -1;
        if (isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "бюджет")))) {
            String budgetString = deleteWhiteSpaces(mainElement.findElement(By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "бюджет"))).getText());
            Pattern pattern = Pattern.compile("[\\d]+");
            Matcher matcher = pattern.matcher(budgetString);
            if (matcher.find()) {
                budget = Long.parseLong(deleteWhiteSpaces(matcher.group(0)));
            }
        }
        return budget;
    }

    private long getParsedBoxOffice() {
        long boxOffice = -1;
        if (isElementPresent(mainElement, By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "сборы в мире")))) {
            String boxOfficeString = deleteWhiteSpaces(mainElement.findElement(By.xpath(String.format(GET_NESSECARY_ROW_LOCATOR, "сборы в мире"))).getText());
            boxOfficeString = boxOfficeString.substring(boxOfficeString.lastIndexOf("$") + 1).replaceAll("[^\\d+]+", "");
            boxOffice = Long.parseLong(boxOfficeString);
        }
        return boxOffice;
    }

    public Film getParsedFilmData() {
        String film = getParsedFilmName();
        Film.FilmType filmType = getParsedFilmType();
        int filmYear = getParsedYear();
        List<String> filmGenres = getParsedFilmGenres();
        List<String> filmCountries = getParsedCountries();
        double KPRating = getParsedKPRating();
        int positiveReviewsCount = getParsedPositiveReviewsCount();
        double IMDBRating = getParsedIMDBRating();
        int worldCriticsPercent = getParsedWorldCriticsPercent();
        Film.MPAAType MPAARating = getParsedMPAARating();
        long budget = getParsedBudget();
        long boxOffice = getParsedBoxOffice();
        Film.AgeType ageRating = getParsedAgeRating();
        boolean isDVDAvailable = isParsedDVDAvailable();
        boolean isBluRayAvailable = isParsedBluRayDVDAvailable();
        boolean is3DAvailable = isParsed3DAvailable();
        return new Film(film, filmType, filmGenres, filmCountries, filmYear, KPRating, IMDBRating, worldCriticsPercent, positiveReviewsCount,
                MPAARating, ageRating, budget, boxOffice, isDVDAvailable, isBluRayAvailable, is3DAvailable);
    }
}

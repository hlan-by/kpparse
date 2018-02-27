package ru.kinopoisk.tests.navigator;

import ru.kinopoisk.models.Film;
import ru.kinopoisk.pages.filmPage.FilmPage;
import ru.kinopoisk.pages.navigator.NavigatorPage;

import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Aliaksei_Kurbatau on 2/21/2018.
 */
public class NavigatorPositiveTest extends BeforeAndAfterClass {

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1792
     */
    @Test(groups = {"navigator"}, priority = 301)
    public void searchByCountryYearRangeReviewRateTest() {

        String country = "США";
        int yearFrom = 1970;
        int yearTo = 1975;
        int criticsRateMin = 68;
        int criticsRateMax = 77;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectCountry(country);
        navigatorPage.selectYearIntervalFromTo(yearFrom, yearTo);
        navigatorPage.setCriticsRate(criticsRateMin, criticsRateMax);
        navigatorPage.pressSearchButton();

        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.hasCountry(country));
        Assert.assertTrue(film.isYearInBoundaries(yearFrom, yearTo));
        Assert.assertTrue(film.isCriticsRateInBoundaries(criticsRateMin, criticsRateMax));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1914
     */
    @Test(groups = {"navigator"}, priority = 302)
    public void searchByOnlySelectedGenreMinNumberOfVotesBudgetTest() {

        String genre = "боевик";
        int minNumberOfVotes = 1000;
        int minBudget = 5;
        int maxBudget = 25;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectGenre(genre);
        navigatorPage.checkOnlySelectedGenres();
        navigatorPage.setMinNumberOfVotes(minNumberOfVotes);
        navigatorPage.setBudget(minBudget, maxBudget);
        navigatorPage.pressSearchButton();

        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.hasGenre(genre));
        Assert.assertTrue(film.getFilmGenres().size() == 1);
        Assert.assertTrue(film.isBudgetInBoundaries(minBudget, maxBudget));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1916
     */
    @Test(groups = {"navigator"}, priority = 303)
    public void searchByOnlySelectedCountriesExcludedGenreYearIMDbRateTest() {

        String country = "США";
        String genre = "детектив";
        int creationYear = 2010;
        int IMDbRateMin = 2;
        int IMDbRateMax = 8;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectCountry(country);
        navigatorPage.checkOnlySelectedCountries();
        navigatorPage.selectExcludedGenre(genre);
        navigatorPage.selectCreationYear(creationYear);
        navigatorPage.setIMDbRate(IMDbRateMin, IMDbRateMax);
        navigatorPage.pressSearchButton();

        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.hasCountry(country));
        Assert.assertFalse(film.hasGenre(genre));
        Assert.assertEquals(film.getYear(), creationYear);
        Assert.assertTrue(film.isIMDbRateInBoundaries(IMDbRateMin, IMDbRateMax));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1918
     */
    @Test(groups = {"navigator"}, priority = 304)
    public void searchByMinNumberOfPositiveVotesAgeTypeTest() {

        int minRate = 3;
        int maxRate = 7;
        Film.AgeType ageType = Film.AgeType.AGE_16_PLUS;
        Film.FilmType filmType = Film.FilmType.SERIAL;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.setFilmRate(minRate, maxRate);
        navigatorPage.selectAge(Film.AgeType.AGE_16_PLUS);
        navigatorPage.selectType(Film.FilmType.SERIAL);
        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isKinopoiskRatingInBoundaries(minRate, maxRate));
        Assert.assertEquals(film.getAgeRating(), ageType);
        Assert.assertEquals(film.getFilmType(), filmType);
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2133
     */
    @Test(groups = {"navigator"}, priority = 305)
    public void searchByGenreTest() {

        String genre = "аниме";

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectGenre(genre);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.hasGenre(genre));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2134
     */
    @Test(groups = {"navigator"}, priority = 306)
    public void searchByKinopoiskRateTest() {

        double minRate = 6.5;
        double maxRate = 7.2;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.setFilmRate(minRate, maxRate);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isKinopoiskRatingInBoundaries(minRate, maxRate));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2135
     */
    @Test(groups = {"navigator"}, priority = 307)
    public void searchByYearTest() {

        int year = 1976;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectCreationYear(year);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertEquals(film.getYear(), year);
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2136
     */
    @Test(groups = {"navigator"}, priority = 308)
    public void searchByOnlySelectedGenreTest() {

        String genre = "комедия";

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectGenre(genre);
        navigatorPage.checkOnlySelectedGenres();

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.hasGenre(genre));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2137
     */
    @Test(groups = {"navigator"}, priority = 309)
    public void searchByCountryTest() {

        String country = "Канада";

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectCountry(country);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.hasCountry(country));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2138
     */
    @Test(groups = {"navigator"}, priority = 310)
    public void searchByOnlySelectedCountryTest() {

        String country = "Беларусь";

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectCountry(country);
        navigatorPage.checkOnlySelectedCountries();

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.getFilmCountries().size() == 1);
        Assert.assertTrue(film.hasCountry(country));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2139
     */
    @Test(groups = {"navigator"}, priority = 311)
    public void searchByYearRangeTest() {

        int yearFrom = 1981;
        int yearTo = 1985;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectYearIntervalFromTo(yearFrom, yearTo);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isYearInBoundaries(yearFrom, yearTo));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2140
     */
    @Test(groups = {"navigator"}, priority = 312)
    public void searchByDecadeTest() {

        int yearDecade = 1970;

        int decadeStart=1970;
        int decadeEnd=1979;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectYearDecade(yearDecade);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isYearInBoundaries(decadeStart, decadeEnd));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2141
     */
    @Test(groups = {"navigator"}, priority = 313)
    public void searchByIMDbRateTest() {

        double minRate = 6.9;
        double maxRate = 7.2;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.setIMDbRate(minRate, maxRate);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isIMDbRateInBoundaries(minRate, maxRate));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2142
     */
    @Test(groups = {"navigator"}, priority = 314)
    public void searchByMovieAgeTest() {

        Film.AgeType ageType= Film.AgeType.AGE_12_PLUS;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectAge(ageType);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertEquals(film.getAgeRating(), ageType);
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2143
     */
    @Test(groups = {"navigator"}, priority = 315)
    public void searchByBudgetTest() {

        int minBudget = 50;
        int maxBudget = 200;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.setBudget(minBudget, maxBudget);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isBudgetInBoundaries(minBudget, maxBudget));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2144
     */
    @Test(groups = {"navigator"}, priority = 316)
    public void searchByBoxofficeTest() {

        int minBoxoffice = 400;
        int maxBoxoffice = 500;
        NavigatorPage.BoxofficeLocation boxofficeLocation = NavigatorPage.BoxofficeLocation.WORLD;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectBoxoffice(minBoxoffice, maxBoxoffice, boxofficeLocation);

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

       // Assert.assertTrue(film.isBoxofficeInBoundaries(minBoxoffice, maxBoxoffice));
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2145
     */
    @Test(groups = {"navigator"}, priority = 317)
    public void searchByAvailableOnDVDTest() {

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.checkAvailableOnDVD();

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isDVDAvailable());
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2146
     */
    @Test(groups = {"navigator"}, priority = 318)
    public void searchByAvailableOnBlurayTest() {

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.checkAvailableOnBlueray();

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isBluRayAvailable());
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2147
     */
    @Test(groups = {"navigator"}, priority = 319)
    public void searchByAvailableOn3DTest() {

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.checkAvailable3DRelease();

        navigatorPage.pressSearchButton();
        navigatorPage.openFirstResultFilmPage();

        turnOffImplicitWaits(driver);
        Film film = new FilmPage(driver).getFilmInfo();
        turnOnImplicitWaits(driver);

        Assert.assertTrue(film.isIs3DAvailable());
    }
}

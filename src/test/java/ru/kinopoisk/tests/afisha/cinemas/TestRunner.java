package ru.kinopoisk.tests.afisha.cinemas;

import ru.kinopoisk.models.Film;
import ru.kinopoisk.models.User;
import ru.kinopoisk.pages.afisha.cinemas.CinemaPage;
import ru.kinopoisk.pages.afisha.cinemas.CinemasPage;
import ru.kinopoisk.pages.afisha.cinemas.MovieAndCinemaSearchResultsPage;
import ru.kinopoisk.pages.afisha.cinemas.MyCinemas;
import ru.kinopoisk.pages.login.LoginPage;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TestRunner extends BeforeAndAfterClass {
    private CinemasPage cinemasPage;
    private CinemaPage cinemaPage;
    private MovieAndCinemaSearchResultsPage movieAndCinemaSearchResultsPage;
    private SoftAssert softAssert;
    private LoginPage loginPage;
    private User user;
    private MyCinemas myCinemas;
    private final String LOGIN = "loginTest";
    private final String PASSWORD = "1234zzzA";

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1811
     */
    @Test(groups = "afisha/cinemas", priority = 600)
    public void testSearchMovieHousesByLocation() {
        cinemasPage = new CinemasPage(driver, driverWait);
        cinemasPage.openPage();
        cinemasPage.openCity(Film.City.MOSCOW);
        Assert.assertEquals(cinemasPage.getQuantity(), cinemasPage.getCINEMASQTYMOSCOW());
        cinemasPage.openCity(Film.City.MINSK);
        Assert.assertEquals(cinemasPage.getQuantity(), cinemasPage.getCINEMASQTYMINSK());
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2066
     **/

    @Test(groups = "afisha/cinemas", priority = 601, expectedExceptions = TimeoutException.class)
    public void testNoMetroStationButton() {
        cinemasPage.openCity(Film.City.MOGILEV);
        cinemasPage.pressMetroButton();
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2067
     **/
    @Test(groups = "afisha/cinemas", priority = 602)
    public void testQtyMetroStations() {
        cinemasPage.openCity(Film.City.MINSK);
        Assert.assertEquals(cinemasPage.stationQty(), cinemasPage.getSTATIONQTYMINSK());
        cinemasPage.openCity(Film.City.MOSCOW);
        Assert.assertEquals(cinemasPage.stationQty(), cinemasPage.getSTATIONQTYMOSCOW());
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1817
     **/
    @Test(groups = "afisha/cinemas", priority = 603)
    public void testSearchMovieHouseByMetroStationNearBy() {
        cinemasPage.openCity(Film.City.MINSK);
        cinemasPage.pressMetroButton();
        cinemasPage.findPushkinskaiaMetroStation();
        Assert.assertEquals(cinemasPage.getMovieHouseTitle(), cinemasPage.getMOVIEHOUSENAME());
    }

    /**
     * Link to testcase in JIRA -  https://jira.epam.com/jira/browse/EPMFARMATS-1836
     **/
    @Test(groups = "afisha/cinemas", priority = 604)
    public void testCorrectDataMovieHouse() {
        Assert.assertEquals(cinemasPage.getMovieHouseAddress(), cinemasPage.getMOVIEHOUSEADDRESS());
        Assert.assertEquals(cinemasPage.getMovieHousePhone(), cinemasPage.getMOVIEHOUSEPHONE());
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2078
     **/
    @Test(groups = "afisha/cinemas", priority = 605)
    public void testCorrectDataMovieHouseOwnPage() {
        softAssert = new SoftAssert();
        cinemasPage.openCinemaPage();
        cinemaPage = new CinemaPage(driver, driverWait);
        softAssert.assertEquals(cinemaPage.getTitle(), cinemaPage.getMOVIEHOUSETITLE(), "Wrong Title");
        softAssert.assertEquals(cinemaPage.getAddress(), cinemaPage.getMOVIEHOUSEADDRESS(), "Wrong Address");
        softAssert.assertEquals(cinemaPage.getMetro(), cinemaPage.getMETROSTATIONHOUSEADDRESS(), "Wrong metroStation");
        softAssert.assertEquals(cinemaPage.getPhone(), cinemaPage.getPHONEHOUSEADDRESS(), "Wrong Phone");
        softAssert.assertEquals(cinemaPage.getWebSite(), cinemaPage.getWEBSITEHOUSEADDRESS(), "Wrong Website");
        softAssert.assertAll();
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2079
     **/
    @Test(groups = "afisha/cinemas", priority = 606)
    public void testScheduleCurrentMovieHouse() {
        cinemaPage.pressButtonSchedule();
        Assert.assertTrue(cinemaPage.isScheduledFilmsExist());
        cinemaPage.pressBackToMovieHousesButton();
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1827
     **/
    @Test(groups = "afisha/cinemas", priority = 607, dataProvider = "formDataProvider")
    public void testSearchScheduledMovie(int movie, int movieHouse, int date, String xpath, String comment) {
        movieAndCinemaSearchResultsPage = new MovieAndCinemaSearchResultsPage(driver, driverWait);
        movieAndCinemaSearchResultsPage.pressStartNewSearchButton();
        movieAndCinemaSearchResultsPage.makeChoice(movie, movieHouse, date);
        movieAndCinemaSearchResultsPage.pressButtonScheduleSessoins();
        Assert.assertTrue(movieAndCinemaSearchResultsPage.isMovieFound(xpath), comment);
        driver.navigate().back();
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2089
     **/
    @Test(groups = "afisha/cinemas", priority = 608)
    public void testAdditionMovieHouseWithoutLogin() {
        cinemasPage.pressMyMovieHouseButton();
        softAssert = new SoftAssert();
        softAssert.assertTrue(cinemasPage.isNoticeVisible());
        cinemasPage.pressAddMovieHouseButton();
        softAssert.assertTrue(cinemasPage.isNoticeVisibleForCurrentMovieHouse());
        softAssert.assertAll();
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1822
     **/
    @Test(groups = "afisha/cinemas", priority = 609)
    public void testAdditionMovieHouseWithLogin() {
        loginPage = new LoginPage(driver, driverWait);
        user = new User(LOGIN, PASSWORD);
        loginPage.login(user);
        myCinemas = new MyCinemas(driver, driverWait);
        myCinemas.pressAllCinemasButton();
        Assert.assertTrue(cinemasPage.isAddicationSuccessful(), "The current cinema wasn't been added to favorite!");
    }

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-2120
     **/
    @Test(groups = "afisha/cinemas", priority = 610)
    public void testDeletionMovieHouseWithLogin() {
        Assert.assertTrue(loginPage.isLogin(), "");
        Assert.assertTrue(cinemasPage.isDeletionSuccessful(), "The current cinema wasn't been deleted from favorite!");
    }

    @DataProvider(name = "formDataProvider")
    public Object[][] data() {
        return new Object[][]{
                {0, 0, 0, "//div[@class='title _FILM_']", "without parameters"},
                {0, 0, 2, "//div[@class='title _FILM_']", "date was choosen"},
                {0, 1, 0, "//div[@class='schedule-film']", "movie house was choosen"},
                {0, 1, 2, "//div[@class='schedule-film']", "movie house and date were choosen"},
                {1, 0, 0, "//div[@class='schedule-cinema']", "movie was choosen"},
                {1, 0, 2, "//div[@class='schedule-cinema']", "movie and date were choosen"},
                {1, 1, 0, "//div[@class='_FILM_']", "movie and movie house were choosen"},
                {1, 1, 2, "//div[@class='_FILM_']", "all was choosen"},
        };
    }
}

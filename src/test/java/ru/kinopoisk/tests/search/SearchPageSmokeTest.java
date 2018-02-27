package ru.kinopoisk.tests.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.kinopoisk.pages.search.SearchPage;
import ru.kinopoisk.utils.search.data.FilmForSearch;
import ru.kinopoisk.utils.search.data.PersonForSearch;
import ru.kinopoisk.utils.search.data.UserForSearch;

import java.util.concurrent.TimeUnit;

public class SearchPageSmokeTest {

    private FilmForSearch filmForSearch = new FilmForSearch();
    private PersonForSearch personForSearch = new PersonForSearch();
    private UserForSearch userForSearch = new UserForSearch();

    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void testSearchFilmByNameYearGenreCountryType() {
        boolean isFilmFound = new SearchPage(driver)
                .openSearchPage()
                .searchFilmByNameYearGenreCountryType(filmForSearch)
                .checkFilmSearchResult(filmForSearch);
        Assert.assertTrue(isFilmFound);
    }

    @Test
    public void testSearchFilmByCreators() {
        boolean isFilmFound = new SearchPage(driver)
                .openSearchPage()
                .searchFilmByCreators(filmForSearch.getFilmCreatorsForSearch())
                .checkFilmSearchResult(filmForSearch);
        Assert.assertTrue(isFilmFound);
    }

    @Test
    public void testSearchPerson() {
        boolean isPersonFound = new SearchPage(driver)
                .openSearchPage()
                .searchPerson(personForSearch)
                .checkPersonSearchResult(personForSearch);
        Assert.assertTrue(isPersonFound);
    }

    @Test
    public void testSearchUser() {
        boolean isUserFound = new SearchPage(driver)
                .openSearchPage()
                .searchUser(userForSearch)
                .checkUserSearchResult(userForSearch);
        Assert.assertFalse(isUserFound);
    }

    @Test
    public void testSearchFilmByKeywords() {
        boolean isFilmFound = new SearchPage(driver)
                .openSearchPage()
                .searchFilmByKeywords(filmForSearch)
                .checkFilmSearchResult(filmForSearch);
        Assert.assertTrue(isFilmFound);
    }

    @AfterClass
    public void stopBrowser() {
        driver.quit();
        System.out.println("Browser was successfully closed.");
    }
}

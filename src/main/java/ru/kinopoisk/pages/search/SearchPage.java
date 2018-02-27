package ru.kinopoisk.pages.search;

import ru.kinopoisk.pages.search.parts.*;
import ru.kinopoisk.utils.search.data.FilmCreatorsForSearch;
import ru.kinopoisk.utils.search.data.FilmForSearch;
import ru.kinopoisk.utils.search.data.PersonForSearch;
import ru.kinopoisk.utils.search.data.UserForSearch;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    private static final String KINOPOISK_SEARCH_URL = "https://www.kinopoisk.ru/s/";

    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchResultPage searchFilmByNameYearGenreCountryType(FilmForSearch filmForSearch) {
        SearchFilmNameYearEtcPage searchFilmNameYearEtc = new SearchFilmNameYearEtcPage(driver);

        searchFilmNameYearEtc.inputDataForSearchFilmByNameYearGenreCountryType(filmForSearch);
        searchFilmNameYearEtc.clickButtonSearchFilmByNameYearGenreCountryType();
        return new SearchResultPage(driver);
    }

    public SearchResultPage searchFilmByCreators(FilmCreatorsForSearch filmCreators) {
        SearchFilmByCreatorsPage searchFilmByCreators = new SearchFilmByCreatorsPage(driver);

        searchFilmByCreators.inputDataForSearchFilmByCreators(filmCreators);
        searchFilmByCreators.clickButtonSearchByCreators();
        return new SearchResultPage(driver);
    }

    public SearchResultPage searchPerson(PersonForSearch personForSearch) {
        SearchPersonPage searchPerson = new SearchPersonPage(driver);

        searchPerson.inputPersonData(personForSearch);
        searchPerson.clickSearchPersonButton();
        return new SearchResultPage(driver);
    }

    public SearchResultPage searchUser(UserForSearch userForSearch) {
        SearchUserPage searchUser = new SearchUserPage(driver);

        searchUser.inputPersonData(userForSearch);
        searchUser.clickSearchUserButton();
        return new SearchResultPage(driver);
    }

    public SearchResultPage searchFilmByKeywords(FilmForSearch filmForSearch) {
        SearchFilmByKeywordsPage searchFilmByKeywords = new SearchFilmByKeywordsPage(driver);

        searchFilmByKeywords.inputKeywords(filmForSearch.getLabelList());
        searchFilmByKeywords.clickSearckByKeywordsButton();
        return new SearchResultPage(driver);
    }

    public SearchPage openSearchPage() {
        driver.get(KINOPOISK_SEARCH_URL);
        return new SearchPage(driver);
    }

}

package ru.kinopoisk.pages.search.parts;

import ru.kinopoisk.pages.search.SearchResultPage;
import ru.kinopoisk.utils.search.data.FilmForSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchFilmNameYearEtcPage extends AbstractSearchPage {

    @FindBy(xpath = "//input[@id = 'find_film']")
    private WebElement findFilmInputField;

    @FindBy(xpath = "//input[@id = 'year']")
    private WebElement releaseYearFilmInputField;

    @FindBy(xpath = "//select[@class = 'text el_6 __genreSB__']")
    private WebElement genreSelect;

    @FindBy(xpath = "//select[@class = 'text el_5 __countrySB__']")
    private WebElement countrySelect;

    @FindBy(xpath = "//select[@class = 'text el_17']")
    private WebElement typeSelect;

    @FindBy(xpath = "//input[@class = 'el_18 submit nice_button']")
    private WebElement buttonSearchFilmByNameYearGenreCountryType;

    public SearchFilmNameYearEtcPage(WebDriver driver) {
        super(driver);
    }

    public SearchFilmNameYearEtcPage inputDataForSearchFilmByNameYearGenreCountryType(FilmForSearch filmForSearch) {

        waitVisibilityOfWebElement(findFilmInputField, 5);

        findFilmInputField.sendKeys(filmForSearch.getFilmName());
        releaseYearFilmInputField.sendKeys(filmForSearch.getFilmReleaseYear());

        dropdownSelectByVisibleText(genreSelect, filmForSearch.getFilmGenre());
        dropdownSelectByVisibleText(countrySelect, filmForSearch.getFilmCountry());
        dropdownSelectByVisibleText(typeSelect, filmForSearch.getFilmType());

        return new SearchFilmNameYearEtcPage(driver);
    }

    public SearchResultPage clickButtonSearchFilmByNameYearGenreCountryType() {
        waitVisibilityOfWebElement(buttonSearchFilmByNameYearGenreCountryType, 5);
        buttonSearchFilmByNameYearGenreCountryType.click();
        return new SearchResultPage(driver);
    }

}

package ru.kinopoisk.pages.navigator;


import ru.kinopoisk.models.Film;
import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * See page https://www.kinopoisk.ru/top/navigator/
 */
public class NavigatorPage extends AbstractPage {

    public enum BoxofficeLocation {
        USA, RUSSIA, WORLD

    }

    private static final String NAVIGATOR_PAGE_URL = "https://www.kinopoisk.ru/top/navigator/";

    @FindBy(xpath = ".//*[@class='old_settings']/a")
    private List<WebElement> removeOldSettingsLink;

    @FindBy(id = "genreListTitle")
    private WebElement genreDropDownList;
    @FindBy(id = "genreList")
    private WebElement genreDropDownListBody;

    @FindBy(name = "m_act[genre_and]")
    private WebElement onlySelectedGenresCheckBox;

    @FindBy(id = "exclude_genreListTitle")
    private WebElement excludeGenreDropDownList;
    @FindBy(id = "exclude_genreList")
    private WebElement excludeGenreDropDownListBody;

    @FindBy(id = "countryListTitle")
    private WebElement countryDropDownList;
    @FindBy(id = "countryList")
    private WebElement countryDropDownListBody;

    @FindBy(name = "m_act[country_and]")
    private WebElement onlySelectedCountriesCheckBox;

    @FindBy(id = "exclude_countryList")
    private WebElement excludeCountryDropDownList;

    @FindBy(xpath = ".//*[@class='wide year_select']")
    private WebElement creationYearDropDownList;

    @FindBy(xpath = ".//*[@name='m_act[years][min]']")
    private WebElement creationYearMinDropDownList;
    @FindBy(xpath = ".//*[@name='m_act[years][max]']")
    private WebElement creationYearMaxDropDownList;

    @FindBy(xpath = ".//*[@class='wide year_select_decade']")
    private WebElement creationDecadeDropDownList;

    @FindBy(id = "rating_min")
    private WebElement movieRateMinTextField;
    @FindBy(id = "rating_max")
    private WebElement movieRateMaxTextField;

    @FindBy(id = "ex_rating_min")
    private WebElement movieIMDbRateMinTextField;
    @FindBy(id = "ex_rating_max")
    private WebElement movieIMDbRateMaxTextField;

    @FindBy(id = "tomat_rating_min")
    private WebElement movieCriticsRateMinTextField;
    @FindBy(id = "tomat_rating_max")
    private WebElement movieCriticsRateMaxTextField;

    @FindBy(id = "review_procent_min")
    private WebElement moviePositiveReviewRateMinTextField;
    @FindBy(id = "review_procent_max")
    private WebElement moviePositiveReviewRateMaxTextField;

    @FindBy(id = "min_vote")
    private WebElement movieMinVotesNumberTextField;

    @FindBy(name = "m_act[mpaa]")
    private WebElement MPAARateDropDownList;

    @FindBy(name = "m_act[restriction]")
    private WebElement ageDropDownList;

    @FindBy(name = "m_act[budget][min]")
    private WebElement movieBudgetMinDropDownList;
    @FindBy(name = "m_act[budget][max]")
    private WebElement movieBudgetMaxDropDownList;

    @FindBy(name = "m_act[gross][min]")
    private WebElement movieBoxOfficeMinDropDownList;
    @FindBy(name = "m_act[gross][max]")
    private WebElement movieBoxOfficeMaxDropDownList;
    @FindBy(name = "m_act[gross_type]")
    private WebElement moneyTypeDropDownList;

    @FindBy(id = "is_film")
    private WebElement movieCheckBox;
    @FindBy(id = "is_serial")
    private WebElement serialCheckBox;
    @FindBy(id = "is_mult")
    private WebElement cartoonCheckBox;
    @FindBy(name = "m_act[hide]")
    private WebElement hideWatchedMoviesCheckBox;

    @FindBy(name = "m_act[is_dvd]")
    private WebElement availableOnDVDCheckBox;
    @FindBy(name = "m_act[is_blu]")
    private WebElement availableOnBlurayCheckBox;
    @FindBy(name = "m_act[is_3d]")
    private WebElement available3DReleaseCheckBox;

    @FindBy(xpath = ".//*[@class='nice_button submit']")
    private WebElement searchButton;

    @FindBy(className = "result")
    private WebElement resultsLabel;

    @FindBy(xpath = ".//*[@class='name']/a")
    private WebElement firstResultFromListOfResults;

    public NavigatorPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public NavigatorPage(WebDriver driver) {
        super(driver);
    }

    public void refreshNavigatorPage() {
        driver.navigate().refresh();
    }


    public void openNavigatorPage() {

        driver.get(NAVIGATOR_PAGE_URL);

        if (!removeOldSettingsLink.isEmpty()) {
            new Actions(driver).moveToElement(removeOldSettingsLink.get(0)).click().perform();
        }
    }

    public void selectGenre(String genreName) {

        genreDropDownList.click();

        List<WebElement> listOfElements = genreDropDownListBody.findElements(By.tagName("li"));

        for (WebElement element : listOfElements) {

            WebElement genre = element.findElement(By.tagName("input"));

            if (genre.getAttribute("data-name").equals(genreName)) {
                genre.click();
                genreDropDownList.click();
                return;
            }
        }

        throw new NoSuchElementException("Can't find " + genreName + " in dropdown");
    }

    public void checkOnlySelectedGenres() {
        onlySelectedGenresCheckBox.click();
    }

    public void selectExcludedGenre(String genreToExclude) {

        new Actions(driver).moveToElement(excludeGenreDropDownList).click().perform();

        List<WebElement> listOfElements = excludeGenreDropDownListBody.findElements(By.tagName("li"));

        for (WebElement element : listOfElements) {
            WebElement genre = element.findElement(By.tagName("input"));

            if (genre.getAttribute("data-name").toLowerCase().equals(genreToExclude.toLowerCase())) {
                new Actions(driver).moveToElement(genre).click().perform();
                new Actions(driver).moveToElement(excludeGenreDropDownList).click().perform();
                return;
            }
        }

        throw new NoSuchElementException("Can't find " + genreToExclude + " in dropdown");
    }


    public void selectCountry(String countryName) {

        countryDropDownList.click();

        List<WebElement> listOfElements = countryDropDownListBody.findElements(By.tagName("li"));

        for (WebElement element : listOfElements) {
            WebElement country = element.findElement(By.tagName("input"));

            if (country.getAttribute("data-name").toLowerCase().equals(countryName.toLowerCase())) {
                country.click();
                countryDropDownList.click();
                return;
            }
        }

        throw new NoSuchElementException("Can't find " + countryName + " in dropdown");
    }

    public void checkOnlySelectedCountries() {

        onlySelectedCountriesCheckBox.click();
    }


    public void selectCreationYear(int year) {
        new Select(creationYearDropDownList).selectByValue(String.valueOf(year));
    }


    public void selectYearIntervalFromTo(int minYear, int maxYear) {

        new Select(creationYearMaxDropDownList).selectByValue(String.valueOf(maxYear));
        new Select(creationYearMinDropDownList).selectByValue(String.valueOf(minYear));
    }


    public void setFilmRate(double minRate, double maxRate) {

        movieRateMaxTextField.clear();
        movieRateMaxTextField.sendKeys(String.valueOf(maxRate));

        movieRateMinTextField.clear();
        movieRateMinTextField.sendKeys(String.valueOf(minRate));
    }


    public void setIMDbRate(double minRate, double maxRate) {

        movieIMDbRateMaxTextField.clear();
        movieIMDbRateMaxTextField.sendKeys(String.valueOf(maxRate));

        movieIMDbRateMinTextField.clear();
        movieIMDbRateMinTextField.sendKeys(String.valueOf(minRate));
    }

    public void setCriticsRate(double minRate, double maxRate) {

        movieCriticsRateMaxTextField.clear();
        movieCriticsRateMaxTextField.sendKeys(String.valueOf(maxRate));

        movieCriticsRateMinTextField.clear();
        movieCriticsRateMinTextField.sendKeys(String.valueOf(minRate));
    }


    public void setPositiveReviewPercent(double min, double max) {

        if (min > 100 || min < 1 || max > 100 || max < 1 || min > max)
            throw new ArithmeticException("min and max should be positive numbs from 1 to 100 and max is more or equals min");

        moviePositiveReviewRateMaxTextField.clear();
        moviePositiveReviewRateMaxTextField.sendKeys(String.valueOf(max));

        moviePositiveReviewRateMinTextField.clear();
        moviePositiveReviewRateMinTextField.sendKeys(String.valueOf(min));
    }

    public void setMinNumberOfVotes(int minNumberOfVotes) {

        movieMinVotesNumberTextField.clear();

        /*
        *
        * movieMinVotesNumberTextField has min value which is '10'.
        * As WebDriver inputs digits one by one the first digit
        * is always replaced with '10'. So, for example, to enter the number '1000'
        * into the field the number '100' should be sent
        *
        * */
        movieMinVotesNumberTextField.sendKeys(String.valueOf(minNumberOfVotes / 10));
    }


    public void selectAge(Film.AgeType ageType) {

        Select listOfAgeTypes = new Select(ageDropDownList);

        switch (ageType) {

            case AGE_ANY:
                listOfAgeTypes.selectByValue("");
                break;

            case AGE_0_PLUS:
                listOfAgeTypes.selectByValue("0+");
                break;

            case AGE_6_PLUS:
                listOfAgeTypes.selectByValue("6+");
                break;

            case AGE_12_PLUS:
                listOfAgeTypes.selectByValue("12+");
                break;

            case AGE_16_PLUS:
                listOfAgeTypes.selectByValue("16+");
                break;

            case AGE_18_PLUS:
                listOfAgeTypes.selectByValue("18+");
                break;

        }

    }


    public void setBudget(long from, long to) {

        Select listOfMaxBudgets = new Select(movieBudgetMaxDropDownList);
        listOfMaxBudgets.selectByValue(String.valueOf(to));

        Select listOfMinBudgets = new Select(movieBudgetMinDropDownList);
        listOfMinBudgets.selectByValue(String.valueOf(from));

    }


    public void selectType(Film.FilmType movieType) {

        switch (movieType) {

            case ANY:
                // movie, serial and cartoon check boxes are checked by default
                break;
            case MOVIE:
                serialCheckBox.click();
                cartoonCheckBox.click();
                break;
            case SERIAL:
                movieCheckBox.click();
                cartoonCheckBox.click();
                break;
            case CARTOON:
                movieCheckBox.click();
                serialCheckBox.click();
                break;
        }
    }


    public void checkAvailable3DRelease() {
        available3DReleaseCheckBox.click();
    }

    public void pressSearchButton() {

        waitVisibilityOfWebElement(searchButton, 5);
        searchButton.click();
    }

    public int getResult() {
        waitVisibilityOfWebElement(resultsLabel, 5);

        String resultAsString = resultsLabel.getText().replaceAll("[^0-9]", "").trim();

        if (resultAsString.isEmpty())
            return 0;
        else
            return Integer.parseInt(resultAsString);
    }


    public void openFirstResultFilmPage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstResultFromListOfResults);
    }


    public void selectYearDecade(int decade){
        new Select(creationDecadeDropDownList).selectByValue(String.valueOf(decade));
    }

    public void selectBoxoffice(int min, int max, BoxofficeLocation boxofficeLocation){

        Select location = new Select(moneyTypeDropDownList);
        switch (boxofficeLocation){
            case USA:
                location.selectByValue("domestic");
                break;
            case RUSSIA:
                location.selectByValue("rus");
                break;
            case WORLD:
                location.selectByValue("overseas");
                break;
        }

        new Select(movieBoxOfficeMaxDropDownList).selectByValue(String.valueOf(max));
        new Select(movieBoxOfficeMinDropDownList).selectByValue(String.valueOf(min));
    }


    public void checkAvailableOnDVD(){
        availableOnDVDCheckBox.click();
    }

    public void checkAvailableOnBlueray(){
        availableOnBlurayCheckBox.click();
    }

}

package ru.kinopoisk.pages.afisha.cinemas;

import ru.kinopoisk.models.Film;
import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CinemasPage extends AbstractPage {
    @FindBy(xpath = "//div[@class='header-fresh-user-partial-component__avatar']")
    private WebElement userLogo;

    @FindBy(xpath = "//a[@href='/logout/']")
    private WebElement logutbutton;

    @FindBy(xpath = "//div[@class='selectCountry']")
    private WebElement counriesList;

    @FindBy(xpath = "//li[@onclick='KPCity.setSelectedCountry(2)']")
    private WebElement russia;

    @FindBy(xpath = "//li[@onclick='KPCity.setSelectedCountry(69)']")
    private WebElement belarus;

    @FindBy(xpath = "//div[@onclick='KPCity.buildCityList()']")
    private WebElement citiesList;

    @FindBy(xpath = "//a[@href='/cinemas/city/1/']")
    private WebElement moscow;

    @FindBy(xpath = "//a[@href='/cinemas/city/182/']")
    private WebElement minsk;

    @FindBy(xpath = "//a[@href='/cinemas/city/5003/']")
    private WebElement mogilev;

    @FindBy(xpath = "//span[@class='default']")
    private WebElement metroButton;

    @FindBy(xpath = "//div[@id='metro_select']")
    private WebElement metroStationsList;

    @FindBy(xpath = "//label[not(@style)]//input[@value='171']")
    private WebElement pushkinskaiaMetroStation;

    @FindBy(xpath = "//a[@href='/cinemas/263901/']")
    private WebElement movieHouseAvrora;

    @FindBy(xpath = "//td[@itemprop='address']/span")
    private WebElement movieHouseAddress;

    @FindBy(xpath = "//td[@class='numb']")
    private WebElement movieHousePhone;

    @FindBy(xpath = "//a[@href='#searchForm']")
    private WebElement startNewSearchButton;

    @FindBy(xpath = "//a[@href='/cinemas/mycinema/my/']")
    private WebElement buttonMyMovieHouses;

    @FindBy(xpath = "//div[@id='ui_notice_1']")
    private WebElement noticeShouldLoginBefore;

    @FindBy(xpath = "//b[starts-with(@class, 'add_mycinema')]")
    private WebElement buttonAddCurrentMovieHouse;

    @FindBy(xpath = "//div[@id='ui_notice_2']")
    private WebElement noticeShouldLoginBeforeForCurrentMovieHouse;

    @FindBy(xpath = "//b[@class='add_mycinema_263901']")
    private WebElement buttonAddToFavouriteAvrora;

    @FindBy(xpath = "//b[@class='delete_mycinema_263901']")
    private WebElement buttonDeleteFromFavouriteAvrora;

    @FindBy(xpath = "//b[@class='delete_mycinema_280309']")
    private WebElement buttonDeleteFromFavourite3D;

    @FindBy(xpath = "//b[@class='add_mycinema_280309']")
    private WebElement buttonAddToFavourite3D;

    private By station = By.xpath("//div[@id='metro_select']//input[@type='checkbox']");

    private WebElement quantity;

    private final String CINEMASQTYMOSCOW = "134";
    private final int STATIONQTYMOSCOW = 132;
    private final String CINEMASQTYMINSK = "20";
    private final int STATIONQTYMINSK = 17;
    private final String MOVIEHOUSENAME = "Аврора";
    private final String MOVIEHOUSEADDRESS = "Притыцкого, 23";
    private final String MOVIEHOUSEPHONE = "363-33-60";
    private final String URL = "https://www.kinopoisk.ru/cinemas/";

    public String getMOVIEHOUSENAME() {
        return MOVIEHOUSENAME;
    }

    public String getMOVIEHOUSEADDRESS() {
        return MOVIEHOUSEADDRESS;
    }

    public String getMOVIEHOUSEPHONE() {
        return MOVIEHOUSEPHONE;
    }

    public int getSTATIONQTYMINSK() {
        return STATIONQTYMINSK;
    }

    public int getSTATIONQTYMOSCOW() {
        return STATIONQTYMOSCOW;
    }

    public String getCINEMASQTYMOSCOW() {
        return CINEMASQTYMOSCOW;
    }

    public String getCINEMASQTYMINSK() {
        return CINEMASQTYMINSK;
    }

    public CinemasPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void openPage() {
        driver.get(URL);
    }

    public void openCity(Film.City city) {
        switch (city) {
            case MINSK:
                chooseCity(belarus, minsk);
                quantity = driver.findElement(By.xpath("//div[@class='pagesFromTo left']"));
                break;
            case MOSCOW:
                chooseCity(russia, moscow);
                quantity = driver.findElement(By.xpath("//div[@class='pagesFromTo']"));
                break;
            case MOGILEV:
                chooseCity(belarus, mogilev);
                break;
            default:
                break;
        }
    }

    private void chooseCity(WebElement country, WebElement city) {
        waitVisibilityOfWebElement(counriesList, 5);
        counriesList.click();
        waitVisibilityOfWebElement(country, 5);
        country.click();
        waitVisibilityOfWebElement(citiesList, 5);
        citiesList.click();
        waitVisibilityOfWebElement(city, 5);
        city.click();
    }

    public String getQuantity() {
        driverWait.until(ExpectedConditions.visibilityOf(quantity));
        String[] array = quantity.getText().split(" ");
        return array[array.length - 1];
    }

    public void pressMetroButton() {
        waitVisibilityOfWebElement(metroButton, 2);
        new Actions(driver).moveToElement(metroButton).click().perform();
    }

    public int stationQty() {
        pressMetroButton();
        List<WebElement> webElementList = new ArrayList<>();
        webElementList = driver.findElements(station);
        return webElementList.size();
    }

    public void findPushkinskaiaMetroStation() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pushkinskaiaMetroStation);
    }

    public String getMovieHouseTitle() {
        webDriverWait(movieHouseAvrora, 5);
        return movieHouseAvrora.getText();
    }

    public String getMovieHouseAddress() {
        webDriverWait(movieHouseAddress, 5);
        return movieHouseAddress.getText();
    }

    public String getMovieHousePhone() {
        webDriverWait(movieHouseAddress, 5);
        return movieHousePhone.getText();
    }

    public void openCinemaPage() {
        new Actions(driver).moveToElement(movieHouseAvrora).click().perform();
    }

    public void pressMyMovieHouseButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonMyMovieHouses);
    }

    public boolean isNoticeVisible() {
        return noticeShouldLoginBefore.isDisplayed();
    }

    public void pressAddMovieHouseButton() {
        new Actions(driver).moveToElement(buttonAddCurrentMovieHouse).click().perform();
    }

    public boolean isNoticeVisibleForCurrentMovieHouse() {
        return noticeShouldLoginBeforeForCurrentMovieHouse.isDisplayed();
    }

    public boolean isAddicationSuccessful() {
        waitVisibilityOfWebElement(buttonAddToFavouriteAvrora, 5);
        new Actions(driver).moveToElement(buttonAddToFavouriteAvrora).click().perform();
        return buttonDeleteFromFavouriteAvrora.isDisplayed();
    }

    public boolean isDeletionSuccessful() {
        waitVisibilityOfWebElement(buttonDeleteFromFavourite3D, 5);
        new Actions(driver).moveToElement(buttonDeleteFromFavourite3D).click().perform();
        return buttonAddToFavourite3D.isDisplayed();
    }

    private void webDriverWait(WebElement element, long time) {
        long startTime = System.currentTimeMillis();
        do {
            try {
                if (element.isDisplayed()) {
                    break;
                }
            } catch (StaleElementReferenceException e) {
            }
        } while ((System.currentTimeMillis() - startTime) <= time * 1000);
    }
}
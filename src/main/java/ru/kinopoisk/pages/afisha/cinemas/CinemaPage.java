package ru.kinopoisk.pages.afisha.cinemas;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CinemaPage extends AbstractPage {
    @FindBy(xpath = "//h1[@class='cinema-header__title']")
    private WebElement tittleMovieHouse;

    @FindBy(xpath = "//div[@class='cinema-header__address']")
    private WebElement addressMovieHouse;

    @FindBy(xpath = "//div[@class='cinema-header__metro']")
    private WebElement metroStationMovieHouse;

    @FindBy(xpath = "//span[@class='cinema-about__row-content']/span")
    private WebElement phoneMovieHouse;

    @FindBy(xpath = "//span[@class='cinema-about__row-content']/span/a")
    private WebElement websiteMovieHouse;

    @FindBy(xpath = "//a[@class='tabs__tab cinema-tabs__tab']")
    private WebElement buttonSchedule;

    @FindBy(xpath = "//a[@class='link schedule-film__title']")
    private WebElement scheduleFilms;

    @FindBy(xpath = "//a[@href='/cinemas/']")
    private WebElement backToMovieHouses;

    private final String MOVIEHOUSETITLE = "Аврора";
    private final String MOVIEHOUSEADDRESS = "г. Минск, ул. Притыцкого, 23";
    private final String METROSTATIONHOUSEADDRESS = "м. Пушкинская, Спортивная, Молодёжная";
    private final String PHONEHOUSEADDRESS = "+375 (17) 363-80-12";
    private final String WEBSITEHOUSEADDRESS = "kinominska.by";

    public CinemaPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public String getMOVIEHOUSETITLE() {
        return MOVIEHOUSETITLE;
    }

    public String getMOVIEHOUSEADDRESS() {
        return MOVIEHOUSEADDRESS;
    }

    public String getMETROSTATIONHOUSEADDRESS() {
        return METROSTATIONHOUSEADDRESS;
    }

    public String getPHONEHOUSEADDRESS() {
        return PHONEHOUSEADDRESS;
    }

    public String getWEBSITEHOUSEADDRESS() {
        return WEBSITEHOUSEADDRESS;
    }

    public String getTitle() {
        waitVisibilityOfWebElement(tittleMovieHouse, 3);
        return tittleMovieHouse.getText();
    }

    public String getAddress() {
        return addressMovieHouse.getText();
    }

    public String getMetro() {
        return metroStationMovieHouse.getText();
    }

    public String getPhone() {
        return phoneMovieHouse.getText();
    }

    public String getWebSite() {
        return websiteMovieHouse.getText();
    }

    public void pressButtonSchedule() {
        buttonSchedule.click();
    }

    public boolean isScheduledFilmsExist() {
        waitVisibilityOfWebElement(scheduleFilms, 3);
        return scheduleFilms.isDisplayed();
    }

    public void pressBackToMovieHousesButton() {
        backToMovieHouses.click();
    }
}

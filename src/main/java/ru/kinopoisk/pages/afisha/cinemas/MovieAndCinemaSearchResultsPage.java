package ru.kinopoisk.pages.afisha.cinemas;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MovieAndCinemaSearchResultsPage extends AbstractPage {
    private WebElement movieFound;

    @FindBy(xpath = "//a[@href='#searchForm']")
    private WebElement startNewSearchButton;

    @FindBy(xpath = "//input[@type='button'][@style]")
    private WebElement buttonScheduleSessions;

    private By movie = By.xpath("//tbody/tr[2]/td/select");
    private By movieHouse = By.xpath("//tbody/tr[3]/td/select");
    private By date = By.xpath("//tbody/tr[4]/td/select");

    public MovieAndCinemaSearchResultsPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }


    public void pressButtonScheduleSessoins() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonScheduleSessions);
    }

    public boolean isMovieFound(String xpath) {
        movieFound = driver.findElement(By.xpath(xpath));
        waitVisibilityOfWebElement(movieFound, 3);
        return movieFound.isDisplayed();
    }

    public void pressStartNewSearchButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", startNewSearchButton);
    }

    public void makeChoice(int movie, int movieHouse, int date) {
        Select selectMovie = new Select(driver.findElement(this.movie));
        Select selectMovieHouse = new Select(driver.findElement(this.movieHouse));
        Select selectDate = new Select(driver.findElement(this.date));
        selectMovie.selectByIndex(movie);
        selectMovieHouse.selectByIndex(movieHouse);
        selectDate.selectByIndex(date);
    }
}
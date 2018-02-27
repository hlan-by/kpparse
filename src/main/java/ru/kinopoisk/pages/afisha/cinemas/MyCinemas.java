package ru.kinopoisk.pages.afisha.cinemas;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyCinemas extends AbstractPage {
    @FindBy(xpath = "//a[@href='/cinemas/mycinema/all/']")
    private WebElement allCinemas;

    public MyCinemas(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void pressAllCinemasButton() {
        allCinemas.click();
    }
}
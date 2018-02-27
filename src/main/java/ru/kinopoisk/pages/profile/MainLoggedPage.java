package ru.kinopoisk.pages.profile;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainLoggedPage extends AbstractPage {

    @FindBy (xpath = "//a[@href='/mykp/']")
    private WebElement linkMykp;

    public MainLoggedPage(WebDriver driver) {
        super(driver);
    }

    public MyKpPage goToProfile(){
        linkMykp.click();
        return new MyKpPage(driver);
    }

    public boolean areWeLoggedIn(){
        return linkMykp.isDisplayed();
    }
}

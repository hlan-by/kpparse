package ru.kinopoisk.pages.filmPage;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrailerFilmPage extends AbstractPage {

    private static final String CLOSE_BUTTON_LOCATOR = "//button[@class='discovery-trailers-closer']";
    private static final String TRAILER_INTERFACE_FRAME_LOCATOR = "//iframe[@class='discovery-trailers-iframe']";
    private static final String VIDEO_FRAME_LOCATOR = "//iframe[@id='player']";
    private static final String VIDEO_OBJECT_LOCATOR = "//div[@id='ya-html5-video-player']//video[@id='video']";

    @FindBy(xpath = CLOSE_BUTTON_LOCATOR)
    private WebElement closeTrailerWindowButton;

    @FindBy(xpath = TRAILER_INTERFACE_FRAME_LOCATOR)
    private WebElement videoFrameWithInterface;

    @FindBy(xpath = VIDEO_FRAME_LOCATOR)
    private WebElement videoFrame;

    @FindBy(xpath = VIDEO_OBJECT_LOCATOR)
    private WebElement videoObject;

    public TrailerFilmPage(WebDriver driver) {
        super(driver);
    }

    public boolean isVideoAvaliable() {
        driver.switchTo().frame(videoFrameWithInterface);
        waitForPageLoad(10);
        driver.switchTo().frame(videoFrame);
        waitForPageLoad(10);
        return !driver.findElements(By.xpath(VIDEO_OBJECT_LOCATOR)).isEmpty();
    }

    public FilmPage closeTrailerWindow() {
        waitVisibilityOfWebElement(closeTrailerWindowButton, 10);
        closeTrailerWindowButton.click();
        return new FilmPage(driver);
    }
}

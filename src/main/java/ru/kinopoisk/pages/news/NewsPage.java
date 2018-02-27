package ru.kinopoisk.pages.news;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewsPage extends AbstractPage {

    @FindBy(className = "article__title")
    private WebElement newsPagePostTitle;
    @FindBy(id = "send_me")
    private WebElement commentAddLink;
    @FindBy(id = "subscribe_comment_ok")
    private WebElement subscribeCommentErrorMessageLessWeek;
    @FindBy(xpath = "//a[text()='Новый комментарий...']")
    private WebElement newCommentLink;
    @FindBy(xpath = "//div[text()='К сожалению, оставлять комментарии могут только те пользователи, с момента регистрации которых прошло более одной недели.']")
    private WebElement errorMessageLessAWeek;

    public NewsPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public boolean isCommentAddLinkPresent() {
        if (commentAddLink.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSubscribeCommentErrorMessageLessWeekPresent() {
        if (subscribeCommentErrorMessageLessWeek.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNewCommentLinkExists() {
        if (newCommentLink.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isErrorMessageLessAWeekExists() {
        if (errorMessageLessAWeek.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean clickOnNewCommentLink() {
        newCommentLink.click();
        return true;
    }

    public boolean clickOnCommentAddLink() {
        commentAddLink.click();
        return true;
    }

    public String getNewsPostTitle() {
        return newsPagePostTitle.getText();
    }

}

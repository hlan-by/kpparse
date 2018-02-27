package ru.kinopoisk.pages.profile;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends AbstractPage {

    private final String outboxLinkXpath = "//a[@href='/mykp/outbox/']";
    private final String systemInboxLinkXpath = "//a[@href='/mykp/inbox/system/']";

    @FindBy(css = "a[href='/mykp/sendmessage/']")
    private WebElement newMsgLink;
    @FindBy(css = "a[href='/mykp/inbox/']")
    private WebElement refreshInboxLink;
    @FindBy(css = "ul.messagez")
    private WebElement foldersLinksBlock;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean areAllLinksPresent(){
        if (!foldersLinksBlock.isDisplayed()) return false;
        if ((foldersLinksBlock.findElements(By.xpath(outboxLinkXpath))).size() == 0) return false;
        if ((foldersLinksBlock.findElements(By.xpath(systemInboxLinkXpath))).size() == 0) return false;
        if (!newMsgLink.isDisplayed()) return false;
        if (!refreshInboxLink.isDisplayed()) return false;
        return true;
    }

}

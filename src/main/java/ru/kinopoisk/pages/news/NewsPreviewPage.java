package ru.kinopoisk.pages.news;

import ru.kinopoisk.enums.PageTransitions;
import ru.kinopoisk.pages.AbstractPage;
import ru.kinopoisk.pages.main.QuickNavBarPage;
import ru.kinopoisk.utils.Screenshoter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static ru.kinopoisk.pages.news.NewsPreviewPage.quantityOfMessagesPerPage.*;

public class NewsPreviewPage extends AbstractPage {
    private final String NEWS_PREVIEW_PAGE_URL = "https://www.kinopoisk.ru/news/";
    private final int SEVEN_DAYS_IN_MILLISECONDS = 604_800_000;
    private final String FOLDER_FOR_SCREENSHOTS = "newsPreviewPage";
    private final String WRONG_TAB_NAME_SCREEN = "News_Section_";
    private final String ALL_FILMS_LAST_50_YEARS = "newsCalendarChecker.selectQuantityOfNewsMessagesPerPage(TWO_HUNDRED);";
    @FindBy(xpath = "//h1[text()='Новости']")
    private WebElement mainTitleNewsPreviewPage;
    @FindBy(xpath = "//a[@class='header-partial-component__logo']")
    private WebElement linkToIndexPage;
    @FindBy(xpath = "//span[@class='header-menu-partial-component__item-name'][@data-reactid='10']")
    private WebElement J;
    @FindBy(xpath = "//a[@class='header-auth-partial-component__link'][@data-reactid='19']")
    private WebElement userProfileLink;
    @FindBy(xpath = "//*[@class=\"header-fresh-user-partial-component__dropdown\"]//a[contains(@href,\"user\")]")
    private WebElement userProfileLinkNew;
    @FindBy(xpath = "//*[@class=\"header-fresh-user-partial-component__avatar\"]")
    private WebElement getUserProfileButtonNew;
    @FindBy(xpath = "//*[@data-reactid='25']")
    private WebElement journalLink;
    @FindBy(xpath = "//div[@class='infoUserAuth clearfix']/span[1]")
    private WebElement userRegistrationDate;
    @FindBy(xpath = "//a[contains(text(),'Новости')]")
    private WebElement linkToNewsPreviewPage;
    @FindBy(xpath = "//*[@class='newsList']//*[@class='item']/div[@class='title']//a")
    private WebElement newsPreviewMessage;
    @FindBy(xpath = "//li[@class]/a[contains(text(),'Новости')]")
    private WebElement newsPreviewMessageNew;
    @FindBy(id = "datepicker")
    private WebElement newsCalendar;
    @FindBy(xpath = "//td[@class=' weekday']//a")
    private WebElement dateLinkInNewsCalendar;
    @FindBy(xpath = "//*[@class='arr']//a[text()='»']")
    private WebElement nextPageLink;
    @FindBy(xpath = "//*[@class='navigator_per_page']")
    private WebElement selectQuantityOfNewsMessagesPerPageExist;
    @FindBy(id = "ddCompany")
    private WebElement Select;
    @FindBy(xpath = "//h1//a[@class='continue']")
    private WebElement newsPreviewHomePage;
    @FindBy(xpath = "//*[@class='newsList']//*[@class='item']/div[@class='date']")
    private WebElement dateNewsCreation;
    @FindBy(xpath = "//*[@class='item']")
    private List<WebElement> messagesOnPage;
    private int countInSelectorQuantityOfMessagesPerPage = 0;


    public NewsPreviewPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public boolean open() {
        driver.get(NEWS_PREVIEW_PAGE_URL);
        return true;
    }

    public boolean isNewsPreviewPageExists() {
        if (mainTitleNewsPreviewPage.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isUserProfileLinkNewExists() {
        getUserProfileButtonNew.click();
        try {
            userProfileLinkNew.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserProfileLinkExists() {
        try {
            userProfileLink.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNewsPreviewMessageExists() {
        try {
            newsPreviewMessage.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNewsPreviewMessageExistsNew() {
        journalLink.click();
        try {
            newsPreviewMessageNew.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserregisterNoMoreOneWeekAgo() throws ParseException {
        int userRegisterDateDay = Integer.parseInt(userRegistrationDate.getText().split("\n")[1].split(" ")[0]);
        String userRegisterDateMonth = userRegistrationDate.getText().split("\n")[1].split(" ")[1].trim();
        int userRegisterDateYear = Integer.parseInt(userRegistrationDate.getText().split("\n")[1].split(" ")[2]);
        HashMap<String,String> userRegisterDateMonthFormatted = new HashMap<String, String>();
        userRegisterDateMonthFormatted.put("января","01");
        userRegisterDateMonthFormatted.put("февраля","02");
        userRegisterDateMonthFormatted.put("марта","03");
        userRegisterDateMonthFormatted.put("апреля","04");
        userRegisterDateMonthFormatted.put("мая","05");
        userRegisterDateMonthFormatted.put("июня","06");
        userRegisterDateMonthFormatted.put("июля","07");
        userRegisterDateMonthFormatted.put("августа","08");
        userRegisterDateMonthFormatted.put("сентября","09");
        userRegisterDateMonthFormatted.put("октября","10");
        userRegisterDateMonthFormatted.put("ноября","11");
        userRegisterDateMonthFormatted.put("декабря","12");
        userRegisterDateMonthFormatted.put(null,"00");
        Date now = new Date();
        Date userRegisterDateInDateFormat = new SimpleDateFormat("yyyy/MM/d").parse(userRegisterDateYear + "/" + userRegisterDateMonthFormatted.get(userRegisterDateMonth) + "/" + userRegisterDateDay);
        if (now.getTime() - userRegisterDateInDateFormat.getTime() < SEVEN_DAYS_IN_MILLISECONDS) {
            return true;
        } else {
            return false;
        }
    }

    public boolean makeScreenshot(String commentToCapture) {
        Screenshoter.getInstance().makeScreenshot(driver, FOLDER_FOR_SCREENSHOTS, WRONG_TAB_NAME_SCREEN + commentToCapture);
        return true;
    }

    public boolean isDateLinkInNewsCalendarPresent() {
        if (dateLinkInNewsCalendar.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNewsCalendarExists() {
        if (newsCalendar.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean clickOnNewsPreviewMessage() {
        if (isNewsPreviewMessageExists()) {
            newsPreviewMessage.click();
            return true;
        } else if (isNewsPreviewMessageExistsNew()) {
            newsPreviewMessageNew.click();
            clickOnNewsPreviewMessage();
            return true;
        } else {
            return false;
        }
    }

    public boolean clickOnIndexPage() {
        linkToIndexPage.click();
        return true;
    }

    public boolean clickOnUserProfileLink() {
        if (isUserProfileLinkExists()) {
            userProfileLink.click();
            return true;
        } else if (isUserProfileLinkNewExists()) {
            userProfileLinkNew.click();
            return true;
        } else {
            return false;
        }
    }

    public boolean clickOnNewsPreviewPage() {
        QuickNavBarPage tabNamesPage = new QuickNavBarPage(driver, driverWait);
        tabNamesPage.openPage(PageTransitions.NEWS);
        return true;
    }

    public boolean clickOnDateLinkInNewsCalendar() {
        dateLinkInNewsCalendar.click();
        return true;
    }

    public String getLinkNameOfNewsPreviewMessage() {
        return newsPreviewMessage.getText();
    }

    public boolean isQuantitySelectorPresent() {
        if (selectQuantityOfNewsMessagesPerPageExist.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean selectQuantityOfNewsMessagesPerPage(quantityOfMessagesPerPage quantity) {
        HashMap<quantityOfMessagesPerPage, Integer> quantities = new HashMap<quantityOfMessagesPerPage, Integer>();
        quantities.put(TEN,10);
        quantities.put(TWENTY_FIVE,25);
        quantities.put(FIFTY,50);
        quantities.put(SEVENTY_FIVE,75);
        quantities.put(HUNDRED,100);
        quantities.put(TWO_HUNDRED,200);
        countInSelectorQuantityOfMessagesPerPage = quantities.get(quantity);
        Select selectQuantityOfNewsMessagesPerPage = new Select(selectQuantityOfNewsMessagesPerPageExist);
        selectQuantityOfNewsMessagesPerPage.selectByValue(countInSelectorQuantityOfMessagesPerPage + "");
        return true;
    }

    public boolean isQuantityOfNewsMessagesRight() {
        if (messagesOnPage.size() == countInSelectorQuantityOfMessagesPerPage) {
            return true;
        } else {
            return false;
        }
    }

    public int getNewsPreviewMessagesQuantityByCalendar() {
        return messagesOnPage.size();
    }

    private boolean nextPressPossible() {
        try {
            nextPageLink.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean goToNewsPreviewHomePage() {
        newsPreviewHomePage.click();
        return true;
    }

    public String getDateNewsCreation() {
        return dateNewsCreation.getText();
    }

    public boolean hilightUserRegisterDate() {
        highlightElement(userRegistrationDate);
        return true;
    }

    public int getNewsPreviewMessagesQuantityByDate(String currentDateNewsCreation) {
        int newsPreviewMessagesQuantityByDate = 0;
        do {
            List<WebElement> messagesOnPageBySelectedDate = driver.findElements(By.xpath("//*[@class='newsList']//*[@class='item']/div[@class='date'][text()='" + currentDateNewsCreation + "']"));
            newsPreviewMessagesQuantityByDate += messagesOnPageBySelectedDate.size();
            try {
                nextPageLink.click();
            } catch (Exception e) {
            }
        } while (nextPressPossible());
        return newsPreviewMessagesQuantityByDate;
    }

    public enum quantityOfMessagesPerPage {
        TEN, TWENTY_FIVE, FIFTY, SEVENTY_FIVE, HUNDRED, TWO_HUNDRED
    }
}

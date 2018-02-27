package ru.kinopoisk.tests.news;

import ru.kinopoisk.dataProviders.news.MessagesPerPageDataProvider;
import ru.kinopoisk.models.User;
import ru.kinopoisk.pages.login.LoginPage;
import ru.kinopoisk.pages.news.NewsPage;
import ru.kinopoisk.pages.news.NewsPreviewPage;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;

import static ru.kinopoisk.pages.news.NewsPreviewPage.quantityOfMessagesPerPage.TWO_HUNDRED;

public class TestNewsSmoke extends BeforeAndAfterClass {

    @Test(priority = 200, groups = {"news"})
    public void isNewsPreviewPageExists() {
        NewsPreviewPage newsPreviewPage = new NewsPreviewPage(driver, driverWait);
        newsPreviewPage.open();
        Assert.assertTrue(newsPreviewPage.isNewsPreviewPageExists());
    }

    @Test(priority = 200, dependsOnMethods = "isNewsPreviewPageExists", groups = {"news"})
    public void isNewsPreviewMessageExists() {
        NewsPreviewPage newsMessagePreview = new NewsPreviewPage(driver, driverWait);
        newsMessagePreview.open();
        Assert.assertTrue(newsMessagePreview.isNewsPreviewMessageExists());
    }

    @Test(priority = 200, dependsOnMethods = "isNewsPreviewPageExists", groups = {"news"}, dataProvider = "quantityOfMessages", dataProviderClass = MessagesPerPageDataProvider.class)
    public void isQuantityOfNewsMessagesChanges(NewsPreviewPage.quantityOfMessagesPerPage quantity) {
        NewsPreviewPage quantitySelectorExistenceChecker = new NewsPreviewPage(driver, driverWait);
        quantitySelectorExistenceChecker.open();
        if (quantitySelectorExistenceChecker.isQuantitySelectorPresent()) {
            quantitySelectorExistenceChecker.selectQuantityOfNewsMessagesPerPage(quantity);
            Assert.assertTrue(quantitySelectorExistenceChecker.isQuantityOfNewsMessagesRight());
        } else {
            Assert.assertTrue(quantitySelectorExistenceChecker.isQuantitySelectorPresent());
        }
    }

    @Test(priority = 201, dependsOnMethods = "isNewsPreviewPageExists", groups = {"news"})
    public void isLinkProvideRedirectionOnCurrentNewspage() {
        NewsPreviewPage titleNewsPreviewMessageGetter = new NewsPreviewPage(driver, driverWait);
        NewsPage titleNewsMessageGetter = new NewsPage(driver, driverWait);
        titleNewsPreviewMessageGetter.open();
        String getterTitleNewsPreviewMessage = titleNewsPreviewMessageGetter.getLinkNameOfNewsPreviewMessage();
        titleNewsPreviewMessageGetter.clickOnNewsPreviewMessage();
        Assert.assertTrue(getterTitleNewsPreviewMessage.equals(titleNewsMessageGetter.getNewsPostTitle()));
    }

    @Test(dependsOnMethods = "isNewsPreviewPageExists", groups = {"news"}, priority = 202)
    public void isNewsCalendarWorks() {
        NewsPreviewPage newsCalendarChecker = new NewsPreviewPage(driver, driverWait);
        newsCalendarChecker.open();
        newsCalendarChecker.selectQuantityOfNewsMessagesPerPage(TWO_HUNDRED);
        if (newsCalendarChecker.isNewsCalendarExists()) {
            if (newsCalendarChecker.isDateLinkInNewsCalendarPresent()) {
                newsCalendarChecker.clickOnDateLinkInNewsCalendar();
                int newsPreviewMessagesQuantityByCalendar = newsCalendarChecker.getNewsPreviewMessagesQuantityByCalendar();
                String dateNewsCreation = newsCalendarChecker.getDateNewsCreation();
                newsCalendarChecker.goToNewsPreviewHomePage();
                int newsPreviewMessagesQuantityByCalculation = newsCalendarChecker.getNewsPreviewMessagesQuantityByDate(dateNewsCreation);
                Assert.assertTrue(newsPreviewMessagesQuantityByCalendar == newsPreviewMessagesQuantityByCalculation);
            } else {
                Assert.assertTrue(newsCalendarChecker.isDateLinkInNewsCalendarPresent());
            }
        } else {
            Assert.assertTrue(newsCalendarChecker.isNewsCalendarExists());
        }
    }

    @Test(groups = {"news"}, priority = 201)
    public void subscribeToNewComments() {
        LoginPage logInPage = new LoginPage(driver, driverWait);
        NewsPreviewPage newsSelector = new NewsPreviewPage(driver, driverWait);
        NewsPage commentNewsChecker = new NewsPage(driver, driverWait);
        newsSelector.open();
        newsSelector.clickOnIndexPage();
        User user = new User(USER_NAME, USER_PASWORD);
        logInPage.login(user);
        newsSelector.clickOnNewsPreviewPage();
        newsSelector.isNewsPreviewMessageExists();
        newsSelector.clickOnNewsPreviewMessage();
        commentNewsChecker.isCommentAddLinkPresent();
        commentNewsChecker.clickOnCommentAddLink();
        Assert.assertTrue(commentNewsChecker.isSubscribeCommentErrorMessageLessWeekPresent());
        logInPage.logOff();
    }

    @Test(groups = {"news"}, priority = 201)
    public void isImpossibleLeaveComment() throws ParseException {
        LoginPage logInPage = new LoginPage(driver, driverWait);
        NewsPreviewPage newsSelector = new NewsPreviewPage(driver, driverWait);
        NewsPage commentCreateChecker = new NewsPage(driver, driverWait);
        newsSelector.open();
        newsSelector.clickOnIndexPage();
        User user = new User(USER_NAME, USER_PASWORD);
        logInPage.login(user);
        if (newsSelector.isUserProfileLinkExists() || newsSelector.isUserProfileLinkNewExists()) {
            Assert.assertTrue(newsSelector.clickOnUserProfileLink());
        }
        newsSelector.hilightUserRegisterDate();
        newsSelector.makeScreenshot("Date_of_register");
        Assert.assertTrue(newsSelector.isUserregisterNoMoreOneWeekAgo());
        newsSelector.clickOnNewsPreviewPage();
        if (newsSelector.isNewsPreviewMessageExists() || (newsSelector.isNewsPreviewMessageExistsNew())) {
            Assert.assertTrue(newsSelector.clickOnNewsPreviewMessage());
        }
        commentCreateChecker.isNewCommentLinkExists();
        commentCreateChecker.clickOnNewCommentLink();
        Assert.assertTrue(commentCreateChecker.isErrorMessageLessAWeekExists());
        logInPage.logOff();
    }
}


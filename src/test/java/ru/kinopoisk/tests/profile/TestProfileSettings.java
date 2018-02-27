package ru.kinopoisk.tests.profile;

import ru.kinopoisk.pages.filmPage.FilmPage;
import ru.kinopoisk.pages.profile.*;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import ru.kinopoisk.utils.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestProfileSettings extends BeforeAndAfterClass {

    private MainLoggedPage mainLoggedPage;
    private MyKpPage myKpPage;
    private HistoryPage historyPage;
    private FilmPage filmPage;
    private InboxPage inboxPage;

    private Data data = new Data();


    @Test(priority = 800, description = "Login and make sure we are logged in")
    public void areWeLoggedOn(){
        Login login = new Login();
        mainLoggedPage = login.login(driver, driverWait, data.getUser());
        Assert.assertTrue(mainLoggedPage.areWeLoggedIn());
    }

    @Test(priority = 802, description = "Make sure all the elements are on the page", dependsOnMethods = "areWeLoggedOn")
    public void formsPresent(){
        myKpPage = mainLoggedPage.goToProfile();
        myKpPage.waitForPageLoad(5);
        Assert.assertTrue(myKpPage.areElementsPresent());
    }

    @Test(priority = 804, description = "All the elements are filled correctly", dependsOnMethods = "formsPresent")
    public void formsFilledCorrectly(){
        myKpPage.fillAllProfile(data);
        myKpPage.submitChanges();
        myKpPage.waitForPageLoad(5);
        Assert.assertTrue( myKpPage.verifyAllProfile(data));
    }

    @Test(priority = 806, description = "Negative test, too many symbols to AboutMyself field", dependsOnMethods = "formsFilledCorrectly")
    public void aboutMyselfNegative(){
        myKpPage.fillAboutMyselfWithTooManySymbols();
        myKpPage.submitChanges();
        Assert.assertTrue( myKpPage.isMsgPopsUp());
    }

    @Test(priority = 808, description = "Check if links present and correct", dependsOnMethods = "aboutMyselfNegative")
    public void linksPresentInHistory(){
        historyPage = myKpPage.goToHistory();
        historyPage.waitForPageLoad(5);
        Assert.assertTrue( historyPage.areLinksPresent()&&historyPage.areLinksCorrect());
    }

    @Test(priority = 810, description = "Check if visited movie present in history", dependsOnMethods = "linksPresentInHistory")
    public void moviePresentInHistory(){
        filmPage = historyPage.searchAMovie(Data.getRndMovieName());
        filmPage.waitForPageLoad(5);
        String filmName = filmPage.getFilmInfo().getFilmName();
        driver.navigate().back();
        historyPage.waitForPageLoad(5);
        Assert.assertTrue(historyPage.isLastVisitedMovieInTheList(filmName));
    }

    @Test(priority = 812, description = "Check links present on Inbox page", dependsOnMethods = "moviePresentInHistory")
    public void linksPresentInInbox(){
        inboxPage = historyPage.goToMessages();
        Assert.assertTrue(inboxPage.areAllLinksPresent());
    }

}

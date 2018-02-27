package ru.kinopoisk.tests.navigator;


import ru.kinopoisk.pages.navigator.NavigatorPage;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Aliaksei_Kurbatau on 2/20/2018.
 */
public class NavigatorNegativeTest extends BeforeAndAfterClass {

    /**
     * Link to testcase in JIRA - https://jira.epam.com/jira/browse/EPMFARMATS-1917
     */
    @Test(groups = {"navigator"}, priority = 351)
    public void searchFor3DReleaseDated1950thTest() {

        String genre = "ужасы";
        int movieCreationYear = 1955;

        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.openNavigatorPage();
        navigatorPage.refreshNavigatorPage();

        navigatorPage.selectGenre(genre);
        navigatorPage.selectCreationYear(movieCreationYear);
        navigatorPage.checkAvailable3DRelease();

        Assert.assertEquals(navigatorPage.getResult(), 0);
    }

}

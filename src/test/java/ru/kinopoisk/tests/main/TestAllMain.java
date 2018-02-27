package ru.kinopoisk.tests.main;

import ru.kinopoisk.beans.main.BoxOfficeBean;
import ru.kinopoisk.dataProviders.main.QuickNavBarDataProvider;
import ru.kinopoisk.dataProviders.main.SideNavBarDataProvider;
import ru.kinopoisk.enums.PageTransitions;
import ru.kinopoisk.enums.TabNames;
import ru.kinopoisk.pages.main.BoxOfficeOnSideNavBarPage;
import ru.kinopoisk.pages.main.MainPage;
import ru.kinopoisk.pages.main.QuickNavBarPage;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestAllMain extends BeforeAndAfterClass {

    @Test(groups = {"main"}, priority = 100)
    public void openIndexPage() {
        MainPage mainPage = new MainPage(driver, driverWait);
        Assert.assertTrue(mainPage.open(), "The site is not kinopoisk.ru!");
    }

    @Test(groups = {"main"}, dataProvider = "tabNamesDataProvider", dataProviderClass = QuickNavBarDataProvider.class,
            dependsOnMethods = "openIndexPage", priority = 101)
    public void testQuickNavigationBarTabNames(TabNames tabName) {
        QuickNavBarPage tabNamesPage = new QuickNavBarPage(driver, driverWait);
        Assert.assertTrue(tabNamesPage.isTabWithNameExist(tabName), "Wrong tab name: " + tabName.getRusName());
    }

    @Test(groups = {"main"}, dataProvider = "linkOnTabDataProvider", dataProviderClass = QuickNavBarDataProvider.class,
            dependsOnMethods = "openIndexPage", priority = 101)
    public void testQuickNavigationBarDropDown(PageTransitions pageTransitions) {
        QuickNavBarPage tabNamesPage = new QuickNavBarPage(driver, driverWait);
        Assert.assertTrue(tabNamesPage.isLinkOnTab(pageTransitions), "Wrong link: " + pageTransitions.getLinkRusName());
    }

    @Test(groups = {"main"}, dependsOnMethods = "openIndexPage", dataProvider = "boxOfficeDataProvider",
            dataProviderClass = SideNavBarDataProvider.class, priority = 102)
    public void testSideNavPanelBoxOffice(BoxOfficeBean boxOfficeBean) {
        MainPage indexPage = new MainPage(driver, driverWait);
        indexPage.open();
        BoxOfficeOnSideNavBarPage boxOfficePage = new BoxOfficeOnSideNavBarPage(driver, driverWait);
        Assert.assertTrue(boxOfficePage.isContains(boxOfficeBean), "Wrong panel - " + boxOfficeBean.getTextInHeader());
    }
}

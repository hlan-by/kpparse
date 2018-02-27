package ru.kinopoisk.tests.filmPage;

import ru.kinopoisk.models.FilmNote;
import ru.kinopoisk.models.User;
import ru.kinopoisk.dataProviders.filmPage.FilmFoldersProdiver;
import ru.kinopoisk.pages.filmPage.FilmPage;
import ru.kinopoisk.pages.login.LoginPage;
import ru.kinopoisk.pages.main.MainPage;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAuthorisedUserFilmPage extends BeforeAndAfterClass {
    private final String HOME_URL = "https://www.kinopoisk.ru/film/kartochnyy-domik-2013-581937/";
    private final String LOGIN = "ATwarrior";
    private final String PASSWORD = "Password";

    @Test(groups = {"filmPageAuthorised"}, priority = 700)
    public void loginTest() {
        User user = new User(LOGIN, PASSWORD);
        MainPage indexPage = new MainPage(driver, driverWait);
        indexPage.open();
        LoginPage loginPage = new LoginPage(driver, driverWait);
        loginPage.login(user);
        Assert.assertTrue(loginPage.isLogin(), "User isn't logged in");
    }

    @Test(groups = {"filmPageAuthorised"}, dependsOnMethods = "loginTest", priority = 701)
    public void viewedFilmCheckTest() {
        FilmPage page = new FilmPage(driver).open(HOME_URL).clickStatusViewedButton();
        boolean isFilmViewed = page.isFilmViewed();
        Assert.assertTrue(isFilmViewed, "Checking 'Viewed' status is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dependsOnMethods = {"loginTest", "viewedFilmCheckTest"}, priority = 702)
    public void viewedFilmUnCheckTest() {
        FilmPage page = new FilmPage(driver).open(HOME_URL).clickStatusViewedButton();
        boolean isFilmViewed = page.isFilmViewed();
        Assert.assertFalse(isFilmViewed,"Unchecking 'Viewed' status is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dependsOnMethods = "loginTest", priority = 703)
    public void createNoteTest() {
        FilmNote note = new FilmNote();
        FilmPage page = new FilmPage(driver).open(HOME_URL).createNote(note);
        boolean isNoteCreated = page.isNoteExist();
        Assert.assertTrue(isNoteCreated,"Adding note is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dependsOnMethods = {"loginTest", "createNoteTest"}, priority = 704)
    public void deleteNoteTest() {
        FilmPage page = new FilmPage(driver).open(HOME_URL).deleteNote();
        boolean isNoteDeleted = page.isNoteExist();
        Assert.assertFalse(isNoteDeleted,"Deleting note is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dependsOnMethods = "loginTest", priority = 705)
    public void addToFavouritesTest() {
        FilmPage page = new FilmPage(driver).open(HOME_URL).clickFavouriteFilmButton();
        boolean isFilmFavouriteChecked = page.isFilmFavourite();
        boolean isFilmViewedChecked = page.isFilmViewed();
        Assert.assertTrue(isFilmFavouriteChecked && isFilmViewedChecked,"Checking 'Favourite' status is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dependsOnMethods = {"loginTest", "addToFavouritesTest"}, priority = 706)
    public void deleteFromFavouritesTest() {
        FilmPage page = new FilmPage(driver).open(HOME_URL).clickFavouriteFilmButton();
        boolean isFilmFavouriteChecked = page.isFilmFavourite();
        Assert.assertFalse(isFilmFavouriteChecked,"Unchecking 'Favourite' status is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dependsOnMethods = "loginTest", priority = 707)
    public void setUserRatingTest() {
        FilmPage page = new FilmPage(driver).open(HOME_URL).setRating("5");
        boolean isRatingSet = page.isUserRatingExist();
        boolean isFilmViewedChecked = page.isFilmViewed();
        Assert.assertTrue(isRatingSet && isFilmViewedChecked,"Setting film rating is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dependsOnMethods = {"loginTest", "setUserRatingTest"}, priority = 708)
    public void deleteUserRatingTest() {
        FilmPage page = new FilmPage(driver).open(HOME_URL).deleteRating();
        boolean isRatingSet = page.isUserRatingExist();
        Assert.assertFalse(isRatingSet,"Deleting film rating is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dataProvider = "filmFolders", dataProviderClass = FilmFoldersProdiver.class, dependsOnMethods = {"loginTest"}, priority = 709)
    public void addFilmToFoldersTest(String filmFolder) {
        FilmPage page = new FilmPage(driver).open(HOME_URL).openDropdownFolderMenu().selectPointInDropdown(" " + filmFolder).closeDropdownFolderMenu();
        boolean isFilmInFolder = page.isFilmInFolder(filmFolder);
        Assert.assertTrue(isFilmInFolder,"Adding film to folder is not working for authorised user");
    }

    @Test(groups = {"filmPageAuthorised"}, dataProvider = "filmFolders", dataProviderClass = FilmFoldersProdiver.class, dependsOnMethods = {"loginTest", "addFilmToFoldersTest"}, priority = 710)
    public void deleteFilmFromFoldersTest(String filmFolder) {
        FilmPage page = new FilmPage(driver).open(HOME_URL).openDropdownFolderMenu().selectPointInDropdown(" " + filmFolder).closeDropdownFolderMenu();
        boolean isFilmInFolder = page.isFilmInFolder(filmFolder);
        Assert.assertFalse(isFilmInFolder,"Deleting film from folder is not working for authorised user");
    }

}

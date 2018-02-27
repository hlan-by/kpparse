package ru.kinopoisk.tests.filmPage;

import ru.kinopoisk.dataProviders.filmPage.FilmFoldersProdiver;
import ru.kinopoisk.models.FilmNote;
import ru.kinopoisk.models.FilmReview;
import ru.kinopoisk.pages.filmPage.FilmPage;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNonAuthorisedUserFilmPage extends BeforeAndAfterClass {
    private final String HOME_URL = "https://www.kinopoisk.ru/film/kartochnyy-domik-2013-581937/";

    @Test(groups = {"filmPageUnauthorised"}, priority = 713)
    public void viewedFilmCheckTest() {
        StringBuilder errorBuffer = new StringBuilder();
        FilmPage page = new FilmPage(driver).open(HOME_URL).clickStatusViewedButton();
        try {
            boolean isFilmViewed = page.isFilmViewed();
            Assert.assertFalse(isFilmViewed, "Button 'Already viewed' is enabled for unauthorised users");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage() + "\n");
        }
        try {
            String expectedError = "Для того чтобы поставить пометку о просмотре необходимо авторизоваться...";
            String popupError = page.getPopupErrorText();
            Assert.assertEquals(popupError, expectedError, "Invalid popup error");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage());
        }

        if (errorBuffer.length() > 0) {
            throw new AssertionError(errorBuffer.toString());
        }
    }

    @Test(groups = {"filmPageUnauthorised"}, priority = 714)
    public void createNoteTest() {
        StringBuilder errorBuffer = new StringBuilder();

        FilmNote note = new FilmNote();
        FilmPage page = new FilmPage(driver).open(HOME_URL).createNote(note);

        try {
            turnOffImplicitWaits(driver);
            boolean isNoteCreated = page.isNoteExist();
            turnOnImplicitWaits(driver);
            Assert.assertFalse(isNoteCreated, "Button 'Create note' is enabled for unauthorised users");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage() + "\n");
        }
        try {
            String expectedError = "Для добавления примечания к сериалу необходимо авторизоваться...";
            String popupError = page.getPopupErrorText();
            Assert.assertEquals(popupError, expectedError, "Invalid popup error");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage());
        }

        if (errorBuffer.length() > 0) {
            throw new AssertionError(errorBuffer.toString());
        }
    }

    @Test(groups = {"filmPageUnauthorised"}, priority = 715)
    public void addToFavouritesTest() {
        StringBuilder errorBuffer = new StringBuilder();
        FilmPage page = new FilmPage(driver).open(HOME_URL).clickFavouriteFilmButton();

        try {
            boolean isFilmFavouriteChecked = page.isFilmFavourite();
            boolean isFilmViewedChecked = page.isFilmViewed();
            Assert.assertFalse(isFilmFavouriteChecked && isFilmViewedChecked, "Button 'Add to favourite' is enabled for unauthorised users");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage() + "\n");
        }
        try {
            String expectedError = "Для того чтобы добавить сериал в список любимых, необходимо авторизоваться...";
            String popupError = page.getPopupErrorText();
            Assert.assertEquals(popupError, expectedError, "Invalid popup error");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage());
        }

        if (errorBuffer.length() > 0) {
            throw new AssertionError(errorBuffer.toString());
        }
    }

    @Test(groups = {"filmPageUnauthorised"}, priority = 716)
    public void setUserRatingTest() {
        StringBuilder errorBuffer = new StringBuilder();
        FilmPage page = new FilmPage(driver).open(HOME_URL).setRating("5");

        try {
            boolean isRatingSet = page.isUserRatingExist();
            boolean isFilmViewedChecked = page.isFilmViewed();
            Assert.assertFalse(isRatingSet && isFilmViewedChecked, "Setting a rating is enabled for unauthorised users");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage() + "\n");
        }
        try {
            String expectedError = "Для голосования необходимо авторизоваться...";
            String popupError = page.getPopupErrorText();
            Assert.assertEquals(popupError, expectedError, "Invalid popup error");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage());
        }

        if (errorBuffer.length() > 0) {
            throw new AssertionError(errorBuffer.toString());
        }
    }

    @Test(groups = {"filmPageUnauthorised"}, dataProvider = "filmFolders", dataProviderClass = FilmFoldersProdiver.class, priority = 717)
    public void addFilmToFoldersTest(String filmFolder) {
        StringBuilder errorBuffer = new StringBuilder();
        FilmPage page = new FilmPage(driver).open(HOME_URL).openDropdownFolderMenu().selectPointInDropdown(" " + filmFolder).closeDropdownFolderMenu();

        try {
            turnOffImplicitWaits(driver);
            boolean isFilmInFolder = page.isFilmInFolder(filmFolder);
            turnOnImplicitWaits(driver);
            Assert.assertFalse(isFilmInFolder, "Adding film to folders is enabled for unauthorised users");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage() + "\n");
        }
        try {
            String expectedError = "Для использования сервиса «Мои фильмы» необходимо авторизоваться...";
            String popupError = page.getPopupErrorText();
            Assert.assertEquals(popupError, expectedError, "Invalid popup error");
        } catch (AssertionError e) {
            errorBuffer.append(e.getMessage());
        }

        if (errorBuffer.length() > 0) {
            throw new AssertionError(errorBuffer.toString());
        }
    }

    @Test(groups = {"filmPageUnauthorised"}, priority = 718)
    public void addReviewTest() {
        FilmReview review = new FilmReview();
        FilmPage page = new FilmPage(driver).open(HOME_URL);
        boolean isReviewFormAvaliable = page.isReviewFormAvaliable();
        Assert.assertFalse(isReviewFormAvaliable, "Writing a review is enabled for unauthorised users");
    }
}

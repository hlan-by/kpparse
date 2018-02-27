package ru.kinopoisk.tests.filmPage;

import ru.kinopoisk.dataProviders.filmPage.ReviewProvider;
import ru.kinopoisk.pages.filmPage.FilmPage;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCommonFilmPage extends BeforeAndAfterClass {
    private final String HOME_URL = "https://www.kinopoisk.ru/film/kak-priruchit-drakona-2-2014-512883/";

    @Test(groups = {"filmPage"}, priority = 711)
    public void trailerBlockTest() {
        boolean isVideoAvaliable = new FilmPage(driver).open(HOME_URL).openTrailerWindow().isVideoAvaliable();
        Assert.assertTrue(isVideoAvaliable,"Video block is not available on trailer page");
    }

    @Test(groups = {"filmPage"}, dataProviderClass = ReviewProvider.class, dataProvider = "reviewTypes", priority = 712)
    public void reviewsSortTest(String type){
        FilmPage page = new FilmPage(driver).open(HOME_URL).openReviewsType(type);
        boolean isReviewsEqual = page.isSortReviewsEqualToType(type);
        Assert.assertTrue(isReviewsEqual,"Incorrect sorting of '" + type + "' reviews on film page");
    }
}

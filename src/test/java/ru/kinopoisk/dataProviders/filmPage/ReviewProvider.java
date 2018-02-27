package ru.kinopoisk.dataProviders.filmPage;

import org.testng.annotations.DataProvider;

public class ReviewProvider {
    @DataProvider(name = "reviewTypes")
    public Object[][] reviewTypesProvider() {
        return new Object[][]{
                {"pos"},
                {"neg"},
                {"neut"}};
    }
}

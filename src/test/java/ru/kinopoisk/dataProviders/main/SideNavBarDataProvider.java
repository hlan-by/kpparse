package ru.kinopoisk.dataProviders.main;

import ru.kinopoisk.beans.main.BoxOfficeBean;
import org.testng.annotations.DataProvider;

public class SideNavBarDataProvider {
    @DataProvider(name = "boxOfficeDataProvider")
    public Object[][] boxOfficeDataProvider() {
        return new Object[][]{
                {new BoxOfficeBean("Кинокасса России", "руб.", "/box/")},
                {new BoxOfficeBean("Кинокасса США", "$", "/box/type/usa/")}
        };
    }
}
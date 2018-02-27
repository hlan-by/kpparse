package ru.kinopoisk.dataProviders.main;

import ru.kinopoisk.enums.PageTransitions;
import ru.kinopoisk.enums.TabNames;
import org.testng.annotations.DataProvider;

public class QuickNavBarDataProvider {

    @DataProvider(name = "tabNamesDataProvider")
    public Object[][] tabNamesDataProvider() {
        return new Object[][]{
                {TabNames.AFISHA},
                {TabNames.JOURNAL},
                {TabNames.FIMLS},
                {TabNames.RATINGS}
        };
    }

    @DataProvider(name = "linkOnTabDataProvider")
    public Object[][] linkOnTabDataProvider() {
        int NumberOfColumns = 1;
        Object object[][] = new Object[PageTransitions.values().length][NumberOfColumns];
        int pageNumber = 0;
        for (PageTransitions page : PageTransitions.values()) {
            object[pageNumber][NumberOfColumns - 1] = page;
            pageNumber++;
        }
        return object;
    }

}

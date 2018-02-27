package ru.kinopoisk.dataProviders.filmPage;

import org.testng.annotations.DataProvider;

public class FilmFoldersProdiver {

    @DataProvider(name = "filmFolders")
    public Object[][] filmFolders() {
        return new Object[][]{
                {"Буду смотреть"},
                {"Избранное"},
                {"Смотреть в кино"},
                {"Найти в Интернете"},
                {"Купить на DVD"},
                {"Смотреть на ТВ"},
               // {"Любимые фильмы"}
        };
    }
}

package ru.kinopoisk.pages.main;

import ru.kinopoisk.utils.Screenshoter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPageWithoutLogin {

    private final String INDEX_PAGE_URL = "https://www.kinopoisk.ru";
    private final String KINOPOISK_TITLE = "КиноПоиск — Все фильмы планеты";
    private final By BY_LINK_IN_FOOTER = By.xpath("//*[@id=\"partial_component__footer\"]//*[@class=\"footer-v2-partial-component__copyright\"]//a");
    private final String ATTRIBUTE_NAME = "href";
    private final By BY_TEXT_ON_MAIN_PAGE = By.xpath("//td[@id=\"main_editorial\"]//span[@class=\"title\"]");
    private final String TEXT_ON_MAIN_PAGE = "Главное";

    private final String FOLDER_FOR_SCREENSHOTS = "main";
    private final String WRONG_MAIN_PAGE_SCREEN = "Main page is not a kinopoisk.ru";

    public MainPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public boolean open() {
        driver.get(INDEX_PAGE_URL);
        return isKinopoiskRu();
    }

    private boolean isKinopoiskRu() {
        if (driver.getTitle().equals(KINOPOISK_TITLE)) {
            if (driver.findElement(BY_LINK_IN_FOOTER).getAttribute(ATTRIBUTE_NAME).contains(INDEX_PAGE_URL)) {
                if (driver.findElement(BY_TEXT_ON_MAIN_PAGE).getText().equals(TEXT_ON_MAIN_PAGE)) {
                    return true;
                }
            }
        }

        Screenshoter.getInstance().makeScreenshot(driver, FOLDER_FOR_SCREENSHOTS, WRONG_MAIN_PAGE_SCREEN);
        return false;
    }
}

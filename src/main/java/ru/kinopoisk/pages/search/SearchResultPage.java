package ru.kinopoisk.pages.search;

import ru.kinopoisk.pages.AbstractPage;
import ru.kinopoisk.utils.search.data.FilmForSearch;
import ru.kinopoisk.utils.search.data.PersonForSearch;
import ru.kinopoisk.utils.search.data.UserForSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends AbstractPage {

    private WebElement showAll;
    private WebElement goNextPageLink;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    private boolean checkXpathOnPage(String xpath) {
        return checkElementDisplayed(xpath);
    }

    private boolean checkElementDisplayed(String xpath) {
        try {
            WebElement webElement = driver.findElement(By.xpath(xpath));
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean showAllClickable() {
        try {
            showAll = driver.findElement(
                    By.xpath("//p[@class = 'show_all']//a[contains(text(), 'показать все')]"));
            waitVisibilityOfWebElement(showAll, 2);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean goNextPageLinkClickable() {
        try {
            goNextPageLink = driver.findElement(
                    By.xpath("//li[@class='arr']//a[text()='»']"));
            waitVisibilityOfWebElement(goNextPageLink, 2);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void checkLinkAndGoNextPage(String xpath) {
        if (showAllClickable()) {
            showAll.click();
        }
        while (goNextPageLinkClickable()) {
            if (!checkXpathOnPage(xpath)) {
                goNextPageLink.click();
            }
        }
    }

    private boolean checkFilmSearchResultOnPage(FilmForSearch filmForSearch) {
        String xpathFilmName = "//div[@id= 'headerFilm']//*[contains(text(), '" +
                filmForSearch.getFilmName() + "')]";
        String xpathForFilmLink = "//a[@data-url = '" + filmForSearch.getFilmPath() + "']";

        return checkXpathOnPage(xpathForFilmLink) || checkXpathOnPage(xpathFilmName);
    }

    public boolean checkFilmSearchResult(FilmForSearch filmForSearch) {
        String xpathForFilmLink = "//a[@data-url = '" + filmForSearch.getFilmPath() + "']";
        checkLinkAndGoNextPage(xpathForFilmLink);
        return checkFilmSearchResultOnPage(filmForSearch);
    }

    private boolean checkPersonSearchResultOnPage (PersonForSearch personForSearch) {
        String xpathPersonName = "//div[@id = 'headerPeople']//*[.= '" + personForSearch.getName() + "']";
        String xpathForPersonLink = "//a[@data-url = '" + personForSearch.getPersonURL() + "']";

        return checkXpathOnPage(xpathPersonName) || checkXpathOnPage(xpathForPersonLink);

    }

    public boolean checkPersonSearchResult (PersonForSearch personForSearch){
        String xpathForPersonLink = "//a[@data-url = '" + personForSearch.getPersonURL() + "']";
        checkLinkAndGoNextPage(xpathForPersonLink);
        return checkPersonSearchResultOnPage(personForSearch);
    }

    private boolean checkUserSearchResultOnPage(UserForSearch userForSearch) {
        String xpathSearchUserName = "//div[@class = 'profile_header clearfix']//*[.='"
                + userForSearch.getUserLogin() + " ']";
        String xpathSearchUserLink = "//a[@href = '" + userForSearch.getUserLink() + "']";

        return checkXpathOnPage(xpathSearchUserName) || checkXpathOnPage(xpathSearchUserLink);

    }

    public boolean checkUserSearchResult(UserForSearch userForSearch) {
        String xpathSearchUserName = "//div[@class = 'profile_header clearfix']//*[.='"
                + userForSearch.getUserLogin() + " ']";
        checkLinkAndGoNextPage(xpathSearchUserName);
        return checkUserSearchResultOnPage(userForSearch);
    }

}

package ru.kinopoisk.pages.profile;

import ru.kinopoisk.pages.AbstractPage;
import ru.kinopoisk.pages.filmPage.FilmPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HistoryPage extends AbstractPage {

    private final int LINKS_NUMBER = 3;
    private final String LINK_PEOPLE = "/mykp/history/list/type_history/people/";
    private final String LINK_SEARCH = "/mykp/history/list/type_history/search/";
    private final String xpathToMovieName = "//td/b/a";

    @FindBy(xpath = "//ul[@class='historyType']/li")
    private List<WebElement> linksInBlock;
    @FindBy(xpath = "//ul[@class='historyType']/li/a")
    private List<WebElement> activeLinks;
    @FindBy(xpath = "//form/input[@name='kp_query']")
    private WebElement searchField;
    @FindBy(xpath = "//div[@class='header-search-suggest-partial-component__content']")
    private List<WebElement> moviesFoundInDropDownList;
    @FindBy(xpath = "//form//div/table/tbody/tr")
    private List<WebElement> moviesVisited;
    @FindBy(css = "a[href='/mykp/messages/']")
    private WebElement tabMessages;

    public HistoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean areLinksPresent(){
       return (linksInBlock.size()==LINKS_NUMBER)&&(activeLinks.size()==LINKS_NUMBER-1);
    }

    public boolean areLinksCorrect(){
        boolean result = true;
        for (WebElement link: activeLinks) {
            String linkText = link.getAttribute("href");
            result &= ((linkText.contains(LINK_SEARCH))||(linkText.contains(LINK_PEOPLE)));
        }
        return result;
    }

    public FilmPage searchAMovie(String movieToSearch){
        searchField.click();
        searchField.sendKeys(movieToSearch);
        moviesFoundInDropDownList.get(0).click();
        return new FilmPage(driver);
    }

    public boolean isMoviesVisitedListNotEmpty(){
        return !moviesVisited.isEmpty();
    }

    public boolean isLastVisitedMovieInTheList(String visitedMovieName){
        if (isMoviesVisitedListNotEmpty()) {
            for (WebElement movieElement :
                    moviesVisited) {
                String movieName = movieElement.findElement(By.xpath(xpathToMovieName)).getText();
                if (movieName.equalsIgnoreCase(visitedMovieName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public InboxPage goToMessages(){
        tabMessages.click();
        return new InboxPage(driver);
    }

}

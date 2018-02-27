package ru.kinopoisk.pages.search.parts;

import ru.kinopoisk.pages.search.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchFilmByKeywordsPage extends AbstractSearchPage {

    @FindBy(xpath = "//input[@id = 'search_field_1']")
    private WebElement firstKeywordInputField;

    @FindBy(xpath = "//input[@id = 'search_field_2']")
    private WebElement secondKeywordInputField;

    @FindBy(xpath = "//input[@id = 'search_field_3']")
    private WebElement thirdKeywordInputField;

    @FindBy(xpath = "//u[@id = 'kw_result']//a")
    private WebElement resultSearchByKeywordLink;

    public SearchFilmByKeywordsPage(WebDriver driver) {
        super(driver);
    }

    public SearchFilmByKeywordsPage inputKeywords(List<String> labelList) {
        inputKeyword(firstKeywordInputField, labelList.get(0));
        inputKeyword(secondKeywordInputField, labelList.get(1));
        inputKeyword(thirdKeywordInputField, labelList.get(2));
        return new SearchFilmByKeywordsPage(driver);
    }

    private SearchFilmByKeywordsPage inputKeyword(WebElement webElement, String item) {
        waitVisibilityOfWebElement(webElement, 3);
        webElement.sendKeys(item);
        clickDropdownRoleName(item);
        return new SearchFilmByKeywordsPage(driver);
    }

    public SearchResultPage clickSearckByKeywordsButton() {
        waitVisibilityOfWebElement(resultSearchByKeywordLink, 5);
        resultSearchByKeywordLink.click();
        return new SearchResultPage(driver);
    }
}

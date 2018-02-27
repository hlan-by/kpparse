package ru.kinopoisk.pages.search.parts;

import ru.kinopoisk.pages.search.SearchResultPage;
import ru.kinopoisk.utils.search.data.UserForSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchUserPage extends AbstractSearchPage {

    @FindBy(xpath = "//input[@id = 'find_user']")
    private WebElement userLogin;

    @FindBy(xpath = "//input[@id = 'name']")
    private WebElement userName;

    @FindBy(xpath = "//input[@name = 'm_act[surname]']")
    private WebElement userSurname;

    @FindBy(xpath = "//input[@class = 'el_9 submit nice_button']")
    private WebElement searchButton;

    public SearchUserPage(WebDriver driver) {
        super(driver);
    }

    public SearchUserPage inputPersonData(UserForSearch userForSearch) {
        waitVisibilityOfWebElement(userLogin, 3);
        userLogin.sendKeys(userForSearch.getUserLogin());
        userName.sendKeys(userForSearch.getUserName());
        userSurname.sendKeys(userForSearch.getUserSurname());
        return new SearchUserPage(driver);
    }

    public SearchResultPage clickSearchUserButton() {
        waitVisibilityOfWebElement(searchButton, 3);
        searchButton.click();
        return new SearchResultPage(driver);
    }

}

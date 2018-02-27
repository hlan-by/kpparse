package ru.kinopoisk.pages.search.parts;

import ru.kinopoisk.pages.search.SearchResultPage;
import ru.kinopoisk.utils.search.data.FilmCreatorsForSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SearchFilmByCreatorsPage extends AbstractSearchPage {

    @FindBy(xpath = "//select[@id = 'cr_search_field_1_select']")
    private WebElement firstSelectField;

    @FindBy(xpath = "//input[@id = 'cr_search_field_1']")
    private WebElement firstInputField;

    @FindBy(xpath = "//select[@id = 'cr_search_field_2_select']")
    private WebElement secondSelectField;

    @FindBy(xpath = "//input[@id = 'cr_search_field_2']")
    private WebElement secondInputField;

    @FindBy(xpath = "//select[@id = 'cr_search_field_3_select']")
    private WebElement thirdSelectField;

    @FindBy(xpath = "//input[@id = 'cr_search_field_3']")
    private WebElement thirdInputField;

    @FindBy(xpath = "//input[@id = 'btn_search_6']")
    private WebElement buttonSearchByCreators;

    @FindBy(xpath = "//u[@id = 'block_result_6']")
    private WebElement resultSearchByCreatorsLink;

    public SearchFilmByCreatorsPage(WebDriver driver) {
        super(driver);
    }

    public SearchFilmByCreatorsPage inputDataForSearchFilmByCreators(FilmCreatorsForSearch filmCreators) {

        selectAndInputRoleName(firstSelectField, filmCreators.getFirstRole(),
                firstInputField, filmCreators.getFirstRoleName());
        clickDropdownRoleName(filmCreators.getFirstRoleName());

        selectAndInputRoleName(secondSelectField, filmCreators.getSecondRole(),
                secondInputField, filmCreators.getSecondRoleName());
        clickDropdownRoleName(filmCreators.getSecondRoleName());

        waitVisibilityOfWebElement(resultSearchByCreatorsLink, 5);
        return new SearchFilmByCreatorsPage(driver);

    }

    public SearchFilmByCreatorsPage clickButtonSearchByCreators() {
        waitVisibilityOfWebElement(buttonSearchByCreators, 5);
        resultSearchByCreatorsLink.click();
        return new SearchFilmByCreatorsPage(driver);
    }

    private SearchResultPage selectAndInputRoleName(WebElement selectField, String selectRole, WebElement inputRoleNameField,
                                                    String roleName) {
        waitVisibilityOfWebElement(selectField, 5);
        Select dropdownRole = new Select(selectField);
        dropdownRole.selectByVisibleText(selectRole);
        inputRoleNameField.sendKeys(roleName);

        return new SearchResultPage(driver);
    }

}

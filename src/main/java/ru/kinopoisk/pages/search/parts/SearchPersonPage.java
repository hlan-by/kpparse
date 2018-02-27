package ru.kinopoisk.pages.search.parts;

import ru.kinopoisk.pages.search.SearchResultPage;
import ru.kinopoisk.utils.search.data.PersonForSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPersonPage extends AbstractSearchPage {

    @FindBy(xpath = "//input[@id = 'find_people']")
    private WebElement personNameInputField;

    @FindBy(xpath = "//input[@id = 'birthday']")
    private WebElement personYearOfBirthInputField;

    @FindBy(xpath = "//input[@id = 'location']")
    private WebElement personPlaceOfBirthInputField;

    @FindBy(xpath = "//input[@class = 'el_8 submit nice_button']")
    private WebElement buttonSearchPerson;

    @FindBy(xpath = "//select[@id = 'sex']")
    private WebElement selectGender;

    public SearchPersonPage(WebDriver driver) {
        super(driver);
    }

    public SearchPersonPage inputPersonData(PersonForSearch personForSearch) {
        waitVisibilityOfWebElement(personNameInputField, 3);

        personNameInputField.sendKeys(personForSearch.getName());
        personYearOfBirthInputField.sendKeys(personForSearch.getYearOfBirth());
        personPlaceOfBirthInputField.sendKeys(personForSearch.getPlaceOfBirth());

        dropdownSelectByVisibleText(selectGender, personForSearch.getGender());
        return new SearchPersonPage(driver);
    }

    public SearchResultPage clickSearchPersonButton() {
        waitVisibilityOfWebElement(buttonSearchPerson, 3);
        buttonSearchPerson.click();
        return new SearchResultPage(driver);
    }

}

package ru.kinopoisk.pages.search.parts;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AbstractSearchPage extends AbstractPage{

    public AbstractSearchPage(WebDriver driver) {
        super(driver);
    }

    protected void dropdownSelectByVisibleText(WebElement webElement, String field) {
        Select dropdownGenre = new Select(webElement);
        dropdownGenre.selectByVisibleText(field);
    }

    protected void clickDropdownRoleName(String clickLabelName) {
        WebElement dropdownClickable = driver.findElement(
                By.xpath("//span[@class = 'act' and text() = '" + clickLabelName + "']"));
        waitVisibilityOfWebElement(dropdownClickable, 5);
        dropdownClickable.click();
    }

}

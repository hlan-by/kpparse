package ru.kinopoisk.pages.main;

import ru.kinopoisk.enums.PageTransitions;
import ru.kinopoisk.enums.TabNames;
import ru.kinopoisk.utils.Screenshoter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class QuickNavBarPage extends AbstractPageWithoutLogin {

    public QuickNavBarPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    @FindBy(xpath = "//*[@id=\"external_header_wrapper\"]//li")
    List<WebElement> tabNamesList;

    private final By BY_EXPECTED_ELEMENT = By.xpath("//*[@id=\"external_header_wrapper\"]//ul/li/a");

    private final String FOLDER_FOR_SCREENSHOTS = "main";
    private final String WRONG_TAB_NAME_SCREEN = "quick nav bar don't contain tab with name  ";
    private final String WRONG_LINK_SCREEN = "quick nav bar don't contain link with name ";

    public boolean isTabWithNameExist(TabNames tabName) {
        for (WebElement element : tabNamesList) {
            if (element.getText().equals(tabName.getRusName())) {
                return true;
            }
        }

        Screenshoter.getInstance().makeScreenshot(driver, FOLDER_FOR_SCREENSHOTS, WRONG_TAB_NAME_SCREEN + tabName.getRusName());
        return false;
    }

    public boolean isLinkOnTab(PageTransitions pageTransitions) {
        if (openPage(pageTransitions)) {
            return true;
        }

        Screenshoter.getInstance().makeScreenshot(driver, FOLDER_FOR_SCREENSHOTS, WRONG_LINK_SCREEN + pageTransitions.getLinkRusName());
        return false;
    }

    public boolean openPage(PageTransitions pageTransitions) {
        openTabWithName(pageTransitions.getParentTabRusName());
        List<WebElement> dropDownElements = driver.findElements(BY_EXPECTED_ELEMENT);
        for (WebElement element : dropDownElements) {
            if (element.getText().equals(pageTransitions.getLinkRusName())) {
                element.click();
                driverWait.until(ExpectedConditions.urlContains(pageTransitions.getLink()));
                if (driver.getCurrentUrl().contains(pageTransitions.getLink())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void openTabWithName(String tabName) {
        WebElement tab;
        for (WebElement element : tabNamesList) {
            if (element.getText().equals(tabName)) {
                tab = element;
                Actions actions = new Actions(driver);
                actions.moveToElement(tab).build().perform();
                driverWait.until(ExpectedConditions.visibilityOfElementLocated(BY_EXPECTED_ELEMENT));
            }
        }
    }
}

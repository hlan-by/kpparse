package ru.kinopoisk.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public AbstractPage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitVisibilityOfWebElement(WebElement webElement, int seconds) {
        new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitInvisibilityOfWebElement(WebDriver webDriver, WebElement webElement, int seconds) {
        new WebDriverWait(webDriver, seconds)
                .until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void waitForPageLoad(int timeout) {
        ExpectedCondition<Boolean> pageLoadCondition = driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        new WebDriverWait(driver, timeout).until(pageLoadCondition);
    }

    public void highlightElement(WebElement currentElement) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", currentElement);
    }
}

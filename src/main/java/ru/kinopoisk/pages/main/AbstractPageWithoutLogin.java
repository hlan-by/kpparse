package ru.kinopoisk.pages.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPageWithoutLogin {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public AbstractPageWithoutLogin(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }
}

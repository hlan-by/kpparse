package ru.kinopoisk.pages.login;

import ru.kinopoisk.models.User;
import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='header-fresh-user-partial-component__dropdown']//a[@href='/logout/']")
    private WebElement newExit;

    @FindBy(xpath = "//div[@class='header-fresh-user-partial-component__avatar']")
    private WebElement newAvatar;

    @FindBy(xpath = "//a[@class='header-auth-partial-component__link'=1]")
    private WebElement linkloginOld;

    @FindBy(xpath = "//*[@id=\"partial_component__header-fresh-experience\"]/div/div/div/nav/div")
    private WebElement linkloginNew;

    @FindBy(xpath = "//iframe[@name='kp2-authapi-iframe']")
    private WebElement frame;

    @FindBy(xpath = "//input[@name ='login']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@name ='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[contains(@class,'button button_type_action button_size_xxl button_block auth__signin i-bem button_js_inited')]")
    private WebElement submitLogin;

    @FindBy(xpath = "//a[@class='header-auth-partial-component__link'=2]")
    private WebElement register;

    @FindBy(xpath = "//a[@class='header-auth-partial-component__link header-auth-partial-component__link_position_right']")
    private WebElement linkLogOff;

    @FindBy(xpath = "//div[@class='header-fresh-user-partial-component__avatar']")
    private WebElement logOff;

    @FindBy(xpath = "//div[@class='social-auth__item social-auth__item_code_ya']")
    private WebElement yandexIcon;

    @FindBy(xpath = "//input[@name='login']")
    private WebElement yandexLogin;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement yandexPassword;

    @FindBy(xpath = "//span[@class='passport-Button-Text']")
    private WebElement yandexButton;

    @FindBy(xpath = "//span[contains(@class,'auth__restore domik__restore')]")
    private WebElement forgetLink;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//button[contains(@class,'button button_type_action button_size_xxl button_block auth__button auth__button_type_submit i-bem button_js_inited')]")
    private WebElement buttonSendEmail;

    @FindBy(xpath = "//div[@class='auth__email-sent-mode']")
    private WebElement messageSend;

    private String YANDEX_NAME = "katiTest";
    private String YANDEX_PASSWORD = "1234zzz";
    private String EMAIL = "katrine.kovalenko@gmail.com";


    public LoginPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void login(User user) {

        try {
            linkloginOld.click();
        } catch (Exception e) {
            linkloginNew.click();
        }
        driver.switchTo().frame(frame);

        waitVisibilityOfWebElement(inputLogin, 10);
        inputLogin.sendKeys(user.getLogin() );

        highlightElement(inputLogin);
        waitVisibilityOfWebElement(inputPassword, 10);
        inputPassword.sendKeys(user.getPassword());

        highlightElement(inputPassword);
        WebElement submitLoginWait = (new WebDriverWait(driver, 2))
                .until(ExpectedConditions.elementToBeClickable(submitLogin));
        submitLoginWait.click();
    }

    public boolean isLogin() {

        try {
            waitVisibilityOfWebElement(newAvatar, 10);
            return newAvatar.isDisplayed();

        } catch (Exception e) {}

        try {
            waitVisibilityOfWebElement(register, 10);
            return register.isDisplayed();
        } catch (Exception e) {}

        return false;
    }


    public boolean logOff() {

        if (linkloginNew != null) {
            Actions action = new Actions(driver);
            waitVisibilityOfWebElement(newAvatar, 10);
            action.moveToElement(newAvatar).build().perform();

            Actions action2 = new Actions(driver);
            waitVisibilityOfWebElement(newExit, 10);
            action2.moveToElement(newExit).click().build().perform();

            return true;
        } else if (linkloginOld != null) {
            waitVisibilityOfWebElement(linkLogOff, 5);
            linkLogOff.click();
            return true;
        }
        return false;

    }

    public void openYandex() {
        try {
            waitVisibilityOfWebElement(linkloginOld, 5);
            linkloginOld.click();
        } catch (Exception e) {
            waitVisibilityOfWebElement(linkloginNew, 5);
            linkloginNew.click();
        }

        waitVisibilityOfWebElement(frame, 10);
        driver.switchTo().frame(frame);
        waitVisibilityOfWebElement(yandexIcon, 10);
        yandexIcon.click();

        if(!isLogin()) {
            String main = driver.getWindowHandle();
            ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
            newTab.remove(main);
            driver.switchTo().window(newTab.get(0));
            waitVisibilityOfWebElement(yandexLogin, 5);
            yandexLogin.sendKeys(YANDEX_NAME);
            highlightElement(yandexLogin);
            WebElement loginWait = (new WebDriverWait(driver, 5))
                    .until(ExpectedConditions.elementToBeClickable(yandexButton));
            yandexPassword.sendKeys(YANDEX_PASSWORD);
            highlightElement(yandexPassword);
            WebElement submitWait = (new WebDriverWait(driver, 5))
                    .until(ExpectedConditions.elementToBeClickable(yandexButton));
            submitWait.click();
            driver.switchTo().window(main);
            driver.navigate().refresh();
        }
    }

    public boolean forgetPassword() {

        try {
            linkloginOld.click();
        } catch (Exception e) {
            linkloginNew.click();
        }
        driver.switchTo().frame(frame);

        waitVisibilityOfWebElement(forgetLink, 10);
        forgetLink.click();

        waitVisibilityOfWebElement(inputEmail, 10);
        inputEmail.sendKeys(EMAIL);
        highlightElement(inputEmail);

        WebElement submitEmail = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.elementToBeClickable(buttonSendEmail));
        submitEmail.submit();

        waitVisibilityOfWebElement(messageSend, 10);
        if (messageSend.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}

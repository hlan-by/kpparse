package ru.kinopoisk.tests.login;

import ru.kinopoisk.models.User;
import ru.kinopoisk.pages.login.LoginPage;
import ru.kinopoisk.pages.main.MainPage;
import ru.kinopoisk.tests.BeforeAndAfterClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogin extends BeforeAndAfterClass{

    @Test(priority = 400, groups = {"login"})
    public void logInSuccess(){
        MainPage indexPage = new MainPage(driver, driverWait);
        indexPage.open();
        LoginPage loginPage = new LoginPage(driver, driverWait);
        User user = new User(USER_NAME, USER_PASWORD);
        loginPage.login(user);
        Assert.assertTrue(loginPage.isLogin(), "user is't logged in");
    }

    @Test(priority = 401, dependsOnMethods = "logInSuccess", groups = {"login"})
    public void logOff(){
        LoginPage loginPage = new LoginPage(driver, driverWait);
        Assert.assertTrue(loginPage.logOff(), "user didn't log off");
    }

    @Test(priority = 402, dependsOnMethods = "logOff", groups = {"login"})
    public void logInFailPassword(){
        LoginPage loginPage = new LoginPage(driver, driverWait);
        User user = new User(USER_NAME, USER_PASWORD+"200");
        loginPage.login(user);
        Assert.assertFalse(loginPage.isLogin(), "user should't logged in");
        driver.navigate().refresh();
    }

    @Test(priority = 402, dependsOnMethods = "logInFailPassword", groups = {"login"})
    public void logInFailUsername(){
        LoginPage loginPage = new LoginPage(driver, driverWait);
        User user = new User(USER_NAME+"ABC", USER_PASWORD);
        loginPage.login(user);
        Assert.assertFalse(loginPage.isLogin(), "user should't logged in");
        driver.navigate().refresh();
    }

    @Test(priority = 403, dependsOnMethods = "logInFailUsername", groups = {"login"})
    public void yandex(){
        LoginPage loginPage = new LoginPage(driver, driverWait);
        loginPage.openYandex();
        Assert.assertFalse(loginPage.isLogin(), "not logged in with yandex");
    }

    @Test(priority = 403, dependsOnMethods = "yandex", groups = {"login"})
    public void forgetPasswordSuccess(){
        LoginPage loginPage = new LoginPage(driver, driverWait);
        Assert.assertTrue(loginPage.forgetPassword(), "password recovery email not sent");
    }
}

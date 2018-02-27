package ru.kinopoisk.utils;

import ru.kinopoisk.models.User;
import ru.kinopoisk.pages.login.LoginPage;
import ru.kinopoisk.pages.main.MainPage;
import ru.kinopoisk.pages.profile.MainLoggedPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    public MainLoggedPage login(WebDriver driver, WebDriverWait driverWait, User user){
        new MainPage(driver, driverWait).open();
        LoginPage loginPage = new LoginPage(driver, driverWait);
        loginPage.login(user);
        if (!loginPage.isLogin())  System.out.println("Fail to login!");
        else                       System.out.println("Login successful!");
        return new MainLoggedPage(driver);
    }
}

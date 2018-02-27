package ru.kinopoisk.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BeforeAndAfterClass {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    private final int IMPLICITY_TIMEOUT = 15;

    public static final String USER_NAME ="loginTest";
    public static final String USER_PASWORD = "1234zzzA";


    @BeforeClass(alwaysRun = true)
    public void setUp(){
        driver = new ChromeDriver();

        // Added for future starts on Selenium Grid
        // ChromeOptions options = new ChromeOptions();
        // driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);

        driverWait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICITY_TIMEOUT, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

       driver.quit();

    }


    public void turnOffImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public void turnOnImplicitWaitsInSeconds(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public void turnOnImplicitWaits(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(IMPLICITY_TIMEOUT, TimeUnit.SECONDS);
    }
}

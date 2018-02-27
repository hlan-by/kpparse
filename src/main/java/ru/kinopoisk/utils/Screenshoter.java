package ru.kinopoisk.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;

public class Screenshoter{

    private final static String PATH_TO_SCREENSHOTS = "./screenshots";

    public static class SingletonHolder {
        public static final Screenshoter HOLDER_INSTANCE = new Screenshoter();
    }

    public static Screenshoter getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private Screenshoter() {
        File file = new File(PATH_TO_SCREENSHOTS);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void makeScreenshot(WebDriver driver, String screenshotFolder, String screenshotName){
        File screenshotFile = new File(PATH_TO_SCREENSHOTS + "/" + screenshotFolder + "/" + screenshotName + ".png");
        makeScreenAndSave(driver, screenshotFile);
    }

    private static void makeScreenAndSave(WebDriver driver, File screenshotFile){
        try{
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, screenshotFile);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

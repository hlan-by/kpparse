package ru.kinopoisk.utils.parser;

import org.openqa.selenium.*;

class ParserUtils {
    boolean isElementPresent(WebElement element, By locator)
    {
        return !element.findElements(locator).isEmpty();
    }

    String deleteTextInBrackets(String income) {
        return income.replaceAll("\\(.*?\\)", "");
    }

    String deleteWhiteSpaces(String income) {
        return income.replaceAll("\\s+", "");
    }
}

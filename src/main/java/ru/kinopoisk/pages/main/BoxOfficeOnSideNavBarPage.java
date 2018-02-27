package ru.kinopoisk.pages.main;

import ru.kinopoisk.beans.main.BoxOfficeBean;
import ru.kinopoisk.beans.main.LinkOnFilmBean;
import ru.kinopoisk.utils.Screenshoter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BoxOfficeOnSideNavBarPage extends AbstractPageWithoutLogin {
    public BoxOfficeOnSideNavBarPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    @FindBy(xpath = "//*[@id=\"rigth_box_weekend_rus\"]/dt/a[2]")
    WebElement buttonForFirstChangeVisibility;

    @FindBy(xpath = "//*[@id=\"block_right\"]//*[@class=\"block block_cash\" and @style=\"visibility: visible;\"]//a[@href=\"#\"]")
    WebElement buttonSwitchOtherPanel;

    @FindBy(xpath = "//*[@id=\"block_right\"]//*[@class=\"block block_cash\" and @style=\"visibility: visible;\"]/dt")
    WebElement boxOfficeHeader;

    @FindBy(xpath = "//*[@id=\"block_right\"]//*[@class=\"block block_cash\" and @style=\"visibility: visible;\"]//dd//i/s")
    List<WebElement> rusNameList;

    @FindBy(xpath = "//*[@id=\"block_right\"]//*[@class=\"block block_cash\" and @style=\"visibility: visible;\"]//dd[@class=\"dl\"]/a")
    List<WebElement> hrefList;

    private int NUMBER_OF_LINKS = 5;

    @FindBy(xpath = "//*[@id=\"block_right\"]//*[@class=\"block block_cash\" and @style=\"visibility: visible;\"]//dt/a[@class]")
    WebElement linkInHeaderBoxOfferTab;

    @FindBy(xpath = "//*[@id=\"block_right\"]//*[@class=\"block block_cash\" and @style=\"visibility: visible;\"]//*[@class=\"more\"]/a")
    WebElement linkInFooterBoxOfferTab;

    @FindBy(xpath = "//*[@class=\"block block_top250\"]")
    WebElement nextAfterBoxOfficeElement;

    private final String HREF = "href";
    private final String FOLDER_FOR_SCREENSHOTS = "main";
    private final String WRONG_TAB_NAME_SCREEN = "side nav bar don't contains box office with header ";

    private final By BY_EXPECTED_ON_NEW_PAGE = By.xpath("//*[@class=\"main_title\"]");
    private final By BY_FILM_LINKS_ON_BOX_PAGE = By.xpath("//*[@class=\"fontsize11\"]//td[@class=\"news\"]/a");

    public boolean isContains(BoxOfficeBean boxOfficeBean) {
        moveToBoxOfficePanel();
        switchTo(boxOfficeBean);

        if (!linkInHeaderBoxOfferTab.getAttribute(HREF).endsWith(boxOfficeBean.getHref())) {
            return makeScreenshotAndReturnFalse(boxOfficeBean);
        }
        if (!linkInFooterBoxOfferTab.getAttribute(HREF).endsWith(boxOfficeBean.getHref())) {
            return makeScreenshotAndReturnFalse(boxOfficeBean);
        }
        if (!boxOfficeHeader.getText().contains(boxOfficeBean.getCurrencyType())) {
            return makeScreenshotAndReturnFalse(boxOfficeBean);
        }

        List<LinkOnFilmBean> linkList = getAllFilmLinksFromVisibleBoxOfficeElement();

        linkInHeaderBoxOfferTab.click();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(BY_EXPECTED_ON_NEW_PAGE));

        return isLinkListContainsList(convertElementsFromBoxPageToLinksList(getElementsWithLinksFromBoxPage()), linkList);
    }

    private void switchTo(BoxOfficeBean boxOfficeBean) {
        makeScreenshotAndReturnFalse(boxOfficeBean);
        if (!boxOfficeHeader.getText().contains(boxOfficeBean.getTextInHeader())) {
            buttonSwitchOtherPanel.click();
        }
    }

    private void moveToBoxOfficePanel() {
        Actions actions = new Actions(driver);
        actions.moveToElement(nextAfterBoxOfficeElement).build().perform();
        buttonForFirstChangeVisibility.click();
    }

    private boolean makeScreenshotAndReturnFalse(BoxOfficeBean boxOfficeBean) {
        Screenshoter.getInstance().makeScreenshot(driver, FOLDER_FOR_SCREENSHOTS, WRONG_TAB_NAME_SCREEN + boxOfficeBean.getTextInHeader());
        return false;
    }

    private List<LinkOnFilmBean> getAllFilmLinksFromVisibleBoxOfficeElement() {
        List<LinkOnFilmBean> linkList = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_LINKS; i++) {
            LinkOnFilmBean linkOnFilmBean = new LinkOnFilmBean();
            linkOnFilmBean.setRusFilmName(rusNameList.get(i).getText());
            linkOnFilmBean.setHref(hrefList.get(i).getAttribute(HREF));
            linkList.add(linkOnFilmBean);
        }

        return linkList;
    }

    private List<WebElement> getElementsWithLinksFromBoxPage() {
        List<WebElement> elementsWithLinksList = driver.findElements(BY_FILM_LINKS_ON_BOX_PAGE);
        return elementsWithLinksList;
    }

    private List<LinkOnFilmBean> convertElementsFromBoxPageToLinksList(List<WebElement> elementWithLinkList) {
        List<LinkOnFilmBean> linkOnFilmList = new ArrayList<>();
        for (WebElement element : elementWithLinkList) {
            LinkOnFilmBean linkOnFilm = new LinkOnFilmBean();
            linkOnFilm.setHref(element.getAttribute(HREF));
            linkOnFilm.setRusFilmName(element.getText());
            linkOnFilmList.add(linkOnFilm);
        }
        return linkOnFilmList;
    }

    private boolean isLinkListContainsList(List<LinkOnFilmBean> searchInList, List<LinkOnFilmBean> whatSearchList) {
        for (LinkOnFilmBean link : whatSearchList) {
            boolean isLinkContains = false;
            for (LinkOnFilmBean linkForEquals : searchInList) {
                if (link.equals(linkForEquals)) {
                    isLinkContains = true;
                    break;
                }
            }
            if (!isLinkContains) {
                return false;
            }
        }
        return true;
    }
}


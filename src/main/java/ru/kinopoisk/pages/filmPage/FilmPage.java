package ru.kinopoisk.pages.filmPage;

import ru.kinopoisk.models.Film;
import ru.kinopoisk.models.FilmNote;
import ru.kinopoisk.models.FilmReview;
import ru.kinopoisk.pages.AbstractPage;
import ru.kinopoisk.utils.parser.FilmPageParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FilmPage extends AbstractPage {

    private static final String HEADER_FILMNAME_BIG_LOCATOR = "moviename-big";
    private static final String HEADER_FILMNAME_SMALL_LOCATOR = "//span[@itemprop='alternativeHeadline']";
    private static final String FILM_INFO_TABLE_ROWS_LOCATOR = "//table[@class='info']//td[@class='type']";
    private static final String TRAILER_BUTTON_LOCATOR = "//div[@id='photoBlock']//button[@id='movie-trailer-button']";
    private static final String FAVOURITE_FILM_BUTTON_LOCATOR = "//a[@id='btn_fav_film']";
    private static final String FAVOURITE_FILM_LOADER_LOCATOR = "//div[@id='star_fav_film']";
    private static final String VIEWED_FILM_BUTTON_LOCATOR = "//a[@id='btn_null_vote']";
    private static final String VIEWED_FILM_LOADER_LOCATOR = "//div[@id='star_null_vote']";
    private static final String NOTE_BUTTON_LOCATOR = "//a[@id='btn_film_notice']";
    private static final String NOTE_FORM_LOCATOR = "//div[@id='txt_film_notice']";
    private static final String NOTE_FORM_SAVE_LOADER_LOCATOR = "//div[@id='ta_film_notice']//div[@class='loader']";
    private static final String NOTE_INPUT_FORM_LOCATOR = "//div[@id='ta_film_notice']//textarea";
    private static final String NOTE_SAVE_BUTTON_LOCATOR = "//div[@id='ta_film_notice']//input[@class='save']";
    private static final String NOTE_DELETE_BUTTON_LOCATOR = "//div[@id='txt_film_notice']//a[@class='remove']";
    private static final String DROPDOWN_BUTTON_LOCATOR = "//div[@id='div_mustsee_main']//div[@class='select']//span[contains(@class,'title')]";
    private static final String DROPDOWN_LIST_LOCATOR = "//div[@id='div_mustsee_main']//dl[@class='list']";
    private static final String DROPDOWN_NECESSARY_FOLDER_LOCATOR = "//div[@id='div_mustsee_main']//dl[@class='list']//dd[text()='%s']";
    private static final String DROPDOWN_FOLDERS_IN_LIST_LOCATOR = "//div[@id='div_mustsee_main']//dl[@class='list']//dd";
    private static final String DROPDOWN_FOLDERS_PANEL_LOCATOR = "//ul[@class='folders']";
    private static final String DROPDOWN_FOLDER_LINK_IN_PANEL_LOCATOR = "//div[@id='div_mustsee_main']//ul[@class='folders']//a[text()='%s']";
    private static final String DROPDOWN_FOLDERS_CLOSE_BUTTON_LOCATOR = "//div[@id='div_mustsee_main']//div[@class='list_title']";
    private static final String RATING_NECESSARY_MARK_BUTTON_LOCATOR = "//div[@class='starbar']//a[@data-title='%s']";
    private static final String RATING_DELETE_MARK_BUTTON_LOCATOR = "//div[@class='rating_bottom']//span[@class='btn_delete_vote remove']";
    private static final String RATING_SAVE_LOADER_LOCATOR = "//div[@class='rating_bottom']//i[@class='loader']";
    private static final String RATING_EDIT_BLOCK_LOCATOR = "//span[@id='my_vote_block']";
    private static final String ERROR_POPUP_WINDOW_LOCATOR = "(//div[@id='ui_notice_container']//div[@class='tdtext'])[last()]";
    private static final String REVIEW_SORT_BY_LOCATOR = "//ul[@class='resp_type']//li[@class='%s']";
    private static final String REVIEW_LIST_LOCATOR = "//div[contains(@class,'reviewItem')]//div[contains(@class,'response')]";
    private static final String REVIEW_FORM_LOCATOR = "//form[contains(@class,'response')]";
    private static final String REVIEW_TITLE_FORM_LOCATOR = "//form[contains(@class,'response')]//input[@id='review_title']";
    private static final String REVIEW_INPUT_FORM_LOCATOR = "//form[contains(@class,'response')]//textarea[@id='review_text']";
    private static final String REVIEW_TYPE_FORM_LOCATOR = "//form[contains(@class,'response')]//select[@id='sb_review_status']";
    private static final String REVIEW_SUMBIT_FORM_LOCATOR = "//form[contains(@class,'response')]//input[@id='btn_review_add']";

    @FindBy(xpath = TRAILER_BUTTON_LOCATOR)
    private WebElement openTrailerWindowButton;

    @FindBy(xpath = FAVOURITE_FILM_BUTTON_LOCATOR)
    private WebElement favouriteFilmButton;

    @FindBy(xpath = FAVOURITE_FILM_LOADER_LOCATOR)
    private WebElement favouriteFilmLoader;

    @FindBy(xpath = VIEWED_FILM_BUTTON_LOCATOR)
    private WebElement viewedFilmButton;

    @FindBy(xpath = VIEWED_FILM_LOADER_LOCATOR)
    private WebElement viewedFilmLoader;

    @FindBy(xpath = NOTE_BUTTON_LOCATOR)
    private WebElement noteButton;

    @FindBy(xpath = NOTE_FORM_LOCATOR)
    private WebElement noteForm;

    @FindBy(xpath = NOTE_FORM_SAVE_LOADER_LOCATOR)
    private WebElement noteFormSaveLoader;

    @FindBy(xpath = NOTE_INPUT_FORM_LOCATOR)
    private WebElement noteInputForm;

    @FindBy(xpath = NOTE_SAVE_BUTTON_LOCATOR)
    private WebElement noteSaveButton;

    @FindBy(xpath = NOTE_DELETE_BUTTON_LOCATOR)
    private WebElement noteDeleteButton;

    @FindBy(xpath = DROPDOWN_BUTTON_LOCATOR)
    private WebElement dropdownButton;

    @FindBy(xpath = DROPDOWN_LIST_LOCATOR)
    private WebElement dropdownFoldersArea;

    @FindBy(xpath = DROPDOWN_FOLDERS_IN_LIST_LOCATOR)
    private List<WebElement> dropdownFoldersInListElements;

    @FindBy(xpath = DROPDOWN_FOLDERS_CLOSE_BUTTON_LOCATOR)
    private WebElement dropdownFoldersCloseArea;

    @FindBy(xpath = DROPDOWN_FOLDERS_PANEL_LOCATOR)
    private WebElement dropdownFoldersPanel;

    @FindBy(xpath = RATING_DELETE_MARK_BUTTON_LOCATOR)
    private WebElement ratingDeleteButton;

    @FindBy(xpath = RATING_SAVE_LOADER_LOCATOR)
    private WebElement ratingSaveLoader;

    @FindBy(xpath = RATING_EDIT_BLOCK_LOCATOR)
    private WebElement ratingEditBlock;

    @FindBy(xpath = ERROR_POPUP_WINDOW_LOCATOR)
    private WebElement popupErrorTextWindow;

    @FindBy(xpath = REVIEW_LIST_LOCATOR)
    private List<WebElement> reviewElements;

    @FindBy(xpath = REVIEW_TITLE_FORM_LOCATOR)
    private WebElement reviewTitleInput;

    @FindBy(xpath = REVIEW_INPUT_FORM_LOCATOR)
    private WebElement reviewTextInput;

    @FindBy(xpath = REVIEW_TYPE_FORM_LOCATOR)
    private WebElement reviewTypeInput;

    @FindBy(xpath = REVIEW_SUMBIT_FORM_LOCATOR)
    private WebElement reviewSubmitButton;

    @FindBy(xpath = REVIEW_FORM_LOCATOR)
    private WebElement reviewForm;

    public FilmPage(WebDriver driver) {
        super(driver);
    }

    public FilmPage open(String URL) {
        driver.get(URL);
        waitForPageLoad(10);
        return new FilmPage(driver);
    }

    public TrailerFilmPage openTrailerWindow() {
        if (openTrailerWindowButton.isDisplayed()) {
            openTrailerWindowButton.click();
            return new TrailerFilmPage(driver);
        } else return null;
    }

    public FilmPage openDropdownFolderMenu(){
        dropdownButton.click();
        return new FilmPage(driver);
    }

    public FilmPage selectPointInDropdown(String folderName) {
        WebElement necessaryFolder = dropdownFoldersArea.findElement(By.xpath(String.format(DROPDOWN_NECESSARY_FOLDER_LOCATOR, folderName)));
        necessaryFolder.click();
        return new FilmPage(driver);
    }

    public FilmPage selectAllPointsInDropdown() {
        for (WebElement currentElement : dropdownFoldersInListElements) {
            currentElement.click();
        }
        return new FilmPage(driver);
    }

    public FilmPage closeDropdownFolderMenu(){
        dropdownFoldersCloseArea.click();
        return new FilmPage(driver);
    }

    public FilmPage clickStatusViewedButton() {
        viewedFilmButton.click();
        return new FilmPage(driver);
    }

    public FilmPage clickFavouriteFilmButton() {
        favouriteFilmButton.click();
        return new FilmPage(driver);
    }

    public FilmPage createNote(FilmNote note) {
        noteButton.click();
        if (noteInputForm.isDisplayed())
        {
            noteInputForm.sendKeys(note.getMessage());
            noteSaveButton.click();
            waitForPageLoad(10);
        }
        return new FilmPage(driver);
    }

    public FilmPage deleteNote() {
        noteDeleteButton.click();
        driver.switchTo().alert().accept();
        return new FilmPage(driver);
    }

    public FilmPage setRating(String rating) {
        WebElement necessaryRatingButton = driver.findElement(By.xpath(String.format(RATING_NECESSARY_MARK_BUTTON_LOCATOR, rating)));
        necessaryRatingButton.click();
        return new FilmPage(driver);
    }

    public FilmPage deleteRating() {
        ratingDeleteButton.click();
        return new FilmPage(driver);
    }

    public FilmPage openReviewsType(String type)
    {
        driver.findElement(By.xpath(String.format(REVIEW_SORT_BY_LOCATOR, type)+"//a")).click();
        return new FilmPage(driver);
    }

    public boolean isSortReviewsEqualToType(String type)
    {
        String reviewTypeString;
        switch(type)
        {
            case "pos": reviewTypeString = "response good"; break;
            case "neg": reviewTypeString = "response bad"; break;
            case "neut": case "all": reviewTypeString = "response"; break;
            default: reviewTypeString =  "none";
        }

        for(WebElement current: reviewElements)
        {
            if(!current.getAttribute("class").equals(reviewTypeString)) return false;
        }
        return true;
    }

    public FilmPage addReview(FilmReview review){
        reviewTitleInput.sendKeys(review.getTitle());
        reviewTextInput.sendKeys(review.getReviewText());
        Select reviewType = new Select(reviewTypeInput);
        reviewType.selectByValue(review.getType());
        reviewSubmitButton.click();
        return new FilmPage(driver);
    }

    public Film getFilmInfo() {
        FilmPageParser parsedPage = new FilmPageParser(driver.findElement(By.tagName("body")));
        return parsedPage.getParsedFilmData();
    }

    public boolean isFilmFavourite() {
        waitInvisibilityOfWebElement(driver, favouriteFilmLoader, 10);
        return favouriteFilmButton.getAttribute("class").equals("el_1_act") && isFilmInFolder("Любимые фильмы");
    }

    public boolean isFilmViewed() {
        waitInvisibilityOfWebElement(driver,viewedFilmLoader,10);
        return viewedFilmButton.getAttribute("class").equals("el_2_act");
    }

    public boolean isNoteExist() {
        waitInvisibilityOfWebElement(driver,noteFormSaveLoader,10);
        highlightElement(noteButton);
        highlightElement(noteForm);
        return noteButton.getAttribute("class").equals("el_3_act") && noteForm.isDisplayed();
    }

    public boolean isFilmInFolder(String folderName) {
        try {
            waitVisibilityOfWebElement(dropdownFoldersPanel, 5);
            WebElement folderElement = driver.findElement(By.xpath(String.format(DROPDOWN_FOLDER_LINK_IN_PANEL_LOCATOR, folderName)));
            return folderElement.isDisplayed();
        }
        catch(NoSuchElementException|TimeoutException e)
        {
            return false;
        }
    }

    public boolean isUserRatingExist() {
        waitInvisibilityOfWebElement(driver, ratingSaveLoader, 10);
        try{
            new WebDriverWait(driver, 5).
                    until(ExpectedConditions.attributeToBeNotEmpty(ratingEditBlock, "style"));
            return false;
        }
        catch (TimeoutException e)
        {
            return true;
        }
    }

    public boolean isReviewFormAvaliable() {
        return !reviewForm.getAttribute("class").contains("disabled");
    }

    public boolean isPopupWindowsExist(){
        return popupErrorTextWindow.isDisplayed();
    }

    public String getPopupErrorText() {
        return popupErrorTextWindow.getText();
    }
}

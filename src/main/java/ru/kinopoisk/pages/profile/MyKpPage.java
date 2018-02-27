package ru.kinopoisk.pages.profile;

import ru.kinopoisk.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MyKpPage extends AbstractPage {

    private final int SAVE_BTTN_NUMBER = 5;

    @FindBy (xpath = "//input[@name='edit[main][first_name]']")
    private WebElement fieldName;
    @FindBy (xpath = "//input[@name='edit[main][last_name]']")
    private WebElement fieldLastName;
    @FindBy (xpath = "//select[@name='edit[main][sex]']")
    private WebElement selectSex;
    @FindBy (xpath = "//select[@name='edit[birth][day]']")
    private WebElement selectDay;
    @FindBy (xpath = "//select[@name='edit[birth][month]']")
    private WebElement selectMonth;
    @FindBy (xpath = "//select[@name='edit[birth][year]']")
    private WebElement selectYear;
    @FindBy (xpath = "//select[@name='edit[main][id_country]']")
    private WebElement selectCountry;
    @FindBy (xpath = "//input[@name='edit[main][city]']")
    private WebElement fieldCity;
    @FindBy (xpath = "//input[@name='edit[main][social][vkontakte]']")
    private WebElement fieldSocVK;
    @FindBy (xpath = "//input[@name='edit[main][social][facebook]']")
    private WebElement fieldSocFB;
    @FindBy (xpath = "//input[@name='edit[main][social][twitter]']")
    private WebElement fieldSocTwit;
    @FindBy (xpath = "//input[@name='edit[messengers][icq]']")
    private WebElement fieldIcq;
    @FindBy (xpath = "//input[@name='edit[messengers][skype]']")
    private WebElement fieldSkype;
    @FindBy (id = "edit[main][about]")
    private WebElement textAreaAbout;
    @FindBy (xpath = "//input[@name='edit[main][interes]']")
    private WebElement fieldInteres;
    @FindBy (xpath = "//input[@value='выбрать файл']")
    private WebElement buttonChooseFile;
    @FindBy (xpath = "//input[@value='сохранить все']")
    private List<WebElement> buttonsSave;
    @FindBy (xpath = "//img[@src='https://st.kp.yandex.net/images/profile/profile_ra.gif']")
    private WebElement imgProfileFillBar;
    @FindBy (xpath = "//input[@type='checkbox' and @name='edit[main][birth_view]']")
    private WebElement checkboxDontShow;
    @FindBy (id = "ui_notice_container")
    private WebElement popUpMsg;
    @FindBy (xpath = "//a[@href='/mykp/history/']")
    private WebElement historyLink;

    public MyKpPage(WebDriver driver) {
        super(driver);
    }

//-----element presents methods----

    private boolean areAllButtonSavePresent(){
        boolean areAllVisible = true;
        for (WebElement buttonSave :
                buttonsSave) {
            areAllVisible &= buttonSave.isDisplayed();
        }
        return (areAllVisible)&&(buttonsSave.size()== SAVE_BTTN_NUMBER);
    }

    public boolean areElementsPresent(){

        return (fieldName.isDisplayed()&&fieldLastName.isDisplayed()&&selectSex.isDisplayed()&&selectDay.isDisplayed()
                &&selectMonth.isDisplayed()&&selectYear.isDisplayed()&&selectCountry.isDisplayed()&&fieldCity.isDisplayed()
                &&fieldSocVK.isDisplayed()&&fieldSocFB.isDisplayed()&&fieldSocTwit.isDisplayed()&&fieldIcq.isDisplayed()
                &&fieldSkype.isDisplayed()&&textAreaAbout.isDisplayed()&&fieldInteres.isDisplayed()&&buttonChooseFile.isDisplayed()
                &&imgProfileFillBar.isDisplayed()&&checkboxDontShow.isDisplayed())&& areAllButtonSavePresent();
    }


    //---end of element presents methods---

    //-----filling form methods----

    private MyKpPage fillTextFields(List<String> textFields){
        fieldName.clear();
        fieldName.sendKeys(textFields.get(0));
        fieldLastName.clear();
        fieldLastName.sendKeys(textFields.get(1));
        textAreaAbout.clear();
        textAreaAbout.sendKeys(textFields.get(2));
        fieldInteres.clear();
        fieldInteres.sendKeys(textFields.get(3));
        fieldCity.clear();
        fieldCity.sendKeys(textFields.get(4));
        return this;
    }

    private MyKpPage fillSocial(String vkText, String fbText, String twitText){
        fieldSocVK.clear();
        fieldSocVK.sendKeys(vkText);
        fieldSocFB.clear();
        fieldSocFB.sendKeys(fbText);
        fieldSocTwit.clear();
        fieldSocTwit.sendKeys(twitText);
        return this;
    }

    private MyKpPage fillIcq(String icqText){
        fieldIcq.clear();
        fieldIcq.sendKeys(icqText);
        return this;
    }

    private MyKpPage fillSkype(String skypeText){
        fieldSkype.clear();
        fieldSkype.sendKeys(skypeText);
        return this;
    }

    private MyKpPage fillBirthday(String day, String month, String year){
       new Select(selectDay).selectByVisibleText(day);
       new Select(selectMonth).selectByVisibleText(month);
       new Select(selectYear).selectByVisibleText(year);
        return this;
    }

    private MyKpPage fillSex(String sex) {
        new Select(selectSex).selectByVisibleText(sex);
        return this;
    }

    private MyKpPage fillCountry(String country) {
        new Select(selectCountry).selectByVisibleText(country);
        return this;
    }

    private MyKpPage markCheckbox() {
        if (!"true".equals(checkboxDontShow.getAttribute("checked")))
        checkboxDontShow.click();
        return this;
    }

    public MyKpPage fillAllProfile(Data data){

        fillTextFields(data.getTextFields());
        fillSocial(data.getVkText(), data.getFbText(), data.getTwitText());
        fillIcq(data.getIcqText());
        fillSkype(data.getSkypeText());
        fillBirthday(data.getDay(), data.getMonth(), data.getYear());
        fillSex(data.getSex());
        fillCountry(data.getCountry());
        markCheckbox();
        return this;
    }

    public MyKpPage fillAboutMyselfWithTooManySymbols(){
        textAreaAbout.clear();
        textAreaAbout.sendKeys(Data.getTooBigString());
        return this;
    }

    //-----end of filling form methods----

    //--- verifications methods----

    private boolean verifyTextFields(List<String> textFields){

        return
                textFields.get(0).equals(fieldName.getAttribute("value"))&&
                textFields.get(1).equals(fieldLastName.getAttribute("value"))&&
                textFields.get(2).equals(textAreaAbout.getText())&&
                textFields.get(3).equals(fieldInteres.getAttribute("value"))&&
                textFields.get(4).equals(fieldCity.getAttribute("value"));
    }

    private boolean verifySocial(String vkText, String fbText, String twitText){

        return
                vkText.equals(fieldSocVK.getAttribute("value"))&&
                fbText.equals(fieldSocFB.getAttribute("value"))&&
                twitText.equals(fieldSocTwit.getAttribute("value"));
    }

    private boolean verifyIcq(String icq){
        return icq.equals(fieldIcq.getAttribute("value"));
    }

    private boolean verifySkype(String skype){
        return skype.equals(fieldSkype.getAttribute("value"));
    }

    private boolean verifyBirthday(String day, String month, String year){

        return
               day.equals(new Select(selectDay).getFirstSelectedOption().getText())&&
               month.equals(new Select(selectMonth).getFirstSelectedOption().getText())&&
               year.equals(new Select(selectYear).getFirstSelectedOption().getText());
    }

    private boolean verifySex(String sex){

        return
                sex.equals(new Select(selectSex).getFirstSelectedOption().getText());
    }

    private boolean verifyCountry(String country){

        return
                country.equals(new Select(selectCountry).getFirstSelectedOption().getText());
    }

    private boolean verifyCheckbox(){

        return "true".equals(checkboxDontShow.getAttribute("checked"));
    }

    public boolean verifyAllProfile(Data data){

        return
                verifyTextFields(data.getTextFields())&&
                verifySocial(data.getVkText(), data.getFbText(), data.getTwitText())&&
                verifyIcq(data.getIcqText())&&
                verifySkype(data.getSkypeText())&&
                verifyBirthday(data.getDay(), data.getMonth(), data.getYear())&&
                verifySex(data.getSex())&&
                verifyCountry(data.getCountry())&&
                verifyCheckbox();
    }

    //---end of verifications methods----

    // ---- other methods-----

    public MyKpPage submitChanges(){
        buttonsSave.get(0).click();
        return this;
    }

    public boolean isMsgPopsUp(){
        waitVisibilityOfWebElement(popUpMsg, 3);
        return popUpMsg.isDisplayed();
    }

    public HistoryPage goToHistory(){
        historyLink.click();
        return new HistoryPage(driver);
    }

}

package org.pageobject.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class PopupPage {

    private final AppiumDriver driver;

    public PopupPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    //locators
    @AndroidFindBy(accessibility = "popup title")
    private WebElement popupTitleText;

    @AndroidFindBy(accessibility = "popup text box")
    private WebElement popupTextField;

    @AndroidFindBy(accessibility = "popup OK button")
    private WebElement popupOKBtn;

    @AndroidFindBy(accessibility = "popup cancel button")
    private WebElement popupCancelBtn;


    //actions

    public String getPopupTitleText() {
        return popupTitleText.getText();
    }
    public String getPopupTextField() {
        return popupTextField.getText();
    }
    public void setPopupTextField(String text) {
        popupTextField.sendKeys(text);
    }
    public void clickPopupOKBtn() {
        popupOKBtn.click();
    }
    public void clickPopupCancelBtn() {
        popupCancelBtn.click();
    }

    public void setTextAndApply(String text) {
        setPopupTextField(text);
        clickPopupOKBtn();
    }
}

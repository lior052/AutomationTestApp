package org.pageobject.ios;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class PopupPage {

    private AppiumDriver driver;

    public PopupPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    //locators

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Add New Item\"`]")
    private WebElement popupTitleText;

    @iOSXCUITFindBy(accessibility = "popupTextField")
    private WebElement popupTextField;

    @iOSXCUITFindBy(accessibility = "popupOKButton")
    private WebElement popupOKBtn;

    @iOSXCUITFindBy(accessibility = "popupCancelButton")
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

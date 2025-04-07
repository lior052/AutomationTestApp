package org.pageobject.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HomePage {

    private final AppiumDriver driver;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    //locators
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"App for Auto Tests\")")
    private WebElement titleText;

    @AndroidFindBy(accessibility = "Open popup button")
    private WebElement setDataBtn;

    @AndroidFindBy(accessibility = "disabled text box")
    private WebElement disabledTxtField;

    @AndroidFindBy(accessibility = "Progress bar text")
    private WebElement progressTxt;

    @AndroidFindBy(accessibility = "slider")
    private WebElement progressBar;

    @AndroidFindBy(accessibility = "Open list view button")
    private WebElement listBtn;


    //actions

    public String getTitleText() {
        return titleText.getText();
    }
    public String getDisabledTxtField() {
        //WebElement parentElement = driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"disabled text box\"]/.."));
        return disabledTxtField.getText();
    }
    public String getProgressTxt() {
        return progressTxt.getText();
    }
    public void setDataBtn() {
        setDataBtn.click();
    }
    public String getProgressBar() {
        String progressVal = progressBar.getText();
        return progressVal.split("\\.")[0];
    }
    public void openPopupAndSetData() {
        setDataBtn.click();
    }
    public void openList() {
        listBtn.click();
    }

    public void setProgressBar(int percent) {
        if (percent > 100)
            percent = 100;
        else if (percent < 0)
            percent = 0;
        if(percent != Integer.parseInt(getProgressBar()))
            progressBar.sendKeys(String.valueOf(percent));
    }

    public void backToHomePage() throws InterruptedException {
        driver.executeScript("mobile: terminateApp", ImmutableMap.of("appId", "com.lior.automationtestapp"));
        Thread.sleep(2000); // Small delay before restarting
        driver.executeScript("mobile: activateApp", ImmutableMap.of("appId", "com.lior.automationtestapp"));
    }

}

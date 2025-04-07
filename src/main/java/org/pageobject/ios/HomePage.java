package org.pageobject.ios;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
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

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"App for Auto Tests\"`]")
    private WebElement titleText;

    @iOSXCUITFindBy(accessibility = "setDataButton")
    private WebElement setDataBtn;

    @iOSXCUITFindBy(accessibility = "disabledTextField")
    private WebElement disabledTxtField;

    @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'Progress:'")
    private WebElement progressTxt;

    @iOSXCUITFindBy(accessibility = "progressBar")
    private WebElement progressBar;

    @iOSXCUITFindBy(accessibility = "listButton")
    private WebElement listBtn;


    //actions
    public String getTitleText() {
        return titleText.getText();
    }
    public String getDisabledTxtField() {
        return disabledTxtField.getText();
    }
    public String getProgressTxt() {
        return progressTxt.getText();
    }
    public void setDataBtn() {
        setDataBtn.click();
    }
    public String getProgressBar() {
        return progressBar.getDomAttribute("value");
    }
    public void openPopupAndSetData() {
        setDataBtn.click();
    }
    public void openList() {
        listBtn.click();
    }

    public void setProgressBar(int percent) {
        double value = percent / 100.0;
        if (value > 1.0)
            value = 1.0;
        else if (value < 0.0)
            value = 0.0;
        progressBar.sendKeys(String.valueOf(value));
    }

    public void backToHomePage() throws InterruptedException {
        driver.executeScript("mobile: terminateApp", ImmutableMap.of("bundleId", "com.lior.AutomationTestApp"));
        Thread.sleep(2000); // Small delay before restarting
        driver.executeScript("mobile: launchApp", ImmutableMap.of("bundleId", "com.lior.AutomationTestApp"));
    }

}

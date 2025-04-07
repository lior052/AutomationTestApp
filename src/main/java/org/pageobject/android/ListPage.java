package org.pageobject.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ListPage {

    private final AppiumDriver driver;
    private final AndroidActions action;
    private PopupPage popupPage;

    public ListPage(AppiumDriver driver) {
        this.driver = driver;
        this.action = new AndroidActions((AndroidDriver) driver);
        popupPage = new PopupPage(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    //locators
    @AndroidFindBy(accessibility = "Back")
    private WebElement backBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"My List\")")
    private WebElement myListTitleTxt;

    @AndroidFindBy(accessibility = "Add Item")
    private WebElement addItemBtn;

    @AndroidFindBy(accessibility = "Delete Item")
    private WebElement deleteItemBtn;


    //actions
    public String getMyListTitleText() {
        return myListTitleTxt.getText();
    }
    public void clickBackBtn() {
        backBtn.click();
    }
    public void clickAddItemBtn() {
        addItemBtn.click();
    }
    public void clickDeleteItemBtn() {
        deleteItemBtn.click();
    }


    public void checkListItem(String itemName) {
        WebElement listItem = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Unselected: "+itemName+"\")"));
        listItem.click();
    }

    public void uncheckListItem(String itemName) {
        WebElement listItem = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Selected: "+itemName+"\")"));
        listItem.click();
    }

    public String getListItemText(String itemName) {
        if (driver.findElement(AppiumBy.androidUIAutomator(
                String.format("new UiSelector().descriptionContains(\"%s\")", itemName))).isDisplayed())
            return itemName;
        return "";
    }

    public void deleteListItem(String itemName) {
        WebElement listItem = driver.findElement(AppiumBy.androidUIAutomator(
                String.format("new UiSelector().descriptionContains(\"%s\")", itemName)));
        listItem.click();
        deleteItemBtn.click();
    }

    public void swipeToDeleteListItem(String itemName) {
        WebElement listItem = driver.findElement(AppiumBy.androidUIAutomator(
                String.format("new UiSelector().descriptionContains(\"%s\")", itemName)));
        action.swipeAction(listItem, "left");
    }

    public void addNewItem(String itemName) {
        addItemBtn.click();
        popupPage.setPopupTextField(itemName);
        popupPage.clickPopupOKBtn();
    }

    public boolean isItemExist(String itemName) {
        try {
            WebElement listItem = driver.findElement(AppiumBy.androidUIAutomator(
                    String.format("new UiSelector().descriptionContains(\"%s\")", itemName)));
            return listItem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

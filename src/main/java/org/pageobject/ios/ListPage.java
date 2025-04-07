package org.pageobject.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.appium.utils.IOSActions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ListPage {

    private final AppiumDriver driver;
    private final IOSActions action;
    private PopupPage popupPage;

    public ListPage(AppiumDriver driver) {
        this.driver = driver;
        this.action = new IOSActions((IOSDriver) driver);
        popupPage = new PopupPage(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    //locators

    @iOSXCUITFindBy(accessibility = "Back")
    private WebElement backBtn;

    @iOSXCUITFindBy(accessibility = "My List")
    private WebElement myListTitleTxt;

    @iOSXCUITFindBy(accessibility = "plus.circle.fill")
    private WebElement addItemBtn;

    @iOSXCUITFindBy(accessibility = "trash.fill")
    private WebElement deleteItemBtn;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == \"Delete\"`]")
    private WebElement deleteItemOnItemBtn;

    @iOSXCUITFindBy(iOSNsPredicate = "name == \"checkmark.circle.fill\" AND label == \"Selected\" AND type == \"XCUIElementTypeImage\"")
    private List<WebElement> checkedListItems;

    //dynamic locator for list item
    //@iOSXCUITFindBy(accessibility = "list item text")

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
    public void clickDeleteItemOnItemBtn() {
        deleteItemOnItemBtn.click();
    }
    public int getCheckedListItemsCount() {
        return checkedListItems.size();
    }

    public void clickListItem(String itemName) {
        WebElement listItem = driver.findElement(AppiumBy.iOSNsPredicateString("name CONTAINS '"+itemName+"'"));
        listItem.click();
    }

    //return the text of the list item without numeric list
    public String getListItemText(String itemName) {
        if(driver.findElement(AppiumBy.iOSNsPredicateString("name CONTAINS '"+itemName+"'")).isDisplayed())
            return itemName;
        return "";
    }

    public void deleteListItem(String itemName) {
        WebElement listItem = driver.findElement(AppiumBy.iOSNsPredicateString("name CONTAINS '"+itemName+"'"));
        listItem.click();
        deleteItemBtn.click();
    }


    public void deleteListItemByOnItemDeleteButton(String itemName) {
        WebElement listItem = driver.findElement(AppiumBy.iOSNsPredicateString("name CONTAINS '"+itemName+"'"));
        //need to swipe left to show delete button
        action.swipeAction(listItem, "left");
        deleteItemOnItemBtn.click();
    }
    public void swipeToDeleteListItem(String itemName) {
        WebElement listItem = driver.findElement(AppiumBy.iOSNsPredicateString("name CONTAINS '"+itemName+"'"));
        action.swipeFullyElement(listItem, "left");
    }

    public void addNewItem(String itemName) {
        addItemBtn.click();
        popupPage.setPopupTextField(itemName);
        popupPage.clickPopupOKBtn();
    }

    public void waitForUpdates() {
        addItemBtn.click();
        popupPage.clickPopupCancelBtn();
    }

    public boolean isItemExist(String itemName) {
        try {
            WebElement listItem = driver.findElement(AppiumBy.iOSNsPredicateString("name CONTAINS '"+itemName+"'"));
            return listItem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //popup actions
    public String getNewItemTextBox() {
        return popupPage.getPopupTextField();
    }
    public void clickPopUpOK() {
        popupPage.clickPopupOKBtn();
    }
    public void clickPopUpCancel() {
        popupPage.clickPopupCancelBtn();
    }



}

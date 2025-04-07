package org.tests.android;

import org.appium.framework.BaseTest;
import org.appium.framework.reports.ReportListeners;
import org.pageobject.android.HomePage;
import org.pageobject.android.ListPage;
import org.pageobject.android.PopupPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;


@Listeners({ReportListeners.class})
@Test(groups = {"Android"})
public class ListTests extends BaseTest {

    private HomePage homePage;
    private ListPage listPage;
    private PopupPage popupPage;

    @BeforeMethod
    public void setupPage() throws IOException {
        homePage = new HomePage(getDriver());
        listPage = new ListPage(getDriver());
        popupPage = new PopupPage(getDriver());

        String homeTitle = homePage.getTitleText();
        Assert.assertEquals(homeTitle, "App for Auto Tests");
        homePage.openList();
        String listTitle = listPage.getMyListTitleText();
        Assert.assertEquals(listTitle, "My List");
    }

    @AfterMethod
    public void tearDown() {
        try {
            homePage.backToHomePage();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddItem() {

        listPage.addNewItem("Hello Appium!");

        // Verify the list item exists
        String listItemText = listPage.getListItemText("Hello Appium!");
        Assert.assertEquals(listItemText, "Hello Appium!");
    }

    @Test
    public void testDeleteItem() {

        String listItemStr = "Delete Item test";
        listPage.addNewItem(listItemStr);

        // Verify the list item exists
        String listItemText = listPage.getListItemText(listItemStr);
        Assert.assertEquals(listItemText, listItemStr);

        // Delete the item
        listPage.deleteListItem(listItemStr);

        listPage.clickAddItemBtn();
        popupPage.clickPopupCancelBtn();

        // Verify the item is deleted
        boolean isItemListExist = listPage.isItemExist(listItemStr);
        Assert.assertFalse(isItemListExist);
    }

    @Test
    public void testSwipeToDeleteItem() {

        String listItemStr = "Swipe to Delete Item test";
        listPage.addNewItem(listItemStr);

        boolean isItemListExist = listPage.isItemExist(listItemStr);
        Assert.assertTrue(isItemListExist);

        // Swipe to delete the item
        listPage.swipeToDeleteListItem(listItemStr);

        listPage.clickAddItemBtn();
        popupPage.clickPopupCancelBtn();

        // Verify the item is deleted
        isItemListExist = listPage.isItemExist(listItemStr);
        Assert.assertFalse(isItemListExist);
    }

}
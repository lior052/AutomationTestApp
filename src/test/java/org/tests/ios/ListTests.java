package org.tests.ios;

import org.appium.framework.BaseTest;
import org.appium.framework.reports.ReportListeners;
import org.pageobject.ios.HomePage;
import org.pageobject.ios.ListPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;


@Listeners({ReportListeners.class})
@Test(groups = {"iOS"})
public class ListTests extends BaseTest {

    private HomePage homePage;
    private ListPage listPage;

    @BeforeMethod
    public void setupPage() throws IOException {
        homePage = new HomePage(getDriver());
        listPage = new ListPage(getDriver());

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

        // Verify the item is deleted
        boolean isItemListExist = listPage.isItemExist(listItemStr);
        Assert.assertFalse(isItemListExist);
    }

    @Test
    public void testDeleteItemByOnItemDeleteButton() {

        String listItemStr = "Delete item on item delete button test";
        listPage.addNewItem(listItemStr);

        // Verify the list item exists
        String listItemText = listPage.getListItemText(listItemStr);
        Assert.assertEquals(listItemText, listItemStr);

        // Delete the item
        listPage.deleteListItemByOnItemDeleteButton(listItemStr);

        // Verify the item is deleted
        boolean isItemListExist = listPage.isItemExist(listItemStr);
        Assert.assertFalse(isItemListExist);
    }

    @Test
    public void testSwipeToDeleteItem() {

        String listItemStr = "Swipe to delete item test";
        listPage.addNewItem(listItemStr);

        // Verify the list item exists
        String listItemText = listPage.getListItemText(listItemStr);
        Assert.assertEquals(listItemText, listItemStr);

        // Swipe to delete the item
        listPage.swipeToDeleteListItem(listItemStr);

        listPage.waitForUpdates();

        // Verify the item is deleted
        boolean isItemListExist = listPage.isItemExist(listItemStr);
        Assert.assertFalse(isItemListExist);
    }

}
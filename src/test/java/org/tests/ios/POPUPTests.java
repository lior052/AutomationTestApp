package org.tests.ios;

import org.appium.framework.BaseTest;
import org.appium.framework.reports.ReportListeners;
import org.testng.Assert;
import org.testng.annotations.*;
import org.pageobject.ios.HomePage;
import org.pageobject.ios.PopupPage;

import java.io.IOException;

@Listeners({ReportListeners.class})
@Test(groups = {"iOS"})
public class POPUPTests extends BaseTest {

    private HomePage homePage;
    private PopupPage popupPage;


    @BeforeMethod
    public void setupPage() throws IOException {
        homePage = new HomePage(getDriver());
        popupPage = new PopupPage(getDriver());

        String homeTitle = homePage.getTitleText();
        Assert.assertEquals(homeTitle, "App for Auto Tests");
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
    public void testHomePopupInteraction() {

        String disabledText = homePage.getDisabledTxtField();
        Assert.assertEquals(disabledText, "Text will appear here");

        homePage.openPopupAndSetData();
        String popupTitle = popupPage.getPopupTitleText();
        Assert.assertEquals(popupTitle, "Add New Item");
        popupPage.setPopupTextField("Hello Appium!");
        popupPage.clickPopupOKBtn();
        // Verify the text in the disabled text field
        disabledText = homePage.getDisabledTxtField();
        Assert.assertEquals(disabledText, "Hello Appium!");
    }

    @Test
    public void testHomePopupCancel() {

        String disabledText = homePage.getDisabledTxtField();
        Assert.assertEquals(disabledText, "Text will appear here");

        homePage.openPopupAndSetData();
        String popupTitle = popupPage.getPopupTitleText();
        Assert.assertEquals(popupTitle, "Add New Item");
        popupPage.setPopupTextField("Hello Appium!");
        popupPage.clickPopupCancelBtn();
        // Verify the text in the disabled text field
        disabledText = homePage.getDisabledTxtField();
        Assert.assertEquals(disabledText, "Text will appear here");
    }

}


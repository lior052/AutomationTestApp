package org.tests.android;


import org.appium.framework.BaseTest;
import org.appium.framework.reports.ReportListeners;
import org.testng.Assert;
import org.testng.annotations.*;
import org.pageobject.android.HomePage;
import org.pageobject.android.PopupPage;

import java.io.IOException;

@Listeners({ReportListeners.class})
@Test(groups = {"Android"})
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

        homePage.openPopupAndSetData();
        String popupTitle = popupPage.getPopupTitleText();
        Assert.assertEquals(popupTitle, "Pop up text");
        popupPage.setPopupTextField("Hello Appium!");
        popupPage.clickPopupOKBtn();
        // Verify the text in the disabled text field
        String disabledText = homePage.getDisabledTxtField();
        Assert.assertEquals(disabledText, "Hello Appium!");
    }
    @Test
    public void testHomePopupCancel() {

        homePage.openPopupAndSetData();
        String popupTitle = popupPage.getPopupTitleText();
        Assert.assertEquals(popupTitle, "Pop up text");
        popupPage.setPopupTextField("Hello Appium!");
        popupPage.clickPopupCancelBtn();
        // Verify the text in the disabled text field
        String disabledText = homePage.getDisabledTxtField();
        Assert.assertEquals(disabledText, "");
    }

}



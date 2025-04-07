package org.tests.android;

import org.appium.framework.BaseTest;
import org.appium.framework.reports.ReportListeners;
import org.pageobject.android.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners({ReportListeners.class})
@Test(groups = {"Android"})
public class SliderTests extends BaseTest {

    private HomePage homePage;


    @BeforeMethod
    public void setupPage() throws IOException {
        homePage = new HomePage(getDriver());
        String progressText = homePage.getProgressTxt();
        Assert.assertEquals(progressText, "Progress: 0%");

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
    public void testProgressBarWithValidValue() {

        homePage.setProgressBar(30);
        String progressBarValue = homePage.getProgressBar();
        Assert.assertEquals(progressBarValue, "30");
    }

    @Test
    public void testProgressBarWithInvalidValue() {

        homePage.setProgressBar(200);
        String progressBarValue = homePage.getProgressBar();
        Assert.assertNotEquals(progressBarValue, "200");
        Assert.assertEquals(progressBarValue, "100");
    }
    @Test
    public void testProgressBarWithNegativeValue() {

        homePage.setProgressBar(-50);
        String progressBarValue = homePage.getProgressBar();
        Assert.assertNotEquals(progressBarValue, "-50");
        Assert.assertEquals(progressBarValue, "0");
    }
    @Test
    public void testProgressBarWithZeroValue() {

        homePage.setProgressBar(0);
        String progressBarValue = homePage.getProgressBar();
        Assert.assertEquals(progressBarValue, "0");
    }


}
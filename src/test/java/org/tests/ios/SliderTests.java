package org.tests.ios;

import org.appium.framework.BaseTest;
import org.appium.framework.reports.ReportListeners;
import org.pageobject.ios.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners({ReportListeners.class})
@Test(groups = {"iOS"})
public class SliderTests extends BaseTest {

    private HomePage homePage;


    @BeforeMethod
    public void setupPage() throws IOException {
        homePage = new HomePage(getDriver());
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
        String progressText = homePage.getProgressTxt();
        Assert.assertEquals(progressText, "Progress: 50%");

        homePage.setProgressBar(30);
        String progressBarValue = homePage.getProgressBar();
        Assert.assertEquals(progressBarValue, "30%");
    }

    @Test
    public void testProgressBarWithInvalidValue() {
        String progressText = homePage.getProgressTxt();
        Assert.assertEquals(progressText, "Progress: 50%");

        homePage.setProgressBar(200);
        String progressBarValue = homePage.getProgressBar();
        Assert.assertNotEquals(progressBarValue, "200%");
        Assert.assertEquals(progressBarValue, "100%");
    }
    @Test
    public void testProgressBarWithNegativeValue() {
        String progressText = homePage.getProgressTxt();
        Assert.assertEquals(progressText, "Progress: 50%");

        homePage.setProgressBar(-50);
        String progressBarValue = homePage.getProgressBar();
        Assert.assertNotEquals(progressBarValue, "-50%");
        Assert.assertEquals(progressBarValue, "0%");
    }
    @Test
    public void testProgressBarWithZeroValue() {
        String progressText = homePage.getProgressTxt();
        Assert.assertEquals(progressText, "Progress: 50%");

        homePage.setProgressBar(0);
        String progressBarValue = homePage.getProgressBar();
        Assert.assertEquals(progressBarValue, "0%");
    }


}

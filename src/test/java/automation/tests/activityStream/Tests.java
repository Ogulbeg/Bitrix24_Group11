package automation.tests.activityStream;

import automation.pages.login.ActivityStreamPage;
import automation.pages.login.LoginPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

public class Tests extends AbstractTestBase {

    LoginPage loginPage = new LoginPage();
    ActivityStreamPage activityStreamPage = new ActivityStreamPage();


    /*
    1. User should be able to click on upload files icon to upload files and pictures from
    local disks, download from external drive, select documents
    from bixtrix24, and create files to upload.
     */

    @Test
    public void userStory5() {


        test = report.createTest("verify  to click on upload files icon to upload files");
        loginPage.login();
        test.info("Login as HR");
        loginPage.navigateTo("Activity Stream");

        WebElement more = Driver.getDriver().findElement(By.xpath("//span[contains(@id,'feed-add-post-form-link-text')]"));
        wait.until(ExpectedConditions.elementToBeClickable(more)).click();
        BrowserUtils.wait(6);

        WebElement file = Driver.getDriver().findElement(By.xpath("(//span[@class ='menu-popup-item-text'])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(file)).click();
        BrowserUtils.wait(5);
        // ==========


        String filePath = "/Users/sevimzini/Desktop/Hello batch 15.docx";

        //Date date = new Date();
        //String timeStamp = "_" + date.toString().substring(4).substring(0, 23).replace(' ', '_');
       // String fileName = "Hello batch 15"+timeStamp+".docx";
       String fileName = "Hello batch 15.docx";

        Driver.getDriver().findElement(By.xpath("//input[contains(@name,'bxu_files[]')]")).sendKeys("/Users/sevimzini/Desktop/Hello batch 15.docx");
        BrowserUtils.wait(6);

        //verification part:
        Assert.assertTrue(activityStreamPage.isFileDownloaded(filePath, fileName), "File is not downloaded successfully");
        test.pass("File uploaded successfully.");

    }
    /*
    Optional Test: User should be able to click "Applications" and see all
    available applications.

     */

    @Test
    public void test2() {

        test = report.createTest("verify the applications are displayed");
        loginPage.login();
        test.info("Login as HR");
        loginPage.navigateTo("Activity Stream");
        Driver.getDriver().findElement(By.xpath("//span[@class='menu-favorites-more-text'][contains(.,'More...')]")).click();
        BrowserUtils.wait(3);


        Driver.getDriver().findElement(By.xpath("//span[@class='menu-item-link-text'][contains(.,'Applications')]")).click();

        List<WebElement> applications = Driver.getDriver().findElements(By.className("mp-item-title"));

        for (WebElement eachApp : applications) {

            Assert.assertTrue(eachApp.isDisplayed());
        }
        test.pass("Applications visibility is verified");
    }

}

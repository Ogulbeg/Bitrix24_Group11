package automation.tests.pool;

import automation.pages.login.LoginPage;
import automation.pages.poll.PollPage;

import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class PollTest extends AbstractTestBase {

private By textBarEditorBy =By.id("bx-html-editor-tlbr-idPostFormLHE_blogPostForm");

    private By uploadingFile=By.name("bxu_files[]");

    private By saveIconBy=By.id("undefined");

    private By saveTheVideoLinkIconBy=By.cssSelector("[name='undefined']");

    private By sendBtn=By.id("blog-submit-button-save");
    @Test
    public void verifyVisibilityOfTextBar(){
        test = reports.createTest("Verify visibility of text editor toolbar");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        PollPage pollPage = new PollPage();
        pollPage.navigateTo("Poll");
        pollPage.makeEditorTextBarVisible();

        List<WebElement> textEditorBar = Driver.getDriver().findElements(textBarEditorBy);
            Assert.assertTrue(textEditorBar.size()>0);
            test.pass("Visibility of text editor toolbar verified!");

    }
@Test
    public void verifyUploadingFile(){
    test = reports.createTest("Verify file uploading");
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    PollPage pollPage = new PollPage();
    pollPage.navigateTo("Poll");
    BrowserUtils.wait(2);


   pollPage.uploadedFileVisibility(System.getProperty("user.dir")+"/pom.xml");

//Driver.getDriver().findElement(uploadingFile).sendKeys((System.getProperty("user.dir")+"/pom.xml"));
    BrowserUtils.wait(2);
}
@Test
    public void verifyAttachingLink(){
    test = reports.createTest("Verify attaching the link");
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    PollPage pollPage = new PollPage();
    pollPage.navigateTo("Poll");
    BrowserUtils.wait(2);

    pollPage.attachingLink("https://www.amazon.com/");
    BrowserUtils.wait(2);
    driver.findElement(saveIconBy).click();
    BrowserUtils.wait(4);
    driver.findElement(sendBtn).click();

}
@Test
public void verifyVideoInsert(){
    test = reports.createTest("Verify inserting the link video");
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    PollPage pollPage = new PollPage();
    pollPage.navigateTo("Poll");
    BrowserUtils.wait(2);

    pollPage.insertVideo("https://www.youtube.com/watch?v=X9eLWkZBKgQ");
    BrowserUtils.wait(2);
    driver.findElement(saveTheVideoLinkIconBy).click();
    BrowserUtils.wait(4);

    //driver.findElement(saveIconBy).click();

    BrowserUtils.wait(4);
    driver.findElement(sendBtn).click();
}

@Test
    public void creatingTheQuote(){
    test = reports.createTest("Verify creating the quote");
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    PollPage pollPage = new PollPage();
    pollPage.navigateTo("Poll");
    BrowserUtils.wait(2);

    pollPage.frameCreateQuote("Today is a beautiful day "+ LocalDate.now());
    String actual = driver.findElement(By.xpath("//blockquote")).getAttribute("class");
    String expected = "bxhtmled-quote";
    Assert.assertEquals(actual,expected);

    BrowserUtils.wait(4);
    pollPage.sendButton();
    BrowserUtils.wait(4);

    String actual1=driver.findElement(By.xpath("(//table[@class='blogquote'])[1]")).getText();
    String expected1="Today is a beautiful day "+LocalDate.now();

Assert.assertEquals(actual1,expected1);
}


}

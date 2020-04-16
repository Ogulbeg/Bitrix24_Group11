package automation.tests.pool;

import automation.pages.login.LoginPageOgulbeg;
import automation.pages.poll.PollPageOgulbeg;

import automation.tests.AbstractTestBaseOgulbeg;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class PollTestOgulbeg extends AbstractTestBaseOgulbeg {

private By textBarEditorBy =By.id("bx-html-editor-tlbr-idPostFormLHE_blogPostForm");

    private By uploadingFile=By.name("bxu_files[]");

    private By saveIconBy=By.id("undefined");

    private By saveTheVideoLinkIconBy=By.cssSelector("[name='undefined']");

    private By sendBtn=By.id("blog-submit-button-save");
    @Test
    public void verifyVisibilityOfTextBar(){
        test = reports.createTest("Verify visibility of text editor toolbar");
        LoginPageOgulbeg loginPageOgulbeg = new LoginPageOgulbeg();
        loginPageOgulbeg.login();
        PollPageOgulbeg pollPage = new PollPageOgulbeg();
        pollPage.navigateTo("Poll");
        pollPage.makeEditorTextBarVisible();

        List<WebElement> textEditorBar = Driver.getDriver().findElements(textBarEditorBy);
            Assert.assertTrue(textEditorBar.size()>0);
            test.pass("Visibility of text editor toolbar verified!");

    }
@Test
    public void verifyUploadingFile(){
    test = reports.createTest("Verify file uploading");
    LoginPageOgulbeg loginPageOgulbeg = new LoginPageOgulbeg();
    loginPageOgulbeg.login();
    PollPageOgulbeg pollPage = new PollPageOgulbeg();
    pollPage.navigateTo("Poll");
    BrowserUtils.wait(2);


   pollPage.uploadedFileVisibility(System.getProperty("user.dir")+"/pom.xml");

//Driver.getDriver().findElement(uploadingFile).sendKeys((System.getProperty("user.dir")+"/pom.xml"));
    BrowserUtils.wait(2);
}
@Test
    public void verifyAttachingLink(){
    test = reports.createTest("Verify attaching the link");
    LoginPageOgulbeg loginPageOgulbeg = new LoginPageOgulbeg();
    loginPageOgulbeg.login();
    PollPageOgulbeg pollPage = new PollPageOgulbeg();
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
    LoginPageOgulbeg loginPageOgulbeg = new LoginPageOgulbeg();
    loginPageOgulbeg.login();
    PollPageOgulbeg pollPage = new PollPageOgulbeg();
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
    LoginPageOgulbeg loginPageOgulbeg = new LoginPageOgulbeg();
    loginPageOgulbeg.login();
    PollPageOgulbeg pollPage = new PollPageOgulbeg();
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

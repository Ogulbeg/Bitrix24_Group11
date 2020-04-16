package automation.pages.poll;

import automation.pages.AbstractPageBaseOgulbeg;
import automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PollPageOgulbeg extends AbstractPageBaseOgulbeg {
    @FindBy(id="bx-b-uploadfile-blogPostForm")
    public WebElement uploadedFileIcon;

@FindBy(name="bxu_files[]")
private WebElement uploadingFile;

    @FindBy(id="bx-destination-tag")
    private WebElement addMoreIcon;

    @FindBy(css = "[title='Link']")
    private WebElement linkIcon;

    @FindBy(css="[placeholder='Link URL']")
    private WebElement attachTheLink;


    @FindBy(css = "[title='Insert video']")
    private WebElement videoInsertIcon;

@FindBy(id="video_idPostFormLHE_blogPostForm-source")
    private WebElement videoSourceLink;

    @FindBy(xpath = "//span[@title='Quote text']")
    private WebElement quoteIcon;

    @FindBy(xpath = "//iframe[@class='bx-editor-iframe']")
    private WebElement frameForQuote;

    @FindBy(id="bx-b-mention-blogPostForm")
    private WebElement addMentionIcon;

    @FindBy(id="lhe_button_editor_blogPostForm")
    private WebElement visualEditorIcon;

    @FindBy(id="bx-html-editor-tlbr-idPostFormLHE_blogPostForm")
    private WebElement textBar;

    @FindBy(id="lhe_button_title_blogPostForm")
    private WebElement topicIcon;

    @FindBy(xpath = "(//span[@class='feed-add-post-form-but-cnt feed-add-videomessage'])[1]")
    private WebElement recordVideoIcon;

@FindBy(id="blog-submit-button-save")
private WebElement sendBtn;
public void makeEditorTextBarVisible(){
    wait.until(ExpectedConditions.elementToBeClickable(visualEditorIcon)).click();
    wait.until(ExpectedConditions.visibilityOf(textBar));
}

public void uploadedFileVisibility(String path){
    uploadedFileIcon.click();
    BrowserUtils.wait(2);
    uploadingFile.sendKeys(path);
    BrowserUtils.wait(2);

}
public void attachingLink(String link){
    linkIcon.click();
    BrowserUtils.wait(2);
    attachTheLink.sendKeys(link);

}
public void insertVideo(String videoLink){
    videoInsertIcon.click();
    BrowserUtils.wait(4);
    videoSourceLink.sendKeys(videoLink);
}


public void frameCreateQuote(  String text){
    wait.until(ExpectedConditions.elementToBeClickable(quoteIcon));
    quoteIcon.click();
   driver.switchTo().frame(frameForQuote);

//    String actual = driver.findElement(By.xpath("//blockquote")).getAttribute("class");
//    String expected = "bxhtmled-quote";
//    Assert.assertEquals(actual,expected);
    driver.findElement(By.xpath("//blockquote")).sendKeys(text);

}
public void sendButton(){
    driver.switchTo().defaultContent();
    sendBtn.click();
}

}

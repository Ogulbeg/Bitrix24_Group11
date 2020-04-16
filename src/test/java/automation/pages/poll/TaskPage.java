package automation.pages.poll;

import automation.pages.AbstractPageBase;
import automation.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TaskPage extends AbstractPageBase {
    @FindBy(id ="tasks-task-priority-cb")
    private WebElement highPriorityChBx;
    @FindBy (css = "input[data-bx-id='task-edit-title']")
    private WebElement taskTitle;
    @FindBy (xpath = "(//iframe)[2]")
    private WebElement descriptionFrame;
    @FindBy (xpath = "//body[@style='min-height: 84px;']")
    private WebElement descriptionTextArea;
    @FindBy (css = "#blog-submit-button-save")
    private WebElement saveBtn;
    @FindBy (css = "#bx-html-editor-tlbr-lifefeed_task_form")
    private WebElement editorTextBar;
    @FindBy (xpath = "//div[@data-bx-id='task-edit-editor-container']//span[@title='Visual editor']")
    private WebElement visualEditorIcn;
    @FindBy (xpath = "//div[@data-bx-id='task-edit-editor-container']//span[@title='Upload files']")
    private WebElement uploadFilesIcn;
    @FindBy (xpath = "//div[@data-bx-id='task-edit-editor-container']//span[@title='Quote text']")
    private WebElement quoteTextIcn;
    @FindBy (xpath = "//div[@data-bx-id='task-edit-editor-container']//span[@title='Add mention']")
    private WebElement addMentionIcn;
    @FindBy (xpath = "//div[@data-bx-id='task-edit-editor-container']//span[@title='Link']")
    private WebElement linkIcn;
    @FindBy (xpath = "//span[text()='Checklist']")
    private WebElement checklistIcn;
    @FindBy (partialLinkText = "Add more")
    private WebElement addMoreLnk;
    @FindBy (xpath = "//span[text()='View task']")
    private WebElement viewTaskBtn;

    @FindBy(css = "[data-bx-id=task-edit-priority]")
    private WebElement highPriorityValue;
    public String getHighPriorityValue(){
        return highPriorityValue.getAttribute("value");
    }

    public void selectHighPriority(){
        wait.until(ExpectedConditions.elementToBeClickable(highPriorityChBx)).click();
    }
    public void enterTaskTitle(String titleValue){
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(taskTitle)).sendKeys(titleValue);
    }
    public void enterTaskDescription (String description){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        descriptionTextArea.click();
        descriptionTextArea.sendKeys(description);
        driver.switchTo().defaultContent();
    }
    public void clickOnSave(){
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }
    public void makeEditorTextBarVisible(){
        wait.until(ExpectedConditions.elementToBeClickable(visualEditorIcn)).click();
        wait.until(ExpectedConditions.visibilityOf(editorTextBar));
    }
    public void pressViewTaskBtnPopUp (){
        driver.switchTo().activeElement();
        wait.until(ExpectedConditions.elementToBeClickable(viewTaskBtn)).click();
    }

}

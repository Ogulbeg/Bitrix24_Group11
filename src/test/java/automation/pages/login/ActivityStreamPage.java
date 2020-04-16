package automation.pages.login;

import automation.pages.AbstractPageBase;
import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ActivityStreamPage extends AbstractPageBase {

    @FindBy(xpath = "//span[contains(@id,'feed-add-post-form-link-text')]")
    public WebElement moreTab;


    @FindBy(xpath = "(//span[@class ='menu-popup-item-text'])[1]")
    private WebElement fileTab;

    @FindBy(xpath = "//span[@class ='menu-popup-item-text']")
    private WebElement tabsUnderMore;


    public void getTabsOnMore(int tabNum) {

        BrowserUtils.waitForPageToLoad(15);
        wait.until(ExpectedConditions.elementToBeClickable(moreTab)).click();
BrowserUtils.wait(6);
        WebElement element = Driver.getDriver().findElement(By.xpath("(//span[@class ='menu-popup-item-text'])[" + tabNum + "]"));

        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();


    }

//Method that verifies a file is downloaded.

    public boolean isFileDownloaded(String path, String filename) {
        return Files.exists(Paths.get(path, filename));
    }

}
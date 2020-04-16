package automation.pages;

import automation.utilities.BrowserUtils;
import automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public abstract class AbstractPageBase {
    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 20);

    @FindBy(xpath = "//span[@class='user-name']")
    protected WebElement currentUser;

    @FindBy(className = "header-search-input")
    protected WebElement searchBar;


    public void navigateTo(String menuName) {

        String name = "//span [@class = 'menu-item-link-text' and contains (text (), '" + menuName + "')]";
        WebElement menuElement = driver.findElement(By.xpath(name));

      /* One-shot way:
         WebElement menuElement = Driver.getDriver().findElement(By.xpath("//span [@class = 'menu-item-link-text' and contains (text (), '"+menuName+"')]"));
      */

        wait.until(ExpectedConditions.visibilityOf(menuElement));
        wait.until(ExpectedConditions.elementToBeClickable(menuElement));
        menuElement.click();

    }
    public String pageSubtitle (){
    WebElement subtitle = Driver.getDriver().findElement(By.id("pagetitle"));
    wait.until(ExpectedConditions.visibilityOf(subtitle));

    String subTitleText = subtitle.getText();
    return subTitleText;


    }
    public String getCurrentUserName(){
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(currentUser));
        return currentUser.getText().trim();
    }
public void searchBar (String searchText){

    BrowserUtils.waitForPageToLoad(10);
    wait.until(ExpectedConditions.visibilityOf(searchBar));
     searchBar.sendKeys(searchText);
     searchBar.click();

}

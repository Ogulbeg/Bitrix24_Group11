package automation.tests.pool;

import automation.pages.login.LoginPage;
import automation.pages.poll.TaskPage;
import automation.tests.AbstractTestBase;
import automation.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskTest extends AbstractTestBase {
    @Test
    public void verifyHighPriorityChkBx() throws InterruptedException {

            test = reports.createTest("Selecting high priority checkbox make task top priority task");
            String titleValue = "Very Important Task";
            String descriptionValue = "Make it High Priority";
            LoginPage loginPage = new LoginPage();
            loginPage.login();
            TaskPage taskPage = new TaskPage();
            taskPage.navigateTo("Task");
            //filling out task form
            //taskPage.enterTaskTitle(titleValue);
            //taskPage.enterTaskDescription(descriptionValue);
            //taskPage.selectHighPriority();
            //taskPage.clickOnSave();
            //taskPage.pressViewTaskBtnPopUp();
            //driver.switchTo().defaultContent();
            //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='side-panel-iframe']")));
            //String actual = driver.findElement(By.cssSelector("#task-detail-important-button")).getAttribute("class");
            BrowserUtils.wait(4);
            WebElement switchLabel = driver.findElement(By.xpath("//label[.='High Priority']"));
            String colorRGB = ((JavascriptExecutor) driver)
                    .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');", switchLabel).toString();
            System.out.println(colorRGB);
            taskPage.selectHighPriority();
            colorRGB = ((JavascriptExecutor) driver)
                    .executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('background-position');", switchLabel).toString();
            System.out.println(colorRGB);
            //String expected = "task-info-panel-important mutable";
            //Assert.assertEquals(actual,expected);
        }
//    @Test
//    public void verifyHighPriorityChkBxVersion2 (){
//        test = reports.createTest("Selecting high priority checkbox make task top priority task");
//        String titleValue = "Very Important Task";
//        String descriptionValue = "Make it High Priority";
//        LoginPage loginPage = new LoginPage();
//        loginPage.login();
//        TaskPage taskPage = new TaskPage();
//        taskPage.navigateTo("Task");
//        BrowserUtils.wait(3);
//        taskPage.selectHighPriority();
//        String expected="2";
//        Assert.assertEquals(taskPage.getHighPriorityValue(),expected,"high property is not selected");
//        BrowserUtils.wait(5);
    //}
    }


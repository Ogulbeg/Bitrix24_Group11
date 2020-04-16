package automation.tests;

import automation.utilities.BrowserUtils;
import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.TestResult;


import java.io.IOException;
import java.util.Date;

public abstract class AbstractTestBaseOgulbeg {
    protected WebDriverWait wait;
    protected Actions actions;
protected WebDriver driver;
    protected ExtentReports reports;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest test;

@BeforeTest
@Parameters("reportName")
public void setupTest(@Optional String reportName){
    System.out.println("Report name "+ reportName);
    String date = new Date().toString().replace("","_").replace(":","_");
    reportName=reportName==null?"report.html":reportName+".html";
    reports = new ExtentReports();
    String reportPath="";

    if(System.getProperty("os.name").toLowerCase().contains("win")){
        reportPath = System.getProperty("user.dir") + "\\test-output\\"+reportName;
    } else {
        reportPath = System.getProperty("user.dir") + "/test-output/"+reportName;
    }
htmlReporter = new ExtentHtmlReporter(reportPath);
    reports.attachReporter(htmlReporter);
    htmlReporter.config().setReportName("Bitrix 24 Test Automation Results");

}
@AfterTest
public void afterTest(){
    reports.flush();
}

    @BeforeMethod
    public void setup() {
        String url = ConfigurationReader.getProperty("url");

       driver= Driver.getDriver();
               driver.get(url);
        Driver.getDriver().manage().window().maximize();

        wait = new WebDriverWait(Driver.getDriver(), 15);
        actions = new Actions(Driver.getDriver());

    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) throws IOException {
        if (iTestResult.getStatus() == TestResult.FAILURE) {
            String screenshotPath = BrowserUtils.getScreenShot(iTestResult.getName());
            test.fail(iTestResult.getName());
            BrowserUtils.wait(2);
            test.addScreenCaptureFromPath(screenshotPath, "Failed");
            test.fail(iTestResult.getThrowable());

        }
        BrowserUtils.wait(2);
        Driver.closeDriver();

    }


}

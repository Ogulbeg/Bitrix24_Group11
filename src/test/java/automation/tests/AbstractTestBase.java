package automation.tests;

import automation.utilities.BrowserUtils;
import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public abstract class AbstractTestBase {


        protected WebDriverWait wait;
        protected Actions actions;

        protected ExtentReports report;
        protected ExtentHtmlReporter htmlReporter;
        protected ExtentTest test;


        @BeforeTest
        @Parameters("reportName")
        public void setupTest(@Optional String reportName) {
            System.out.println("Report name: " + reportName);
            reportName = reportName == null ? "report.html" : reportName + ".html";

            report = new ExtentReports();

            String reportPath = "";

            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                reportPath = System.getProperty("user.dir") + "\\test-output\\" + reportName;
            } else {
                reportPath = System.getProperty("user.dir") + "/test-output/" + reportName;
            }

            htmlReporter = new ExtentHtmlReporter(reportPath);

            report.attachReporter(htmlReporter);
            htmlReporter.config().setReportName("Bitrix24 Test Automation Results");
        }

        @AfterTest
        public void afterTest() {

            report.flush();
        }

        @BeforeMethod
        public void setup() {
            String URL = ConfigurationReader.getProperty("url");
            Driver.getDriver().get(URL);
            Driver.getDriver().manage().window().maximize();
            wait = new WebDriverWait(Driver.getDriver(), 25);
            actions = new Actions(Driver.getDriver());
        }


        @AfterMethod
        public void teardown(ITestResult iTestResult) throws IOException {

            if (iTestResult.getStatus() == ITestResult.FAILURE) {

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


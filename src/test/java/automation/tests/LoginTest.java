package automation.tests;

import automation.pages.login.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends AbstractTestBase {

    LoginPage loginPage = new LoginPage();

    @Test
    public void loginTest1() {

        loginPage.login();
        loginPage.navigateTo("Activity Stream");
        System.out.println(loginPage.pageSubtitle());
        System.out.println("Current user: " + loginPage.getCurrentUserName());
        loginPage.searchBar("Contact info");

    }
}

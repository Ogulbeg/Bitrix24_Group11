package automation.tests.pool;

import automation.pages.login.LoginPageOgulbeg;
import automation.tests.AbstractTestBaseOgulbeg;
import automation.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTestOgulbeg extends AbstractTestBaseOgulbeg {

    @Test
    public void validCredentialLogin(){
        test= reports.createTest("Valid credential login");
        LoginPageOgulbeg loginPageOgulbeg = new LoginPageOgulbeg();
        loginPageOgulbeg.login();
        test.info("Login as helpdesk employee");
        Assert.assertEquals(Driver.getDriver().getTitle(),"Portal");
        test.pass("Page title Portal was verified");
    }
@Test(dataProvider="credentials")
    public void verifyCredentialDDT(String username){
    test = reports.createTest("Valid multiple credentials login");
    LoginPageOgulbeg loginPageOgulbeg = new LoginPageOgulbeg();
    loginPageOgulbeg.login(username);
    test.info(String.format("login as %s employee",username));
    Assert.assertEquals(Driver.getDriver().getTitle(),"Portal");
    test.pass("Page title Portal was verified");

}

@DataProvider
    public Object[] credentials(){
        return new Object[]{"helpdesk","marketing","hr"};
}

    @Test (dataProvider = "invalid_credentials")
    public void invalidCredentialLogin (String username, String password){
        test = reports.createTest(String.format("Log in with invalid credentials %s and %s",username,password));
        LoginPageOgulbeg loginPageOgulbeg = new LoginPageOgulbeg();
        loginPageOgulbeg.login(username, password);
        String expected = "Incorrect login or password";
        test.pass(expected);
    }
    @DataProvider (name = "invalid_credentials")
    public Object[][] invalidCredentials (){
        return new Object[][]{{"invalid_user_name", "UserUser"},{ "helpdesk45@cybertekschool.com","incorrect_password"}};
    }
}

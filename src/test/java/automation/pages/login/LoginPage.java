package automation.pages.login;

import automation.utilities.ConfigurationReader;
import automation.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(name = "USER_LOGIN")
    public WebElement userName;
    @FindBy  (name="USER_PASSWORD")
    public WebElement password;
    @FindBy (className = "login-btn")
    public WebElement loginBtn;

    public void login(){
       userName.sendKeys(ConfigurationReader.getProperty("helpdesk"));
       password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);

    }

    /**
     * This method
     * @param userNameValue email address
     * @param passwordValue password
     */
    public void login(String userNameValue,String passwordValue){
        userName.sendKeys(userNameValue);
        password.sendKeys(passwordValue);
    }

    // to read from config file
    public void login(String cUserName){
        try {
            userName.sendKeys(ConfigurationReader.getProperty(cUserName));
            password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
        }catch (Exception e){
            System.out.println("Invalid username in config.prop. file");
        }

    }

}

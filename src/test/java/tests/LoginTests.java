package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preConditions(){
        if(app.getUser().isLogged()){
            app.getUser().logOut();
        }
    }

    @Test
    public void loginPositive() {
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm("liora@gmail.com", "$Liora12345");
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//h1[.='Logged in']")));
        app.getUser().clickOkButton();
        Assert.assertTrue(app.getUser().isLogged());
    }
    @AfterMethod
    public void postCondition(){

    }

}

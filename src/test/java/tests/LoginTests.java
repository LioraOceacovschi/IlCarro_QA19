package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preConditions() {
        if (app.getUser().isLogged()) {
            app.getUser().logOut();
        }
    }

    @Test
    public void loginPositiveTest() {
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm("liora@gmail.com", "$Liora12345");
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @Test
    public void loginPositiveTestUser() {
        User user = new User()
                .withEmail("liora@gmail.com")
                .withPassword("$Liora12345");
        app.getUser().openLoginForm();
//        app.getUser().fillLoginForm("liora@gmail.com", "$Liora12345");
//        app.getUser().fillLoginForm(user.getEmail(), user.getPassword());
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @Test
    public void loginWrongPassNegativeTest() {
        User user = new User().withEmail("liora@gmail.com").withPassword("iora12345");
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedFailed());


    }

    @Test
    public void loginWrongEmailNegativeTest() {
        User user = new User().withEmail("lioragmail.com").withPassword("$Liora12345");
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isIncorrectTypeEmailNotification());
    }

    @AfterMethod
    public void postCondition() {
        if (app.getUser().isOkButtonPresent())
            app.getUser().clickOkButton();
    }
}

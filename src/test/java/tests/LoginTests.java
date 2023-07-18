package tests;

import models.User;
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
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @Test
    public void loginPositiveTestUser() {
        User user = User.builder()
                .email("liora@gmail.com")
                .password("$Liora12345")
                .build();
        app.getUser().openLoginForm();
//        app.getUser().fillLoginForm("liora@gmail.com", "$Liora12345");
//        app.getUser().fillLoginForm(user.getEmail(), user.getPassword());
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @Test
    public void loginWrongPassNegativeTest() {
        User user = User.builder()
                .email("liora@gmail.com")
                .password("iora12345")
                .build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isLoggedFailed());


    }

    @Test
    public void loginWrongEmailNegativeTest() {
        User user = User.builder()
                .email("lioragmail.com")
                .password("$Liora12345")
                .build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isIncorrectTypeEmailNotification());
    }

    @AfterMethod
    public void postCondition() {
        if (app.getUser().isOkButtonPresent())
            app.getUser().clickOkButton();
    }
}

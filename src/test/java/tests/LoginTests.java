package tests;

import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
        if (app.getUser().isLogged()) {
            app.getUser().logOut();
        }
    }

    @Test(groups = {"smoke"})
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
        logger.info("LOGIN TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }
    @Test(dataProvider = "userModelListDTO",dataProviderClass = ProviderData.class)
    public void loginPositiveUserDTO(User user) {
        logger.info("LOGIN TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }
    @Test(dataProvider = "loginUserModelDTO_CSV",dataProviderClass = ProviderData.class)
    public void loginPositiveUserDTO_CSV(User user) {
        logger.info("LOGIN TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @Test(groups = {"sanity","smoke"})
    public void loginWrongPassNegativeTest() {
        User user = User.builder()
                .email("liora@gmail.com")
                .password("iora12345")
                .build();
        logger.info("LOGIN TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isLoggedFailed());


    }
    @Test
    public void loginWrongPassNegativeTest2() {
        User user = User.builder()
                .email("liora@gmail.com")
                .password("Liora12345")
                .build();
        logger.info("LOGIN TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
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

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        if (app.getUser().isOkButtonPresent())
            app.getUser().clickOkButton();


    }
}

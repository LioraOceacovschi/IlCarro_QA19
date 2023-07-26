package tests;

import manager.NgListener;
import manager.ProviderData;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(NgListener.class)
public class RegistrationTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        app.getUser().clickOnLogoInHeader();
        if (app.getUser().isLogged())
            app.getUser().logOut();
    }

    @Test(groups = {"sanity","smoke"})
    public void registrationPositive() {

        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        User user = User.builder()
                .name("John")
                .lastname("Snow")
                .email("snow" + i + "@gmail.com")
                .password("$Asdf1234")
                .build();
        logger.info("REGISTRATION TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().clickCheckbox();
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isRegisteredSuccess());
    }


    @Test(dataProvider = "regUserModelDTO",dataProviderClass = ProviderData.class)
    public void registrationPositiveDTO(User user) {
        logger.info("REGISTRATION TEST STARTS WITH DATA: " +user.toString());
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().clickCheckbox();
        app.getUser().submitLoginRegForm();
        Assert.assertTrue(app.getUser().isRegisteredSuccess());
    }

    @Test(groups = {"sanity"})
    public void registrationWrongPasswordNegavite() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        User user = User.builder()
                .name("John")
                .lastname("Snow")
                .email("snow" + i + "@gmail.com")
                .password("Asdf1234")
                .build();
        logger.info("REGISTRATION TEST STARTS WITH DATA: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().clickCheckbox();
        Assert.assertTrue(app.getUser().isWrongFormatPasswordMessage());
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        if (app.getUser().isOkButtonPresent())
            app.getUser().clickOkButton();
    }


}

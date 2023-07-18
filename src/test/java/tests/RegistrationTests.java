package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getUser().isLogged())
            app.getUser().logOut();
    }

    @Test
    public void registrationPositive() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        User user = User.builder()
                .name("John")
                .lastname("Snow")
                .email("snow" + i + "@gmail.com")
                .password("$Asdf1234")
                .build();
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().clickCheckbox();
        app.getUser().submitLoginRegForm();
        logger.info("Registration tests starts with data: " + user.getEmail() + " & " + user.getPassword());
        Assert.assertTrue(app.getUser().isRegisteredSuccess());
    }

    @Test
    public void registrationWrongPasswordNegavite() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        User user = User.builder()
                .name("John")
                .lastname("Snow")
                .email("snow" + i + "@gmail.com")
                .password("Asdf1234")
                .build();
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().clickCheckbox();
        Assert.assertTrue(app.getUser().isWrongFormatPasswordMessage());
    }

    @AfterMethod
    public void postCondition(){
        if(app.getUser().isOkButtonPresent())
            app.getUser().clickOkButton();
    }


}

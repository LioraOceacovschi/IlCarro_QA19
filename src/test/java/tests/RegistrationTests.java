package tests;

import manager.NgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//@Listeners(NgListener.class)
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
        logger.info("Registration tests starts with data: " + user.getEmail() + " & " + user.getPassword());
        app.getUser().openRegistrationForm();
        logger.info("Method openRegistrationForm() invoked");
        app.getUser().fillRegistrationForm(user);
        logger.info("Method fillRegistrationForm() invoked");
        app.getUser().clickCheckbox();
        logger.info("Method clickCheckbox() invoked");
        app.getUser().submitLoginRegForm();
        logger.info("Method submitLoginRegForm() invoked");
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

package tests;

import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){

    }

    @Test
    public void registrationPositive(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        User user = new User()
                .withName("John")
                .withLastname("Snow")
                .withEmail("snow"+ i + "@gmail.com")
                .withPassword("$Asdf1234");
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
    }

}

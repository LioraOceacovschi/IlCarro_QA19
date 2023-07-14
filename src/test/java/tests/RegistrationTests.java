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

        User user = User.builder()
                .name("John")
                .lastname("Snow")
                .email("snow"+ i + "@gmail.com")
                .password("$Asdf1234")
                .build();
        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
    }

}

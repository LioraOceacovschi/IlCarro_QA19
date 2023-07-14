package tests;

import models.Car;
import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {
//isLogged?
    //open form
    //fill form
    //atach foto
    //submit form

    @BeforeMethod
    public void preConditions() {
        if (app.getUser().isLogged() == false)
            app.getUser().login(new User()
                    .withEmail("liora@gmail.com")
                    .withPassword("$Liora12345"));
    }

    @Test
    public void addNewCarPositive() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Car car = Car.builder()
                .location("Haifa")
                .manufacture("KIA")
                .model("Sportage")
                .year("2023")
                .fuel("Petrol")
                .seats("5")
                .carClass("B")
                .carRegNumber("100-200-" + i)
                .price("500")
                .build();

        app.getCar().openCarForm();
        app.getCar().fillCarForm(car);

    }

}

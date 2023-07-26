package tests;

import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarTests extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void preConditions() {
        app.getUser().clickOnLogoInHeader();

        if (!app.getUser().isLogged())
            app.getUser().login(User.builder()
                    .email("liora@gmail.com")
                    .password("$Liora12345")
                    .build());
    }

    @Test(groups = {"sanity","smoke"})
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
        app.getCar().submitCarForm();
        Assert.assertTrue(app.getCar().isCarAdded());

    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        app.getCar().clickAddAnotherCarButton();
    }

}

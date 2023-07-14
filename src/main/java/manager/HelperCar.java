package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(5000);
        click(By.id("1"));
    }

    public boolean isCarFormPresent() {
        return new WebDriverWait(wd, 10)
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(By.cssSelector("h1"), "Let the car work"));
    }

    public void typeLocation(String location) {
        type(By.id("pickUpPlace"),location);
        click(By.xpath("//div[@class='pac-item'][position()=1]"));
    }

    public void select(By locator,String option){
        new Select(wd.findElement(locator)).selectByValue(option);
    }

    public void fillCarForm(Car car){
        if(isCarFormPresent()==false) return;
        typeLocation(car.getLocation());
        type(By.id("make"),car.getManufacture());
        type(By.id("model"),car.getModel());
        type(By.id("year"),car.getYear());
        select(By.id("fuel"),car.getFuel());
        type(By.id("seats"),car.getSeats());
        type(By.id("class"),car.getCarClass());
        type(By.id("serialNumber"),car.getCarRegNumber());
        type(By.id("price"),car.getPrice());

    }
}

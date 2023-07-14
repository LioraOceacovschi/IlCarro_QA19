package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    HelperUser user;
    HelperCar car;
    public void init(){
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro.web.app/search");
        user = new HelperUser(wd);
        car = new HelperCar(wd);
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public HelperUser getUser() {
        return user;
    }

    public HelperCar getCar() {
        return car;
    }

    public void tearDown(){
        wd.quit();;
    }

}

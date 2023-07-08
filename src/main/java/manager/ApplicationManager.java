package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    HelperUser user;
    public void init(){
        wd = new ChromeDriver();
        wd.navigate().to("https://ilcarro.web.app/search");
        user = new HelperUser(wd);
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public HelperUser getUser() {
        return user;
    }

    public void tearDown(){
        wd.quit();;
    }

}

package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    EventFiringWebDriver wd;
    HelperUser user;
    HelperCar car;
    String browser;
    HelperSearch search;
    Properties properties;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        properties.load(new FileReader(new File("src/test/resources/prod_config.properties")));
        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Testing on Chrome Driver");
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Testing on Firefox Driver");
        }
        wd.register(new MyListener());
//        wd.manage().window().maximize();
//        wd.navigate().to("https://ilcarro.web.app/search");
        wd.navigate().to(properties.getProperty("web.baseURL"));
        user = new HelperUser(wd);
        car = new HelperCar(wd);
        search = new HelperSearch(wd);
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public HelperSearch getSearch() {
        return search;
    }

    public HelperUser getUser() {
        return user;
    }

    public HelperCar getCar() {
        return car;
    }

    public void tearDown(){
        wd.quit();
    }

}

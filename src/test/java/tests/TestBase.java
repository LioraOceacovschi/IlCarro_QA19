package tests;

import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setApp(){
        app.init();
    }

    @AfterSuite
    public void stop(){
    //    app.tearDown();
    }

    @BeforeMethod
    public void startTest(Method method){
        logger.info("            @@@@@@@@@ START TEST " + method + "  @@@@@@@@@@@");

    }

    @AfterMethod
    public  void  finishTest(Method method){
        logger.info("            ######## FINISHED TEST " + method + "  ##########");

    }
}

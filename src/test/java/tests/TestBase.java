package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.internal.TestResult;

public class TestBase {

    public static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setApp(){
        app.init();
    }

    @AfterSuite
    public void stop(){
    //    app.tearDown();
    }
}

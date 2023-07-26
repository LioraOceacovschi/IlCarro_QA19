package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase {
    //fillSearchForm with location
    //fillSearchForm with date
    //submit
    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        app.getSearch().clickOnLogoInHeader();
        app.getSearch().clearSearchForm();

    }

    @Test
    public void searchCarPositiveTest() {
        app.getSearch().fillSearchForm("Haifa", "10/23/2023", "10/29/2023");
        app.getSearch().submitCarSearch();
        Assert.assertFalse(app.getCar().isElementPresent(By.xpath("//h2[contains(.,'Type your data and hit Yalla!')]")));
    }
    @Test
    public void searchCarPositiveTestDatePickerDate() {
        app.getSearch().fillSearchFormByDatePickerWithPeriodDays("Tel Aviv", "10/26/2023", "12/29/2023");
        app.getSearch().submitCarSearch();
        Assert.assertFalse(app.getCar().isElementPresent(By.xpath("//h2[contains(.,'Type your data and hit Yalla!')]")));

    }

    @Test
    public void searchCarPositiveTestDatePickerMonthDays() {
        app.getSearch().fillSearchFormByDatePickerWithPeriodDaysAndMonth("Tel Aviv", "08/24/2023", "08/29/2023");
        app.getSearch().submitCarSearch();
        Assert.assertFalse(app.getCar().isElementPresent(By.xpath("//h2[contains(.,'Type your data and hit Yalla!')]")));

    }
    @Test
    public void searchCarPositiveTestDatePickerMonthDaysYear() {
        app.getSearch().fillSearchFormByDatePickerWithPeriodDaysMonthAndYear("Tel Aviv", "09/24/2023", "05/29/2024");
        app.getSearch().submitCarSearch();
        Assert.assertFalse(app.getCar().isElementPresent(By.xpath("//h2[contains(.,'Type your data and hit Yalla!')]")));


    }

    @AfterMethod
    public void postCondition(){
        app.getSearch().clickOnLogoInHeader();

    }


}

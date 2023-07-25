package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase {
    //fillSearchForm with location
    //fillSearchForm with date
    //submit
    @BeforeMethod(alwaysRun = true)
    public void preCondition() {

    }

    @Test
    public void searchCarPositiveTest() {
        app.getSearch().fillSearchForm("Tel Aviv", "10/23/2023", "10/29/2023");
        app.getSearch().submitCarSearch();
    }
    @Test
    public void searchCarPositiveTestDatePickerDate() {
        app.getSearch().fillSearchFormByDatePickerWithPeriodDays("Tel Aviv", "10/23/2023", "12/29/2023");
        app.getSearch().submitCarSearch();
    }

    @Test
    public void searchCarPositiveTestDatePickerMonthDays() {
        app.getSearch().fillSearchFormByDatePickerWithPeriodDaysAndMonth("Tel Aviv", "08/24/2023", "08/29/2023");
        app.getSearch().submitCarSearch();
    }
    @Test
    public void searchCarPositiveTestDatePickerMonthDaysYear() {
        app.getSearch().fillSearchFormByDatePickerWithPeriodDaysMonthAndYear("Tel Aviv", "07/24/2023", "05/29/2024");
        app.getSearch().submitCarSearch();
    }


}

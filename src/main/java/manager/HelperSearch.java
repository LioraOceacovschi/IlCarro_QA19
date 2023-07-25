package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase {
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void fillSearchForm(String city, String dateFrom, String dateTo) {
        fillCity(city);
        selectPeriodDays(dateFrom, dateTo);
    }

    public void selectPeriodDays(String dateFrom, String dateTo) {
        type(By.id("dates"), dateFrom + " - " + dateTo);
        pause(3000);

    }

    public void fillCity(String city) {
        type(By.id("city"), city);
        pause(2000);
        click(By.cssSelector("div.pac-item"));
        pause(2000);
    }

    public void fillSearchFormByDatePickerWithPeriodDays(String city, String dateFrom, String dateTo) {
        fillCity(city);
        selectPeriodByDatePickerWithPeriodDays(dateFrom, dateTo);
    }

    public void selectPeriodByDatePickerWithPeriodDays(String dateFrom, String dateTo) {
        String[] startDate = dateFrom.split("/");
        String[] endDate = dateTo.split("/");
        click(By.id("dates"));
        pause(2000);
//        click(By.xpath("//div[.=' "+startDate[1]+" ']"));
//        click(By.xpath("//div[.=' "+endDate[1]+" ']"));
        String locatorStartDate = String.format("//div[.=' %s ']", startDate[1]);
        String locatorEndDate = String.format("//div[.=' %s ']", endDate[1]);
        click(By.xpath(locatorStartDate));
        click(By.xpath(locatorEndDate));
        pause(3000);

    }

    public void fillSearchFormByDatePickerWithPeriodDaysAndMonth(String city, String dateFrom, String dateTo) {
        fillCity(city);
        selectPeriodByDatePickerWithPeriodDaysAndMonth(dateFrom, dateTo);
    }

    public void selectPeriodByDatePickerWithPeriodDaysAndMonth(String dateFrom, String dateTo) {
        //10/25/2023 - 12/6/2023
        String[] startDate = dateFrom.split("/");
        String[] endDate = dateTo.split("/");
        int startMonth = Integer.parseInt(startDate[0]);
        int endMonth = Integer.parseInt(endDate[0]);
        int monthNow = LocalDate.now().getMonthValue();
        int fromStartToEndMonth = endMonth - startMonth;
        int fromNowToStartMonth = startMonth - monthNow;
        String locatorStartDate = String.format("//div[.=' %s ']", startDate[1]);
        String locatorEndDate = String.format("//div[.=' %s ']", endDate[1]);
        click(By.id("dates"));
        if (startMonth > monthNow) {
            for (int i = fromNowToStartMonth; i != 0; i--) {
                pause(2000);
                click(By.xpath("//button[@aria-label='Next month']"));
            }
        }
        click(By.xpath(locatorStartDate));

        if (endMonth > startMonth) {
            for (int i = fromStartToEndMonth; i != 0; i--) {
                pause(2000);
                click(By.xpath("//button[@aria-label='Next month']"));
            }
        }
        click(By.xpath(locatorEndDate));

//        if (monthNow != startMonth) {
//            fromNowToStartMonth = startMonth - monthNow;
//        }
//        for (int i = 0; i < fromNowToStartMonth; i++) {
//            click(By.xpath("//button[@aria-label='Next month']"));
//        }
//        click(By.xpath(locatorStartDate));
//
//        for(int i =0; i<fromStartToEndMonth;i++){
//            click(By.xpath("//button[@aria-label='Next month']"));
//
//        }
//        click(By.xpath(locatorEndDate));
    }

    public void fillSearchFormByDatePickerWithPeriodDaysMonthAndYear(String city, String dateFrom, String dateTo) {
        fillCity(city);
        selectPeriodByDatePickerWithPeriodDaysMonthAndYear(dateFrom, dateTo);
    }

    public void selectPeriodByDatePickerWithPeriodDaysMonthAndYear(String dateFrom, String dateTo) {
        //10/25/2023 - 12/6/2023
        LocalDate startDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate endDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate nowDate = LocalDate.now();
        String locatorStartDate = String.format("//div[.=' %s ']", startDate.getDayOfMonth());
        String locatorEndDate = String.format("//div[.=' %s ']", endDate.getDayOfMonth());

        click(By.id("dates"));

        int fromNowToStartMonth = startDate.getYear() - nowDate.getYear() == 0 ?
                startDate.getMonthValue() - nowDate.getMonthValue() :
                12 - nowDate.getMonthValue() + startDate.getMonthValue();

        for (int i = 0; i < fromNowToStartMonth; i++) {
            pause(1000);
            click(By.xpath("//button[@aria-label='Next month']"));
        }
        pause(2000);
        click(By.xpath(locatorStartDate));


        int fromStartToEndMonth = endDate.getYear() - startDate.getYear() == 0 ?
                endDate.getMonthValue() - startDate.getMonthValue() :
                12 - startDate.getMonthValue() + endDate.getMonthValue();

        for (int i = 0; i < fromStartToEndMonth; i++) {
            pause(1000);
            click(By.xpath("//button[@aria-label='Next month']"));
        }
        pause(2000);
        click(By.xpath(locatorEndDate));


    }


    public void submitCarSearch() {
        click(By.xpath("//button[contains(.,\"alla\")]"));
    }


}

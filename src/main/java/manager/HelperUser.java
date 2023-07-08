package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase {


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }
    public boolean isLogged(){
       return isElementPresent(By.xpath("//a[.=' Logout ']"));
    }
    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void submitLogin() {
        click(By.xpath("//button[contains(.,\"alla!\")]"));
    }

    public void clickOkButton() {
        click(By.xpath("//button[.='Ok']"));
    }

    public void logOut() {
        click(By.xpath("//a[.=' Logout ']"));
    }
}

package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase {


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[.=' Logout ']"));
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void fillLoginForm(User user) {
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
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

    public boolean isLoggedSuccess() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[contains(.,'success')]")));
        return wd.findElement(By.xpath("//h2[contains(.,'success')]"))
                .getText().contains("success");
    }

    public boolean isLoggedFailed() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h1[.='Login failed']")));
        return wd.findElement(By.xpath("//h1[.='Login failed']")).getText()
                .contains("failed");
    }

    public boolean isIncorrectTypeEmailNotification() {
        new WebDriverWait(wd, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(text(),\"It'snot look like email\")]")));
        return isElementPresent(By.xpath("//div[contains(text(),\"It'snot look like email\")]"));
    }

    public boolean isOkButtonPresent() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        try {
          WebElement  element = wait.until(ExpectedConditions
              .visibilityOfElementLocated(By.xpath("//button[.='Ok']")));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void login(User user){
        openLoginForm();
        fillLoginForm(user);
        submitLogin();
        clickOkButton();
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getLastname());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
        click(By.xpath("//label[@for='terms-of-use']"));
    }
}

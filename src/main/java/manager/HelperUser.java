package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
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
        pause(2000);
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

    public void submitLoginRegForm() {
        click(By.xpath("//button[contains(.,\"alla\")]"));
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
                .visibilityOfElementLocated(By.xpath("//h2[.='Logged in success']")));
        return isElementPresent(By.xpath("//h2[.='Logged in success']"));
    }

    public boolean isLoggedFailed() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h1[.='Login failed']")));
        return isElementPresent(By.xpath("//h1[.='Login failed']"));
    }

    public boolean isIncorrectTypeEmailNotification() {
        new WebDriverWait(wd, 10).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(text(),\"It'snot look like email\")]")));
        return isElementPresent(By.xpath("//div[contains(text(),\"It'snot look like email\")]"));
    }

    public boolean isOkButtonPresent() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        try {
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//button[contains(.,'Ok')]")));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submitLoginRegForm();
        clickOkButton();
    }

    public void fillRegistrationForm(User user) {
        type(By.id("name"), user.getName());
        type(By.id("lastName"), user.getLastname());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void clickCheckbox() {
//        Variant1
        JavascriptExecutor script = (JavascriptExecutor)wd;
        script.executeScript("document.querySelector('#terms-of-use').click();");

//        Variant2
//        Actions actions = new Actions(wd);
//        actions.sendKeys(Keys.PAGE_DOWN).perform();
//        pause(2000);
//        WebElement element = new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkbox-container")));
//        element.sendKeys(Keys.TAB);
//        Rectangle rect = element.getRect();
//        int x = rect.getX() + 5;
//        int y = rect.getY() + rect.getHeight() / 2;
//        actions.moveByOffset(x, y).click().perform();


    }

    public boolean isRegisteredSuccess() {
        WebDriverWait wait = new WebDriverWait(wd, 10);
        WebElement element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h1[contains(.,'Registered')]")));
        return element.getText().contains("Registered");
    }

    public boolean isWrongFormatPasswordMessage() {
        click(By.xpath("//input[@id='email']"));
        new WebDriverWait(wd, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Password must contain')]")));
        return isElementPresent(By.xpath("//div[contains(text(),'Password must contain')]"));
    }
}

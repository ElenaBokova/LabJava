package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page1 {

    WebDriver driver;
    By email = By.xpath("//div//table//input[@id='email']");
    By password = By.xpath("//div//table//input[@id='pass']");
    By buttonSearch = By.xpath("//div//table//input[@id='u_0_b']");
    By searchAccount = By.xpath("//div/a[@href='https://www.facebook.com/recover/initiate?lwv=110&ars=royal_blue_bar']");

    public Page1(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String strEmail) {
        driver.findElement(email).sendKeys(strEmail);
    }

    public void setPassword(String strPassword) {
        driver.findElement(password).sendKeys(strPassword);
    }

    public void clickLogin() {
        WebElement button = driver.findElement(buttonSearch);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
    }

    public void clickSearchAccount() {
        WebElement button = driver.findElement(searchAccount);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
    }

    public void loginToFacebook(String strEmail, String strPassword) {
        this.setEmail(strEmail);
        this.setPassword(strPassword);
        this.clickLogin();
    }

    public void searchAccount(){
        this.clickSearchAccount();
    }
}

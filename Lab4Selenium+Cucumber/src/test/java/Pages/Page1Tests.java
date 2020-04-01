package Pages;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.testng.Assert.assertTrue;

public class Page1Tests {

    WebDriver driver;
    WebElement error, search;
    Page1 objLogin, objAccount;


    @Given("^Login facebook$")
    public void loginFailed() {
        driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com");
        objLogin = new Page1(driver);
        objAccount = new Page1(driver);


    }

    @When("^I enter email and password$")
    public void iEnterEmailAndPassword() {

        objLogin.loginToFacebook("89000000000", "hellomyfriend");

        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("войдите на facebook");
            } });

        error = driver.findElement(By.xpath("//div/input[@id='pass']"));

    }

    @Then("^The system asks for the password again$")
    public void theSystemAsksForThePasswordAgain() {

        assertTrue(error.isDisplayed());

    }

    @When("^Click on button for recovery$")
    public void clickOnButtonForRecovery() {

        objAccount.searchAccount();

        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("утерян пароль");
            } });

        search = driver.findElement(By.xpath("//div[@class='clearfix uiHeaderTop']"));

    }

    @Then("^Recovery page$")
    public void recoveryPage() {
        assertTrue(search.isDisplayed());
    }


    @Then("^Close first browser$")
    public void closeBrowser() {

        driver.quit();
    }
}
package Pages;

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

public class Page2Tests {

    WebDriver driver;
    Page2 objTerms, objNorms;
    WebElement terms, norms;


    @Given("^Go to terms of use$")
    public void goToTermsOfUse() {

        driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com");
        objTerms = new Page2(driver);
        objNorms = new Page2(driver);

    }

    @When("^Click on button terms$")
    public void clickOnButtonTerms() {

        objTerms.buttonTerms();


        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("правила facebook");
            } });

        terms = driver.findElement(By.xpath("//div/a[@class='policiesPolicy mvl']"));
    }

    @Then("^Link on standards$")
    public void linkOnStandards() {

        assertTrue(terms.isDisplayed());
    }

    @When("^Click on button norms$")
    public void clickOnButtonNorms() {

        objNorms.buttonTerms();


        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("правила facebook");
            } });

       norms = driver.findElement(By.xpath("//div[@class='policiesContent']"));
    }

    @Then("^Link on norms$")
    public void linkOnNorms() {

        assertTrue(norms.isDisplayed());
    }

    @Then("^Close second browser$")
    public void closeBrowser() {

        driver.quit();
    }

}
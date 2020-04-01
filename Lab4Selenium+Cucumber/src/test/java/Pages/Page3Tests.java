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

public class Page3Tests {

    WebDriver driver;
    Page3 objRefer, objReg;
    WebElement enter, smsCode;

    @Given("^Login youtube$")
    public void loginYouTube() {

        driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com/signup/v2/webcreateaccount?service=youtube&continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Faction_handle_signin%3Dtrue%26app%3Ddesktop%26hl%3Dru%26next%3Dhttps%253A%252F%252Fwww.youtube.com%252F&hl=ru&gmb=exp&biz=false&flowName=GlifWebSignIn&flowEntry=SignUp&nogm=true");
        objRefer = new Page3(driver);
        objReg = new Page3(driver);

    }

    @When("^Click on button to entrance$")
    public void clickOnButtonToEntrance() {

        objRefer.enterButton();

        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("youtube");
            } });

        enter = driver.findElement(By.xpath("//div[@class='jXeDnc']"));
    }


    @Then("^Page entrance$")
    public void pageEntrance() {

        assertTrue(enter.isDisplayed());
    }


    @When("^Enter data$")
    public void enterData() {

        objReg.regNewAccount("Елена", "Бокова", "alenabokovaaa@yandex.ru", "hellomyfriend", "hellomyfriend");

        (new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("создайте аккаунт");
            } });


        smsCode = driver.findElement(By.xpath("//div/input[@id='code']"));

    }

    @Then("^Get sms for registration$")
    public void getSmsForRegistration() {

        assertTrue(smsCode.isDisplayed());
    }


    @Then("^Close third browser$")
    public void closeBrowser() {

        driver.quit();
    }

}
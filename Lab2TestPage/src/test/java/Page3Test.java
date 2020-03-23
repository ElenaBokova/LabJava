import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class Page3Test {

    WebDriver driver;
    Page3 objRefer;
    StringBuffer verificationErrors = new StringBuffer();

    @BeforeTest
    public void setUp() {

        driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com/signup/v2/webcreateaccount?service=youtube&continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Faction_handle_signin%3Dtrue%26app%3Ddesktop%26hl%3Dru%26next%3Dhttps%253A%252F%252Fwww.youtube.com%252F&hl=ru&gmb=exp&biz=false&flowName=GlifWebSignIn&flowEntry=SignUp&nogm=true");
    }

    @AfterTest
    public void tearDown() {

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @Test
    public void testReferButton() {

        try {
            objRefer = new Page3(driver);
            objRefer.enterButton();

            (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith("youtube");
                } });

           WebElement enter = driver.findElement(By.xpath("//div[@class='jXeDnc']"));

           assertTrue(enter.isDisplayed());
        }catch (Error e){

            verificationErrors.append(e.toString());
        }
    }
}
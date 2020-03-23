import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class Page2Test {

    WebDriver driver;
    Page2 objTerms;
    StringBuffer verificationErrors = new StringBuffer();

    @BeforeTest
    public void setUp() {

        driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com");
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
    /* В данном тесте происходит проверка предоставления пользовательского соглашения*/
    public void testClickButton() {

        try {
            objTerms = new Page2(driver);
            objTerms.buttonTerms();


            (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith("правила facebook");
                } });

            WebElement terms = driver.findElement(By.xpath("//div/a[@class='policiesPolicy mvl']"));

            assertTrue(terms.isDisplayed());
        }catch (Error e){

            verificationErrors.append(e.toString());
        }
    }
}
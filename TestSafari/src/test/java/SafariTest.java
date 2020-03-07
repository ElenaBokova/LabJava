import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class SafariTest {
    WebDriver driver = new SafariDriver();
    StringBuffer verificationErrors = new StringBuffer();

    @BeforeTest
    public void setUp() {

        driver.get("https://yandex.ru");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }

    }

    @Test
    public void testMain() {

        try {
            WebElement text = driver.findElement(By.xpath("//div[@class='search2__input']//input[@name='text']"));
            text.sendKeys("Google");
            text.submit();

            WebElement button = driver.findElement(By.xpath("//div[@class='search2__button']//span[@class='button__text']"));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", button);

            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith("google");
                } });



            WebElement link = driver.findElement(By.xpath("//a[@href='https://www.google.ru/']"));

            assertTrue(link.isDisplayed());
        }catch (Error e){

            verificationErrors.append(e.toString());
        }
    }
}
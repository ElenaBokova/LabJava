import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.testng.Assert.*;

public class Page1Test1 {

    WebDriver driver;
    Page1 objLogin;
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
    /* В данном тесте происходит проверка вывода ошибки о неправильно введенный данных в полях email и пароля*/
    public void testLogin() {


        try {
            objLogin = new Page1(driver);
            objLogin.loginToFacebook("89000000000", "hellomyfriend");

            (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                 return d.getTitle().toLowerCase().startsWith("войдите на facebook");
            } });

            WebElement error = driver.findElement(By.xpath("//div[@id = 'error_box']"));

            assertTrue(error.isDisplayed());
        }catch (Error e){

            verificationErrors.append(e.toString());
        }


    }


}
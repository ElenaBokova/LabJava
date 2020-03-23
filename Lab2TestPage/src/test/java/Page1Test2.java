import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class Page1Test2 {

    WebDriver driver;
    Page1 objAccount;
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
    /* В данном тесте проверяется перенаправление на страницу поиска и восстановления аккаунта*/
    public void testClickSearchAccount() {

        try {
            objAccount = new Page1(driver);
            objAccount.searchAccount();

            (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith("утерян пароль");
                } });

            WebElement search = driver.findElement(By.xpath("//div[@class='clearfix uiHeaderTop']"));

            assertTrue(search.isDisplayed());
        }catch (Error e){

            verificationErrors.append(e.toString());
        }
    }
}
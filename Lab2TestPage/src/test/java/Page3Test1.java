import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class Page3Test1 {

    WebDriver driver;
    Page3 objReg;
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


    }

    @Test
    /* В данном тесте происходит проверка перенаправления на ввод кода из сообщения при регистрации на сайте*/
    public void testRegNewAccount() {

        try {
            objReg = new Page3(driver);
            objReg.regNewAccount("Елена", "Бокова", "alenabokovaaa@yandex.ru", "hellomyfriend", "hellomyfriend");

            (new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase().startsWith("создайте аккаунт");
                } });


            WebElement smsCode = driver.findElement(By.xpath("//div/input[@id='code']"));

            assertTrue(smsCode.isDisplayed());
        }catch (Error e){

            verificationErrors.append(e.toString());
        }
    }
}
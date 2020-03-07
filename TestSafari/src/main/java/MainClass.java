import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args){

        WebDriver driver = new SafariDriver();

        driver.get("https://yandex.ru");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

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

        System.out.println("Page title is: " + driver.getTitle());

        WebElement link = driver.findElement(By.xpath("//a[@href='https://www.google.ru/']"));

    }
}

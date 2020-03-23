import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page2 {

    WebDriver driver;
    By termsOfAgreement = By.xpath("//li/a[@accesskey='9']");

    public Page2(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton() {
        WebElement button = driver.findElement(termsOfAgreement);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
    }

    public void buttonTerms(){
        this.clickButton();
    }
}

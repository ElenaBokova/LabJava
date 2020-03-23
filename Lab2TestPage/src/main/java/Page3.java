import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page3 {

    WebDriver driver;
    By firstName = By.xpath("//div/input[@id='firstName']");
    By lastName = By.xpath("//div/input[@id='lastName']");
    By regEmail = By.xpath("//div/input[@id='username']");
    By newPassword = By.xpath("//div/input[@name='Passwd']");
    By newPassword2 = By.xpath("//div/input[@name='ConfirmPasswd']");
    By regButton = By.xpath("//span[@class='RveJvd snByac']");
    By enter = By.xpath("//div[@role='link']");

    public Page3(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String strFirstName) {
        driver.findElement(firstName).sendKeys(strFirstName);
    }

    public void setLastName(String strLastName) {
        driver.findElement(lastName).sendKeys(strLastName);
    }

    public void setRegEmail(String strRegEmail) {
        driver.findElement(regEmail).sendKeys(strRegEmail);
    }

    public void setNewPassword(String strNewPass) {
        driver.findElement(newPassword).sendKeys(strNewPass);
    }

    public void setNewPassword2(String strNewPass2) {
        driver.findElement(newPassword2).sendKeys(strNewPass2);
    }


    public void clickReg() {
        WebElement button = driver.findElement(regButton);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
    }

    public void clickEnter() {
        WebElement button = driver.findElement(enter);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
    }

    public void regNewAccount(String strFirstName, String strLastName, String email, String newPass , String newPass2){

        this.setFirstName(strFirstName);
        this.setLastName(strLastName);
        this.setRegEmail(email);
        this.setNewPassword(newPass);
        this.setNewPassword2(newPass2);
        this.clickReg();
    }

    public void enterButton(){
        this.clickEnter();
    }


}

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager {

    public static String userName = "bsuser67818";
    public static String accessKey = "oDD7petzBQKZxqdusYpy";

    static AppiumDriverLocalService appiumDriverLocalService = null;

    public static AndroidDriver driver;

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public AndroidDriver getDriver() throws MalformedURLException, InterruptedException {
        if (driver == null) {

            startService();
            createDriver();
        }
        return driver;
    }

    protected void startService() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingAnyFreePort();
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        AppiumDriverLocalService appiumDriverLocalService = builder.build();
        AndroidDriverManager.appiumDriverLocalService = appiumDriverLocalService;
        appiumDriverLocalService.start();
    }


    public static void createDriver() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");
        caps.setCapability("project", "My First Project");
        caps.setCapability("build", "My First Build");
        caps.setCapability("name", "Bstack-[Java] Sample Test");
        caps.setCapability("app", "bs://4997ead5a4ada78c90d613066bc77637cc36a670");


        driver = new AndroidDriver<MobileElement>(appiumDriverLocalService.getUrl(),caps);

        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);


    }
}

import io.appium.java_client.android.AndroidDriver;
import org.junit.*;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class TestsAppium {

    private static AndroidDriverManager driverManager;
    static AndroidDriver driver;
    private LogIn loginLayout;
    private Registration registrationLayout;
    private Expenses expensesLayout;

    @BeforeClass
    public static void prepareTest() throws MalformedURLException, InterruptedException {
        driverManager = new AndroidDriverManager();
        driver = driverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Before
    public void initPageObject(){
        driver.launchApp();
        loginLayout = new LogIn(driver);
        registrationLayout = new Registration(driver);
        expensesLayout = new Expenses(driver);
    }

    @Test
    public void registrationTest(){
        registrationAll();
        Assert.assertEquals(".activities.LoginActivity",driver.currentActivity());
    }

    @Test
    public void loginTest(){
        registrationAll();
        loginLayout.enterEmailAndPassword("bokova_alena@mail.ru","1234567890");
        Assert.assertEquals(".activities.BudgetActivity",driver.currentActivity());
    }

    @Test
    public void expensesTest(){
        registrationAll();
        loginLayout.enterEmailAndPassword("bokova_alena@mail.ru","1234567890");
        expensesLayout.addExpenses();
        expensesLayout.addNewExpenses("Ticket","550","27/04/2020","Cinema");
        Assert.assertTrue(expensesLayout.expensesList("Ticket"));
    }

    @After
    public void tearDown(){
        driver.closeApp();
    }

    @AfterClass
    public static void tearDownDriver(){
        driverManager.quitDriver();

    }

    public void registrationAll(){
        registrationLayout.registerClick();
        registrationLayout.registrationNewAccount("bokova_alena@mail.ru","Alena","1234567890","1234567890");
    }

}

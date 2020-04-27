import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class Expenses {

    @FindBy(id="platkovsky.alexey.epamtestapp:id/add_new_expense")
    MobileElement newExpenseButton;

    @FindBy(id="platkovsky.alexey.epamtestapp:id/new_expense_title_edit")
    MobileElement newTitle;

    @FindBy(id="platkovsky.alexey.epamtestapp:id/new_expense_sum_edit")
    MobileElement newSum;

    @FindBy(id="platkovsky.alexey.epamtestapp:id/new_expense_date_edit")
    MobileElement newDate;

    @FindBy(id="platkovsky.alexey.epamtestapp:id/new_expense_category_picker")
    MobileElement newCategory;

    @FindBy(id = "platkovsky.alexey.epamtestapp:id/save_new_expense")
    MobileElement saveButton;

    @FindBy(id="platkovsky.alexey.epamtestapp:id/cancel_new_expense")
    MobileElement cancelButton;

    @FindBy(id="platkovsky.alexey.epamtestapp:id/expense_title")
    List<MobileElement> expensesTitle;


    Expenses(AndroidDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)),this);
    }

    public void addExpenses(){
        newExpenseButton.click();
    }

    public void addNewExpenses(String title, String sum, String date,String category){
        newTitle.sendKeys(title);
        newSum.sendKeys(sum);
        newDate.sendKeys(date);
        newCategory.sendKeys(category);
        saveButton.click();
    }

    public boolean expensesList(String expense){
        for(MobileElement e: expensesTitle){
            if(e.getText().equals(expense));
            return true;
        }
        return false;
    }

    public void cancelExpenses(){
        cancelButton.click();
    }
}

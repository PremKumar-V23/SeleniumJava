package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import extentions.UIElements;

public class CreateEmployee {

    private WebDriver driver;

    private By Newname = By.id("Name");
    private By Newage = By.id("Age");
    private By NewSalary = By.name("Salary");
    private By NewDW = By.name("DurationWorked");
    private By ddlgrade = By.id("Grade");
    private By NewEmail = By.name("Email");
    private By Btn = By.cssSelector("button.btn-submit");
    private By BTlist = By.linkText("← Back to List");

    public CreateEmployee(WebDriver driver) {
        this.driver = driver;
    }

    public Employeelist CreateNewEmp(String username,
                                     String age,
                                     String salary,
                                     String durationWorked,
                                     String grade,
                                     String email) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(Newname));

        driver.findElement(Newname).sendKeys(username);
        driver.findElement(Newage).sendKeys(age);
        driver.findElement(NewSalary).sendKeys(salary);
        driver.findElement(NewDW).sendKeys(durationWorked);

        UIElements.PerformDropDownByText(driver, ddlgrade, grade);

        driver.findElement(NewEmail).sendKeys(email);

        WebElement createButton = wait.until(
                ExpectedConditions.elementToBeClickable(Btn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", createButton);

        try {
            createButton.click();
        } catch (Exception e) {
            try {
                new Actions(driver)
                        .moveToElement(createButton)
                        .click()
                        .perform();
            } catch (Exception ex) {
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", createButton);
            }
        }

        System.out.println("Employee Added Successfully");

        // Wait until the Back to List link is available after page refresh
        wait.until(ExpectedConditions.presenceOfElementLocated(BTlist));

        // Retry once if the element becomes stale
        try {
            wait.until(ExpectedConditions.elementToBeClickable(BTlist)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(BTlist)).click();
        }

        // Wait until Employee List page loads
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("searchTerm")));

        return new Employeelist(driver);
    }
}
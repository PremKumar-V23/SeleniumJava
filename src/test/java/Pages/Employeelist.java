package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Employeelist {

    private WebDriver driver;

    private By CreateEmpBtn = By.linkText("+ New Employee");
    private By txtSearch = By.name("searchTerm");
    private By btnSearch = By.cssSelector(".btn-search");

    public Employeelist(WebDriver driver) {
        this.driver = driver;
    }

    public CreateEmployee ClickCreateBtn() {
        driver.findElement(CreateEmpBtn).click();
        return new CreateEmployee(driver);
    }

    public boolean VerifyEmployee(String username) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(txtSearch));

        searchBox.clear();
        searchBox.sendKeys(username);

        WebElement searchButton = wait.until(
                ExpectedConditions.elementToBeClickable(btnSearch));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", searchButton);

        try {
            searchButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", searchButton);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(txtSearch));

        String pageContent = driver.getPageSource();

        if (pageContent.contains(username)) {
            System.out.println("Employee Found");
            return true;
        } else {
            System.out.println("Employee Not Found");
            return false;
        }
    }
}
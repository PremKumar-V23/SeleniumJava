package gettingStarted;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.CreateEmployee;
import Pages.Employeelist;
import Pages.Homepage;
import Pages.Loginpage;

public class SeleniumTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        // Open Application
        driver.get("https://eaapp.somee.com/");
        driver.manage().window().maximize();
        
        Homepage homepage = new Homepage(driver);

        Loginpage loginpage = homepage.Clicklogin();

        homepage = loginpage.performlogin("admin", "password");

        Employeelist employeelist = homepage.ClickEmployeeList();

        CreateEmployee createEmployee = employeelist.ClickCreateBtn();

        // Capture the returned Employee List page
        employeelist = createEmployee.CreateNewEmp(
                "Autouser11",
                "27",
                "92568",
                "28",
                "Junior",
                "yomi@gmail.com"
        );

        // Search for the employee you just created
        boolean result = employeelist.VerifyEmployee("Autouser10");

        if (result) {
            System.out.println("Employee Verified");
        } else {
            System.out.println("Employee Not Found");
        }
        
        driver.quit();

    }
}
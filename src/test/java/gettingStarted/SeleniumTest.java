package gettingStarted;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Pages.CreateEmployee;
import Pages.Employeelist;
import Pages.Homepage;
import Pages.Loginpage;
import extentions.UIElements;

public class SeleniumTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        // Open Application
        driver.get("https://eaapp.somee.com/");
        driver.manage().window().maximize();
        
//        // String Username = "kllen";
//        Login(driver);
//        //CreateUsers(driver,Username,"32","50007","58","3","boat@gmail.com");
//        //VerifyEmployee(driver,Username);
//        //Logoff(driver);
        
        Homepage homepage = new Homepage(driver);

        Loginpage loginpage = homepage.Clicklogin();

        homepage = loginpage.performlogin("admin", "password");

        Employeelist employeelist = homepage.ClickEmployeeList();

        CreateEmployee createEmployee = employeelist.ClickCreateBtn();

        // Capture the returned Employee List page
        employeelist = createEmployee.CreateNewEmp(
                "Autouser10",
                "25",
                "92562",
                "20",
                "Junior",
                "yami@gmail.com"
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
    
    public static void Login(WebDriver driver) {
    	
    		UIElements.PerformClick(driver, By.linkText("Login"));
        UIElements.PerformEnterText(driver, By.id("UserName"), "admin");
        UIElements.PerformEnterText(driver, By.id("Password"), "password");
        UIElements.PerformClick(driver, By.cssSelector("button[type='submit']"));
        
    }
    
    public static void CreateUsers(WebDriver driver,String Username,String Age,String Salary,String Dw,String Value,String Email) {
    		driver.findElement(By.xpath("/html/body/header/nav/div/div/ul[1]/li[2]")).click(); //Employees
        driver.findElement(By.xpath("/html/body/div[1]/main/form/a")).click(); //Click Employees
        
        driver.findElement(By.id("Name")).sendKeys(Username); 
        driver.findElement(By.id("Age")).sendKeys(Age); 
        driver.findElement(By.name("Salary")).sendKeys(Salary); 
        driver.findElement(By.name("DurationWorked")).sendKeys(Dw);
        
        // DropDown List
        Select selectgrade = new Select(driver.findElement(By.id("Grade")));
        selectgrade.selectByValue(Value);
        driver.findElement(By.id("Email")).sendKeys(Email);

        // Submit
        driver.findElement(By.cssSelector(".form-actions button")).submit();
        driver.findElement(By.linkText("← Back to List")).click();
        
        System.out.println("Employee added successfully.");
    }
    
    public static void VerifyEmployee(WebDriver driver,String Username) {
    		driver.findElement(By.name("searchTerm")).sendKeys(Username);
    		driver.findElement(By.cssSelector(".btn-search")).click();
    		String PageContent = driver.getPageSource();
    		if (PageContent.contains(Username)){
    			System.out.println("Details added Successfully");
    		} else {
    			System.out.print("Failed");
    		}
    }
    
    public static void Logoff(WebDriver driver) {
    		driver.findElement(By.xpath("//button[text()='Logout']")).click();
    		
    }
}
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage {
	
	private WebDriver driver;
	
	public Homepage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By loglnk = By.linkText("Login");
	
	private By Employeelnk = By.linkText("👥 Employees");
	
	public Loginpage Clicklogin() {
		driver.findElement(loglnk).click();
		return new Loginpage(driver);
	}
	
	public Employeelist ClickEmployeeList() {
		driver.findElement(Employeelnk).click();
		return new Employeelist(driver);
	}

}

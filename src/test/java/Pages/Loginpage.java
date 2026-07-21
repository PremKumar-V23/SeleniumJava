package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {
	
	private WebDriver driver;
	
	private By txtUsername = By.id("UserName");
	private By txtPassword = By.id("Password");
	private By loginbtn = By.cssSelector("button[type='submit']");

	
	public Loginpage(WebDriver driver) {
		this.driver = driver;
	}
	
	public Homepage performlogin(String Username,String Password) {
		driver.findElement(txtUsername).sendKeys(Username);
		driver.findElement(txtPassword).sendKeys(Password);
		driver.findElement(loginbtn).click();
		
		return new Homepage(driver);
	}
}

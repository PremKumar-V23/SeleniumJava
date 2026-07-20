package extentions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UIElements {
	
	public static void PerformEnterText(WebDriver driver,By locator,String Values) {
		
		driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(Values);

	}
	
	public static void PerformClick (WebDriver driver,By locator) {
		driver.findElement(locator).click();
	}
	
	public static void PerformDropDownByText(WebDriver driver,By locator,String Value) {
		Select selectgrade = new Select(driver.findElement(locator));
        selectgrade.selectByVisibleText(Value);
	}
	
	public static void PerformDropDownByValue(WebDriver driver,By locator,String Value) {
		Select selectgrade = new Select(driver.findElement(locator));
        selectgrade.selectByValue(Value);
	}
	
	public static void PerformDropDownByIndex(WebDriver driver,By locator,int Value) {
		Select selectgrade = new Select(driver.findElement(locator));
        selectgrade.selectByIndex(Value);
	}
}

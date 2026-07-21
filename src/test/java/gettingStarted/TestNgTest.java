package gettingStarted;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestNgTest {

	private WebDriver driver;

	@BeforeTest
	public void Launch() {
		driver = new ChromeDriver();
		driver.get("https://eaapp.somee.com/");
	}

	@Test
	public void testOprerations(){
		Homepage homepage = new Homepage(driver);
		Loginpage loginpage = homepage.Clicklogin();
		homepage = loginpage.performlogin("admin", "password");
		Employeelist employeelist = homepage.ClickEmployeeList();
		CreateEmployee createEmployee = employeelist.ClickCreateBtn();

		// Parameters
		employeelist = createEmployee.CreateNewEmp(
				"Autouser11",
				"27",
				"92568",
				"28",
				"Junior",
				"yomi@gmail.com"
		);
	}
	@AfterTest
	public void ClosingBrowser(){
		System.out.println("Quitting The Browser");
		driver.quit();
	}
}


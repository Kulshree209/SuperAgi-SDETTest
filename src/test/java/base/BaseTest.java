package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.WebDriverManagerUtil;

public class BaseTest {
	protected WebDriver driver;
	protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());

	@BeforeMethod
	public void setUp() {
		WebDriverManagerUtil.setupDriver();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in"); // Open the base URL

		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "src/main/resources/drivers/chromedriver"); driver = new ChromeDriver(); //
		 * Maximize the browser window driver.manage().window().maximize();
		 * logger.info("Browser maximized."); // Navigate to the URL
		 * driver.get("https://www.amazon.in");
		 * logger.info("Navigated to the test URL.");
		 */
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit(); // Close the browser and clean up
		}
	}
}

package runners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.BaseTest;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Wait;

public class AmazonLGSoundbarSearchTest extends BaseTest {

	protected WebDriver driver;

	@BeforeClass
	public void setUp() {
		// Set up the ChromeDriver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver"); 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Step 1: Open amazon.in
		driver.get("https://www.amazon.in");

	}

	@Test
	public void testProductSortingByPrice() {
		// Step 2: Search for "LG Soundbar"
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys("LG Soundbar");
		searchBox.submit();

		// Step 3 & 4: Read product names and prices from the first search result page
		Map<String, Double> productMap = new HashMap<>();
		By productItems = By
				.xpath("//div[contains(@class,'s-main-slot')]//div[contains(@data-component-type,'s-search-result')]");
		By productName = By.xpath(".//span[contains(@class,'a-text-normal')]");
		// By productPrice =
		// By.xpath(".//span[contains(@class,'a-price')]//span[contains(@class,'a-offscreen')]");
		By productPrice = By.xpath(".//span[contains(@class,'a-price')]");

		FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		// Wait for the product list to be visible
		List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItems));
		String priceText = " ";
		for (WebElement product : products) {
			try {
				String productNameText = product.findElement(productName).getText();

				System.out.println("***productNameText***" + productNameText);

				// String priceText = product.findElement(productPrice).getText().replace("₹",
				// "").replace(",", "").trim();
				priceText = product.findElement(productPrice).getText().replace("₹", "").replace(",", "")
						.replace("Error reading product or price. Skipping product.", "0");
				System.out.println("***PRICE***" + priceText);

				// If the price is not present, treat it as 0
				Double productPriceValue = priceText.isEmpty() ? 0.0 : Double.parseDouble(priceText);

				// Add product name and price to the map
				productMap.put(productNameText, productPriceValue);

			} catch (Exception e) {
				System.out.println("Error reading product or price. Skipping product.");
				// Capture the product name to handle it properly in case of exception
				try {
					WebElement nameElement = product.findElement(productName);
					String productNameText = nameElement.getText();

					// Add product name with price 0 to the map
					productMap.put(productNameText, 0.0);
				} catch (Exception innerException) {
					System.out.println("Error reading product name. Skipping product.");
				}
			}
		}

		// Step 5: Sort by price and print the products
		System.out.println("Sorted products:");
		productMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEach(entry -> System.out.println("Product: " + entry.getKey() + ", Price: ₹" + entry.getValue()));
	}

	@AfterClass
	public void tearDown() {
		// Step 6: Close the browser
		if (driver != null) {
			driver.quit();
		}
	}
}

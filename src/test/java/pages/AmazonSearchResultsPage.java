package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonSearchResultsPage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Locators for product items, names, and prices
	By productItems = By
			.xpath("//div[contains(@class,'s-main-slot')]//div[contains(@data-component-type,'s-search-result')]");
	By productName = By.xpath(".//span[contains(@class,'a-text-normal')]");
	// By productPrice =
	// By.xpath(".//span[contains(@class,'a-price')]//span[contains(@class,'a-offscreen')]");
	By productPrice = By.xpath(".//span[contains(@class,'a-price')]");

	public AmazonSearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void getProductNamesAndPrices() {
		Map<String, Double> productMap = new HashMap<>();

		// FluentWait to handle dynamic loading and retrying for stale elements
		FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(500)).ignoring(Exception.class);

		// Wait for the product list to be visible
		List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItems));

		String priceText = " ";
		for (WebElement product : products) {
			try {
				String productNameText = product.findElement(productName).getText();

				System.out.println("***ProductNameText***" + productNameText);

				// String priceText = product.findElement(productPrice).getText().replace("₹",
				// "").replace(",", "").trim();
				priceText = product.findElement(productPrice).getText().replace("₹", "").replace(",", "")
						.replace("Error reading product or price. Skipping product.", "0");
				System.out.println("***Price***" + priceText);

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

		// Sort by price and print the products
		System.out.println("Sorted product by price");
		productMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEach(entry -> System.out.println("Product: " + entry.getKey() + ", Price: ₹" + entry.getValue()));
	}

}

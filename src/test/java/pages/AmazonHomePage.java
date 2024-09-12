package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonHomePage {
	private WebDriver driver;
	private By searchBox = By.id("twotabsearchtextbox");
	private By searchButton = By.id("nav-search-submit-button");

	public AmazonHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void searchFor(String searchTerm) {
		WebElement searchField = driver.findElement(searchBox);
		searchField.sendKeys(searchTerm);
		driver.findElement(searchButton).click();
	}
}

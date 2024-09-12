package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;
import pages.AmazonHomePage;
import pages.AmazonSearchResultsPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class SearchSteps extends BaseTest {
	private AmazonHomePage homePage;
	private AmazonSearchResultsPage resultsPage;
	BaseTest basetest;
	private Map<String, Double> productPriceMap = new HashMap<>();
	private Map<String, Double> productMap;

	@When("I redirect to amazon")
	public void i_redirect_to_amazon() {
		super.setUp();
	}

	@When("I search for {string}")
	public void i_search_for(String searchTerm) {
		homePage = new AmazonHomePage(driver);
		homePage.searchFor(searchTerm);
	}

	@Then("I see and sort product names and prices")
	public void i_see_product_names_and_prices() {
		resultsPage = new AmazonSearchResultsPage(driver);
		resultsPage.getProductNamesAndPrices();
		super.tearDown();
	}

	/*
	 * @Then("I sort and print the products by price") public void
	 * i_sort_and_print_the_products_by_price() { productMap.entrySet().stream()
	 * .sorted(Map.Entry.comparingByValue()) .forEach(entry ->
	 * System.out.println("Product: " + entry.getKey() + ", Price: ₹" +
	 * entry.getValue())); super.tearDown(); }
	 */

}

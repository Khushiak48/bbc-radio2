package com.bbc.radio.functional.test;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * The Class BBCRadioStepDefs.
 *
 * @author Khushboo Taneja
 */
public class BBCRadioStepDefs {

	/** The driver. */
	private WebDriver driver;
	
	/** The section element. */
	private WebElement sectionElement;

	/**
	 * I can see the radio nav.
	 *
	 * @throws Throwable the throwable
	 */
	@Given("^I can see the radio nav$")
	public void i_can_see_the_radio_nav() throws Throwable {
		driver = new FirefoxDriver();
		driver.get("http://www.bbc.co.uk/radio");// get the url
		driver.manage().window().maximize();// maximize the window
		Thread.sleep(3000);// wait for 3000ms
	}

	/**
	 * I select in the radio nav.
	 *
	 * @param section the section
	 * @throws Throwable the throwable
	 */
	@When("^I select (.+) in the radio nav$")
	public void i_select_in_the_radio_nav(String section) throws Throwable {
		sectionElement = driver.findElement(By.xpath("//a[contains(text(),'" + section + "')]"));
		sectionElement.click();
		Thread.sleep(3000);
	}

	/**
	 * The drawer is open.
	 *
	 * @param section the section
	 * @throws Throwable the throwable
	 */
	@Then("^the (.+) drawer is open$")
	public void the_drawer_is_open(String section) throws Throwable {
		WebElement panel = driver.findElement(By.xpath("//div[@id='" + section.toLowerCase() + "-panel']/ul"));
		Assert.assertNotNull(panel);
		Thread.sleep(3000);
	}

	/**
	 * I select in the radio nav again.
	 *
	 * @param section the section
	 * @throws Throwable the throwable
	 */
	@When("^I select (.+) in the radio nav again$")
	public void i_select_in_the_radio_nav_again(String section) throws Throwable {
		sectionElement.click();
	}

	/**
	 * The drawer is closed.
	 *
	 * @param section the section
	 * @throws Throwable the throwable
	 */
	@Then("^the (.+) drawer is closed$")
	public void the_drawer_is_closed(String section) throws Throwable {
		List<WebElement> panel1 = driver.findElements(By.xpath("//div[@id='" + section + "']/ul"));
		Assert.assertTrue(panel1.size() == 0);
		driver.close();
	}

	/**
	 * The drawer is open and the other drawers are closed.
	 *
	 * @param section the section
	 * @throws Throwable the throwable
	 */
	@Then("^the (.+) drawer is open and the other drawers are closed$")
	public void the_drawer_is_open_and_the_other_drawers_are_closed(String section) throws Throwable {
		WebElement sectionElement = driver.findElement(By.xpath("//a[contains(text(),'" + section + "')]"));
		sectionElement.click();
		System.out.println("Section Station is open");
		// Thread.sleep(3000);

		boolean isStationDisplayed = driver.findElement(By.xpath("//div[@id='stations-panel']/ul")).isDisplayed();

		boolean isCategoriesDisplayed = driver.findElement(By.xpath("//div[@id='categories-panel']/ul")).isDisplayed();

		boolean isSchedulesDisplayed = driver.findElement(By.xpath("//div[@id='schedules-panel']/ul")).isDisplayed();

		boolean result = false;

		if (section.equalsIgnoreCase("Stations")) {
			result = (isStationDisplayed && !isCategoriesDisplayed && !isSchedulesDisplayed);
		} else if (section.equalsIgnoreCase("Categories")) {
			result = (!isStationDisplayed && isCategoriesDisplayed && !isSchedulesDisplayed);
		} else if (section.equalsIgnoreCase("Schedules")) {
			result = (!isStationDisplayed && !isCategoriesDisplayed && isSchedulesDisplayed);
		}

		Assert.assertTrue(result);
		driver.close();
	}

	/**
	 * I can see the following categories.
	 *
	 * @param dataTable the data table
	 * @throws Throwable the throwable
	 */
	@Then("^I can see the following categories$")
	public void i_can_see_the_following_categories(DataTable dataTable) throws Throwable {
		WebElement categoryPanel = driver.findElement(By.xpath("//div[@id='categories-panel']/ul"));

		List<String> expectedAsList = dataTable.asList(String.class);

		for (String categoryName : expectedAsList) {
			WebElement categoryWebElement = categoryPanel.findElement(By.xpath(".//a[text()='" + categoryName + "']"));

			Assert.assertEquals(categoryName, categoryWebElement.getText());

		}

		driver.close();
	}

	/*
	 * @When("^I select categories in the radio nav$") public void
	 * i_select_categories_in_the_radio_nav() throws Throwable { WebElement
	 * categoryElement =
	 * driver.findElement(By.xpath("//a[@data-panelid='categories']"));
	 * categoryElement.click(); }
	 */

	/**
	 * I select the all categories link.
	 *
	 * @throws Throwable the throwable
	 */
	@And("^I select the all categories link$")
	public void i_select_the_all_categories_link() throws Throwable {
		WebElement allCategories = driver.findElement(By.xpath("//a[text()='All Categories']"));
		allCategories.click();
		System.out.println("Section category is open");
		Thread.sleep(3000);
	}

	/**
	 * I am on the all categories page.
	 *
	 * @throws Throwable the throwable
	 */
	@Then("^I am on the all categories page$")
	public void i_am_on_the_all_categories_page() throws Throwable {
		WebElement categoriesPage = driver.findElement(By.xpath("//*[@id='programmes-main-content']/h1"));
		System.out.println(categoriesPage.getText());
		Assert.assertEquals("Categories: Genres", categoriesPage.getText());
		driver.close();
	}

}
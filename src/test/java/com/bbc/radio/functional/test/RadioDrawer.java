package com.bbc.radio.functional.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author Khushboo Taneja
 *
 */
public class RadioDrawer {

	WebDriver driver = new FirefoxDriver();

	@Before
	public void setUp() throws InterruptedException {
		// System.setProperty("webdriver.gecko.driver","D:/Workspace/GeckoDriver/geckodriver.exe");
		driver.get("http://www.bbc.co.uk/radio");// get the url
		driver.manage().window().maximize();// maximize the window
		Thread.sleep(3000);

	}

	// @Test
	public void radioNav() throws InterruptedException {
		WebElement sectionElement = driver.findElement(By.xpath("//a[contains(text(),'Stations')]"));
		sectionElement.click();
		System.out.println("Section Station is open");
		WebElement panel = driver.findElement(By.xpath("//div[@id='stations-panel']/ul"));
		Assert.assertNotNull(panel);
		Thread.sleep(3000);
		sectionElement.click();
		System.out.println("Section Station is close");
		Assert.assertNotNull(panel);
		Thread.sleep(3000);

		try {
			WebElement panel1 = driver.findElement(By.xpath("//div[@id='stations-panel']/ul"));
			Assert.assertTrue(false);

		} catch (Exception e) {
			Assert.assertTrue(true);

		}

	}

	// @Test
	public void drawersOpenClosed() throws InterruptedException {
		WebElement sectionElement = driver.findElement(By.xpath("//a[@data-panelid='stations']"));
		sectionElement.click();
		System.out.println("Section Station is open");
		Thread.sleep(3000);

		boolean isStationDisplayed = driver.findElement(By.xpath("//div[@id='stations-panel']/ul")).isDisplayed();

		boolean isCategoriesDisplayed = driver.findElement(By.xpath("//div[@id='categories-panel']/ul")).isDisplayed();

		boolean isSchedulesDisplayed = driver.findElement(By.xpath("//div[@id='schedules-panel']/ul")).isDisplayed();

		if (isStationDisplayed && !isCategoriesDisplayed && !isSchedulesDisplayed) {
			Assert.assertTrue(true);
			System.out.println("station section is enabled, categories and schedules sections are disabled");
		} else {
			Assert.assertTrue(false);
		}
	}

	// @Test
	public void categoryDisplays() throws InterruptedException {
		WebElement categoryElement = driver.findElement(By.xpath("//a[@data-panelid='categories']"));
		categoryElement.click();
		System.out.println("Section category is open");
		Thread.sleep(3000);
		WebElement categoryPanel = driver.findElement(By.xpath("//div[@id='categories-panel']/ul"));
		WebElement politics = categoryPanel.findElement(By.xpath("//a[text()='Politics']"));
		Assert.assertEquals("Politics", politics.getText());
		// System.out.println(categoryPanel.getText());
	}

	// @Test
	public void categoryPage() throws InterruptedException {
		WebElement categoryElement = driver.findElement(By.xpath("//a[@data-panelid='categories']"));
		categoryElement.click();
		System.out.println("Section category is open");
		Thread.sleep(3000);
		WebElement allCategories = driver.findElement(By.xpath("//a[text()='All Categories']"));
		allCategories.click();
		Thread.sleep(2000);
		WebElement categoriesPage = driver.findElement(By.xpath("//*[@id='programmes-main-content']/h1"));
		System.out.println(categoriesPage.getText());
		Assert.assertEquals("Categories: Genres", categoriesPage.getText());
	}

	@Test
	public void linkingAllLinksOfStation() throws InterruptedException
	{
		WebElement sectionElement = driver.findElement(By.xpath("//a[contains(text(),'Stations')]"));
		sectionElement.click();
		Thread.sleep(3000);
		System.out.println("Section Station Panel is open");
		
		WebElement stationsPanel = driver.findElement(By.xpath("//div[@id='stations-panel']/ul"));
		Actions action =new Actions(driver);
		WebElement radioList=stationsPanel.findElement(By.xpath(".//li[@data-networkid='radio1']"));
		action.moveToElement(radioList).click().build().perform();
		Thread.sleep(5000);
		
		System.out.println(radioList.getAttribute("href"));
		Assert.assertEquals("http://www.bbc.co.uk/radio1", radioList.getAttribute("href"));
		Thread.sleep(2000);
		
		List<WebElement> listOfStations = stationsPanel.findElements(By.xpath(".//li"));
		for(WebElement stationName : listOfStations)
		{
			  //WebElement stationLogo  =  driver.findElement(By.xpath("//li[@class='stations--bbc_radio_one']"));
//			  System.out.println(stationsList.getAttribute("data-networkid"));
//			  Assert.assertEquals(listOfStations, stationsList.getAttribute("data-networkid"));
		}

	}
		
	// @Test
	public void iplayerRadioLogo() throws InterruptedException {
		// WebElement
		// mainlogoDiv=driver.findElement(By.xpath("//div[@class='radionav__wrap
		// b-g-m cf']"));
		WebElement iplayerlogo = driver.findElement(By.xpath("//i[@class='iplayer-radio__logo-pr']"));
		iplayerlogo.click();
		Thread.sleep(2000);
	}

	@After
	public void tearDown() {
		driver.close();// session close
	}
}
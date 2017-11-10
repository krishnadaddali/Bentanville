package com.internal.gfpp;

import internal.gfpp.page.HomePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class GFPPHomePageTest extends BaseTest{
	
	 public static Logger logger =LogManager.getLogger(GFPPHomePageTest.class);
	 
	  HomePage homePage;

	/**
	 * This function will execute before each Test tag in testng.xml
	 * @param browser
	 * @throws Exception
	 */
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		initializeDriver(browser);
		homePage= new HomePage(driver);
		
		
    }
	
		
	@Test(dataProvider = "backery-stores")
	public void selectCategoryStoreAndSubmit(String category, String[] stores) throws InterruptedException{
		logger.debug("Category name: "+category+"       Stores:"+ stores);
	
		//access the page
		driver.get(prop.getProperty("qa.globalfreshproductionplanning.app.url"));
		
		// select backery option from category dropdown
		homePage.selectCategory(category);
			
		//select store from store dropdown
		homePage.selectStrore(stores[0]);
		
		//click submit buttion
		homePage.clickSubmitButton();
		
		WebElement myDynamicElement = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Bakery Production Plan']")));
		
		//verify page contains the title - "Backery producution plan"
		Assert.assertTrue(myDynamicElement.isDisplayed());
	}
	
	
	
	
	
	
	  @DataProvider(name = "backery-stores")
	  public static Object[][] credentials() {
	        return new Object[][] { { "Bakery", getBackeryStores() }};
	  }
	  
	  public static String[] getBackeryStores() {
		  return new String[] {"1000", "1001"};
	  }
	  
	  public static String[] getDeliStores() {
		  return new String[] {"1000", "1001", "1003"};
	  }
}

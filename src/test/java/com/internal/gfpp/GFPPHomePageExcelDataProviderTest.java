package com.internal.gfpp;

import internal.gfpp.page.ExcelUtil;
import internal.gfpp.page.HomePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GFPPHomePageExcelDataProviderTest extends BaseTest {

	public static Logger logger = LogManager
			.getLogger(GFPPHomePageExcelDataProviderTest.class);
	
	HomePage homePage = null;

	/**
	 * This function will execute before each Test tag in testng.xml
	 * 
	 * @param browser
	 * @throws Exception
	 */
	@BeforeTest
	public void setup() throws Exception {
		initializeDriver("chrome");
		homePage= new HomePage(driver);
	}

	@Test(dataProvider = "backery-stores")
	public void selectCategoryStoreAndSubmit(String category, String storeName)
			throws InterruptedException {
		
		logger.debug("Category name: " + category + "       Stores:" + storeName);

		// access the page
		driver.get(prop.getProperty("qa.globalfreshproductionplanning.app.url"));

		// select backery option from category dropdown
		homePage.selectCategory(category);
				
		//select store from store dropdown
		homePage.selectStrore(storeName);
		
		//click submit buttion
		homePage.clickSubmitButton();

		WebElement myDynamicElement = (new WebDriverWait(driver, 20))
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("//span[text()='Bakery Production Plan']")));

		// verify page contains the title - "Backery producution plan"
		Assert.assertTrue(myDynamicElement.isDisplayed());
	}

	@DataProvider(name = "backery-stores")
	public Object[][] getBackeryStoresData() throws Exception {
		Object[][] data =  ExcelUtil.readExcel("c:\\krishna", "gfpp_test_data.xlsx", "category");
		return data;
	}

	
}

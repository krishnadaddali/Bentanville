package com.internal.gfpp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseTest {

	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver(String browserName) throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\workspace\\gfpp\\src\\test\\java\\resources\\application.properties");

		prop.load(fis);
		
		System.out.println(browserName);

		if (browserName.equals("chrome")) {
		
		    System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome.driver.path"));
			driver = new ChromeDriver();
			// execute in chrome driver

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",prop.getProperty("firefox.gecko.driver.path"));
			driver = new FirefoxDriver();
			// firefox code
		} else if (browserName.equals("IE")) {
			// IE code
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	

}

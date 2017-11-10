package internal.gfpp.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	

	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectCategory(String category) {
		Select categoryDropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='hc.dprmtModal']")));
		categoryDropdown.selectByVisibleText(category);
	}
	
	public void selectStrore(String storeName) {
		Select storeDropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='strNbr']")));
		storeDropdown.selectByVisibleText(storeName);
	}
	
	public void clickSubmitButton() {
		//click submit buttion
		WebElement submitButton = driver.findElement(By.className("login-button"));
		submitButton.click();
	}
	
	
}

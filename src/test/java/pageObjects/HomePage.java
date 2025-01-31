package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

//Locator

	@FindBy(xpath = "//h5[normalize-space()='Login']")
	WebElement logintxt;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	WebElement alerttxt;
	
	
	public boolean alertExists() {
		boolean status = alerttxt.isDisplayed();
		return status;
	}
	
	// Methods or Actions
	public void logoPresent() {
		
		Assert.assertTrue(logintxt.getText().equals("Login"));
    }
         
  
	}

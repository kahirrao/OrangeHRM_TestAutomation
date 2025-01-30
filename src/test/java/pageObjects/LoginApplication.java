package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginApplication extends BasePage{
	
	public LoginApplication(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// Locators

	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement inputUsername;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement inputPassword;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	WebElement accoutHolderName;
	
	
	public void enterUsername(String username_txt) {
	    inputUsername.sendKeys(username_txt);
	}

	public void enterPassword(String password_txt) {
		inputPassword.sendKeys(password_txt);
	}
	
	public void clickLogin() {
		loginBtn.click();
	}	
	
	public String getConfirmationMsg() {
		try {
			String accountHolderNameText = accoutHolderName.getText();
			System.out.println(accountHolderNameText);
			return accountHolderNameText; 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return null;
	}
}
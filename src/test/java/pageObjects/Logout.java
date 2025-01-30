package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout extends BasePage {
    
	public Logout(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']//child::i")
	WebElement accountIcon;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logoutBtn;
	
	public void clickAccountIcon() {
		accountIcon.click();
    }

	public void clickLogout() {
        logoutBtn.click();
                
	}
}

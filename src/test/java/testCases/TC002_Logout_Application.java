package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginApplication;
import pageObjects.Logout;
import testBase.BaseClass2;

public class TC002_Logout_Application extends BaseClass2 {

	
	@Test(groups = "sanity")
	public void verify_login_is_successful() {
		LoginApplication login = new LoginApplication(driver);
		login.enterUsername(p.getProperty("username_txt"));
		login.enterPassword(p.getProperty("password_txt"));
		login.clickLogin();
		
	}
	
	@Test(priority=2, groups = "sanity")
	public void logout_application() {
		Logout logout = new Logout(driver);
		logout.clickAccountIcon();
		logout.clickLogout();
		
		HomePage homePage = new HomePage(driver);
		// logger.info("Click on account link and validate Login page is visible");
		homePage.logoPresent();
	}
}

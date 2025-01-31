package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginApplication;
import testBase.BaseClass2;

public class TC001_Login_OrangeHRM_Account extends BaseClass2 {

	@Test(groups = {"regression"})
	public void verify_login_is_successful() {
		try
		{
		logger.info("** Login Test Started ***");
        Thread.sleep(5000);
		HomePage homePage = new HomePage(driver);
		logger.info("Click on account link and validate Login page is visible");
		homePage.logoPresent();

		LoginApplication login = new LoginApplication(driver);
		logger.info("Enter username and password and validate the login");
		login.enterUsername(p.getProperty("username_txt"));
		login.enterPassword(p.getProperty("password_txt"));
		login.clickLogin();
		Thread.sleep(5000);
		logger.info("Clicked on Login Button");
		String name_txt = login.getConfirmationMsg();
		if(login.getConfirmationMsg().equals("uerser"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Login Test Failed: ");
			logger.debug("Debugging the error");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("** Login Test Completed ***");
	}
}


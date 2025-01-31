package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginApplication;
import pageObjects.Logout;
import testBase.BaseClass2;
import utilities.DataProviders;

public class TC003_TDD_Login_OrangeHRM extends BaseClass2 {

	@Test(groups = {"regression","Datadriven"}, dataProvider = "LoginData", dataProviderClass=DataProviders.class)
	public void verify_login_is_successful(String username, String password, String exp) {
		try
		{
		logger.info("** Login Test Started ***");
        Thread.sleep(5000);
		HomePage homePage = new HomePage(driver);
		logger.info("Click on account link and validate Login page is visible");
		homePage.logoPresent();

		LoginApplication login = new LoginApplication(driver);
		logger.info("Enter username and password and validate the login");
		login.enterUsername(username);
		login.enterPassword(password);
		login.clickLogin();
		Thread.sleep(5000);
		logger.info("Clicked on Login Button");
		Logout logout = new Logout(driver);
		
		boolean status1 = login.homePageExists();
	
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(status1==true)
			{			
				logout.clickAccountIcon();
				logout.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(status1==true)
			{
				logout.clickAccountIcon();
				logout.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				boolean status2 = homePage.alertExists();
				Assert.assertTrue(status2);
			}
		}
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TC_003_LoginDDT ******");
		
	}
		

		
}



package testCases;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import pageObjects.HomePage;
//import pageObjects.LoginApplication;
//import testBase.BaseClass;
//import testBase.BaseClass2;
//
//public class TC001_Login_Amazon_Account extends BaseClass {
//
////	@Test()
//	public void verify_login_is_successful() {
//		try
//		{
//		// logger.info("** Login Test Started ***");
//        Thread.sleep(5000);
//		HomePage homePage = new HomePage(driver);
//		// logger.info("Click on account link and validate Login page is visible");
//		homePage.logoPresent();
//
//		LoginApplication login = new LoginApplication(driver);
//		// logger.info("Enter username and password and validate the login");
//		login.enterUsername(p.getProperty("username_txt"));
//		login.enterPassword(p.getProperty("password_txt"));
//		login.clickLogin();
//		Thread.sleep(5000);
//		// logger.info("Clicked on Login Button");
//		String name_txt = login.getConfirmationMsg();
//		if(login.getConfirmationMsg().equals(name_txt))
//		{
//			Assert.assertTrue(true);
//		}
//		else {
//			// logger.error("Login Test Failed: ");
//			// logger.debug("Debugging the error");
//			Assert.assertTrue(false);
//		}
//		}
//		catch(Exception e)
//		{
//			Assert.fail();
//		}
//		// logger.info("** Login Test Completed ***");
//	}
//}
//
//package testCases;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import pageObjects.HomePage;
//import pageObjects.LoginApplication;
//import testBase.BaseClass;
//import testBase.BaseClass2;
//
//public class TC001_Login_Amazon_Account extends BaseClass {
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginApplication;
import testBase.BaseClass;

public class H_Login_Amazon_Account extends BaseClass {
	@Test(priority = 1)
	public void verify_login_is_successful() {
		try {
			// logger.info("** Login Test Started ***");
			Thread.sleep(5000);
			HomePage homePage = new HomePage(BaseClass.getDriver());
			// logger.info("Click on account link and validate Login page is visible");
			homePage.logoPresent();

			LoginApplication login = new LoginApplication(BaseClass.getDriver());
			// logger.info("Enter username and password and validate the login");
			login.enterUsername("Admin");
			login.enterPassword("admin123");
			login.clickLogin();
			Thread.sleep(5000);
			// logger.info("Clicked on Login Button");
			String name_txt = login.getConfirmationMsg();
			if (login.getConfirmationMsg().equals(name_txt)) {
				Assert.assertTrue(true);
			} else {
				// logger.error("Login Test Failed: ");
				// logger.debug("Debugging the error");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.fail();
		}
		// logger.info("** Login Test Completed ***");
	}

}
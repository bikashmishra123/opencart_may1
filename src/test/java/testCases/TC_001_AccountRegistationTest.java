package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistationTest extends BaseClass {

	

	@Test(groups= {"Regression","Master"})
	void test_account_Registraion() throws InterruptedException {
		
	
		logger.debug("Application logs .....");
		logger.info("*** starting TC_001_AccountRegistrationTest ***");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("clicked on myaccount");
		
		hp.clickRegister();
		
		logger.info("clicked on Register link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("providing customer data");
		
		regpage.setFirstName(randomString());
		regpage.setLastName(randomString());
		regpage.setEmail(randomString() + "@gmail.com");
		regpage.setPassword(randomAlphaNumeric());
		//regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("clicked on continue");
		
		String confmsg = regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		logger.info("validating expected message");
		
		//}
	
		/*
		catch(Exception e ) {
			
			logger.error("test failed");
			Assert.fail();
		
	}*/
	
		logger.info("*** finished TC_001_AccountRegistrationTest ***");
	

	}

}

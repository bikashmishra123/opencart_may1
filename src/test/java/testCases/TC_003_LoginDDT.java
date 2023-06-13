package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)	
	public void test_loginDDT(String email , String password, String exp) {
		
		logger.info("***starting TC_003_LoginDDT***");
		
		try {
		
		//we have already written the login script right , same login we can poy paste till cliked on login buttonn
		
        HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver) ;
		lp.setEmail(email); 
		lp.setPassword(password); 
		lp.clickLogin();
	
		//now here we are validating target page 
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
	
		//now observe this how i am writing validation 
		
		if(exp.equals("Valid")) {
			
			if(targetpage==true) {
				
				macc.clickLogout(); //if you do not logit how will it take next step of data ,if login is succeful then only need to logout
				Assert.assertTrue(true);
			}
			else {
				
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equals("Invalid")) {
			

			if(targetpage==true) {
				
				macc.clickLogout(); //if you do not logit how will it take next step of data ,if login is succeful then only need to logout
				Assert.assertTrue(false);
			}
			else {
				
				Assert.assertTrue(true);
			}
		   }
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("***finish TC_003_LoginDDT***");
	}
}

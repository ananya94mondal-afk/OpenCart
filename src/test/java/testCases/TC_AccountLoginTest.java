package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_AccountLoginTest extends BaseTest{

	@Test(groups={"Regression","Master"})
	public void verifyLoginFunctionality() {
		try {
			logger.info("************ Execution Started TC_AccountLoginTest ******** ");
			HomePage homepage=new HomePage(getDriver());
			 logger.info("*** Click on My Account Link ***");
			homepage.clikOnMyAccountLnk();
			
			 logger.info("*** Click on Login Link ***");
			homepage.clickOnLoginLnk();
			
			LoginPage loginpage=new LoginPage(getDriver());
			
			 logger.info("*** Enter Valid Login Details ***");
			
			 //using random username & pwd
			 
//			loginpage.setUserName(AccountRegistrationPage.email);
//			
//			loginpage.setPassword(AccountRegistrationPage.password);
			 
			 loginpage.setUserName(p.getProperty("email"));
			 loginpage.setPassword(p.getProperty("password"));
			loginpage.clickLoginBtn();
			
			logger.info("*** Verify the user is successfully logged in..  ***");
			
			MyAccountPage myaccountpage=new MyAccountPage(getDriver());
	        
			if(myaccountpage.isMyAccountExists()) {
				Assert.assertTrue(true);
			}
			else {
				throw new RuntimeException("Login failed: My Account page was not displayed"+myaccountpage.isMyAccountExists());
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Test failed due to exception in execution flow",e);
//			Assert.fail(e.getMessage());
			throw e;
		}
	
	}
}

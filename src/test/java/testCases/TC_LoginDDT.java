package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.Dataprovider;
import utilities.ExcelUtility;

public class TC_LoginDDT extends BaseTest {

	@Test(dataProvider="LoginData",dataProviderClass=Dataprovider.class,groups="DataDriven")//when data provider method in seperate class
	public void checkLoginFunctionality(String username, String password, String res) {
		
		try {
			logger.info("************ Execution Started TC_LoginDDT ******** ");
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
			 
			 loginpage.setUserName(username);
			 loginpage.setPassword(password);
			loginpage.clickLoginBtn();
			
			logger.info("*** Verify the user is successfully logged in..  ***");
			
			MyAccountPage myaccountpage=new MyAccountPage(getDriver());
	        if(res.equalsIgnoreCase("Valid")) {
	        	
	        	if(myaccountpage.isMyAccountExists()) {
	        		
//	        		homepage.clikOnMyAccountLnk();
	        		
	        		loginpage.clickLogoutBtn();
	        		Assert.assertTrue(true);
					
				}
				else {
					String path=System.getProperty("user.dir")+"//testData//OpenCart_LoginData.xlsx";
					
					ExcelUtility excelutility=new ExcelUtility(path);
						excelutility.setCellData("Sheet1", 5, 2, "Invalid");	
					
					
					throw new RuntimeException("Login is not successful...... "+myaccountpage.isMyAccountExists());
				}
	        }
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Test failed due to exception in execution flow",e);
			Assert.fail();
		}
	

	}
}

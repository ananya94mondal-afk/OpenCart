package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(how=How.XPATH,using="//div[@id='content']//h1[normalize-space()='My Account']")
	WebElement msgLoginConfirmation;
	
	public boolean isMyAccountExists() {
		try{
			super.wait.until(ExpectedConditions.visibilityOf(msgLoginConfirmation));
			
			return true;
			
		}catch(Exception e) {
			return false;
		}
		
	}
}

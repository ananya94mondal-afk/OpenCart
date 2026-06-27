package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);

	}
	
	
	@FindBy(how=How.XPATH,using="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(how=How.XPATH,using="//a[normalize-space()='Register']")
	WebElement lnkRegistration;
	
	@FindBy(how=How.XPATH,using="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	public void clikOnMyAccountLnk() {
		int attempts=0;
		while(attempts<3)
		try {
			wait.until(ExpectedConditions.elementToBeClickable(lnkMyAccount)).click();	
			break;
		}catch(Exception e) {
			attempts++;
			System.out.println("Retry to click again"+attempts);
			if(attempts==3) {
				throw e;
			}
		}
		
		
	}
	
	public void clickOnRegistrationLnk() {
		wait.until(ExpectedConditions.elementToBeClickable(lnkRegistration)).click();
	}
	
	public void clickOnLoginLnk() {
		wait.until(ExpectedConditions.elementToBeClickable(lnkLogin)).click();
	}
	
}

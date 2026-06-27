package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountRegistrationPage extends BasePage {

	public static String email;
	public static String password;
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);

	}
	
	@FindBy(how=How.XPATH,using="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(how=How.XPATH,using="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(how=How.XPATH,using="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(how=How.XPATH,using="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(how=How.XPATH,using="//input[@name='agree']")
	WebElement checkPolicy;
	
	@FindBy(how=How.XPATH,using="//input[@name='newsletter']")
	WebElement checkNewsLetter;
	
	
	@FindBy(how=How.XPATH,using="//button[normalize-space()='Continue']")
	WebElement btnContinue;
	
	@FindBy(how=How.XPATH,using="//div[@id='content']//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgRegistrationConfirmation;	
	
	public void setFirstName(String fname) {	
	
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {	
		
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String emailid) {	
		
		email=emailid;
		txtEmail.sendKeys(email);
	}

	
	public void setPassword(String pwd) {	
		
		password=pwd;
		txtPassword.sendKeys(password);
	}
	
	public void setPrivacyPolicy() {	
		
		 WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(checkPolicy));

//		 js.executeScript("arguments[0].scrollIntoView(true);",ele);

		 action.moveToElement(ele).build().perform();
		 ele.click();

//		 js.executeScript("arguments[0].click();",ele);
      
	}
	
	public void clickContinue() {
		
		super.wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMsg() {
		try{
			return super.wait.until(ExpectedConditions.visibilityOf(msgRegistrationConfirmation)).getText();
			
			
		}catch(Exception e) {
			return e.getMessage();
		}
		
	}
}

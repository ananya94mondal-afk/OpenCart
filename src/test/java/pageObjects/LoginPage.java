package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

	Actions action;
	public LoginPage(WebDriver driver) {
		super(driver);
		action=new Actions(driver);
	}
	
	@FindBy(how=How.XPATH,using="//input[@id='input-email']")
	WebElement txtEmailAddress;
	
	@FindBy(how=How.XPATH,using="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(how=How.XPATH,using="//button[normalize-space()='Login']")
	WebElement btnLogin;
	
	@FindBy(how=How.XPATH,using="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btnLogout;

	
	
	public void setUserName(String username) {
		txtEmailAddress.sendKeys(username);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLoginBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
	}
	
	public void clickLogoutBtn() {
		action.moveToElement(btnLogout).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(btnLogout)).click();
	}
}

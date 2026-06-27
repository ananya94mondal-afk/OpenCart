package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	JavascriptExecutor js;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);  
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));	
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
	}
	
//	public static void click(WebElement el) {
//		
//		el.click();
//	}
}

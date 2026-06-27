package testCases;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseTest {
	
	public Logger logger;
	  public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

	    public static WebDriver getDriver() {
	        return tdriver.get();
	    }
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","DataDriven","Master"})
	@Parameters({"browser","os"})
	public void setUp(String browser, String os) throws FileNotFoundException,IOException {
		
		 System.out.println("Browser = " + browser);
		    System.out.println("OS = " + os);
		p=new Properties();
		
		FileReader file=new FileReader(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_method").equalsIgnoreCase("remote")) {
		
			String hubrul="http://localhost:4444/wd/hub";
			DesiredCapabilities cap=new DesiredCapabilities();
			
			switch(os.toLowerCase()) {
			
			case "windows":cap.setPlatform(Platform.WIN11);break;
			case "mac": cap.setPlatform(Platform.MAC);break;
			case "linux":cap.setPlatform(Platform.LINUX);break;
			case "default": System.out.println("No required os available");return;
				
			}
			switch(browser.toLowerCase()) {
			
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge": cap.setBrowserName("MicrosoftEdge");break;
			case "firefox":cap.setBrowserName("firefox");break;
			case "default": System.out.println("No required browser matched");return;
				
			}
			
			
			tdriver.set(new RemoteWebDriver(new URL(hubrul),cap));
			
		}
		else if(p.getProperty("execution_method").equalsIgnoreCase("local")) {
			switch(browser.toLowerCase()) {
			case "chrome": tdriver.set(new ChromeDriver());break;
			case "edge": tdriver.set(new EdgeDriver());break;
			case "firefox":tdriver.set(new FirefoxDriver());break;
			case "default":System.out.println("Invalid browser");return;
			}
		}
	
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(p.getProperty("appUrl"));
		getDriver().manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression","DataDriven","Master"})
	public void tearDown() {
		getDriver().quit();
	}
	
	public String randomAlphabetic() {
		String randomdata=RandomStringUtils.secure().nextAlphabetic(8);
		return randomdata;
	}
	
	public String randomNumeric() {
		String randomdata=RandomStringUtils.secure().nextNumeric(8);
		return randomdata;
	}
	
	public String randomAlphaNumeric() {
//		String randomdata=RandomStringUtils.secure().nextAlphanumeric(8);
		
		
		return (RandomStringUtils.secure().nextAlphabetic(5)+"@"+RandomStringUtils.secure().nextNumeric(4));
	}
	
	public static String getScreenshot(String tname) {
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		
		TakesScreenshot ts= (TakesScreenshot)getDriver();
		File sourcefile=ts.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath=System.getProperty("user.dir")+"//screenshots//"+tname+"_"+timestamp+".png";
		
		File targetFile=new File(targetfilepath);
		
		sourcefile.renameTo(targetFile);
		return targetfilepath;
	}
}

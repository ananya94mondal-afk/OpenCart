package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail2.javax.DefaultAuthenticator;
import org.apache.commons.mail2.javax.EmailAttachment;
import org.apache.commons.mail2.javax.ImageHtmlEmail;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseTest;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter reporter;
	
	public ExtentReports extent; 
	
	public static ThreadLocal<ExtentTest> extenttest=new ThreadLocal<>();
	
	
	String repname;
	String timestamp;
	
	public void onStart(ITestContext context) {
		
		String timestamp =
			    new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
			        .format(new Date());

		
		repname="Test-Report-"+timestamp+".html";
		reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//reports//"+repname);
		
		reporter.config().setDocumentTitle("OpenCart Automation Report");
		reporter.config().setReportName("OpenCart Functional Testing");
		
		reporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("OS", "windwows10");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));		
		extent.setSystemInfo("Environment", "QA");
		
		String os=context.getCurrentXmlTest().getParameter("os");		
		extent.setSystemInfo("Operating System", os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> groups=context.getCurrentXmlTest().getIncludedGroups();
		if(!groups.isEmpty())
		extent.setSystemInfo("Groups", groups.toString());
	}
	
	public void onTestStart(ITestResult result) {
		
		ExtentTest test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		extenttest.set(test);
	}
	public void onTestSuccess(ITestResult result) {
		
//		test=extent.createTest(result.getTestClass().getName());
//		test.assignCategory(result.getMethod().getGroups());
		extenttest.get().log(Status.PASS, "Test Case Passed "+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		
		
//		test=extent.createTest(result.getTestClass().getName());
//		test.assignCategory(result.getMethod().getGroups());
		
		extenttest.get().log(Status.FAIL, "Test Case Failed "+result.getName());
		extenttest.get().log(Status.INFO, "Test Case Failed log "+result.getThrowable());
		
		try {
			String path=BaseTest.getScreenshot(result.getName());
			extenttest.get().addScreenCaptureFromPath(path);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		
//		test=extent.createTest(result.getTestClass().getName());
//		test.assignCategory(result.getMethod().getGroups());
		extenttest.get().log(Status.SKIP, "Test Case Skipped "+result.getName());
		extenttest.get().log(Status.INFO, "Test Case Failed log "+result.getThrowable());
	}
	
	public void onFinish(ITestContext context) {
		
		extent.flush();
		extenttest.remove();
		File reportfile=new File(System.getProperty("user.dir")+"//reports//"+repname);
		
		try {
			Desktop.getDesktop().browse(reportfile.toURI());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/*try {
			EmailAttachment attachment = new EmailAttachment();

			attachment.setPath(reportfile.getAbsolutePath());
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setName("ExtentReport.html");

			ImageHtmlEmail email = new ImageHtmlEmail();

			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(587);

			email.setAuthenticator(
			        new DefaultAuthenticator(
			                "ananya.m.mondal94@gmail.com",
			                "#19Ananya130194"));

			email.setStartTLSEnabled(true);

			email.setFrom("ananya.m.mondal94@gmail.com");

			email.setSubject("Automation Execution Report");

			email.setMsg("Please find attached OpenCart Automation Report.");

			email.addTo("ananya94mondal@gmail.com");

			email.attach(attachment);

			email.send();
		}catch(Exception e) {
			e.printStackTrace();
		}*/
	}
}

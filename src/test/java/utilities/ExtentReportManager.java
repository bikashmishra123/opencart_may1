package utilities;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReporter ; 
	public ExtentReports extent ; 
	public ExtentTest test ;
	
	String repname ;
	
	 public void onStart(ITestContext context) {
	 
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 repname = "Test-Report-" + timeStamp + ".html" ;
		 //this code will geneate time stamp 
		 
		 sparkReporter = new ExtentSparkReporter(".\\reports\\" + repname);
		 //here we are generating report dynamically 
		 
		 sparkReporter.config().setDocumentTitle("opencart Automation Report") ; //Title of the report 
		 sparkReporter.config().setReportName("opencart Functional Testing"); //name of the report
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
		 extent.setSystemInfo("Application", "opencart");
		 extent.setSystemInfo("Module", "Admin");
		 extent.setSystemInfo("Sub Module", "Customers");
		 extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		 extent.setSystemInfo("User Name", System.getProperty("user.name"));
		 extent.setSystemInfo("Environemt", "QA");
		 	 
	 }
	 
	 public void onTestSuccess(ITestResult result) { 
		 
		 test = extent.createTest(result.getName()); //create a new entry in the report
		 test.log(Status.PASS, "Test case PASSED is :" + result.getName()); //update the status pass , fail , skip	 
		 
	 }
	 
	 public void onTestFailure(ITestResult result) {
		 
		 test = extent.createTest(result.getName()); //create a new entry in the report
		 test.log(Status.FAIL, "Test FAILED" );
		 test.log(Status.FAIL, result.getThrowable().getMessage());
		 
		 try {
			 String imgPath = new BaseClass().captureScreen(result.getName());
		 }catch(IOException e1) {
			 
			 e1.printStackTrace();
		 }
	 }
	 
	 public void onTestSkipped(ITestResult result) { 
		 
		 test = extent.createTest(result.getName());
		 test.log(Status.SKIP,"Test Skipped");
		 test.log(Status.SKIP, result.getThrowable().getMessage());
	 }
	
	 public void onFinish(ITestContext context) { 
		 
		 extent.flush();
		 
		 //after everything we can write code to send to gmail, so it depends in your company what ever they will b using you can use 
		 // that host 
		
	 }

	
}

package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver ;
	public Logger logger ;
	
	//we have to take help of one special class
	public ResourceBundle rb ; // you should import from util package , if you do any mistake it will not work 
	
	@BeforeClass(groups= {"Master","Sanity","Regression"}) 
	@Parameters("browser")
	public void setup(String br) {
		
		rb = ResourceBundle.getBundle("config"); // this statemnt load the config.properties file 
		
		//ResourceBundle - a pre defined class, and getbundle is a static method
		//now by using this rb variable we can access all the variables 
		
	    logger = LogManager.getLogger(this.getClass());
		
		//ChromeOptions option = new ChromeOptions();
		//option.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		//WebDriverManager.chromedriver().setup();
	    
	    //before launching the browser we have to check the condition then we will laucnh the browser 
	    if(br.equals("chrome")) {
	    	
	    driver = new ChromeDriver() ;
	    
	    }
	    else if(br.equals("edge")) {
	    	
	    driver = new EdgeDriver();
	    	
	    }
		
	    else {
	    	
	    driver = new FirefoxDriver();
	    
	    }
		
	    //after launching the browser if any prepolulated info stored in the webpage we can delete cookies
	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(rb.getString("appURL")); // here we need to pass the key 
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Master","Sanity","Regression"}) 
	public void tearDown() {
		
		driver.quit();
	}
	
	
	public String randomString() {
		String genertaedstring = RandomStringUtils.randomAlphabetic(5);
		return (genertaedstring);
	}
	
	public String randomNumber() {
		String genertaedstring2 = RandomStringUtils.randomNumeric(10);
		return (genertaedstring2);
	}
	
	public String randomAlphaNumeric() {
		
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		return (st+"@"+num) ;
	}
	
	 public String captureScreen(String tname) throws IOException {
		 
		 String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot takesScreenshot = (TakesScreenshot) driver ;
		 
		 File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		 String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png" ;
				 
				 try {
					 FileUtils.copyFile(source, new File(destination));
				 }catch(Exception e) {
					 
					 e.getMessage();
				 }
		 
				 return destination ;
	 }
}

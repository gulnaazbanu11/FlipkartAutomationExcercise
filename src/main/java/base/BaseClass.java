package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObjects.FlipkartValidation;

public class BaseClass {

	
	public static WebDriver driver=null;
	public static String webBrowser="chrome";
	
	@BeforeMethod
	public void openBrowser() throws InterruptedException, IOException
	{
		
		if(webBrowser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//main//java//resources//chromedriver.exe");
			driver=new ChromeDriver();
			Reporter.log("Opened Chrome Browser");
		}
		else if(webBrowser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//src/main//java//resources//geckodriver.exe");
			driver=new FirefoxDriver(); 
			Reporter.log("Opened firefox Browser");
		}
		else if(webBrowser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"//src//main//java//resources//IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			Reporter.log("Opened IE Browser");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(readConfigProperty("url"));
		Reporter.log("Opened Flipkart Application");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closeBrowser() throws IOException
	{
		driver.quit();
		Reporter.log("Browser Closed");
	}
	
	public static void captureScreenshot(String screenshotName) throws IOException
	{
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/resources.Screenshots/"+screenshotName+".jpg"));
		System.out.println("Screenshot " +screenshotName+"saved successfully under resources folder");
		Reporter.log("Captured the screenshot and saved under resources folder");
	}
	
	public static String readConfigProperty(String value) throws IOException
	{
		Properties prop=new  Properties();
		FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
		prop.load(input);
		return prop.getProperty(value);
	}
	
	public static void switchToCheckoutWindow()
	{
		String mainWindow = driver.getWindowHandle();
		ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
		for(String window : windowHandles)
		{
			if(!window.equals(mainWindow))
				driver.switchTo().window(window);
		}
		Reporter.log("Switched to product window");
	}

}

package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class BaseClass {
	public  WebDriver driver=null;
	public ExtentTest test;
	public ExtentReports report;
	public ITestResult iTestResult = null;
	public LoginPage loginPage = null;
	public HomePage homePage = null;
	public String defaultBrowser = "chrome";
	@Parameters("browser")
	@BeforeMethod
	public void beforeMethod(@Optional String browser, Method method) throws InterruptedException, IOException {
		Test testMethod = method.getAnnotation(Test.class);
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		report.loadConfig(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\extent-config.xml"));
		test = report.startTest(testMethod.description());
		if (browser != null) {
			this.openBrowser(browser);
		} else {
			this.openBrowser(defaultBrowser);
		}
		loginPage = new LoginPage(this);
		homePage = new HomePage(this);
	}

	public void openBrowser(String webBrowser) throws InterruptedException, IOException
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
		driver.get(ReadProperty.readConfigProperty("url"));
		Reporter.log("Opened Flipkart Application");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void closeBrowser(Method method) throws IOException {
		Test testMethod = method.getAnnotation(Test.class);
		if (!iTestResult.isSuccess()) {
			test.log(LogStatus.FAIL, "Test "+testMethod.description()+" is failed");
			test.addBase64ScreenShot(getBase64Image());
		}
		driver.quit();
		report.endTest(test);
		report.flush();
	}
	
	public  void captureScreenshot(String screenshotName) throws IOException
	{
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/resources.Screenshots/"+screenshotName+".jpg"));
		System.out.println("Screenshot " +screenshotName+"saved successfully under resources folder");
		Reporter.log("Captured the screenshot and saved under resources folder");
	}
	

	public  void switchToCheckoutWindow()
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
	public String getBase64Image() {
		TakesScreenshot newScreen = (TakesScreenshot) driver;
		String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
		return "data:image/jpg;base64, " + scnShot;
	}

	public void waitForInvisibelityOfElement(By xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
	}
	public WebElement getVisibleElement(By xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(xpath)));
		return element;
	}
}

package com.qa.base;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.internal.invokers.TestMethodArguments;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.qa.utils.*;

public class BaseTest {
 public static WebDriver driver;
 public ExtentSparkReporter sparkReporter;
 public ExtentReports extent;
 public ExtentTest logger;
 @BeforeTest
 public void beforeTestMethod() {
//	 String sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")) + File.separator+"Reports"+ File.separator+"RamyaExtentReport.html");
	 
	 ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ramyaExtentReport.html");
	 extent = new ExtentReports();
	 extent.attachReporter(sparkReporter);
	 sparkReporter.config().setTheme(Theme.DARK);
	 extent.setSystemInfo("HostName", "RHEL8");
	 extent.setSystemInfo("UserName", "root");
	 sparkReporter.config().setDocumentTitle("Automation Report");
	 sparkReporter.config().setReportName("Automation Test Results by Ramya");

}
  @BeforeMethod
  @Parameters("browser")
  public void setupDriver(String browser,Method testMethod) {
	  logger = extent.createTest(testMethod.getName());
	  if(browser.equalsIgnoreCase("chrome")) {
		  driver = new ChromeDriver();
	  }else if(browser.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
	  }else if(browser.equalsIgnoreCase("edge")) {
		  driver= new EdgeDriver();
	  }
	  driver.manage().deleteAllCookies();
	  driver.manage().window().maximize();
	  driver.get(Constants.url);
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	 
	  
  }
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		}else if(result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));	
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
		}
		driver.quit();
	}
	@AfterTest
	public void afterTest() {
		//flushout extent reports
		extent.flush();
	}

}

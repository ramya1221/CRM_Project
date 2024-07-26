package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.google.common.io.Files;

import com.qa.base.*;
//rery also - ITestListener, IAnnotationTransformer or if test failed then it will execute below screenshot method
public class SuiteListener implements ITestListener, IAnnotationTransformer{
  public void onTestFailure(ITestResult result) {
	  String fileName = System.getProperty("user.dir")+File.separator+"Screenshots"+File.separator+result.getMethod().getMethodName();
	  File screenshot = ((TakesScreenshot)BaseTest.driver).getScreenshotAs(OutputType.FILE);
	  try {
		Files.copy(screenshot, new File(fileName+ ".png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
  public void transform(
	      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
	      annotation.setRetryAnalyzer(RetryAnalyzer.class);
	  }

	

}

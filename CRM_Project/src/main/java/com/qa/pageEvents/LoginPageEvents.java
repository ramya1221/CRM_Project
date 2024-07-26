package com.qa.pageEvents;

import org.testng.Assert;
import com.qa.base.*;
import com.qa.pageEvents.*;
import com.qa.utils.*;
import com.qa.pageObjects.*;
public class LoginPageEvents extends BaseTest{
	ElementFetch ele = new ElementFetch();
	public void verify_If_Login_Page_is_loaded() {
		Assert.assertTrue(ele.getWebElements("XPATH", LoginPageElements.loginText).size()>0, "Login Element button is not found");
		
		 
		
	}
	public void enterCredentials() {
		//logger = extent.createTest("Enter Credentials");
		ele.getWebElement("XPATH", LoginPageElements.emailText).sendKeys("rmishra@gmail.com");
		ele.getWebElement("XPATH", LoginPageElements.passwordText).sendKeys("12345");

		
	}
}

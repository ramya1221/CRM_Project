package com.qa.pageEvents;

import com.qa.base.*;
import com.qa.pageEvents.*;
import com.qa.utils.*;
import com.qa.pageObjects.*;

public class HomePageEvents extends BaseTest{
	ElementFetch ele = new ElementFetch();
	public void logInButton() {
		//logger = extent.createTest("Validate Login Button in Home Page");
		ele.getWebElement("XPATH", HomePageElements.loginButtonText).click();
		
		
	}

}

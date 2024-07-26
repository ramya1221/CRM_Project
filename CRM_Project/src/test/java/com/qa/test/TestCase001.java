package com.qa.test;

import org.testng.annotations.Test;

import com.qa.base.*;
import com.qa.pageEvents.*;
import com.qa.utils.*;

public class TestCase001 extends BaseTest{
 ElementFetch ele;
 HomePageEvents homePage = new HomePageEvents();
 LoginPageEvents logInPage = new LoginPageEvents();
 
  @Test
  public void enter_Credentials() {

	  homePage.logInButton();
	  logInPage.verify_If_Login_Page_is_loaded();
	  logInPage.enterCredentials();
  }
}

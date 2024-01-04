package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100:Design open cart login page")
@Story("US 101: Login pge features")
@Feature("F50: Feature Login Page")
public class LoginPageTest extends BaseTest {
	@Description("Login page title test........")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		String actualtitle=loginpage.getLoginPageTitle();
		Assert.assertEquals(actualtitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login page url test verification........")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageURLTest()
	{
		String actualURL=loginpage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("verifying forgot pwd link test........")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwfLinkExist()
	{
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	@Description("verifying App logo exist test........")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void appLogoExistTest()
	{
		 Assert.assertTrue(loginpage.isLogoExist());
	}
	
//	@Test(priority = 5)
//	public void aboutUsExistTest() {
//		Assert.assertTrue(loginpage.isAboutUSExist());
//	}
	@Description("Verifying user is able to login with  correct cridential........")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest()
	{
		
	//Assert.assertTrue(loginpage.doLogin("augbatch2023@opencart.com", "Selenium@12345"));
	accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	Assert.assertTrue(accpage.isLogoutLinkExist());
	
	}
	

}




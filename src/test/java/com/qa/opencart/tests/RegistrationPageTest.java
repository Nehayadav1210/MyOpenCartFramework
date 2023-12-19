package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		registerPage=loginpage.nevigateToRegisterPage();
	}
	//getrandom email id with current time
	public String getRandomEmailId() {
		
		return "testautomation"+System.currentTimeMillis()+"@opencart.com";
	//return "testautomation"+UUID.randomUUID()+"@gmail.com";
	
	}
	
	
//	
//	@DataProvider
//	public Object[][] regUserRegdata() {
//		return new Object[][] {
//			{"asha", "yadav",  "9876543224", "karu@123", "yes"},
//			{"saru", "automation",  "9876543224", "karu@123", "yes"},
//			{"paru", "auto", "9876543224", "karu@123", "yes"}
//		
//		};
//		
//	}
	
	
	
	@DataProvider
	public Object[][] getUserRegistrationTestData()
	{
	Object regData[][]=	ExcelUtils.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
	return regData;
	}
	
	@Test(dataProvider = "getUserRegistrationTestData")
	public void userRegTest(String firstName,String lastName,String telephone,String password,String suscribe ) {
  boolean isRegDone=registerPage.userRegistration(firstName, lastName,getRandomEmailId(), telephone,password, suscribe);
	
	Assert.assertTrue(isRegDone);
	}

}

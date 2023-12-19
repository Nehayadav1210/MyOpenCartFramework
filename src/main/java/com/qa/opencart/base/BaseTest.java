package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
protected	WebDriver driver;
	DriverFactory df;
protected	LoginPage loginpage;
protected AccountsPage accpage;
	protected Properties prop;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegistrationPage registerPage;

@BeforeTest
	public void setup()
	{
		df=new DriverFactory();
		
		prop=df.initProp();
		
		driver=df.initDriver(prop);
		loginpage=new LoginPage(driver);
		softAssert=new SoftAssert();
		
	}
	@AfterTest
	 public void tearDown()
	 {
		driver.quit();
	 }

}

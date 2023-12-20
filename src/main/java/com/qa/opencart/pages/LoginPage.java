package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.*;
import com.qa.opencart.utils.*;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver ;
	private ElementUtil eleUtil;
	
	
	//by locators
	private By userName=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By logo=By.cssSelector("img[title='naveenopencart']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By aboutUs=By.linkText("About Us");
	private By registerLink=By.linkText("Register");
	private By neha=By.linkText("neha");
	//page consturutor
	public LoginPage (WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
		
		
	}
	
	//page actions or pag methods
	@Step("Getting Login page Title")
	public String getLoginPageTitle() {
		
		String title	=eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_WAIT);
		
	System.out.println( "Login Page title"+title);
	return title;
		
	}
	
	@Step("Getting Login page URL")
	public String getLoginPageURL() {
		
		
		String url=	eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		
		
	//String url=	driver.getCurrentUrl();
	System.out.println( "Login Page URL"+url);
	return url;
		
	}
	
	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
	
   return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
		
		
		//	return  driver.findElement(forgotPwdLink).isDisplayed();
		
	}
	@Step("checking logo link exist")
	public boolean isLogoExist() {
		
		
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		//return driver.findElement(logo).isDisplayed();
		
	}
	
//	public boolean isAboutUSExist() {
//		return eleUtil.waitForVisibilityOfElement(aboutUs, 5).isDisplayed();
//		
//	}
//	
	@Step("username is: {0} and password {1}")
	public AccountsPage doLogin(String username,String pwd ) {
		
		System.out.println("credential are "+username+":"+password);
		
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.LONG_DEFAULT_WAIT).sendKeys(username);
		eleUtil.dosendkeys(password, pwd);
		eleUtil.doclick(loginBtn);
		return new AccountsPage(driver);
		
		
//		driver.findElement(userName).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		System.out.println("user is logged in successfully");

	
	}
	@Step("nevigating to registration page")
public RegistrationPage nevigateToRegisterPage() {
	eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();

return new RegistrationPage(driver);
}
	public boolean isAboutUSExist() {
		// TODO Auto-generated method stub
		return false;
	}

}

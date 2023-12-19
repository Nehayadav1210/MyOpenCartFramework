package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By logoutlink=By.linkText("Logout");
	private By search=By.name("search");
	private By accheaders=By.cssSelector("div#content > h2");
	private By searchIcon=By.cssSelector("div#search button");
	//page const....
	
	public AccountsPage (WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
		
	}
	
	//page methods
	
	
	
	
	

	public String getAccPageTitle() {
		
		String title=eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_WAIT);
		
	System.out.println( "My Account Page title"+title);
	return title;
		
	}
	

	public String getAccPageURL() {
		
		
		String url=	eleUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, AppConstants.MEDIUM_DEFAULT_WAIT);
		
	//String url=	driver.getCurrentUrl();
	System.out.println( "Account Page URL"+url);
	return url;
		
	}
	
	
	public boolean isLogoutLinkExist() {
		
	return	eleUtil.waitForVisibilityOfElement(logoutlink,AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
	}
	
	public void logout () {
		
		if(isLogoutLinkExist())
		{
			eleUtil.doclick(logoutlink);
		}
		
	}
	
	public boolean isSerchFieldExist() {
		
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
	}
	
	public List<String> getAccountHeaders() {
		
	List<WebElement>headerList= eleUtil.waitForVisibilityOfElements(accheaders, AppConstants.LONG_DEFAULT_WAIT);
	List<String>headervalList=new ArrayList<String>();
	for(WebElement e:headerList)
	{
		String text=e.getText();
		headervalList.add(text);
	
	}
	return headervalList;
	}
	
	
public SearchResultPage doSearch(String searchKey) {
	
	eleUtil.waitForVisibilityOfElement(search, AppConstants.LONG_DEFAULT_WAIT).clear();
	eleUtil.waitForVisibilityOfElement(search, AppConstants.LONG_DEFAULT_WAIT).sendKeys(searchKey);
	eleUtil.doclick(searchIcon);
	return new SearchResultPage(driver);
}
}

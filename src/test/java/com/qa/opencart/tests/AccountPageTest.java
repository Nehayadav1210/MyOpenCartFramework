package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;


public class AccountPageTest extends BaseTest{
	
	
	@BeforeClass
	public void accSetUp()
	{
		//accpage=new AccountsPage(driver);
		//accpage=loginpage.doLogin("augbatch2023@opencart.com", "Selenium@12345");
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void accPageTitleTest()
	{
	Assert.assertEquals(accpage.getAccPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest()
	{
	Assert.assertTrue(accpage.getAccPageURL().contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest()
	{
	Assert.assertTrue(accpage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchExistTest()
	{
	Assert.assertTrue(accpage.isSerchFieldExist());
	}
	
	
	@Test
	public void accPageHeadersTest()
	{
	List<String>actAccPageHeadersList=accpage.getAccountHeaders();
	
	
	System.out.println(actAccPageHeadersList);
	
	
	Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void searchTest()
{
	searchResultPage=	accpage.doSearch("MacBook");
	productInfoPage=searchResultPage.selectProduct("MacBook Pro");
	String actProductHeader=productInfoPage.getProductHeaderName();
	Assert.assertEquals(actProductHeader, "MacBook Pro");
		
		}
	
}


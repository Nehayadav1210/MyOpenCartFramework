
package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	
	//page const....
	
	public SearchResultPage  (WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
		
	}
	
	//page methods
	
	
	

	public ProductInfoPage selectProduct(String productName) {
		
	// By productNameLocator=By.linkText("MacBook Pro");
//eleUtil.waitForVisibilityOfElement(productNameLocator, AppConstants.LONG_DEFAULT_WAIT).click();
		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.LONG_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}

}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver ;
	private ElementUtil eleUtil;
	
	
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	
	
	private By subscribeYes=By.xpath("//label[@class='radio-inline'][position()=1]/input[@type='radio']");

	private By subscribeNo=By.xpath("//label[@class='radio-inline'][position()=2]/input[@type='radio']");
	
	private By agreeCheckBox=By.name("agree");
	private By continueButton=By.xpath("//input[@type='submit' and @value ='Continue']");
	
	private By successMessg=By.cssSelector("div#content h1");
	private By logoutLink =By.linkText("Logout");
	private By registerLink =By.linkText("Register");
	
	
	//page consturutor
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);

}

public boolean userRegistration(String firstName,String lastName,String email,String telephone,String password , String subscribe) {
       
    	   eleUtil.waitForVisibilityOfElement(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(firstName);;
    	   eleUtil.dosendkeys(this.lastName, lastName);
    	   eleUtil.dosendkeys(this.email, email);
    	   eleUtil.dosendkeys(this.telephone, telephone);
    	   eleUtil.dosendkeys(this.password,password);
    	   eleUtil.dosendkeys(this.confirmPassword,password);
    	   
    	   
    	  if(subscribe.equalsIgnoreCase("yes"))
    	  {
    		  eleUtil.doclick(subscribeYes);
    	  }
    	  else {
			
    		  eleUtil.doclick(subscribeNo);
		}
    	  
    	  eleUtil.doclick(agreeCheckBox);
    	  eleUtil.doclick(continueButton);
    	  
    	  String successMesg =eleUtil.waitForVisibilityOfElement(successMessg, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
      
System.out.println(successMesg);

if(successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESG))
{
	
	eleUtil.doclick(logoutLink);
	eleUtil.doclick(registerLink);
	return true;
	}else {
		return false;
	}

}
	


}


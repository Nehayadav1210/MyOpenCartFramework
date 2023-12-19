package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	
	public ElementUtil(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		jsUtil=new JavaScriptUtil(driver);
	}
	

	private void isHighlight(WebElement element) {
		if (Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsUtil.flash(element);
		}
	}
	
	
	
	public By getBy(String locatorType,String locatorValue  ) {
		
		By by=null;
		switch (locatorType.toLowerCase().trim()) {
		case"id": 
			by=By.id(locatorValue);
			break;
		case"name": 
			by=By.name(locatorValue);
			break;
		case"classname": 
			by=By.className(locatorValue);
			break;
		case"xpath": 
			by=By.xpath(locatorValue);
			break;
		case"cssselector": 
			by=By.cssSelector(locatorValue);
			break;
		case"tagname": 
			by=By.tagName(locatorValue);
			break;
		case"linktext": 
			by=By.linkText(locatorValue);
			break;
		case"partiallinktext": 
			by=By.partialLinkText(locatorValue);
			break;

		default:
			System.out.print("wrong locator passed");
			throw  new FrameworkException ("WRONG LOCATER TYPE");
		
		}
		return by;
		
	}
	
	
	
	public String doGetElementAttribute(By locator,String attrName)
	{
		return getElement(locator).getAttribute(attrName);
		
	}
	@Step("clicking on element: {0} ")
	public  void doclick(By locator)
	{
		getElement(locator).click();
		}
	public  void doclick(String locatorType,String locatorValue)
	{
		getElement(locatorType, locatorValue).click();
		}
	
	
	
	public String doElementgetText(By locator) {
		return getElement(locator).getText();
		
	}
	
	public String doElementgetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
		
	}
	
	
	@Step("entering value: {1} to element{0}")
	public void dosendkeys(String locatorType,String locatorValue, String value)
	{
		getElement(locatorType, locatorValue).sendKeys(value);
		}
	
	
	
	public void dosendkeys(By locator,String value)
	{
		getElement(locator).sendKeys(value);
	
		}
	
	
	public WebElement getElement(By locator)
	{
		WebElement element=driver.findElement(locator);
		isHighlight(element);
	return element;
	}
	

	public  WebElement getElement(String locatorType,String locatorValue)
	{
		WebElement element=driver.findElement(getBy( locatorType,locatorValue));
		isHighlight(element);
		return element;
		}
	 
	 
		
	
public  int TotalElementsCount(By locator) {
		
		return getElements(locator).size();
	}
	
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
		
	}

	public List<String> getElementTextList(By locator) {
List<WebElement>elelist =getElements(locator);
	List<String>eleTextList=new ArrayList<String>();
	
	
	for(WebElement e:elelist)
	{
		String text=e.getText();
		if(text.length()!=0)
		{
			eleTextList.add(text);
		}
		
	}return eleTextList;
	}
	
	//************select
	
	
	
	private Select createSelect(By locator) {
	Select select=new Select(getElement(locator));
	return select;
		
	}
	
	public void selectDropDownValue(By locator,String value)
	{
		List<WebElement> optionlist=getElements(locator);
		for(WebElement e:optionlist)
			
		{
			
			//String text=e.getText();
		//	e.getText();
			
			if(e.equals(value))
			{
				e.click();
			}
			break;
			}
	
	}
	
	
	
	public  void doSelectDropDownByIndex(By locator,int index) {
		//Select select=new Select(getElement(locator));
		createSelect(locator).selectByIndex(index);
		//select.selectByIndex(index);
	}


	public  void doSelectDropDownByVisibleText(By locator,String visibletext) {
		//Select select=new Select(getElement(locator));
		//select.selectByVisibleText(visibletext);
		createSelect(locator).selectByVisibleText(visibletext);
		
		
	}


	public  void doSelectDropDownByValue(By locator,String value) {
		createSelect(locator).selectByValue(value);
	}
	
	
	public  int getDropDownOptionsCount(By locator) {
		Select select=new Select(getElement(locator));
		return createSelect(locator).getOptions().size();
	}
	
	public  void selectDropDownOption(By locator,String dropDownValue) {
		//Select select=new Select(getElement(locator));
		List<WebElement>optionsList=createSelect(locator).getOptions();
		System.out.println(optionsList.size());
		for(WebElement e:optionsList)
		{
			String text=e.getText();
			System.out.println(text);
			if(text.equals(dropDownValue))
			{
				e.click();
				break;
			}}
		}
	
	
		public List<String> getDropDownOptions(By locator) {
			//Select select=new Select(getElement(locator));
			List<WebElement>optionsList=createSelect(locator).getOptions();
			List<String>optionsTextList=new ArrayList<String>();
			
			for(WebElement e:optionsList)
			{
				String text=e.getText();
				optionsTextList.add(text);
				
				}
			return optionsTextList;
			}
		
		public   boolean isDropDownMultiple(By locator) {
			
			//Select select=new Select(getElement(locator));
			return createSelect(locator).isMultiple() ?true :false;
		}
		
		
		
		
		
		/*This methos is used to select the values from the drop down
		 * 1. single selection
		 * multi values selection
		 * all  values*/
		public  void selectDropDownMultipleValues(By locator,By optionLocator,String...values) {
			//Select select=new Select(getElement(locator));
			if(isDropDownMultiple(locator))
			{
				
					if(values[0].equalsIgnoreCase("all"))
					{
					List<WebElement>optionlist=	getElements(optionLocator);
					
					for(WebElement e: optionlist)
					{
						e.click();
					}
					
					}else {
						for(String value : values)
						{
							createSelect(locator).selectByVisibleText(value);
						}
					}
				
				}
			}
		/********* Action **************      */
		
		 public void twoLevelMenuHandle(By parentMenuLocator,By childMenuLocator) throws InterruptedException {
			
			Actions act=new Actions(driver);
			act.moveToElement(getElement(parentMenuLocator)).build().perform();
			Thread.sleep(2000);
			driver.findElement(childMenuLocator).click();
		doclick(childMenuLocator);
		  }
		 
		 
		 public void fourLevelMenuHandle(By parentMenuLocator,By firstChildMenuLocator,By secondChildMenuLocator,By ThirdChildMenuLocator ) throws InterruptedException
			{
				Actions act=new Actions(driver);
				driver.findElement(parentMenuLocator).click();
				Thread.sleep(2000);
				act.moveToElement(getElement(firstChildMenuLocator)).build().perform();
				Thread.sleep(2000);
				act.moveToElement(getElement(secondChildMenuLocator)).build().perform();
				Thread.sleep(2000);
				act.moveToElement(getElement(ThirdChildMenuLocator));
				Thread.sleep(2000);
				doclick(ThirdChildMenuLocator);
				
			}
		 
		 
		 public  void doActionClick(By locator) {
				Actions act=new Actions(driver);
				act.click(getElement(locator)).perform();
				
			}
			
			public  void doActionSendKeys(By locator,String value) {
				Actions act=new Actions(driver);
				act.sendKeys(getElement(locator), value).perform();
				
				
			}
			
			
			
			public  void doActionSendKeysWithPause(By Locator,String value) {
				Actions act=new Actions(driver);
				//String value="naveen@gmail.com";
				char val[]=value.toCharArray();
				for(char c:val)
				{
					
					act.sendKeys(getElement(Locator), String.valueOf(c)).pause(500).build().perform();
			}
			}
			
			
			
			
			//*****************Wait Util********
			
			
			
			
			
			public  WebElement waitForPresenceOfElement(By locator,int timeout) {
				
				WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
				
				
				WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				isHighlight(element);
				return element;
				}
				
				
				
			
public  WebElement waitForPresenceOfElement(By locator,int timeout,int intervalTime) {
				
				WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout),Duration.ofSeconds(intervalTime));
				WebElement element=wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				isHighlight(element);
				return element;
				}
				
				//its present on the dom nad page also
@Step("waiting for element: {0} with timeout{1}")
				public  WebElement waitForVisibilityOfElement(By locator,int timeout) {
					
					WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
					WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
					isHighlight(element);
					return element;
					
					}
				
				
					public  WebElement waitForVisibilityOfElement(By locator,int timeout,int intervalTime) {
					
					WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout),Duration.ofSeconds(intervalTime));
					WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
					isHighlight(element);
					return element;
					
					
					}
				
	
	
				public   void doclickWithWait(By locator,int timeOut) {
					
					waitForPresenceOfElement(locator, timeOut).click();
					
				}
			public   void doSendKeysWithWait(By locator,String value,int timeOut) {
					
					waitForPresenceOfElement(locator, timeOut).sendKeys(value);;
					
				}
			
			public  List<WebElement> waitForVisibilityOfElements(By locator,int timeout) {
				
				WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
				
				
				}
			
			
			
public  List<WebElement> waitForVisibilityOfElements(By locator,int timeout,int intervalTime) {
				
				WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout),Duration.ofSeconds(intervalTime));
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
				
				
				}


		//its not related to the dom at least onr element presence on the page
		public List<WebElement> waitForPresenceOfElements(By locator,int timeout) {
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
			
			}
		
		@Step("waiting for url: {0} timeout{1}")
		public  String waitForTitleContains(String titleFraction,int timeout) {
			
			
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			try {
			if (wait.until(ExpectedConditions.titleContains(titleFraction)))
			{
				return driver.getTitle();
				}
			}catch (TimeoutException e) {
				System.out.println(titleFraction +"title value is not present...");
				e.printStackTrace();
				
			}
			return null;
			
				}
		@Step("waiting for the page title{0} and timeout: {1}")
	public  String waitForTitleIs(String title,int timeout) {
		
			
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			try {
			if (wait.until(ExpectedConditions.titleContains(title)))
			{
				return driver.getTitle();
				}
			}catch (TimeoutException e) {
				System.out.println(title+"title value is not present...");
				e.printStackTrace();
				
			}
			return null;
			
				}
		
		
	public  String waitForURLContains(String urlFraction,int timeout) {
		
		
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
		if (wait.until(ExpectedConditions.urlContains(urlFraction)))
		{
			return driver.getCurrentUrl();
			}
		}catch (TimeoutException e) {
			System.out.println(urlFraction+"url value is not present...");
			e.printStackTrace();
			
		}
		return null;
		
			}
	//fullurl
	
	public  String waitForURLToBe (String url,int timeout) {
		
		
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
		if (wait.until(ExpectedConditions.urlToBe(url)))
		{
			return driver.getTitle();
			}
		}catch (TimeoutException e) {
			System.out.println(url+"title value is not present...");
			e.printStackTrace();
			
		}
		return null;
		
			}
	
	
	
	//*************Waitfor lert
	
	
public  Alert waitForJSAlert(int timeout) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public  void acceptJSAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
		
	} 
	public  void dismissJSAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
		
	} 
	public  void acceptJSAlertText(int timeOut) {
		waitForJSAlert(timeOut).getText();
		
	} 
	public  void enterValueOnJSAlert(int timeOut,String value) {
		waitForJSAlert(timeOut).sendKeys(value);
		
	} 

	
	public  void waitForFrameByLocator(By frameLocator,int timeOut) 
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		
	}
	
	
	public  void waitForFrameByIndex(int frameIndex,int timeOut) 
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		
	}
	
	public  void waitForFrameByIdOrName(String IdOrName,int timeOut) 
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IdOrName));
		
	}
	
	public  void waitForFrameByElement(WebElement frameElement,int timeOut) 
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		
	}
	
	
	public   boolean  checkNewWindowExist(int timeOut,int expectedNumberOfWindows) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		
		//it will not swithing to window automaticaly
		try {
		if(wait.until(ExpectedConditions.numberOfWindowsToBe(2)))
		{ return true;
		}
		}catch (TimeoutException e) {
			System.out.println("number of windows are not same....");
		}
		
		
	return false;
	}
	
	
	public void clickElementWhenReady(By locator,int timeOut) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	
	public  WebElement waitForElementWithFluentWait(By locator,int timeOut,int intevalTime) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intevalTime))
				.withMessage("--time out is done...element is not found--")
				.ignoring(NoSuchFieldException.class)
				.ignoring(ElementNotInteractableException.class);
		
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		isHighlight(element);
		return element;
	}
	
	
	public  void waitForFrametWithFluentWait(String frameIDOrName,int timeOut,int intevalTime) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intevalTime))
				.withMessage("--time out is done...frame is not found--")
				.ignoring(NoSuchFrameException.class);
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDOrName));
			
		}
	
	public  Alert waitForJSAlertWithFluentWait(int timeOut,int intevalTime) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intevalTime))
				.withMessage("--time out is done...Alert is not Apear--")
				.ignoring(NoAlertPresentException.class);
		
		return wait.until(ExpectedConditions.alertIsPresent());
			
		}
	
	
	
	//*********************Custom wait
	
	
	public  WebElement retryingElement(By locator,int timeOut ){
		WebElement element=null;
		int attempts=0;
		while(attempts<timeOut)
		{
			try
			{
			element=getElement(locator);
			System.out.println("element is found"+locator+"in attempt"+attempts);
			break;
		} 
			catch(NoSuchElementException e)
		{
				System.out.println("element is ot found"+locator+"in attempt"+attempts);
				try {
					Thread.sleep(500);//polling time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				}
			
		
		attempts++;
		}
		if(element==null)
		{
		
			System.out.println("element is not found tried for"+timeOut+"times"+"with the interval of"
			+500+"milli second");
		throw new FrameworkException("No such element");
		}
		isHighlight(element);
		return element;
		}
	
	
	
	public WebElement retryingElement(By locator,int timeOut,int intervalTime ){
		WebElement element=null;
		int attempts=0;
		while(attempts<timeOut)
		{
			try
			{
			element=getElement(locator);
			System.out.println("element is found"+locator+"in attempt"+attempts);
			break;
		} 
			catch(NoSuchElementException e)
		{
				System.out.println("element is ot found"+locator+"in attempt"+attempts);
				try {
					Thread.sleep(intervalTime);//polling time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				}
			
		
		attempts++;
		}
		if(element==null)
		{
		
			System.out.println("element is not found tried for"+timeOut+"times"+"with the interval of"+intervalTime+"milli second");
		throw new FrameworkException("No such element");
		}
		isHighlight(element);
		return element;
		}
	
	
	
	
	
	
	
	public  boolean isPageLoaded(int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	String flag=	wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete'")).toString();
		
	
	return Boolean.parseBoolean(flag);
	}
		
	}
	



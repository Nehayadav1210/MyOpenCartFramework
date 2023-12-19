package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {


private WebDriver driver;

private JavascriptExecutor js;
public JavaScriptUtil(WebDriver driver) {
	
	this.driver=driver;
	js=(JavascriptExecutor)this.driver;
	
}
public String getTitleByJs() {
	
	return js.executeScript("return document.title").toString();
	
	
}

public String getURLByJs() {

	return js.executeScript("return document.URL").toString();
}

public void generateJSAlert(String mesg) {
	js.executeScript("alert('"+mesg+"')");
	
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver.switchTo().alert().accept();
}


public void generateJSConfirm(String mesg) {
	js.executeScript("confirm('"+mesg+"')");
	
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver.switchTo().alert().accept();

}


public void generateJSPromp(String mesg,String value) {
	js.executeScript("prompt('"+mesg+"')");
	
	try {
		Thread.sleep(500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Alert alert=driver.switchTo().alert();
	alert.sendKeys(value);
	alert.accept();

}

public void goBackWithJS() {
	js.executeScript("history.go(-1)");
	
}
public void goForwardWithJS() {
	js.executeScript("history.go(1)");
	
}
public void pageRefereshWithJS() {
	js.executeScript("history.go(0)");
	
}

//the text of entire text
//document.documentElement.innerText

private String getPageInnerText() {
	return js.executeScript("return document.documentElement.innerText;").toString();
	
}

//for scrollling

public void scrollPageDown() {
	js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	
}

public void scrollMiddlePageDown() {
	js.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");
	
}


public void scrollIntoView(WebElement element) {
	js.executeScript("arguments[0].scrollIntoView(true);",element );
	
}
public void scrollPageDown(String height) {
	js.executeScript("window.scrollTo(0,'"+height+"')");
	
}
public void scrollPageUp() {
	js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	
}

public void zoomChromeEdgeSafari(String zoomPercentage) {
	String zoom="document.body.style.zoom = '"+zoomPercentage+"%'";
	js.executeScript(zoom);
	
}

public void zoomFirefox(String zoomPercentage) {
	String zoom="document.body.style.MozTransform = 'scale("+zoomPercentage+")'";
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript(zoom);
	
}
public void flash1(WebElement element) {
	String bgcolor=element.getCssValue("backroundColor");
	for(int i=0;i<10;i++)
	{
		changeColor("rgb(0,200,0)",element);
		changeColor(bgcolor, element);
	}	
}

private void changeColor(String string, WebElement element) {
	// TODO Auto-generated method stub
	
}
public void drawBorder(WebElement element) {
	js.executeScript("arguments[0].style.border='3px solid red'", element);
	
}
public void flash(WebElement element) {
	// TODO Auto-generated method stub
	
}

}
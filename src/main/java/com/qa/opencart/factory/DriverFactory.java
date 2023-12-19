package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.io.Files;

import com.qa.opencart.exception.FrameworkException;


public class DriverFactory {
	
WebDriver driver;
Properties prop;
OptionsManager optionManager;
public static ThreadLocal<WebDriver>tldriver=new ThreadLocal<WebDriver>();
public static String highlight=null;




public WebDriver initDriver(Properties prop) {
	String browserName=prop.getProperty("browser");
	//String browserName=System.getProperty("browser");
	
	System.out.print("browsername is =" +browserName);
	highlight=prop.getProperty("highlight");
	
	optionManager=new OptionsManager(prop);
	
	switch (browserName.toLowerCase().trim()) {
	case "chrome":
		 
	//driver=new ChromeDriver(optionManager.getChromeOption());
	tldriver.set(new ChromeDriver(optionManager.getChromeOption()));
	
	break;
	case "firefox":
		 //driver=new FirefoxDriver(optionManager.getFirefoxOption());
		
		tldriver.set(new FirefoxDriver(optionManager.getFirefoxOption()));
		
		
		break;
	case "edge":
		//driver=new EdgeDriver(optionManager.getEdgeOptions());
		tldriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
		
		break;
	case "safari":
		// driver=new SafariDriver();
		 tldriver.set(new SafariDriver());
		break;

	default:
		System.out.print("please pass currect browser");
		throw new FrameworkException("INVALID BROWSER"+browserName);
		
	}
getDriver().manage().deleteAllCookies();
getDriver().manage().window().maximize();
//driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
getDriver().get(prop.getProperty("url"));

return getDriver();
}

public  static WebDriver getDriver() {
return	tldriver.get();
	
}



public Properties initProp()

//java code
{
	//mvn clean insatall -Denv="qa"
	FileInputStream ip=null;
	prop=new Properties();
String envName=	System.getProperty("env");
System.out.println("env name=:"+envName);
	try {
if(envName==null)
{
	ip=new FileInputStream("./src/test/resources/config/config.qa.properties");
	}
else {
	switch (envName.toLowerCase().trim()) {
	case "qa":
		ip=new FileInputStream("./src/test/resources/config/config.qa.properties");
		break;
		
	case "dev":
		ip=new FileInputStream("./src/test/resources/config/config.dev.properties");
		break;
		
	case "stage":
		ip=new FileInputStream("./src/test/resources/config/config.stage.properties");
		break;
		
	case "uat":
		ip=new FileInputStream("./src/test/resources/config/config.uat.properties");
		break;
		
	case "prod":
		ip=new FileInputStream("./src/test/resources/config/config.properties");
		break;

	default:
		System.out.println("please pass right  env name"+envName);
		throw new FrameworkException("wrong Env Name="+envName);
		
	}
}
	
	}
	catch(FileNotFoundException e)
	{
	}
	try {
		prop.load(ip);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop;


	
	
//	prop=new Properties();
//	try {
//		FileInputStream ip=new FileInputStream("./src/test/resources/config/config.properties");
//	
//	prop.load(ip);
//	
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return prop;
	
	}

public static String getScreenshot(String methodName) {
	File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	
	String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+".png";
			
	File destination = new File(path);
	
	try {
		org.openqa.selenium.io.FileHandler.copy(srcFile, destination);
		
		//Files.copy(srcFile, destination);
	
		
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}

	return path;
}
	

}

package com.modules.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.maven.surefire.shared.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class CommonFunctions {
	public static WebDriver driver;
	static Document doc;
	static ExtentReports report;
	static ExtentTest test;
	
	public static void openurl() throws IOException, InterruptedException {	
		try {
		String browsertype=getglobalprops("browser");
			if(browsertype.equals("Chrome")) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\web-drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String url=getdatafromxml("url");
		System.out.println(url);
		driver.get(url);
		pageloadwait();
		loggerWithScreenshot("URL launched","PASS");
			}
		}catch (Exception e) {
			loggerWithScreenshot("URL not launched","FAIL");
		}			
	}
	
	public static String getglobalprops(String key) throws IOException {
		Properties pr= new Properties();
		FileInputStream in= new FileInputStream("global.properties");
		pr.load(in);
		String value=pr.getProperty(key);
		return value;		
	}
	
	public static String getdatafromxml(String data) throws DocumentException {
		File in= new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata.xml");
		SAXReader sx= new SAXReader();
		doc= sx.read(in);
		String key="//menu/"+data;
		String value=doc.selectSingleNode(key).getText();
		return value;
	}
	
	public static void pageloadwait() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	//Reporting
	@BeforeMethod
	public void setupReports() {
	report = new ExtentReports("C:/Workspace/ExtentReport.html",true);
	test=report.startTest(this.getClass().getName());
	}
	
	@AfterMethod
	public void closeReports() {
	report.endTest(test);
	report.flush();
	}
	
	@AfterClass
	public void closeDriver(){
		driver.quit();
	}
	
	public void logger(String method,String status) {
		test.log(LogStatus.valueOf(status), method);
	}
	
	public static void loggerWithScreenshot(String method,String status) throws IOException, InterruptedException {
		String filewithpath= "C:\\Workspace\\Snapshots\\"+method+".PNG";
		getsnapshot(driver,method);
		Thread.sleep(1000);
		test.log(LogStatus.valueOf(status), test.addScreenCapture(filewithpath));
	}
	
	public static void getsnapshot(WebDriver driver,String method) throws IOException {
	  TakesScreenshot tk= (TakesScreenshot)driver;
	  File old= tk.getScreenshotAs(OutputType.FILE);
	  String filewithpath= "C:\\Workspace\\Snapshots\\"+method+".PNG";
	  File nw= new File(filewithpath);
	  FileUtils.copyFile(old,nw);
	  
 }
}

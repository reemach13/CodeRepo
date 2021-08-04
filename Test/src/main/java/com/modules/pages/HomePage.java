package com.modules.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.modules.utilities.CommonFunctions;

public class HomePage extends CommonFunctions {
	
	static WebElement element=null;
	
	public static WebElement signIn_button(WebDriver driver) {
		element= driver.findElement(By.name("name"));
		return element;
	}
	
	public static WebElement from_txtbox(WebDriver driver) {
		element= driver.findElement(By.id("gosuggest_inputSrc"));
		return element;
	}
	
	public static WebElement destination_txtbox(WebDriver driver) {
		element= driver.findElement(By.id("gosuggest_inputDest"));
		return element;
	}
	
	public static WebElement departure_datebox(WebDriver driver) {
		element= driver.findElement(By.id("departureCalendar"));
		return element;
	}
	
	public static WebElement return_datebox(WebDriver driver) {
		element= driver.findElement(By.id("returnCalendar"));
		return element;
	}
	
	public static WebElement search_button(WebDriver driver) {
		element= driver.findElement(By.id("gi_search_btn"));
		return element;
	}
	
	public static WebElement travellercount_selector(WebDriver driver) {
		element= driver.findElement(By.id("pax_link_common"));
		return element;
	}
	
	public static WebElement departure_Date(WebDriver driver) {
		element= driver.findElement(By.id("fare_20210805"));
		return element; 
	}
	
	public static WebElement error_Message(WebDriver driver) {
		element= driver.findElement(By.xpath("//span[@class='status_cont red ico13']"));
		return element;
	}	
	
	
	
	
	
	/** Methods for performing actions on the page objects 
	 * @throws InterruptedException **/
	public void selectFromCity(WebDriver driver) throws InterruptedException {
		from_txtbox(driver).click();
		from_txtbox(driver).sendKeys("Kolkata");
		pageloadwait();
		from_txtbox(driver).sendKeys(Keys.ARROW_DOWN);
		pageloadwait();
		from_txtbox(driver).sendKeys(Keys.ENTER);
		logger("From City is selected","PASS");
	}
	
	public void selectToCity(WebDriver driver) throws InterruptedException {
		destination_txtbox(driver).click();
		destination_txtbox(driver).sendKeys("Mumbai");
		pageloadwait();
		destination_txtbox(driver).sendKeys(Keys.ARROW_DOWN);
		pageloadwait();
		destination_txtbox(driver).sendKeys(Keys.ENTER);
		logger("To City is selected","PASS");
		}
	
	public void selectDepartureDate(WebDriver driver) throws InterruptedException, IOException {
		departure_datebox(driver).click();
		departure_Date(driver).click();
		pageloadwait();	
		loggerWithScreenshot("Date is selected","PASS");
	}
	
	public void selectReturnDate(WebDriver driver) throws InterruptedException {
		return_datebox(driver).click();
		pageloadwait();
	
		}

	public void clickSearchButton(WebDriver driver) throws InterruptedException {
		search_button(driver).click();
		pageloadwait();
		logger("Clicked Search Button","PASS");
	}
	
	public void selectTraveller(WebDriver driver) throws InterruptedException, IOException {
		travellercount_selector(driver).click();
		pageloadwait();
		loggerWithScreenshot("No. of traveller is selected","PASS");
	}

	public void verifyErrorWithoutDate(WebDriver driver) {
		try {
		if (error_Message(driver).isDisplayed()) 
			loggerWithScreenshot("Error is displayed for blank Date","PASS");}
		catch (Exception e) {
			logger("Error is not displayed for blank Date","FAIL");
		}
		
	}
}

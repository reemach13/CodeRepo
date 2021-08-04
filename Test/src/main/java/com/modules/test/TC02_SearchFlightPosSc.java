package com.modules.test;

import java.io.IOException;
import org.dom4j.DocumentException;
import com.modules.pages.HomePage;
import com.modules.utilities.CommonFunctions;
import org.testng.annotations.Test;

public class TC02_SearchFlightPosSc extends CommonFunctions {
	
	static HomePage hpage= new HomePage();
	
	  @Test
	    public static void login() throws IOException,InterruptedException,DocumentException {
	       openurl();
	       
	       //Search a Flight
	       hpage.selectFromCity(driver);  
	       hpage.selectToCity(driver);
	       hpage.selectDepartureDate(driver);
	       hpage.selectTraveller(driver);
	       hpage.clickSearchButton(driver);
	      
	  }
}

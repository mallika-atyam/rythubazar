//import MercuryDemoTours;

package vegetables;

import CommonUtil.*;
import HTMLReport.TestHTMLReporter6;
import ExcelUtil.ExcelApiTest3;
import Login.Login;

import org.testng.annotations.Test;



import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import org.apache.commons.io.FileUtils;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.ie.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import java.sql.Timestamp;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

import java.io.File;
import java.io.*;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import java.net.*;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.*;

/*
1. Open Chrome browser
2. open Manarythubazar website
3. Navigate to Hyd screen
4. Navigate to erragadda, falaknama, mehdipatnam screens
5. Export Export vegetable costs to Excel sheet
6. Find least cost of vegetables and export to Excel sheet
7. Quit
*/
 
public class ExportnLeastCosts
{
	//public String UserName,Password;
	//public String empname;
	
	
	public  int iRow;
	
	WebDriver driver;
	TestHTMLReporter6 TH3;	
	String HtmlOutputFileName;
	String error;
	
	public  void ExportnLeastCosts(WebDriver driver,String HtmlOutputFileName,TestHTMLReporter6 TH3  )throws Exception
	{  
			  this.driver=driver;
			  this.HtmlOutputFileName=HtmlOutputFileName;
			  this.TH3=TH3;
	}
	
	// Draws a red border around the found element. Does not set it back anyhow.
		public WebElement findElement(By by)throws Exception {
		    WebElement elem = driver.findElement(by);
		 
		    // draw a border around the found element
		    if (driver instanceof JavascriptExecutor) {
		        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", elem);
		    } 
		    Thread.sleep(10);
		    
		    return elem;
		}
		
		
	
	
	
	
	@Test
	public  void ExportAllVegetables( )throws Exception
	{  
		
		TH3= new TestHTMLReporter6();
		HtmlOutputFileName=TH3.CretaeHTMLFile("TC01_Veg.xls","Chrome");
	    driver=TestBrowser.OpenChromeBrowser();
			
		
			 ExcelApiTest3 eat = new ExcelApiTest3();
			 
				 for(int i=1;i<27;i++)
				 {
					 eat.PutCellData( "C://HTML Report//OrangeHRM6//TC01_Veg.xls","Sheet1",i,4,"999"); 
				 } 
				  call_allmethods();
				 
				 
	}
	
				 
				 
				 public void call_allmethods()throws Exception
					{  
						
					 
					//String str=String.valueOf(iRow);
					
						Login l1=new Login();
						l1.Login(driver ,HtmlOutputFileName,TH3);
						l1.openRythubazar();
						TH3.HTMLScreenShot("Rythubazar website opened","Rythubazar website opened","Pass", HtmlOutputFileName,driver);
						
						ExportnLeastCosts Ae=new  ExportnLeastCosts();		
						Ae.ExportnLeastCosts(driver ,HtmlOutputFileName,TH3);

						 Actions actions = new Actions(driver);   
						 WebElement ele=findElement(By.xpath(OR.HYD));
				         actions.moveToElement(ele).click().perform();
				         TH3.HTMLScreenShot("HomePage navigation to Hyderabadpage","Navigated to Hyderabad Page","Pass", HtmlOutputFileName,driver);
				         
						Ae.Export_VegetableCosts(1);
						Ae.Export_VegetableCosts(2);
						Ae.Export_VegetableCosts(3);
						//TH3.HTMLScreenShot("Export Employee Details","Employee Details exported to E://OrangeHrm//TC05_empname.xls","Pass", HtmlOutputFileName,driver);
						
						TH3.HTMLCloser(HtmlOutputFileName);
						TH3=null;
						System.gc();
						driver.quit();
					}
					
				 

					public  void Export_VegetableCosts(int market)throws Exception
					{ 
						
						
						 if (market==1)
						 {
							 findElement(By.xpath(OR.Erragadda)).click();
							 TH3.HTMLScreenShot("Export of Erragadda market Vegetable costs","Vegetable costs exported to C://OrangeHrm//TC01_Veg.xls","Pass", HtmlOutputFileName,driver);
						 }
						 else if(market==2)
						 {
							 findElement(By.xpath(OR.Falaknama)).click();
							 TH3.HTMLScreenShot("Export of Falaknama Vegetable costs","Vegetable costs exported to C://OrangeHrm//TC01_Veg.xls","Pass", HtmlOutputFileName,driver);
						 }
						 else if(market==3)
						 {
							 findElement(By.xpath(OR.Mehdipatnam)).click();
							 TH3.HTMLScreenShot("Export of Mehdipatnam Vegetable costs","Vegetable costs exported to C://OrangeHrm//TC01_Veg.xls","Pass", HtmlOutputFileName,driver);
						 }
						
						ExcelApiTest3 eat = new ExcelApiTest3();
				        int k=1,cheaper;
				         
								    for ( int i=1 ; i<4 ;i++)
								    {
								    	  for ( int j=1; j<10 && k<27 ;j++,k++)
								    	   {
								    		 // cheaper=eat.getCellTypeEnum("C://HTML Report//OrangeHRM6//TC01_Veg.xls","Sheet1",k,4);
								    		 //cheaper= ( ( (("C://HTML Report//OrangeHRM6//TC01_Veg.xls").getSheet("sheet1")).getRow(k) ).getCell(4)).getCellTypeEnum();
								    		  	String str1="//*[@id='body']/section/div/div[2]/div[1]/div[2]/div["+i+"]/ol/li["+j+"]/table/tbody/tr/td[4]";
								    		  	
								      		//*[@id="resultTable"]/tbody/tr[1]/td[2]
								    		    WebElement CellText1=findElement(By.xpath(str1));
								    		   
								    	        String valueIneed1 = CellText1.getText();
								    	        valueIneed1=valueIneed1.substring(1);
								    	        
								    	        
								    	        if (valueIneed1.endsWith("/Kg"))
								    	        {
								    	        int len=valueIneed1.length();
								    	        valueIneed1=valueIneed1.substring(0,len-3);
								    	        }
								    	        
								    	        
								    	      //  System.out.println("Cell Text Value is: " + valueIneed1);
								    	        
								    	        if (valueIneed1 !=null)
								    	        {
								    	        eat.PutCellData( "C://HTML Report//OrangeHRM6//TC01_Veg.xls","Sheet1",k,market,valueIneed1);
								    	        int cheaperweb= Integer.parseInt(valueIneed1);
								    	        String cheaperstr=eat.getCellData("C://HTML Report//OrangeHRM6//TC01_Veg.xls","Sheet1",k,4);
								    	        cheaper=Integer.parseInt(cheaperstr);
								    	        if (cheaperweb < cheaper)
								    	        {
								    	        	 eat.PutCellData( "C://HTML Report//OrangeHRM6//TC01_Veg.xls","Sheet1",k,4,valueIneed1);  	
								    	        }
								    	        }
								    	        else
								    	        eat.PutCellData( "C://HTML Report//OrangeHRM6//TC01_Veg.xls","Sheet1",k,market,"Blank Data");
								    	     //  if (valueIneed1 < cheaper) 
								    	    	//   eat.PutCellData( "C://HTML Report//OrangeHRM6//TC01_Veg.xls","Sheet1",k,4,valueIneed1);   
								    	   }
								    }
				   
								    TH3.HTMLScreenShot("Export Vegetable costs","Vegetable costs exported to C://OrangeHrm//TC01_Veg.xls","Pass", HtmlOutputFileName,driver);
								    findElement(By.xpath(OR.HYD_Sub)).click();
					}
					
					
					


					@BeforeTest
				    public void suiteSetup1() throws Exception {
						
						 String xlsFile="C://HTML Report//HtmlTemplates//TC05.xls";
						 String xlsFileSheet="Sheet1";
						 
						 ExcelApiTest3 eat = new ExcelApiTest3();
						 eat.clearsheetdata(xlsFile,xlsFileSheet);
				    }  	
					
					@AfterTest
				  
				    public void AftersuiteSetup2() throws Exception {
						 
						 TestHTMLReporter6 TH8 = new TestHTMLReporter6();	
						 TH8.Regression_CretaeHTMLFile();
						 
				    }    			
										
					
}
				        
					
					
				
					
					
					
					
						
		





















						
						


	
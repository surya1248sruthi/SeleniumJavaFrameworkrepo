package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	
	Logger log;
	
	@BeforeMethod
	public void openApplication() throws IOException {
	
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver=initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("Url"));
		log.debug("Navigated to application URL");
	}
	
	@Test(dataProvider = "getLoginData")
	public void login(String email,String password,String expected) throws IOException {
		
		LandingPage landing = new LandingPage(driver);
		landing.myAccount().click();
		log.debug("Clicked on My Account DropDown");
		landing.loginOption().click();
		log.debug("Clicked on login option");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddress().sendKeys(email);
		log.debug("Entered Email address");
		loginPage.password().sendKeys(password);
		log.debug("Entered Password");
		loginPage.loginButton().click();
		log.debug("Clicked on Login Button");
		
		MyAccountPage map= new MyAccountPage(driver);
		String actual=null;
		try
		{
		if(map.editAccountInfo().isDisplayed()) {
			log.debug("Login is Successfull");
			actual="Successful";
		}
       }catch(Exception e)
		 {
    	   log.debug("Login Failed");
			actual="Failure";
		}
		
		Assert.assertEquals(actual, expected);
	
		log.info("Login test got passed");
		
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
		log.debug("Browser got closed");
	}
	
	
	  @DataProvider
	  public Object[][] getLoginData() throws Exception {
			
			  Object[][] data =
			  {{"phaniatw2@gmail.com","atw@123","Successful"},
					  {"sample@test","sample", "Failure"}};
			  
			  return data;
			  
				 }
		  
	  
	  }
	 
	

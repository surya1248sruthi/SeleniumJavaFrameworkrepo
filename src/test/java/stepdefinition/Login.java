package stepdefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import resources.Base;

public class Login extends Base {
	WebDriver driver;
	LoginPage loginPage;
	 @Given("^open any browser$")
	    public void open_any_browser() throws IOException{
		 driver=initializeDriver();
	    }

	 @And("^Navigate to loginpage$")
	    public void navigate_to_loginpage() {
		 driver.get(prop.getProperty("Url"));
		 LandingPage landing = new LandingPage(driver);
			landing.myAccount().click();
			landing.loginOption().click();
	    }
	    @When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" in to the fileds$")
	    public void user_enters_username_as_something_and_password_as_something_in_to_the_fileds(String email, String password) {
	    	loginPage = new LoginPage(driver);
			loginPage.emailAddress().sendKeys(email);
			loginPage.password().sendKeys(password);
	    }
	    @And("^User clicks on login button$")
	    public void user_clicks_on_login_button() {
	    	loginPage.loginButton().click();
	    }
	    @Then("^verify user is able to login successfully$")
	    public void verify_user_is_able_to_login_successfully() {
	    	MyAccountPage map= new MyAccountPage(driver);
	    	Assert.assertTrue(map.editAccountInfo().isDisplayed());
	    }
	    
	    @After
	    public void tearDown() {
	    	driver.close();
	    }

	   

	  

}

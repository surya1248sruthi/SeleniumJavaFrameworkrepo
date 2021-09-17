package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTest extends Base{
	public WebDriver driver;
	@Test
	public void twoTest() throws IOException, InterruptedException {
		
		System.out.println("Two Test");
		driver=initializeDriver();
		driver.get("https://selenium.dev/");
		Thread.sleep(2000);
		Assert.assertTrue(false);
		driver.close();
		
	}

}

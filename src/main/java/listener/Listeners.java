package listener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class Listeners extends Base implements ITestListener {
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> TestThread= new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		 extentTest= extentReport.createTest(result.getName()+" execution started");
		 TestThread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		TestThread.get().log(Status.PASS, result.getName()+" got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		String testMethodName = result.getName();
		TestThread.get().fail(result.getThrowable());
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String screenshotPath=takeScreenshot(testMethodName,driver);
			TestThread.get().addScreenCaptureFromPath(screenshotPath, testMethodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
	

}

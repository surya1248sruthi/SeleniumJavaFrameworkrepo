package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {
		String reportPath=System.getProperty("user.dir")+"\\reports\\extentreport.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("FrameWork Report Details");
		reporter.config().setDocumentTitle("FrameWork results");
		
		extentReport= new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("operating system", "Windows10");
		extentReport.setSystemInfo("Tested By", "Phani");
		
		return extentReport;
	}

}

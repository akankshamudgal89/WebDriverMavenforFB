package FbReporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReporterClass {
	public Properties prop;
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testCaseName, testDescription, category, authors;
	public SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss.SSS");
	
	public ExtentReports startReport(String testName) {
		Calendar cal = Calendar.getInstance();
		Date currentTimeStamp = cal.getTime();

		String timeInFormat = formater.format(currentTimeStamp);
		String reportLocation = System.getProperty("user.dir") + "/reportingOutput/TestRun_" + timeInFormat + ".html";
		extent = new ExtentReports(reportLocation, false);
		return extent;
	}

	public void report(String Status, String expected, String actual, Boolean flagTakeSnap) {
        if (flagTakeSnap && !Status.equalsIgnoreCase("INFO")) {
            String screen = getScreenShot("");
            actual = actual + test.addScreenCapture(screen);
        }
//        test= new ExtentTest("validation", "validation2");//added to initialise test 

        if (Status.equalsIgnoreCase("Pass")) {
            test.log(LogStatus.PASS, expected, actual);
        }
        else if (Status.equalsIgnoreCase("Fail")) {
            test.log(LogStatus.FAIL, expected, actual);
        }
        else if (Status.equalsIgnoreCase("Warning")) {
            test.log(LogStatus.WARNING, expected, actual);
        }
        else {
            test.log(LogStatus.INFO, expected, actual);
        }
    }

	public ExtentTest startTestCase() {
		test = extent.startTest(testCaseName, testDescription);
		test.assignAuthor(authors);
		test.assignCategory(category);
		return test;
	}

	public void endTestcase() {
		extent.endTest(test);
	}

	public void endReport() {
		extent.flush();
	}	
	

	public String getScreenShot(String imageName) {
		String snapShot = null;
		try {
			if (imageName.equals("")) {
				imageName = "Screenshot";
			}
			Calendar cal = Calendar.getInstance();
			Date currentTimeStamp = cal.getTime();
			String timeInFormat = formater.format(currentTimeStamp);
			File src = null;
			try {
				src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String screenShotLocation = System.getProperty("user.dir") + "/ScreenPrints/";
			File des = new File(screenShotLocation + imageName + "_" + timeInFormat + ".png");
			
			FileUtils.copyFile(src, des);
			snapShot = des.toString();
		} catch (Exception e) {
			System.out.println("The ScreenShot could not be taken\n" + e);
			e.printStackTrace();
		}
		return snapShot;
	}
	
}

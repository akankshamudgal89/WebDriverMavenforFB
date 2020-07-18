package FBBaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import FBDataProvider.ExcelReaderCLass;
import FbReporting.ReporterClass;

public class BbaseClassFb extends ReporterClass {
	public static Properties config = new Properties();
	public static WebDriver wait;
	public static WebDriver ReadExcel;
	public static FileInputStream fis;

	@BeforeSuite
	public void beforeSuite() {
		startReport("Login to facebook");
	}
	
	public void setUp() {
		try {
			if (driver == null) {
				fis = new FileInputStream(
						"C:\\Users\\akanksha.mudgal\\eclipse-workspace\\POMforFB\\src\\main\\java\\FBConfig\\Config.Properties");
				config.load(fis);

				if (config.getProperty("Browser").contains("Chrome")) {
					System.setProperty("webdriver.chrome.driver",
							"C:\\Users\\akanksha.mudgal\\eclipse-workspace\\POMforFB\\Executables\\chromedriver.exe");

					driver = new ChromeDriver();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					driver.get(config.getProperty("URL"));
					report("pass", "Browser should be opened with specified URL", "Browser is opened successfully",
							true);
				}
			}
		} catch (Exception e) {
			report("fail", "Browser should be opened with specified URL", "Browser is not opened successfully" + e,
					true);
			e.printStackTrace();
		}
	}
	public static boolean IsEelementPresent(String xpath) {
		try {
		driver.findElement(By.xpath(xpath));
		return true;
		}
		catch(Throwable t) {
		return false;	
		}
	}
//	@DataProvider(name = "getData")
//	public static Object[][] getDataExcel() throws IOException {
//		return ExcelReaderCLass.ReadExcelData("./data/AppleProduct.xlsx", "appleRecords");
//	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		endReport();
	}

}

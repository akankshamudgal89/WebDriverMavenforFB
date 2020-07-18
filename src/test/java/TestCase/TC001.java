package TestCase;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import FBBaseClass.BbaseClassFb;
import FBPages.HomePage;
import FBPages.LoginPage;
import FbReporting.ReporterClass;

public class TC001 extends BbaseClassFb {

	HomePage homePage;
	LoginPage loginPage;

	@BeforeTest
	public void startTestFB() {
		testCaseName = "Verify Facebook Login";
		testDescription = "To verify whether the facebook page is opened";
		authors = "Akansha";
		category = "Functional";

		startTestCase();
		setUp();
	}

	@Test
	public void validatelogin() {
		

		loginPage = new LoginPage();
		homePage = loginPage.enterCredentials("8076016429", "@jaingolu1234");
		if(
		BbaseClassFb.IsEelementPresent("//*[@id=\"mount_0_0\"]/div/div/div[1]/div[2]/div[4]/div[1]/div[1]/span/div/div[1]")) {
			System.out.println("Entered home page");
		}
		
	}
	

	@AfterTest
	public void endTestFB() {
		endTestcase();
	}

}

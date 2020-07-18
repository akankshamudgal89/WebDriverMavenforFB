package FBPages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import FBBaseClass.BbaseClassFb;
import FbReporting.ReporterClass;

public class LoginPage extends ReporterClass {
//Driver Declaration

	// Page Constructor
	public LoginPage() {
		PageFactory.initElements(driver, this);

	}

	// Page Objects
	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement userid;
	@FindBy(xpath = "//*[@id=\"pass\"]")
	WebElement password;
	@FindBy(id = "loginbutton")
	WebElement loginbutton;

//Methods
	public HomePage enterCredentials(String myusername, String mypassword) {
		try {
			userid.sendKeys(myusername);
			password.sendKeys(mypassword);
			loginbutton.click();
			report("Fail", "all details are entered and clicked successfully to be on homepage", "not able to proceed",
					true);
		} catch (Exception n) {
			report("pass", "not able to login, please check the exception", "able to proceed", false);
			n.printStackTrace();
		}

		return new HomePage();

	}

}

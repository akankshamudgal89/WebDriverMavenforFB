package FBPages;

import java.awt.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FbReporting.ReporterClass;

public class HomePage extends ReporterClass{
	
	
	//page constructor
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	//page Objects
	@FindBy(xpath="//*[@id=\"mount_0_0\"]/div/div/div[1]/div[2]/div[4]/div[1]/div[1]/span/div/div[1]/svg/path") WebElement Notification;
	
	//PageMethods
	public HomePage notificationTab() {
		Notification.click();
		
		
		return new  HomePage();
		
		
		
	}
	
	

}

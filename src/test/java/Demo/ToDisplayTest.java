package Demo;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


import BasicPackage.BaseMain;
import PkgForObject.DisplayPage;
import PkgForObject.LoginPage;

public class ToDisplayTest extends BaseMain {

	public static Logger log = LogManager.getLogger(ToDisplayTest.class.getName());

	/*
	 * Testcase to check if username and profile image is displayed
	 * 
	 */
	@Test
	public void testTwo() throws IOException {
		
		driver = initializingDRiver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		LoginPage lp = new LoginPage(driver);
		lp.getusername().sendKeys("harshini.iyli@qualitestgroup.com");
		lp.getpassword().sendKeys("P@ssw0rd");
		lp.submit().click();
		
		//LoginPage lp = new LoginPage(driver);
		DisplayPage dp = new DisplayPage(driver);
		System.out.println(lp.title());
		lp.title().equals("QTRecognition");
		System.out.println(dp.showName().getText());
		System.out.println(dp.showImg().isDisplayed());

		Assert.assertTrue(dp.showName().isDisplayed());
		Assert.assertTrue(dp.showImg().isDisplayed());
		log.info("Username and profile :Displayed ");
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();	
	}
}

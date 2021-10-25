package Demo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import PkgForObject.LoginPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BasicPackage.BaseMain;

public class ToTestLoginPage extends BaseMain {

	public static Logger log = LogManager.getLogger(ToTestLoginPage.class.getName());

	/*
	 * To initialize the browser driver
	 * 
	 */
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializingDRiver();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

	}

	/*
	 * Test case to login with wrong username or password
	 * Test case to check the colors of the page
	 */
	@Test
	public void testInvalid() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		//Check for gold color
		String colourGold = driver.findElement(By.xpath("//div[@class='myHeading']")).getCssValue("background-color");
		String c = Color.fromString(colourGold).asHex();
		String gold="#fdcc16"; 
		Assert.assertTrue(c.equalsIgnoreCase(gold));
		System.out.println(c +" Gold color displayed");
		// Checking Navy Blue color
		String colourNavyBlue = driver.findElement(By.xpath("//button[@type='submit']")).getCssValue("background-color");
		String c1 = Color.fromString(colourNavyBlue).asHex();
		String blue="#2a2559"; 
		Assert.assertTrue(c1.equalsIgnoreCase(blue));
		System.out.println(c1 + " Blue color displayed");

		
		String username = "harshini.iyli@qualitestgroup.com";
		String pwd = "P@ssw";
		LoginPage lp = new LoginPage(driver);
		lp.getusername().sendKeys(username);
		lp.getpassword().sendKeys(pwd);
		lp.submit().click();
		Thread.sleep(2000);
		// verify if the alert text is displayed
		
		System.out.println(driver.switchTo().alert().getText());
		log.info("Alert displayed for wrong entry of username or password : working");
		// accept the alert
		driver.switchTo().alert().accept();

	}

	/*
	 * Testcase to login to the website
	 * 
	 */
	@Test
	public void testOne() throws IOException {

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		String username = "harshini.iyli@qualitestgroup.com";
		String pwd = "P@ssw0rd";
		LoginPage lp = new LoginPage(driver);
		System.out.println(lp.title());
		lp.title().equals("QTRecognition");
		lp.getusername().sendKeys(username);
		lp.getpassword().sendKeys(pwd);
		log.info("Login successful");
		lp.submit().click();
	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}

package Demo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import BasicPackage.BaseMain;
import PkgForObject.DisplayPage;
import PkgForObject.KudosSearchPage;
import PkgForObject.LoginPage;
import PkgForObject.OthersPage;
import PkgForObject.SendKudosPage;

public class OtherActivity extends BaseMain {

	public static Logger log = LogManager.getLogger(OtherActivity.class.getName());
	/*To initialize the browser and login
	 * 
	 * 
	 */
	@BeforeTest
	public void initialize() throws IOException
	{
		driver = initializingDRiver();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		LoginPage lp = new LoginPage(driver);
		lp.getusername().sendKeys("harshini.iyli@qualitestgroup.com");
		lp.getpassword().sendKeys("P@ssw0rd");
		lp.submit().click();

	}
	
	/*
	 * Testcase to check kudos from me
	 * 
	 */
	@Test
	public void TSix() throws IOException, InterruptedException {
		OthersPage op = new OthersPage(driver);
		DisplayPage dp = new DisplayPage(driver);
		KudosSearchPage ksp = new KudosSearchPage(driver);

	
	op.clickfromctn().click();
	
	
	System.out.println(ksp.getmediaBox().isDisplayed());
	String str = ksp.getFname().getText();
	String str1 =ksp.getSmallText().getText();
	String check=dp.showName().getText();
	System.out.println(str);
	System.out.println(str1);
	
	String actual= check.split(" ")[0];
	if(str.contains(actual) ) {
		
		if(str1.equalsIgnoreCase("has sent an appreciation to")) {
			System.out.println("has sent - true");
			log.info(actual+" has sent an appreciation : Kudos from me");
		}
	}
	else {
		System.out.println("do not print");
		log.error("Kudos from me : failure");
	}
	}
	
	/*
	 * Test case to check kudos to me
	 * 
	 */
	@Test
	public void tSixofTwo() throws InterruptedException {
		OthersPage op = new OthersPage(driver);
		DisplayPage dp = new DisplayPage(driver);
		
		op.clicktobtn().click();
	
		String n1=op.getname1().getText();
		String n2 = op.getstext1().getText();
		String check=dp.showName().getText();
		
		System.out.println(n1);
		System.out.println(n2);
		
		String actual= check.split(" ")[0];
		if(n1.contains(actual)) {
			if(n2.equalsIgnoreCase("received an appreciation from")) {
				System.out.println("true-  has received");
				log.info(actual+" has received an appreciation : Kudos to me ");
			}
		}
		else {
			System.out.println("false");
			log.error("Kudos to me : failure");
		}

	}
	
	/*
	 * Testcase to check activity page
	 * 
	 */
	@Test
	public void tSixofThree() throws InterruptedException {
		OthersPage op = new OthersPage(driver);
		KudosSearchPage ksp = new KudosSearchPage(driver);
		
		op.clickActivity().click();
	
		System.out.println("activity clicked");
	
		System.out.println(op.aCheckdisplaymedia().getText());
		System.out.println(ksp.getmediaBox().isDisplayed());
		log.info("Activity page working properly : Kudos displayed");
	}
	
	/*
	 * testcase to check kudos count update
	 * 
	 */
	@Test
	public void tSixofFour() {
		OthersPage op = new OthersPage(driver);
		System.out.println(op.countKudos());
		
	}
	
	/*
	 * Test case to send kudos to self
	 * 
	 */
	@Test
	public void testCSelfkudos() throws InterruptedException {
		OthersPage op = new OthersPage(driver);

		
		SendKudosPage sendkudos = new SendKudosPage(driver);
		System.out.println(op.countKudos());
		
		sendkudos.getKudosSend().click();
		
		String Ename= "Harshini M Iyli��(harshini.iyli@qualitestgroup.com)";
		sendkudos.enterEmail().sendKeys(Ename);
		
		WebElement down=sendkudos.enterfullemail();
		
		Actions a=new Actions(driver);
		
		a.moveToElement(down).sendKeys(Keys.ARROW_DOWN,Keys.RETURN).build().perform();
		
		
		List<WebElement> li2 =sendkudos.selecttrophy();
		for(WebElement list2: li2) {
			System.out.println(list2.getText());
			
			if(list2.getText().contains("Exceptional Work") ) {
				list2.click();
				System.out.println("exceptional work selected");
				//Thread.sleep(1000);

				break;
				}
		}
	
		sendkudos.sendcomment().sendKeys("Keep up the good work");
		Thread.sleep(1000);
		System.out.println("kudos sent");
		sendkudos.clicksend().click();
		System.out.println(driver.findElement(By.xpath("//*[@id='display_message']/span/center")).getText());
		Thread.sleep(10000);
		String mailerror= driver.findElement(By.xpath("//*[@id='display_message']/span/center")).getText();
	
		System.out.println(mailerror);
		
		Assert.assertTrue(mailerror.equalsIgnoreCase("Mailer Error: SMTP connect() failed."));
		log.error("Kudos sent to oneself : error");
		sendkudos.clickClose().click();
		//System.out.println("This test is wrong send kudos to oneself");
		driver.navigate().refresh();
		op.clicktobtn().click();
		op.clickActivity().click();
		Thread.sleep(2000);
		String nameTocheck = driver.findElement(By.xpath("//*[@id=\"0\"]/div/h5/b[1]")).getText();
		String nameToself = driver.findElement(By.xpath("//*[@id='0']/div/h5/b[2]")).getText();
		System.out.println(nameToself+ " is for cheking name");
		System.out.println(nameTocheck);
		Assert.assertTrue(nameToself.equals(nameTocheck));
		System.out.println("This test is wrong send kudos to oneself");
		Thread.sleep(4000);
		System.out.println(op.countKudos() + " updated count");
		log.info("Kudos count updated :"+ op.countKudos());

	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();	
	}
}

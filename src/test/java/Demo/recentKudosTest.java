package Demo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BasicPackage.BaseMain;
import PkgForObject.LoginPage;
import PkgForObject.RecentKudosPage;
import PkgForObject.SendKudosPage;

public class recentKudosTest extends BaseMain{

	
	@BeforeTest
	public void initialize() throws IOException
	{
		driver = initializingDRiver();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
		LoginPage lp = new LoginPage(driver);
		lp.getusername().sendKeys("harshini.iyli@qualitestgroup.com");
		lp.getpassword().sendKeys("P@ssw0rd");
		lp.submit().click();

	}
	
	@Test
	public void tFive() {

		
		RecentKudosPage rp = new RecentKudosPage(driver);
		SendKudosPage sendkudos = new SendKudosPage(driver);
//		List<WebElement> li = rp.sendContacts();
//
//		for(WebElement opt: li) {
//			
//			String recent=opt.getText();
			//System.out.println(recent);
			rp.clickKudos().click();
			System.out.println("clicked");
			
			
			List<WebElement> li2 =sendkudos.selecttrophy();
			for(WebElement list: li2) {
				System.out.println(list.getText());
				
				if(list.getText().contains("Quick Learner")) {
					list.click();
					System.out.println("Quick Learner");
					break;
				}
			}
			
			//}
		sendkudos.sendcomment().sendKeys("Appreciate your work");
		sendkudos.clicksend();
		System.out.println("kudos sent");
		sendkudos.clickClose().click();
	}
	
	
	
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();	
	}
}
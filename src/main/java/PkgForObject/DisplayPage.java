package PkgForObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class DisplayPage {

	public WebDriver driver;
	
	//By name = By.xpath("//div[@class='container']/div[1]/div[1]/div/div[1]/div/h5");
	By name = By.xpath("(//h5[@class='header-font-size'])[1]");
	By image =By.xpath("//div[@class='container']/div[1]/div[1]/div/div[1]/img");
	
	
	public DisplayPage(WebDriver driver) {

		
		this.driver=driver;
		
	}
		
	public WebElement showName() {
		return driver.findElement(name);
	}
	
	public WebElement showImg() {
		return driver.findElement(image);
	}
}

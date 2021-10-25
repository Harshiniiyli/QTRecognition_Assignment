package BasicPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;

public class BaseMain {
	
	public static  WebDriver driver;
	public Properties prop;

	public WebDriver initializingDRiver() throws IOException {
		
		 prop= new Properties();
		 //System.getProperty("user.dir") will giver path till current project
		 FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\BasicPackage\\data.properties");

		prop.load(fis);

		//String browserName=System.getProperty("browser"); 
		String browserName=prop.getProperty("browser");
		System.out.println(browserName);
		if(browserName.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",
					"F:\\QUALITEST TRAINING COURSE 1\\selenium course\\chromedriver_win32\\chromedriver.exe");
			
			 driver= new ChromeDriver();
				//execute in chrome driver
			
		}
		//driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		
		return driver;
	}
	
}

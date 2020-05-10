package EbayAssignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import ebayPageObjects.EbayMainPage;


public class VerifyEbayLogo {

	private String searchPage ;
	public Properties prop;
	public static Logger log=LogManager.getLogger(VerifyEbayLogo.class.getName());
	
	@Test
	public void verifyLogo() throws IOException {

		// Creating object prop for Properties class
		prop = new Properties();

		// Creating object fis for FileInputStream class.User.dir will get the current directory of the user
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\datadriven.properties");
		prop.load(fis);
		searchPage = prop.getProperty("url");

		// Specifying the path of the chromedriver.exe
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// Passing the webUrl
		driver.get(searchPage);

		// Maximizing the browser that is launched
		driver.manage().window().maximize();

		/* Creating object for EbayMainPage & passing driver which points to
		 * EbayMainpage which will be assigned to local driver
		 */

		EbayMainPage ebayMainPage = new EbayMainPage(driver);

		// Getting the currentUrl
		String Url = driver.getCurrentUrl();

		// verifying the value whether it is true or false and printing the output
		Assert.assertEquals(ebayMainPage.logo().isDisplayed(), true);
		System.out.println("Landed on the first page : " + Url);
		System.out.println("The ebay logo is found on the web page.");

		driver.close();
	}
}

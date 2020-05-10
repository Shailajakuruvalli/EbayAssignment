package ebayPageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EbayMainPage {

	public WebDriver driver;

	// Identifying an object for the logo on the landing page
	private By logo = By.id("gh-la");

	// Creating a constructor to pass the driver object
	public EbayMainPage(WebDriver driver) {
		this.driver = driver;
	}

	// Method to get logo WebElement
	public WebElement logo() {
		return driver.findElement(logo);
	}
}

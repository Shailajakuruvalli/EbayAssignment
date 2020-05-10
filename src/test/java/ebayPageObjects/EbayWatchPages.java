package ebayPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EbayWatchPages {
	public WebDriver driver;
	
	// Identifying the watches listed on the page
	private By getAllWatchItems = By.cssSelector("div[class='s-item__wrapper clearfix'] div[class='s-item__info clearfix'] a[class='s-item__link']");

	// Identifying the no. of pages in the footer
	private By getNoofPages = By.cssSelector("ol[class='ebayui-pagination__ol'] li[class*='ebayui-pagination__li ']");

	// Identifying the object to get no. of views for each item
	private By viewsCount = By.xpath("//*[@id='vi_notification_new']/span");

	// Creating a constructor to pass the driver object
	public EbayWatchPages(WebDriver driver) {
		this.driver = driver;
	}

	// Method to return the all watches on the page
	public List<WebElement> getAllItems() {

		return driver.findElements(getAllWatchItems);
	}

	// Method to return no. of pages in the footer
	public List<WebElement> getNoofPages() {

		return driver.findElements(getNoofPages);
	}

	// Method to return the no. of views for each item

	public WebElement getViewedItems() {

		return driver.findElement(viewsCount);
	}

	// Method to check whether the items is visible
	public List<WebElement> getViewsCount() {

		return driver.findElements(viewsCount);
	}

}

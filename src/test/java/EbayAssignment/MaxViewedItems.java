package EbayAssignment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ebayPageObjects.EbayWatchPages;

public class MaxViewedItems {

	private static final int MAX_ITEMS = 250;

	public Map<String, Integer> getMaxViewedItems(final String searchPage) {
		// Specifying the path of chromedriver.exe
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get(searchPage);
		driver.manage().window().maximize();
		HashMap<String, Integer> linksandviews = new HashMap<String, Integer>();
		EbayWatchPages ebayWatchPage = new EbayWatchPages(driver);

		// no.ofpages in the search page
		int noOfPages = ebayWatchPage.getNoofPages().size();

		// no. of links in the page
		int mapSize = 0;
		// Iterating through the pages to click on 250 links
		for (int i = 0; i < noOfPages; i++) {
			// Getting the no. of links
			int noOfItems = ebayWatchPage.getAllItems().size();
			// Iterating to click on the no. of links in each page
			for (int j = 0; j < noOfItems; j++) {
				// ClickOnLinks is for passing keyboard control to click on links
				String clickOnLinks = Keys.chord(Keys.CONTROL, Keys.ENTER);
				// Click on each link
				ebayWatchPage.getAllItems().get(j).sendKeys(clickOnLinks);
				// Waiting for the page to load
				driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				// Trying to get window handles have to switch to child window to get the views
				// on each item
				Set<String> goToNewWindow = driver.getWindowHandles();
				Iterator<String> iterateOverPages = goToNewWindow.iterator();
				String parentid = iterateOverPages.next();
				String childid = iterateOverPages.next();
				// using to switch the context from parent to child window
				while (iterateOverPages.hasNext()) {
					driver.switchTo().window(childid);
					driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
					String currenturl = driver.getCurrentUrl();
					int totalViews = 0;
					
					// Get size of the no. of views per hour as it is visible when it has views
					int viewsSearch = ebayWatchPage.getViewsCount().size();
					if (viewsSearch == 1) {
						WebDriverWait myWait = new WebDriverWait(driver, 8);
						// Waiting for the views to be visible
						myWait.until(ExpectedConditions.visibilityOf(ebayWatchPage.getViewedItems()));
						// Get the no. of views
						String noOfViews = ebayWatchPage.getViewedItems().getText();
						// Splitting the string to get the count of views
						String[] views = noOfViews.split(" ");
						totalViews = Integer.valueOf(views[0]);
					}

					// Putting links and no. of views into HashMap
					linksandviews.put(currenturl, totalViews);
					// Will display all the links clicked and no. of views
					// System.out.println(currenturl + ":" + totalViews);

					// Switching to the child window to close
					driver.switchTo().window(childid).close();
					break;
				}
				// Getting the HashMap size
				mapSize = linksandviews.size();
				// If HashMap size has reached max items  exiting
				if (hasMaxReached(mapSize))
					break;
				// Switching to parent id to click on the next link
				driver.switchTo().window(parentid);
			}
			// If HashMap size has reached max items  exiting
			if (hasMaxReached(mapSize))
				break;
		}
		//Quitting the driver
		driver.quit();
		return linksandviews;
	}

	private boolean hasMaxReached(int mapsize) {
		if (mapsize >= MAX_ITEMS) {
			return true;
		}
		return false;
	}
}

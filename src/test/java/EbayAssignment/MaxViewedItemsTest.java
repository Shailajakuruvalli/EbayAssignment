package EbayAssignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.testng.annotations.Test;

public class MaxViewedItemsTest {
	private String searchPage ;
	private Properties  prop;
	
	@Test
	public void testMaxViewedItems() throws IOException  {
		MaxViewedItems maxViewedItems = new MaxViewedItems();
		prop = new Properties();

		// Creating object fis for FileInputStream class.User.dir will get the current directory of the user
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Resources\\datadriven.properties");
		prop.load(fis);
		searchPage = prop.getProperty("url");
		
		//getting all the map values 
		Map<String, Integer> pageAndViewCountMap = maxViewedItems.getMaxViewedItems(searchPage);

		// convert HashMap into List
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(pageAndViewCountMap.entrySet());
		
		// sorting the list elements
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
        //after sorting storing the elements
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		//displaying top 5 values
		System.out.println("Top five items viewed most:");
		int counter = 1;
		for (String link : sortedMap.keySet()) {
			System.out.println(link + "::" + sortedMap.get(link));
			if (counter == 5)
				break;
			counter++;
		}
	}
}

package StepDefinition;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
  
public class First {  
  
    public static void main(String[] args) throws
    MalformedURLException, IOException {  
        
    // get response from page  
    System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");  
    WebDriver driver=new ChromeDriver();  
     
    
    HttpURLConnection c=(HttpURLConnection)new URL("https://www.w3.org/standards/webdesign/htmlcss").openConnection();
    		c.setRequestMethod("HEAD");
    		c.connect();
    		System.out.println(c.getResponseCode());
    
    
    		
    		
    		
    		
    // get console errors
    //----------------------------------------------------------------
    DesiredCapabilities caps = DesiredCapabilities.chrome();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.BROWSER, Level.ALL);
    caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    driver.get("https://www.w3.org/standards/webdesign/htmlcss");
    LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
    for (LogEntry entry : logs) {
     System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
     
    	
    		
    		
    		
    // Verify all links in 	page navigating to other page	
    //--------------------------------------------------------------------
    
    		
    String homePage = "https://www.w3.org/standards/badpage";
    String url = "";
    HttpURLConnection huc = null;
    int respCode = 200;
    
    driver = new ChromeDriver();
    
    driver.manage().window().maximize();
    
    driver.get(homePage);
    
    List<WebElement> links = driver.findElements(By.tagName("a"));
    
    Iterator<WebElement> it = links.iterator();
    
    while(it.hasNext()){
        
        url = it.next().getAttribute("href");
        
        System.out.println(url);
    
        if(url == null || url.isEmpty()){
System.out.println("URL is either not configured for anchor tag or it is empty");
            continue;
        }
        
        //if(!url.startsWith(homePage)){
          //  System.out.println("URL belongs to another domain, skipping it.");
            //continue;
        //}
        
        try {
            huc = (HttpURLConnection)(new URL(url).openConnection());
            
            huc.setRequestMethod("HEAD");
            
            huc.connect();
            
            respCode = huc.getResponseCode();
            
            if(respCode >= 400){
                System.out.println(url+" is a broken link");
            }
            else{
                System.out.println(url+" is a valid link");
            }
                
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    driver.quit();

    }  
  
}
}

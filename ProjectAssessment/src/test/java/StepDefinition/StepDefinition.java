package StepDefinition;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {

	public WebDriver driver;

	@When("^user launch the \"([^\"]*)\" browser$")
	public void user_launch_the_browser(String browser) throws Exception {
		if (browser.toLowerCase().contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Driver/chromedriver");
			driver = new ChromeDriver(); 
		}
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
	}

	@Then("^I verify console errors in \"([^\"]*)\" page$")
	public void i_verify_console_errors_in_page(String url) throws Exception {
		driver.get(url);
		LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logs) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
		}
		driver.quit();
	}

	@Then("^I verify page response from \"([^\"]*)\" page$")
	public void verify_response_page(String url) throws Exception {
		HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
		c.setRequestMethod("HEAD");
		c.connect();
		System.out.println(c.getResponseCode());
	}

}

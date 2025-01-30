package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public static Properties p;
	public static void setup(String os, String br) throws InterruptedException, IOException, URISyntaxException {
		
		FileReader fis = new FileReader(System.getProperty("user.dir") + ".//src/test/resources//config.properties");
		Properties p = new Properties();
		p.load(fis);
		
			switch (br.toLowerCase()) {
			case "chrome":
				ChromeOptions chOptions = new ChromeOptions();
				driver = new ChromeDriver(chOptions);
				chOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				break;
			case "firefox":
				FirefoxOptions ffOption = new FirefoxOptions();
				driver = new FirefoxDriver(ffOption);
				ffOption.addPreference("excludeSwitches", new String[] { "enable-automation" });
				break;
			case "edge":
				EdgeOptions edOption = new EdgeOptions();
				driver = new EdgeDriver(edOption);
				edOption.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				break;
			default:
				System.out.println("No browser found");
				return;
			}

//		logger = LogManager.getLogger(this.getClass());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p.getProperty("amazon_url_txt"));
		
	}

	public static WebDriver getDriver() throws InterruptedException, IOException, URISyntaxException {
		if (driver == null) {
			BaseClass.setup("windows","chrome");
		}
		return driver;
	}
	
	public static void tearDown() {
		driver.quit();
	}

}

package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass2 {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "regression", "sanity","master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws InterruptedException, IOException, URISyntaxException {

		FileReader fis = new FileReader(System.getProperty("user.dir") + ".//src/test/resources//config.properties");
		p = new Properties();
		p.load(fis);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
				
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			String hubUrl = p.getProperty("hub_url");
			URI uri = new URI(hubUrl);
			driver = new RemoteWebDriver(uri.toURL(), capabilities);
		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
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

		}
		logger = LogManager.getLogger(this.getClass());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p.getProperty("amazon_url_txt"));
	}

	@AfterClass(groups = { "regression", "sanity","master" })
	public void tearDown() {
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}

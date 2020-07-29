package zaleniumDocker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestOnZalenium {
	RemoteWebDriver driver;
	DesiredCapabilities dc;

	@BeforeTest
	@Parameters("browser")
	void setUp(String br) throws MalformedURLException, InterruptedException {
		dc = new DesiredCapabilities();

		if (br.equals("chrome")) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		} else if (br.equals("firefox")) {
			dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
			dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
		}

		URL url = new URL("http://localhost:4444/wd/hub");

		driver = new RemoteWebDriver(url, dc);
		driver.get("https://test-st-wl-envoy.aw.atos.net/WorldlineSTConsole/");
		Thread.sleep(2000);

	}

	@Test
	void test() throws InterruptedException {

		driver.findElement(By.id("LogonId")).sendKeys("Asnow");
		driver.findElement(By.id("LoginPasscode")).sendKeys("Cayman5!");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		System.out.println(driver.getTitle());

	}

	@AfterTest
	void terminate() {
		driver.quit();

	}

}

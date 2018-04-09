package genericlibrary;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class BrowserUtilities {

	static final String BROWSER = "browser";
	Wait<WebDriver> wait;

	private BrowserUtilities() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * @author Indrapal Singh, File Contains methods to launch any browser with
	 *         url as per constant properties file
	 */

	public static WebDriver driver;

	public static WebDriver getBrowser() throws IOException {

		if (ConfigProperties.getObject(BROWSER).equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.firefox.marionette", ConfigProperties.getObject("pathGeckoDriver"));
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(ConfigProperties.getObject("url"));

		}

		else if (ConfigProperties.getObject(BROWSER).equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", ConfigProperties.getObject("pathChromeDriver"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(ConfigProperties.getObject("url"));

		} else if (ConfigProperties.getObject(BROWSER).equalsIgnoreCase("ie")) {

			System.setProperty("webdriver.ie.driver", ConfigProperties.getObject("pathIEDriver"));
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(ConfigProperties.getObject("url"));
		}

		return driver;

	}

	public static void closeBrowser() {

		driver.close();

	}

	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;

		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public void clickWhenReady(By locator, int timeout) {
		wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void fluentWait(final By locator) {
		wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement element = (WebElement) locator;
		element.click();

	}

}

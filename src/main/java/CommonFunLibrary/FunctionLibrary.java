package CommonFunLibrary;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ProperticesFileUtil;

public class FunctionLibrary {
	static WebDriver driver;
	static Logger log = Logger.getLogger(FunctionLibrary.class);

	public static WebDriver startBrowser(WebDriver driver) {
		log.info("Setting Browser Config");
		System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}

	public static void openApplication(WebDriver driver) throws Throwable {
		log.info("opening website..");
		driver.navigate().to(ProperticesFileUtil.getProperty("url"));

	}

	public static void selectAction(WebDriver driver, String locator_type, String locator_value, String testData) {
		log.info("performing Selection Action.");
		String value = String.valueOf((int) Double.parseDouble(testData));
		Select dropDown = new Select(driver.findElement(By.xpath(locator_value)));
		dropDown.selectByValue(value);

	}

	public static void clickAction(WebDriver driver, String locator_type, String locator_value) throws Throwable {
		log.info("Clicking on " + locator_value);
		WebElement element = null;
		if (locator_type.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locator_value));
		}
		if (locator_type.equalsIgnoreCase("name")) {
			element = driver.findElement(By.name(locator_value));
		}
		try {
			if (locator_type.equalsIgnoreCase("xpath")) {
				element = driver.findElement(By.xpath(locator_value));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			element = driver.findElement(By.xpath("//*[contains(text(),'" + locator_value + ")')]"));
		}
		try {
			element.click();
		} catch (Exception e) {
			Thread.sleep(3000);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
			// element.sendKeys(Keys.ENTER);
		}

	}

	public static void filter(WebDriver driver, String locator_type, String locator_value, String TestData) {
		System.out.println("filtering");
	}

	public static void changeDriverFocus(WebDriver driver) throws InterruptedException {
		log.info("changing Driver Focus");
		Set<String> window_ids = driver.getWindowHandles();
		Object[] ids = window_ids.toArray();
		// String main_windows_ids = ids[0].toString();

		String child_windows_ids = ids[1].toString();

		Thread.sleep(10);

		driver.switchTo().window(child_windows_ids);

	}

	public static void typeAction(WebDriver driver, String locator_type, String locator_value, String TestData) {
		log.info("Typing  " + TestData + "  Into  " + locator_value);
		WebElement element2 = null;
		if (locator_type.equalsIgnoreCase("id")) {
			element2 = driver.findElement(By.id(locator_value));
		}
		if (locator_type.equalsIgnoreCase("name")) {
			element2 = driver.findElement(By.name(locator_value));
		}
		try {
			if (locator_type.equalsIgnoreCase("xpath")) {
				element2 = driver.findElement(By.xpath(locator_value));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			element2 = driver.findElement(By.xpath("//*[contains(text(),'" + locator_value + ")')]"));
		}
		element2.sendKeys(TestData);
	}

	public static void waitforelement(WebDriver driver, String locator_type, String locator_value, String TestData) {
		int timeToWait = (int) Double.parseDouble(TestData);
		System.out.println(timeToWait);
		System.out.println();
		WebDriverWait wait = new WebDriverWait(driver, timeToWait);
		System.out.println("locating Element");
		if (locator_type.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator_value)));
		}
		if (locator_type.equalsIgnoreCase("name")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator_value)));
		}
		try {
			if (locator_type.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator_value)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + locator_value + ")')]")));
		}
	}

}

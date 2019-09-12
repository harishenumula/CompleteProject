package Test_pack;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;
import javax.swing.JSeparator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.interactions.touch.Scroll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Test1 {
	static WebDriver driver;
	static String cata = "Data Transfer";
	static String selected_option = "4G";

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/s?k=mobile&ref=nb_sb_noss");

		List<WebElement> filter_type = driver.findElements(By.xpath("//*[@id='filters']/div"));
		for (int i = 0; i < filter_type.size(); i++) {
			System.out.println(i + "::" + filter_type.get(i).getText());
			if (filter_type.get(i).getText().equalsIgnoreCase(cata)) {

				List<WebElement> option = driver.findElement(By.xpath("//*[@id='filters']/ul[" + (i + 1) + "]"))
						.findElements(By.tagName("li"));
				System.out.println(option.size());
				for (int j = 0; j < option.size(); j++) {
					System.out.println("------------------");
					System.out.println(option.get(j).getText());

					Thread.sleep(10);
					if (option.get(j).getText().equalsIgnoreCase(selected_option)) {
						WebElement element = option.get(j);
						System.out.println(element.isDisplayed());
						WebDriverWait wait=new WebDriverWait(driver, 10);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filters']/ul[" + (i + 1) + "]/li["+j+"]")));
						
						try {
							System.out.println("trying --method 1 ");
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].click();", element);

							 option.get(j).click();
							System.out.println("Success --method 1 ");
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("Failed --method 1 ");
							System.out.println("trying --method 2 ");

							Actions action = new Actions(driver);
							action.moveToElement(option.get(j)).build().perform();
							action.contextClick(option.get(j)).build().perform();
							// TODO: handle exception
						}
					}
				}
				System.out.println("****************");
			}
		}
		/*
		 * List<WebElement> element = filter.findElements(By.tagName("li")); WebElement
		 * os=element.get(0);
		 * 
		 * System.out.println(os.getText()); Thread.sleep(10); try { os.click(); } catch
		 * (Exception e) { Thread.sleep(3000); Actions action = new Actions(driver);
		 * action.moveToElement(os).build().perform();
		 * action.click(os).build().perform(); // element.sendKeys(Keys.ENTER); }
		 */

	}

	public void m3() {
		List<WebElement> filter = driver.findElement(By.xpath("//*[@id='filters']/div")).findElements(By.tagName("ul"));
		for (WebElement cat : filter) {
			// System.out.println(cat.getText());
			System.out.println("****************");
			if (cat.getText().contains(cata)) {
				List<WebElement> options = cat.findElements(By.tagName("li"));
				for (WebElement opt : options) {
					System.out.println(opt.getText());

				}

			}

		}

	}

	public void method2() {
		WebElement os = null;
		try {
			System.out.println("1");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filters']/ul[4]/li[1]")));

			os = driver.findElement(By.xpath("//*[@id='filters']/ul[4]/li[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(os).contextClick(os).build().perform();
			action.click(os).build().perform();

			// os.click();
			System.out.println("2");
			/*
			 * System.out.println(os.isDisplayed()); System.out.println(os.isSelected());
			 * os.click();
			 * 
			 * System.out.println("2");
			 */
			// }
		} catch (Exception e) {
			System.out.println("Exception");

			e.printStackTrace();
		}
	}
}
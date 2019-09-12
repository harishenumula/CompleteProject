package Test_pack;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test4 {
	WebDriver driver;

	@Test
	public void test2() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/s?k=redmi+mobiles&rh=n%3A1389401031&ref=nb_sb_noss");
		String homewindow = driver.getWindowHandle();
		System.out.println(homewindow);
		driver.findElement(By.xpath("(//*[@class='a-size-medium a-color-base a-text-normal'])[2]")).click();
		Thread.sleep(3000);
		Set<String> window_ids = driver.getWindowHandles();
		Object[] ids = window_ids.toArray();
		String main_windows_ids = ids[0].toString();

		String child_windows_ids = ids[1].toString();

		System.out.println();
		driver.switchTo().window(child_windows_ids);
		Thread.sleep(5000);
		WebElement add_button = driver.findElement(By.xpath("//*[@id='add-to-cart-button']"));
		System.out.println(add_button.isDisplayed());
		if (add_button.isDisplayed()) {
			add_button.click();
		}else
		{
			Thread.sleep(5000);
			
			//add_button
			
			
		}
		
		// driver.findElement(by.)
	}
}

package Test_pack;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test3 {
	static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("file:///C:/Users/mounika/Desktop/test1.html");

		List<WebElement> boxs = driver.findElements(By.tagName("input"));
		Object[] box = boxs.toArray();
		for (int i = 1; i <= box.length; i++)
			if ((i % 3) == 0) {
				driver.findElement(By.xpath("//input[" + i + "]")).click();
				System.out.println(box[1].toString());
				// box[i].toString().
			}
	}

}

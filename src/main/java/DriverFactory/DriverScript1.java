package DriverFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript1 {
	WebDriver driver;
	String step_status;
	String testcase_status;
	ExtentReports extent;
	ExtentTest logger;

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "\\test-output\\ExtentReport.html", true);
		System.out.println("creating extent report at \n"+System.getProperty("user.dir") + "\\test-output\\ExtentReport.html);
		extent.addSystemInfo("username", "Harish");
		extent.addSystemInfo("OS", "Windows");
		extent.addSystemInfo("env", "testing");

	}

	@AfterTest
	public void endreport() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent.flush();
		extent.close();
		driver.close();

	}

	@Test
	public void start_Test() throws Throwable, Throwable {
		System.out.println("test");
		ExcelFileUtil xls = new ExcelFileUtil();
		int no_test_cases = xls.getrowCount("Dashboard");
		for (int i = 1; i <= no_test_cases; i++) {
			String execution_mode = xls.getData("Dashboard", i, 2);
			if (execution_mode.equalsIgnoreCase("y")) {

				String test_case_name = xls.getData("Dashboard", i, 1);
				logger = extent.startTest(test_case_name);

				int no_of_steps = xls.getrowCount(test_case_name);
				for (int j = 1; j <= no_of_steps; j++) {
					String description = xls.getData(test_case_name, j, 0);
					String oject_type = xls.getData(test_case_name, j, 1);
					String locator_type = xls.getData(test_case_name, j, 2);
					String locator_value = xls.getData(test_case_name, j, 3);
					String testdata = xls.getData(test_case_name, j, 4);
					try {
						if (oject_type.equalsIgnoreCase("startBrowser")) {
							driver = FunctionLibrary.startBrowser(driver);
						} else if (oject_type.equalsIgnoreCase("openApplication")) {
							FunctionLibrary.openApplication(driver);
						} else if (oject_type.equalsIgnoreCase("waitforelement")) {
							FunctionLibrary.waitforelement(driver, locator_type, locator_value, testdata);
						} else if (oject_type.equalsIgnoreCase("typeAction")) {
							FunctionLibrary.typeAction(driver, locator_type, locator_value, testdata);
						} else if (oject_type.equalsIgnoreCase("ClickAction")) {
							FunctionLibrary.clickAction(driver, locator_type, locator_value);
						} else if (oject_type.equalsIgnoreCase("filter")) {
							FunctionLibrary.filter(driver, locator_type, locator_value, testdata);
						} else if (oject_type.equalsIgnoreCase("changeDriverFocus")) {
							FunctionLibrary.changeDriverFocus(driver);
						} //
						else if (oject_type.equalsIgnoreCase("selectAction")) {
							FunctionLibrary.selectAction(driver, locator_type, locator_value, testdata);
						}
						step_status = "pass";
						testcase_status = "true";

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						step_status = "fail";
						testcase_status = "false";
					}
					xls.setData(test_case_name, j, 5, step_status);
					System.out.println(description + ":\t" + step_status);
					if (testcase_status.equalsIgnoreCase("true")) {
						xls.setData("Dashboard", i, 3, "pass");
						logger.log(LogStatus.PASS, description);
					} else {
						xls.setData("Dashboard", i, 3, "fail");
						logger.log(LogStatus.FAIL, description);
					}
				}
				extent.endTest(logger);
			} else {
				xls.setData("Dashboard", i, 3, "Not Executed");
			}
		}
	}

}

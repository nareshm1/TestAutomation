package com.zyto.test.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.zyto.test.pages.Signup_Options_page;

public class ZytoBaseTest {
	public WebDriver driver;

	public ZytoBase zytobase;
	public Signup_Options_page Signupoptionspage;
	public Properties prop;

	@BeforeMethod
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) {
		zytobase = new ZytoBase();
		prop = zytobase.init_prop();
		prop.setProperty("browser", browserName);
		driver = zytobase.init_driver(prop);
		//Signupoptionspage = new Signup_Options_page(driver);
	}

	@AfterMethod
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

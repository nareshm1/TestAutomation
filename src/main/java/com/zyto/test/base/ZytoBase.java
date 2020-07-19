package com.zyto.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.model.Test;
import com.zyto.test.util.java.Browser_Options;
import com.zyto.test.util.java.ElementLocatorUtils;
import com.zyto.test.util.java.Test_Utilities_File;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZytoBase {
	public static WebDriver driver;
	public Properties prop;
	public ElementLocatorUtils elementutil;
	public Browser_Options BrowserOptions;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to initialize the WebDriver on the basis of browser
	 * 
	 * @param browserName
	 * @return driver
	 */
	public WebDriver init_driver(Properties prop) {

		/// To get browser from system or config.prop file
		String browserName = null;
		if (System.getProperty("browser") == null) {
			browserName = prop.getProperty("browser");
		} else {
			browserName = System.getProperty("browser");
		}

		System.out.println("Running on --->" + browserName + " browser");

		BrowserOptions = new Browser_Options(prop);

		/// To Choose browser
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			} else {
				tlDriver.set(new ChromeDriver(BrowserOptions.getChromeOptions()));
			}

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver(browserName);
			} else {
				tlDriver.set(new FirefoxDriver(BrowserOptions.getFirefoxOptions()));

			}
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();

		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	private void init_remoteWebDriver(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, BrowserOptions.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("remoteurl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(ChromeOptions.CAPABILITY, BrowserOptions.getFirefoxOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("remoteurl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * this method is used to initialize the properties from config.proeprties
	 * 
	 * 
	 * 
	 * @return prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		// String path = null;

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "//src//main//java//com//zyto//test//config//Config.Properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * this method will take the screenshot
	 */
	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;

	}

	// Declaration of Implicit and Pageload timeouts

	public void pageTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(Test_Utilities_File.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
	}

	public static void implicitTimeOut() {
		driver.manage().timeouts().implicitlyWait(Test_Utilities_File.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
	}

	public void addTest(Test arg0) {
		// TODO Auto-generated method stub

	}

	public void start(Reporter arg0) {
		// TODO Auto-generated method stub

	}

	public void stop() {
		// TODO Auto-generated method stub

	}

	public void setTestRunnerLogs() {
		// TODO Auto-generated method stub

	}

	public void flush() {
		// TODO Auto-generated method stub

	}

}

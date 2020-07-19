package com.zyto.test.ReportListeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.zyto.test.ConsoleColors.Console_Colors;
import com.zyto.test.base.ZytoBase;

import io.qameta.allure.Attachment;

public class AllureReports extends ZytoBase implements ITestListener {
	
	public static Console_Colors concol;

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println(concol.PURPLE_BOLD_BRIGHT+"I am in onStart method " + iTestContext.getName()+concol.RESET);
		//iTestContext.setAttribute("WebDriver", BasePage.getDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println(concol.PURPLE_BOLD_BRIGHT+"I am in onFinish method " + iTestContext.getName()+concol.RESET);
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println(concol.PURPLE_BOLD_BRIGHT+"I am in onTestStart method " + getTestMethodName(iTestResult) + " start"+concol.RESET);
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println(concol.PURPLE_BOLD_BRIGHT+"I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed"+concol.RESET);
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println(concol.PURPLE_BOLD_BRIGHT+"I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed"+concol.RESET);
		Object testClass = iTestResult.getInstance();
		//WebDriver driver = BasePage.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (getDriver() instanceof WebDriver) {
			System.out.println(concol.PURPLE_BOLD_BRIGHT+"Screenshot captured for test case:" + getTestMethodName(iTestResult)+concol.RESET);
			saveScreenshotPNG(getDriver());
		}
		// Save a log on allure.
		saveTextLog(concol.PURPLE_BOLD_BRIGHT+getTestMethodName(iTestResult) + " failed and screenshot taken!"+concol.RESET);		
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println(concol.PURPLE_BOLD_BRIGHT+"I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped"+concol.RESET);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println(concol.PURPLE_BOLD_BRIGHT+"Test failed but it is in defined success ratio " + getTestMethodName(iTestResult)+concol.RESET);
	}
}

package com.zyto.test.util.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import com.zyto.test.base.ZytoBase;

public class Test_Utilities_File extends ZytoBase{

	/***************************************************************************************
	 * Path of the TEST DATA sheet
	 ****************************************************************************************/
	static Workbook book;
	static Sheet sheet;
	//public static String sheetname1 = "user logins";
	//public static String sheetname2 = "Password";
	//public static String sheetname3 = " Details";

	static JavascriptExecutor js;

	/***************************************************************************************
	 * Declaration for the Page Load and Implicit Timeouts and fixed values are given
	 ****************************************************************************************/
	public static long PAGELOAD_TIMEOUT = 20;
	public static long IMPLICIT_TIMEOUT = 20;

	/***************************************************************************************
	 * Method for driver to implicitly wait for the action to happen when needed
	 ****************************************************************************************/
	public void Implicitwait() {
		driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
	}

	/***************************************************************************************
	 * Method for handling the frames on pages and get to the main element frame
	 ****************************************************************************************/
	public static void switchToframe() {
		driver.switchTo().frame("main element");
	}

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\dell\\Desktop\\photonclipse\\"
			+ "CampusOil.ie\\src\\main\\java\\com\\campusOil\\qa\\testdata\\CampusOilTestData.xlsx";

	
	//public static String sheetname4 = "quote";
	/*******************************************************************************************
	 * Method for TEST DATA import from excel sheet with own workbook to read and write the data
	 *******************************************************************************************/
	public static Object[][] getCampusOilTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	/***************************************************************************************
	 * Method for taking screenshot of the error and store it in the folder named screenshots
	 ****************************************************************************************/
	public static void takeScreenshotAtEndOfTest() throws IOException {
		// TODO Auto-generated method stub
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	
		
	}

	/***************************************************************************************
	 * Method for Javascript Executor to handle the jquery parts on the page and handle them
	 ****************************************************************************************/

	public static void runTimeInfo(String messageType, String message) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		// Check for jQuery on the page, add it if need be
		js.executeScript("if (!window.jQuery) {"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
		Thread.sleep(5000);

		// Use jQuery to add jquery-growl to the page
		js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

		// Use jQuery to add jquery-growl styles to the page
		js.executeScript("$('head').append('<link rel=\"stylesheet\" "
				+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
		Thread.sleep(5000);

		// jquery-growl w/ no frills
		js.executeScript("$.growl({ title: 'GET', message: '/' });");
		// '"+color+"'"
		if (messageType.equals("error")) {
			js.executeScript("$.growl.error({ title: 'ERROR', message: '" + message + "' });");
		} else if (messageType.equals("info")) {
			js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
		} else if (messageType.equals("warning")) {
			js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
		} else
			System.out.println("no error message");
		// jquery-growl w/ colorized output
		// js.executeScript("$.growl.error({ title: 'ERROR', message: 'your error
		// message goes here' });");
		// js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice
		// message goes here' });");
		// js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning
		// message goes here' });");
		Thread.sleep(5000);
	}
	
	/***************************************************************************************
	 * Method for Calendar to handle and Timer
	 ****************************************************************************************/

	public static void DatePicker(WebElement CalendarBox, WebElement NextLinkButton, WebElement MidLink,
			WebElement PreviousButtonLink, List<WebElement> ListOfMonths, List<WebElement> ListAllDates)
			throws Exception {

		// Date and Time to be set in textbox

		String dateTime = "20-03-2020 12:23:09";

		// button to open calendar

		WebElement selectDate = CalendarBox;
		selectDate.click();

		// button to move next in calendar
		WebElement nextLink = NextLinkButton;

		// button to click in center of calendar header
		WebElement midLink = MidLink;

		// button to move previous month in calendar
		WebElement previousLink = PreviousButtonLink;

		// Split the date time to get only the date part
		String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");
		

		// get the year difference between current year and year to set in calendar
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);
		midLink.click();
//if(year=! 2017){

		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					System.out.println("Year Diff->" + i);
					nextLink.click();
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					System.out.println("Year Diff->" + i);
					previousLink.click();
				}
			}
		}
		Thread.sleep(1000);
		// Get all months from calendar to select correct one
		List<WebElement> list_AllMonthToBook = ListOfMonths;
		list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1).click();

		Thread.sleep(1000);

		// get all dates from calendar to select correct one
		List<WebElement> list_AllDateToBook = ListAllDates;
		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1).click();
	}

	/***************************************************************************************
	 * Method for Calendar to handle and Timer
	 ****************************************************************************************/
	public static void TimePicker(String TimeInput, WebElement TimeSelection, List<WebElement> getAllTimes) {
		String Time = TimeInput;
		WebElement selectTime = TimeSelection;
		// click time picker button
		selectTime.click();
		// get list of times
		List<WebElement> allTime = getAllTimes;
		Time = Time.split(" ")[1] + " " + Time.split(" ")[2];
		// select correct time
		for (WebElement webElement : allTime) {
			if (webElement.getText().equalsIgnoreCase(Time)) {
				webElement.click();
			}
		}
	
	}
	public static void DateSelector(String Date) {
		  Calendar calendar = Calendar.getInstance();
		    
		    // get a date to represent "today"
		    Date today = calendar.getTime();
		    //System.out.println("today:    " + today);
		 
		    // add one day to the date/calendar
		    calendar.add(Calendar.DAY_OF_YEAR, 1);
		    
		    // now get "tomorrow"
		    Date tomorrow = calendar.getTime();

		    // print out tomorrow's date
		    //System.out.println("tomorrow: " + tomorrow);
		  }

	

}

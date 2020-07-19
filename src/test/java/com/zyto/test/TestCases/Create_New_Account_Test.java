
package com.zyto.test.TestCases;


import org.testng.annotations.Test;
import org.apache.poi.ss.util.CellUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zyto.test.ConsoleColors.Console_Colors;
import com.zyto.test.base.ZytoBase;
import com.zyto.test.pages.Signup_Options_page;

public class Create_New_Account_Test extends ZytoBase {

	// Pages to Used //
    ZytoBase zytobase;
	Console_Colors concol;
	Signup_Options_page Signuppage;
	
	public Create_New_Account_Test() {
		super();
	}

	@BeforeMethod
	public void SetUp() {
		zytobase =  new ZytoBase();
		prop = zytobase.init_prop();
		driver = zytobase.init_driver(prop);
		driver.get(prop.getProperty("url"));
		System.out.println(concol.BLUE_BOLD_BRIGHT + "****Create New Account Test Started****" + concol.RESET);
		Signuppage = new Signup_Options_page();
	}

	@Test
	public void Click_On_Create_button() throws InterruptedException {
		System.out.println(concol.BLUE_BOLD_BRIGHT + "****Clicking on Create New Account button****" + concol.RESET);
		Signuppage.Click_Signup_btn();
		driver.navigate().back();
	}

	@Test
	public void Click_Signin_button() {
		System.out.println(concol.BLUE_BOLD_BRIGHT + "****Clicking on Sign in  button****" + concol.RESET);
		Signuppage.Click_Sign_In_btn();
		driver.navigate().back();
	}

	@Test
	public void Click_world_icon() {
		System.out.println(concol.BLUE_BOLD_BRIGHT + "****Clicking on World icon ****" + concol.RESET);
		Signuppage.Click_World_icon();

		// Check English Language
		Signuppage.Select_Language();

		// Change Language
		Signuppage.Change_Language();

		// Click onCross icon
		Signuppage.Click_Cross_icon();

	}

	@AfterMethod
	public void EndTest() {
		System.out.println(concol.BLUE_BOLD_BRIGHT + "****Quitting the browser****" + concol.RESET);
		driver.quit();
	}

}

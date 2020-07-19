package com.zyto.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zyto.test.ConsoleColors.Console_Colors;
import com.zyto.test.base.ZytoBase;
import com.zyto.test.util.java.ElementLocatorUtils;

public class Signup_Options_page extends ZytoBase {
	

	/***********************************
	 ******* SIGNUP OPTIONS PAGE*********
	 ***********************************/
	// Create Account button on Signup Options page
	@FindBy(xpath = "//button[@type='button'][contains(text(),'Create Account')]")
	static WebElement Create_Account_Button;

	// Sign in button on Signup options page
	@FindBy(xpath = "//button[@type='button'][contains(text(),'Sign In')]")
	static WebElement SignIn_Button;

	// Language change option -- World icon
	@FindBy(xpath = "//a[@class='mr-2']")
	static WebElement Language_Change_World_Icon;

	/****************** SELECT A LANGUAGE FROM POPUP/FRAME *******************/

	// English Language Radiobutton
	@FindBy(xpath = "//input[@value='en']")
	static WebElement English_Language_RadioBtn;

	// Spanish Language Radiobutton
	@FindBy(xpath = "//input[@value='es']")
	static WebElement Spanish_Language_RadioBtn;

	// Spanish Language Radiobutton
	@FindBy(xpath = "//i")
	static WebElement Cross_icon_on_Frame;

	
	/************** PAGES TO BE USED ****************/

	public static Console_Colors colcon;
	private WebDriver driver;

	/************** Initialize page elements ***********/

	public Signup_Options_page() {
		PageFactory.initElements(driver, this);
		this.driver =  driver;
		System.out.println(colcon.BLUE_BOLD_BRIGHT + "Signup Options Page Test Started" + colcon.RESET);
	}


	/************** ACTIONS TO BE PERFOMED ***********/
	// Click on Signup button
	public static void Click_Signup_btn() {
		Create_Account_Button.click();
	}

	// Click on SignIn button
	public static void Click_Sign_In_btn() {
		SignIn_Button.click();
	}

	// Click on World icon
	public static void Click_World_icon() {
		Language_Change_World_Icon.click();
	}

	// Select the Language
	public static void Select_Language() {
		if (English_Language_RadioBtn.isSelected()) {
			System.out.println(colcon.PURPLE_BOLD_BRIGHT + "English Language is selected" + colcon.RESET);
		} else {
			English_Language_RadioBtn.click();
			System.out.println(colcon.PURPLE_BOLD_BRIGHT + "English Language is selected" + colcon.RESET);
		}
	}

	// Change the Language
	public static void Change_Language() {
		if (Spanish_Language_RadioBtn.isSelected()) {
			System.out.println(colcon.PURPLE_BOLD_BRIGHT + "Spanish Language is selected" + colcon.RESET);
		} else {
			Spanish_Language_RadioBtn.click();
			System.out.println(colcon.PURPLE_BOLD_BRIGHT + "Spanish Language is selected" + colcon.RESET);
		}

	}

	// Click on cross icon
	public static void Click_Cross_icon() {
		Cross_icon_on_Frame.click();
	}
}

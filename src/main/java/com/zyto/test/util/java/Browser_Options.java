package com.zyto.test.util.java;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser_Options {
	
	public Properties prop;
	public ChromeOptions co;
	public FirefoxOptions fo;
	
	public Browser_Options(Properties prop){
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions(){
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions(){
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
		return fo;
	}

}

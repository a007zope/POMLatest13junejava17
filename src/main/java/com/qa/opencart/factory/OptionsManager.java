package com.qa.opencart.factory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

	private static Properties prop;
	private static ChromeOptions co;
	private FirefoxOptions fo;

	public OptionsManager(Properties prop)
	{
		this.prop = prop;
	}
	public static  ChromeOptions getChromeOptions()
	{
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
			// co.addArguments("--remote-allow-origins=*");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
			//  co.addArguments("--remote-allow-origins=*");
		}
		return co;
	}
}

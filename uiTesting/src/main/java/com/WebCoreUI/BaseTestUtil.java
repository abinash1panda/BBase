package com.WebCoreUI;

import org.testng.annotations.Test;

import com.backbase.coreUtils.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;

public class BaseTestUtil {
	private static WebDriver driver;
	String chromeDriver = "chromedriver";	

	@BeforeSuite(alwaysRun = true)
	public void suiteSetup(ITestContext context) throws Exception {

	}


	@BeforeMethod(alwaysRun = true)
	public static WebDriver beforeMethod() {
		driver = BrowserFactory.getBrowser("Chrome");
		return driver;
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite(ITestContext context) throws Exception {
		BrowserFactory.closeAllDriver();
	}

}

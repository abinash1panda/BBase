package com.backbase.coreUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import com.WebCoreUI.BrowserFactory;

import com.google.common.base.Function;

public class BasePage {

	public void mouseOver(WebElement e) throws Exception {
		Actions action = new Actions(BrowserFactory.getBrowser("Chrome"));
		action.moveToElement(e).perform();
	}

	public void waitForElementVisible(WebDriver wb, By e) {
		WebDriverWait wait = new WebDriverWait(wb, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(e));
	}

	public void waitForElementVisible(By e) throws Exception {
		WebDriver wb = BrowserFactory.getBrowser("Chrome");
		WebDriverWait wait = new WebDriverWait(wb, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(e));
	}

	public void selectValueByVisibleTextFromDropDown(WebElement dropDownSelector, String value){
		Select select = new Select(dropDownSelector);
		select.selectByVisibleText(value);
	}

	public static boolean isClickable(WebElement element,WebDriver wb)      
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(wb, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

}

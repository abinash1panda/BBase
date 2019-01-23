package com.backbase.uiTesting;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.testng.asserts.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.WebCoreUI.BaseTestUtil;
import com.WebCoreUI.BrowserFactory;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import backBase.pageObjects.BackBasePo;

import com.backbase.coreUtils.ConfigManager;
import com.backbase.coreUtils.Log;


public class BackBaseUITest  extends BaseTestUtil
{
	private WebDriver wb=BaseTestUtil.beforeMethod();;
	private static int totalCount = 0;
	@Test()
	public void Test01_validateHomePage() throws Exception {
		Log.startTestCase("Test01_validateHomePage");
		SoftAssert sassert = new SoftAssert();
		wb.get(ConfigManager.getProperty("BaseUrl"));
		Log.info("New chrome driver instantiated");

		wb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 10 seconds");

		BackBasePo backBase_Po = PageFactory.initElements(wb, BackBasePo.class);

		Thread.sleep(60);

		String topText = backBase_Po.verifyTopText();
		sassert.assertEquals(topText.contains("computers found"), true);
		String count = topText.split(" ")[0];
		Log.info("Total number of computers found is " +  count);
		totalCount = Integer.parseInt(count);
		System.out.println(totalCount);
		sassert.assertTrue(backBase_Po.isSearchBoxPresent(),"search box not present");
		Log.info("Search box is present on the page");
		sassert.assertTrue(backBase_Po.isFilterByNameBtnPresent(),"FilterByName Btn not present");
		Log.info("FilterByNameBtn is present on the page");
		sassert.assertTrue(backBase_Po.isAddANewComputerBtnPresent(),"Add a new computer Btn not present");
		Log.info("Add a new computer btn is present on the page");
		Log.endTestCase("Test01_validateHomePage");

	}
	@Test()
	public void Test02_validateSearchFeature() throws Exception {
		Log.startTestCase("Test02_validateSearchFeature");
		SoftAssert sassert = new SoftAssert();
		wb.get(ConfigManager.getProperty("BaseUrl"));
		Log.info("New chrome driver instantiated");

		wb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 10 seconds");

		BackBasePo backBase_Po = PageFactory.initElements(wb, BackBasePo.class);

		Thread.sleep(3000);

		String topText = backBase_Po.verifyTopText();
		sassert.assertEquals(topText.contains("computers found"), true);
		String count = topText.split(" ")[0];
		Log.info("Total number of computers found is " +  count);
		totalCount = Integer.parseInt(count);
		sassert.assertEquals((backBase_Po.getUnderlyingTextOfSearchTextbox()),"Filter by computer name...","Underlying text not found");

		Log.info("Search Text box underlying text read");

		backBase_Po.enterSearchText("ASCI White");
		backBase_Po.clickOnFilterByNameBtn();
		sassert.assertEquals((backBase_Po.verifySearchResult()),"ASCI White","Search didn't match");
		Log.info("Search Result Found");
		sassert.assertEquals((backBase_Po.verifyDisplayText()),"Displaying 1 to 1 of 1","text didnt match");
		Log.info("Displaying number of records text matched");

		backBase_Po.enterSearchText("");
		backBase_Po.clickOnFilterByNameBtn();
		sassert.assertEquals((backBase_Po.verifyDisplayText()),"Displaying 1 to 10 of"+" "+totalCount,"text didnt match");
		String str = "Displaying 1 to 10 of"+" "+totalCount;
		System.out.println(str);
		List<WebElement> allHeadersOfTable = backBase_Po.accessWebTableHeaders();
		System.out.println("Headers in table are below:");
		System.out.println("Total headers found: "+allHeadersOfTable.size());
		for(WebElement header: allHeadersOfTable)
		{
			switch(header.getText())

			{
			case "Computer name":
				sassert.assertEquals(true,"Computer name header not found");
				Log.info("Computer name header not found");
				break;
			case "Introduced":
				sassert.assertEquals(true,"Introduced name header not found");
				Log.info("Introduced name header not found");
				break;
			case "Discontinued":
				sassert.assertEquals(true,"Discontinued name header not found");
				Log.info("Discontinued name header not found");
				break;
			case "Company":
				sassert.assertEquals(true,"Company name header not found");
				Log.info("Company name header not found");
				break;	
			}

		}
		HashMap<String, String> uiMap = backBase_Po.getUISummary(wb);
		HashMap<String, String> inPutMap = backBase_Po.getInputData();
		Iterator<Entry<String, String>> itr = inPutMap.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, String> entry = itr.next();
			String key = entry.getKey();
			Log.info(key + entry.getValue());

		}
		sassert.assertEquals(uiMap.keySet(),(inPutMap.keySet()),"Data mismatch");
		sassert.assertEquals(uiMap.values(),(inPutMap.values()),"Data mismatch");

		backBase_Po.enterSearchText("ABCDEF");
		backBase_Po.clickOnFilterByNameBtn();
		sassert.assertEquals((backBase_Po.verifyTextNothingToDisplay()),"Nothing to display","Search result is not there even no text is there");
		Log.endTestCase("Test02_validateSearchFeature");

	}
	@Test()
	public void Test03_validatePageNavigation() throws Exception {
		Log.startTestCase("Test03_validatePageNavigation");
		SoftAssert sassert = new SoftAssert();
		wb.get(ConfigManager.getProperty("BaseUrl"));
		Log.info("New chrome driver instantiated");

		wb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 10 seconds");

		BackBasePo backBase_Po = PageFactory.initElements(wb, BackBasePo.class);

		Thread.sleep(3000);

		String topText = backBase_Po.verifyTopText();
		sassert.assertEquals(topText.contains("computers found"), true);
		String count = topText.split(" ")[0];
		Log.info("Total number of computers found is " +  count);
		totalCount = Integer.parseInt(count);
		backBase_Po.clickOnNextLink();
		sassert.assertEquals((backBase_Po.verifyDisplayText()),"Displaying 11 to 20 of"+" "+totalCount,"text didnt match");
		Log.info("Displaying number of records text matched");

		sassert.assertTrue((backBase_Po.isPreviousLinkEnabled()));
		Log.info("Previous link is enabled");
		sassert.assertTrue((backBase_Po.isNextLinkEnabled()));
		Log.info("Next link is enabled");

		backBase_Po.clickOnPreviousLink();
		sassert.assertEquals((backBase_Po.verifyDisplayText()),"Displaying 1 to 1 of"+" "+totalCount,"text didnt match");
		Log.info("Displaying number of records text matched");

		backBase_Po.clickOnNextLinkUntilNextLinkEnabled(wb);
		Log.info("Last page is displaying");
		sassert.assertTrue((backBase_Po.isPreviousLinkEnabled()));
		Log.info("Previous link is enabled");
		sassert.assertFalse((backBase_Po.isNextLinkEnabled()));
		Log.info("Next link is disabled");

		Log.endTestCase("Test03_validatePageNavigation");
	}

	@Test()
	public void Test04_verifyAddComputerFeature() throws Exception {
		Log.startTestCase("Test04_verifyAddComputerFeature");
		SoftAssert sassert = new SoftAssert();
		wb.get(ConfigManager.getProperty("BaseUrl"));
		Log.info("New chrome driver instantiated");

		wb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 10 seconds");

		BackBasePo backBase_Po = PageFactory.initElements(wb, BackBasePo.class);

		Thread.sleep(3000);

		backBase_Po.clickOnAddANewComputerBtn();
		Log.info("Add new computer button is clicked");

		String computerName = "Abinash Panda";
		backBase_Po.enterComputerName(computerName);
		Log.info("Computer Name entered");

		backBase_Po.enterIntroducedDate("2019-01-23");
		Log.info("IntroducedDate  entered");

		backBase_Po.enterDiscontinuedDate("2019-01-24");
		Log.info("DiscontinuedDate Name entered");

		backBase_Po.selectCompanyName("Apple Inc.");
		Log.info("Company Name is selected");

		backBase_Po.clickOnCreateThisComputerBtn();
		Log.info("ComputerCreate btn is Clicked");

		String confirmationMsg = backBase_Po.getConfirmationMsg();
		String[] str = confirmationMsg.split(" ");
		if(str[2]==computerName) {
			sassert.assertEquals(confirmationMsg,"Done! Computer ["+str[2]+"]+ has been created","Confirmation message is incorrect");
			Log.info("Confirmation message asserted");
		}
		Thread.sleep(1000);
		backBase_Po.enterSearchText("Abinash Panda");
		backBase_Po.clickOnFilterByNameBtn();
		Thread.sleep(30);
		sassert.assertEquals((backBase_Po.verifySearchResult()),"Abinash Panda","Search didn't match");
		Log.info("Search Result Found");
		sassert.assertEquals((backBase_Po.verifyDisplayText()),"Displaying 1 to 1 of 1","text didnt match");
		Log.info("Displaying number of records text matched");

		Log.endTestCase("Test04_verifyAddComputerFeature");
	}
}


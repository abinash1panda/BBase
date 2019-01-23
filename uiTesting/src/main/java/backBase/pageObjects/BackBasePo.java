package backBase.pageObjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.WebCoreUI.BaseTestUtil;
import com.WebCoreUI.BrowserFactory;
import com.backbase.coreUtils.*;
import com.backbase.coreUtils.Log;
public class BackBasePo extends BasePage{


	@FindBy(id="searchbox")
	private WebElement searchBox;

	@FindBy(id="searchsubmit")
	private WebElement filterByNameBtn;

	@FindBy(id="add")
	private WebElement addANewComputer;

	@FindBy(xpath="//*[@id='pagination']/ul//descendant::a[3]")
	private WebElement nextLink;

	@FindBy(xpath="//*[contains(text(),'Displaying')]")
	private WebElement displayText;

	@FindBy(xpath="//*[contains(@href,'computers') and contains(text(),'Previous')]")
	private WebElement previousLink;

	@FindBy(xpath="//*[contains(@href,'computers')]")
	private WebElement computerColumnName;

	@FindBy(xpath="//*[contains(@href,'computers') and contains(text(),'Introduced')]")
	private WebElement introducedColumnName;

	@FindBy(xpath="//*[contains(@href,'computers') and contains(text(),'Discontinued')]")
	private WebElement disContinuedColumnName;

	@FindBy(xpath="//*[contains(@href,'computers') and contains(text(),'Company')]")
	private WebElement companyColumnName;

	@FindBy(xpath="//h1[contains(text(),'computers found')]")
	private WebElement topText;

	@FindBy(id="name")
	private WebElement enterComputerName;

	@FindBy(id="introduced")
	private WebElement enterIntroducedDate;

	@FindBy(id="discontinued")
	private WebElement enterDiscontinuedDate;

	@FindBy(xpath="//select/option[contains(text(),'company')]/..")
	private WebElement selectCompanyname;

	@FindBy(xpath="//*[@class='btn primary']")
	private WebElement createThisComputerBtn;

	@FindBy(xpath="//*[@class='btn']")
	private WebElement cancelBtn;

	@FindBy(xpath="//table[@class='computers zebra-striped']//descendant::a[1]")
	private WebElement findResultComputerName;

	@FindBy(xpath="//*[contains(text(),'Nothing to display')]")
	private WebElement verifyTextNothingToDisplay;

	@FindBy(xpath="//table[@class='computers zebra-striped']/thead/tr[1]/th")
	private List<WebElement> webTableHeaders;

	@FindBy(xpath="//table[@class='computers zebra-striped']/tbody/tr/..")
	private List<WebElement> webTableAllRows;


	@FindBy(xpath="//table[@class='computers zebra-striped']/tbody/tr")
	private List<WebElement> webTableAllColumns;

	public List<WebElement> accessWebTableHeaders() {
		return webTableHeaders;
	}
	@FindBy(xpath="//*[@class='alert-message warning']")
	private WebElement confirmationMsg;

	public HashMap<String, String> getUISummary(WebDriver wb){

		List<WebElement> allHeadersOfTable = accessWebTableHeaders();
		System.out.println("Headers in table are below:");
		System.out.println("Total headers found: "+allHeadersOfTable.size());


		HashMap<String, String> map = new HashMap<>();
		WebElement parentTable = wb.findElement(By.xpath("//table[@class='computers zebra-striped']/tbody"));
		List<WebElement> allRows = parentTable.findElements(By.tagName("*"));
		int n = allRows.size();
		System.out.println(n);
		for(int i=1;i<=allRows.size();i++)
		{
			List<WebElement> allColumnsInRow= parentTable.findElements(By.xpath("/html/body/section/table/tbody/tr["+i+"]/td"));
			int k = allColumnsInRow.size();

			for (int j=0;j<allColumnsInRow.size();j++) {
				System.out.println(allHeadersOfTable.get(j).getText() + "*********"  + allColumnsInRow.get(j).getText());
				map.put((allHeadersOfTable.get(j).getText()), (allColumnsInRow.get(j).getText()));
			}

		}

		return map;
	}

	/* We can use data provider to read multiple sets of data but to save time, I am just reading one data and comparing same hence restricting this to one set*/

	public HashMap<String, String> getInputData(){
		HashMap<String, String> map = new HashMap<>();
		map.put("Computer name", "ASCI White");
		map.put("Introduced", "01 Jan 2001");
		map.put("Discontinued", "01 Jan 2006");
		map.put("Company", "IBM");
		return map;
	}	


	public boolean isSearchBoxPresent(){
		boolean boxPresent = searchBox.isDisplayed();
		Log.info("Search text entered");
		return boxPresent;
	}

	public void enterSearchText(String searchText){
		searchBox.sendKeys(searchText);
		Log.info("Search text entered");
	}

	public String getUnderlyingTextOfSearchTextbox(){
		String text = searchBox.getAttribute("value");
		Log.info("Search text underlying text");
		return text;
	}

	public String verifyTopText(){
		String topTexts = topText.getText();
		Log.info("TopText captured");
		return topTexts;
	}

	public void clickOnNextLink() throws InterruptedException{
		nextLink.click();
		Thread.sleep(30);
		Log.info("Next link Clicked");
	}

	public void clickOnNextLinkUntilNextLinkEnabled(WebDriver wb) throws InterruptedException{
		String projectURL =  "http://computer-database.herokuapp.com/computers?p=60";
		while(isNextLinkEnabled()==true) {
			clickOnNextLink();
			if((wb.getCurrentUrl().equalsIgnoreCase(projectURL))) {
				break;
			}

		}}

	public void clickOnPreviousLink(){
		previousLink.click();
		Log.info("Previous link Clicked");
	}

	public boolean isPreviousLinkEnabled() {
		boolean previousLinkCondition = previousLink.isEnabled();
		Log.info("Previous link enabled");
		return previousLinkCondition;
	}

	public boolean isNextLinkEnabled() {
		boolean nextLinkCondition = nextLink.isEnabled();
		Log.info("Next link enabled");
		return nextLinkCondition;
	}

	public String verifyDisplayText(){
		String displaytext = displayText.getText();
		Log.info("Display Text Captured");
		return displaytext;
	}

	public void clickOnFilterByNameBtn(){
		filterByNameBtn.click();
		Log.info("FilterByName Btn Clicked");
	}

	public String verifySearchResult() {
		String result = findResultComputerName.getText();
		Log.info("result listed");
		return result;
	}

	public String verifyTextNothingToDisplay() {
		String nothingToDisplay = verifyTextNothingToDisplay.getText();
		Log.info("Nothing to display text is showing");
		return nothingToDisplay;
	}

	public boolean isFilterByNameBtnPresent(){
		boolean btnPresent = filterByNameBtn.isDisplayed();
		Log.info("FilterByName Btn Clicked");
		return btnPresent;
	}

	public void clickOnAddANewComputerBtn(){
		addANewComputer.click();
		Log.info("AddANewComputer Btn Clicked");
	}

	public boolean isAddANewComputerBtnPresent(){
		boolean  newBtn = addANewComputer.isDisplayed();
		Log.info("AddANewComputer Btn Clicked");
		return newBtn;
	}

	public void enterComputerName(String computerName) {
		enterComputerName.sendKeys(computerName);
		Log.info("Computer Name Entered");
	}
	public void enterIntroducedDate(String introducedDate) {
		enterIntroducedDate.sendKeys(introducedDate);
		Log.info("Introduced Date Entered");
	}

	public void enterDiscontinuedDate(String discontinuedDate) {
		enterDiscontinuedDate.sendKeys(discontinuedDate);
		Log.info("Discontinued Date Entered");
	}

	public void selectCompanyName(String companyName) {
		selectValueByVisibleTextFromDropDown(selectCompanyname,companyName);
		Log.info("CompanyName Selected");
	}

	public void clickOnCreateThisComputerBtn(){
		createThisComputerBtn.click();
		Log.info("Create Btn Clicked");
	}

	public void clickOnCancelBtn(){
		cancelBtn.click();
		Log.info("Canceled Btn Clicked");
	}

	public String getConfirmationMsg() {
		String conMsg = confirmationMsg.getText();
		Log.info("Confirmation Msg");
		return conMsg;
	}

}

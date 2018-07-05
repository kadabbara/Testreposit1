package MyPostBasicTestCases;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyPostApplication

{
	static WebDriver driver;
	static Actions a;
	static WebDriverWait wd;
	
	public static void main(String[] args) throws InterruptedException, ElementNotVisibleException {
		// TODO Auto-generated method stub

		
		String chromeDriverPath = "C:/Users/dabbarak/Documents/Kamesh/Testing/Appium/Softwares/chromedriver_win32/chromedriver.exe";	
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		
/*		System.setProperty("webdriver.gecko.driver", "F://Softwares1/Technical/Appium Softwares/geckodriver-v0.18.0-win64/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
*/		
		driver.manage().window().maximize();
		
		driver.get("https://eparcel-ptest.corptest.aptest.local/eParcel/");
		System.out.println(driver.getTitle());
		
		//Merchant Username
		driver.findElement(By.name("userName")).sendKeys("HMMMerchant");
				
		//Merchant Password
		driver.findElement(By.name("password")).sendKeys("Password1$");
		
		//Login button
		//driver.findElement(By.id("LoginForm")).click();
		driver.findElement(By.xpath("//*[@id=\"LoginForm\"]/fieldset/div[2]/table/tbody/tr[6]/td/div/div[2]/button")).click();
		
		//Selecting Returns Menu
		WebElement returnsMenu = driver.findElement(By.xpath(".//*[@id='returns']"));
		//System.out.println(returnsMenu.isDisplayed());
		
		Actions a = new Actions(driver);
		a.moveToElement(returnsMenu).build().perform();
		Thread.sleep(1000);
		
		//Clickin Create Domestic Return
		//WebElement domReturn = driver.findElement(By.linkText("Create Domestic Return"));
		WebElement domReturn = driver.findElement(By.xpath("//*[@id='navigation_top']/ul/li[2]/ul/li[1]/a"));
		domReturn.click();

		Thread.sleep(1000);
		List<WebElement> retTypes = driver.findElements(By.name("chargeCode"));
		
		//System.out.print(retTypes.size());
		for(int i=1; i<=retTypes.size(); i++)
		
		{
			
			String retOption = retTypes.get(i).getAttribute("value");
			
			if(retOption.equalsIgnoreCase("PR"))
			{
				
				retTypes.get(i).click();
				break;
				
			}
			
		}
		
		//Person making return
		driver.findElement(By.name("personMakingReturn")).sendKeys("AFC-ER12");

		//Consignee address line1
		driver.findElement(By.name("consigneeAddressLine1")).sendKeys("L2 111 Bourke Street");

		//Consignee Suburb
		driver.findElement(By.name("consigneeSuburb")).sendKeys("Melbourne");
		
		//Consignee Post code
		driver.findElement(By.name("consigneePostcode")).sendKeys("3000");
		
		//Consignee Phone Number
		driver.findElement(By.name("contactPhoneNumber")).sendKeys("0450448240");
		
		//Consignee Email
		driver.findElement(By.name("emailAddress")).sendKeys("cssouser084@test.npe.auspost.com.au");
		
		//Consignee State
		WebElement cState = driver.findElement(By.name("consigneeState"));
		cState.click();
		
		Select StateOptions = new Select(cState);
		//StateOptions.selectByVisibleText("VIC");
		StateOptions.selectByValue("VIC");
		
		Thread.sleep(1000);
		//Submit button
		driver.findElement(By.xpath("//*[@value='Submit']")).click();
		
		//Parent window
		String currentWindow = driver.getWindowHandle();
	    System.out.println(" Check title " + driver.getTitle() + driver.getWindowHandles().size());
	    for (String popUpHandle : driver.getWindowHandles()) 
	    {  
	        if(popUpHandle.equalsIgnoreCase(currentWindow))
	            continue;
	        
	        //Switing to the child window
	        driver.switchTo().window(popUpHandle);
	        String sTitle = driver.getTitle();
	        System.out.println(sTitle);
	        
	        //Submit button in the pop up window
	        driver.findElement(By.name("smtbtn")).click();
	    }   
		
	}

}



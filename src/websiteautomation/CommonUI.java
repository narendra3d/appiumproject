package websiteautomation;

import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CommonUI 
{
	public static void main(String[] args) throws Exception
	{
		//Get platform name
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter platform as computer/mobile");
		String p=sc.nextLine();
		//Object Creation
		WebDriver driver=null;
		if(p.equalsIgnoreCase("computer"))
		{
			System.setProperty("webdriver.chrome.driver","E:\\Automation\\chromedriver\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(p.equalsIgnoreCase("mobile"))
		{
			//Details of app and device(AVD)
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
			dc.setCapability("deviceName","ce081718334a5b0b05");
			dc.setCapability("platformName","android");
			dc.setCapability("platformVersion","8.0.0");
			dc.setCapability("automationName","uiautomator2");
			//Start appium server
			Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
			//Get address of appium Server
			URL u=new URL("http://0.0.0.0:4723/wd/hub");
			//Create driver object
			while(2>1)
			{
				try
				{
					driver=new AndroidDriver(u,dc);
					break;
				}
				catch(Exception ex)
				{
				}
			}
		}
		else
		{
			System.out.println("Wrong Platform name");
		}
		//Automation
		//Launch site
		driver.get("http://newtours.demoaut.com/");
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("REGISTER")));
		driver.findElement(By.linkText("REGISTER")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));
		driver.findElement(By.name("firstName")).sendKeys("rohan");
		driver.findElement(By.name("lastName")).sendKeys("kumar");
		driver.findElement(By.name("phone")).sendKeys("9030957386");
		driver.findElement(By.name("userName")).sendKeys("gatturohankumar@gmail.com");
		driver.findElement(By.name("address1")).sendKeys("207");
		driver.findElement(By.name("address2")).sendKeys("India");
		driver.findElement(By.name("city")).sendKeys("Hyderabad");
		driver.findElement(By.name("state")).sendKeys("Telangana");
		driver.findElement(By.name("postalCode")).sendKeys("500035");
		WebElement e=driver.findElement(By.name("country"));
		Select s=new Select(e);
		s.selectByVisibleText("INDIA");
		driver.findElement(By.name("email")).sendKeys("gatturohankumar");
		driver.findElement(By.name("password")).sendKeys("gatturohankumar134");
		driver.findElement(By.name("confirmPassword")).sendKeys("gatturohankumar134");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("register")));
		driver.findElement(By.name("register")).click();
		Thread.sleep(5000);
		if(p.equalsIgnoreCase("computer"))
		{
			//Close site
			driver.close();
		}
		else
		{
			//Close site
			driver.close();
			//Stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		}
	}
}

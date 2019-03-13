package srinivasansekar;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Cricbuzz 
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.cricbuzz.android");
		dc.setCapability("appActivity","com.cricbuzz.android.lithium.app.view.activity.NyitoActivity");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//Create driver object
		AndroidDriver driver;
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
		//Automation
		try
		{
			Thread.sleep(30000);
			//Get Current Context
			String x=driver.getContext();
			System.out.println(x);
			Thread.sleep(5000);
			Set s=driver.getContextHandles();
			System.out.println(s);
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@bounds='[0,2120][1440,2320]']")));
			driver.findElement(By.xpath("//*[@bounds='[0,2120][1440,2320]']")).click();
			Thread.sleep(10000);
			driver.launchApp();
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//Close app
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}

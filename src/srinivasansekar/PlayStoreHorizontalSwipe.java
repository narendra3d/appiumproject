package srinivasansekar;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class PlayStoreHorizontalSwipe
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.android.vending");
		dc.setCapability("appActivity","com.android.vending.AssetBrowserActivity");
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
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='HOME']")));
			TouchAction ta=new TouchAction(driver);
			WaitOptions wo=new WaitOptions();
			wo.withDuration(Duration.ofSeconds(1));
			//Horizontal Swipe(Right to left)
			while(2>1)
			{
				List<MobileElement> l1=driver.findElements(By.xpath("(//*[@resource-id='com.android.vending:id/cluster_content'])[2]/child::*"));
				if(l1.get(l1.size()-1).getAttribute("className").equals("android.view.View"))
				{
					break;
				}
				else
				{
					ta.press(ElementOption.point(1306,1737)).waitAction(wo).moveTo(ElementOption.point(280,1737)).release().perform();
				}
			}
			
			//Horizontal Swipe(Right to left)
			while(2>1)
			{
				List<MobileElement> l2=driver.findElements(By.xpath("(//*[@resource-id='com.android.vending:id/cluster_content'])[2]/child::*"));
				if(l2.get(0).getAttribute("className").equals("android.view.View"))
				{
					break;
				}
				else
				{
					ta.press(ElementOption.point(280,1737)).waitAction(wo).moveTo(ElementOption.point(1306,1737)).release().perform();
				}
			}
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

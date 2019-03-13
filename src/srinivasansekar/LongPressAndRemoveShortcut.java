package srinivasansekar;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.ElementOption;

public class LongPressAndRemoveShortcut
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("automationName","uiautomator2");
		dc.setCapability("appPackage","com.samsung.android.contacts");
		dc.setCapability("appActivity","com.android.dialer.DialtactsActivity");
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		//Get address of appium Server
		URL u=new URL("http://127.0.0.1:4723/wd/hub");
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Keypad']")));
			KeyEvent k=new KeyEvent(AndroidKey.HOME);
			driver.pressKey(k);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@content-desc='Phone'])[1]")));
			WebElement e=driver.findElement(By.xpath("(//*[@content-desc='Phone'])[1]"));
			TouchAction ta=new TouchAction(driver);
			ta.longPress(ElementOption.element(e)).perform();
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Remove from Home']")));
			driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Remove from Home\")").click();
			//driver.findElement(By.xpath("//*[@text='Remove from Home']")).click();
			driver.resetApp();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Keypad']")));
			driver.closeApp();	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}

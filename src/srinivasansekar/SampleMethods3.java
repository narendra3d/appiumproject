package srinivasansekar;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.appmanagement.ApplicationState;

public class SampleMethods3
{
	public static void main(String[] args) throws Exception
	{
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","ce081718334a5b0b05");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.0.0");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
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
		//Get details of launched app specified in desired capabilities
		System.out.println("package name is "+driver.getCurrentPackage());
		System.out.println("activity name is "+driver.currentActivity());
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']")));
		driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Back']")));
		Thread.sleep(3000);
		//Work with other app
		if(driver.isAppInstalled("com.telangana.twallet"))
		{
			System.out.println("app is available");
			driver.activateApp("com.telangana.twallet");
			Thread.sleep(5000);
		}
		else
		{
			driver.installApp("Downloads\\twallet.apk");
			System.out.println("app is now available");
		}
		//Get status of other launched app
		ApplicationState as=driver.queryAppState("com.telangana.twallet");
		System.out.println(as.toString());
		Thread.sleep(5000);
		//Get other launched app details
		System.out.println("package name is "+driver.getCurrentPackage());
		System.out.println("activity name is "+driver.currentActivity());
		Thread.sleep(5000);
		KeyEvent k=new KeyEvent(AndroidKey.HOME);
		driver.pressKey(k);
		Thread.sleep(5000);
		driver.resetApp();
		Thread.sleep(5000);
		driver.closeApp();
		//Stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}
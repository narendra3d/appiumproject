package srinivasansekar;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

public class OcrMaps
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
		dc.setCapability("locationServicesEnabled",true);
		dc.setCapability("locationServicesAuthorized",true);
		dc.setCapability("noReset","true");
		dc.setCapability("fullReset","false");
		dc.setCapability("appPackage","com.google.android.apps.maps");
		dc.setCapability("appActivity","com.google.android.maps.MapsActivity");
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
			Thread.sleep(4000);
			File src=driver.getScreenshotAs(OutputType.FILE);
			File dest=new File("abc.png");
			FileHandler.copy(src, dest);
			driver.closeApp();
			//Load inbuilt tessdata folder which have characters
			File fo=LoadLibs.extractTessResources("tessdata");
			//Create object to apply OCR on an image
			Tesseract obj=new Tesseract();
			obj.setDatapath(fo.getAbsolutePath());
			//Take screenshot of the file which have text in context
			String res=obj.doOCR(dest);
			Thread.sleep(20000);
			System.out.println(res);
			if(res.contains("You are here"))
			{
				System.out.println("Test passed");
			}
			else
			{
				System.out.println("Test failed");
			}	
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

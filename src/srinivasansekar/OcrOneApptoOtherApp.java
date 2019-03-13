package srinivasansekar;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.ElementOption;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

public class OcrOneApptoOtherApp 
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='My Galaxy']")));
			driver.findElement(By.xpath("//*[@content-desc='My Galaxy']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='MY GALAXY']")));
			k=new KeyEvent(AndroidKey.BACK);
			driver.pressKey(k);
			Thread.sleep(1000);
			File src=driver.getScreenshotAs(OutputType.FILE);
			File dest=new File("screenshot.png");
			FileHandler.copy(src, dest);
			driver.terminateApp("com.mygalaxy");
			driver.launchApp();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Keypad']")));
			driver.closeApp();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='My Galaxy']")));
			WebElement e=driver.findElement(By.xpath("//*[@content-desc='My Galaxy']"));
			TouchAction ta=new TouchAction(driver);
			ta.longPress(ElementOption.element(e)).perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='Remove from Home']")));
			driver.findElement(By.xpath("//*[@content-desc='Remove from Home']")).click();
			//Load inbuilt tessdata folder which have characters
			File fo=LoadLibs.extractTessResources("tessdata");
			//Create object to apply OCR on an image
			Tesseract obj=new Tesseract();
			obj.setDatapath(fo.getAbsolutePath());
			//Take screenshot of the file which have text in context
			String res=obj.doOCR(dest);
			Thread.sleep(20000);
			System.out.println(res);
			if(res.contains("again to"))
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

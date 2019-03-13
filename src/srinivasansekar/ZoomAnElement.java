package srinivasansekar;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;

public class ZoomAnElement
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']")));
			driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Back']")));
			TouchAction ta=new TouchAction(driver);
			//Swipe for Wheel picker option (bottom to top)
			while(2>1)
			{
				try
				{
					driver.findElement(By.xpath("//*[@text='Photo View']")).click();
					break;
				}
				catch(Exception ex)
				{
					int w=driver.manage().window().getSize().getWidth();
					int h=driver.manage().window().getSize().getHeight();
					int x1=w/2;
					int y1=(int) (h*0.7);
					int x2=w/2;
					int y2=(int) (h*0.5);
					ta.press(ElementOption.point(x1,y1)).moveTo(ElementOption.point(x2,y2)).release().perform();
				}
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.ImageView']")));
			WebElement e=driver.findElement(By.xpath("//*[@class='android.widget.ImageView']"));
			/*int x=e.getLocation().getX();
			int y=e.getLocation().getY();
			int w=e.getSize().getWidth();
			int h=e.getSize().getHeight();*/
			//Zoom out on an element
			TouchAction ta1=new TouchAction(driver);
			ta1.press(ElementOption.point(846,1334)).moveTo(ElementOption.point(1150,1334)).release();
			TouchAction ta2=new TouchAction(driver);
			ta2.press(ElementOption.point(600,1384)).moveTo(ElementOption.point(300,1384)).release();
			Thread.sleep(5000);
			MultiTouchAction ma=new MultiTouchAction(driver);
			ma.add(ta1).add(ta2).perform();
			Thread.sleep(5000);	
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

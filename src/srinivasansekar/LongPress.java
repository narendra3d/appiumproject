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
import io.appium.java_client.touch.offset.ElementOption;

public class LongPress
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
					driver.findElement(By.xpath("//*[@text='Long Press']")).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Back']")));
					WebElement e=driver.findElement(By.xpath("//*[@text='Long Press Me']"));
					TouchAction ta=new TouchAction(driver);
					ta.longPress(ElementOption.element(e)).perform();
					//Validations
					try
					{
						if(driver.findElement(By.xpath("//*[@text='you pressed me hard :P']")).isDisplayed())
						{
							System.out.println("Long Press Performed");
							wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='OK']")));
							driver.findElement(By.xpath("//*[@text='OK']")).click();
						}
					}
					catch(Exception exe)
					{
						System.out.println("Long Press not performed");
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

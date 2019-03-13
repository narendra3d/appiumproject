package srinivasansekar;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class TestingCalculatorInAVD 
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter input1");
		String x=sc.nextLine();
		System.out.println("Enter input2");
		String y=sc.nextLine();
		System.out.println("Enter intended operation as add/subtract/multiply/divide");
		String z=sc.nextLine();
		//Details of app and device(AVD)
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","4.2.2");
		dc.setCapability("appPackage","com.android.calculator2");
		dc.setCapability("appActivity","com.android.calculator2.Calculator");
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
		//App automation
		try
		{
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='DELETE']")));
			//Enter input1
			for(int i=0;i<x.length();i++)
			{
				char d=x.charAt(i);
				if(d=='-')
				{
					driver.findElement(By.xpath("//*[@content-desc='minus']")).click();
				}
				else
				{
					driver.findElement(By.xpath("//*[@text='"+d+"']")).click();
				}
			}
			//Click operator
			if(z.equalsIgnoreCase("add"))
			{
				driver.findElement(By.xpath("//*[@content-desc='plus']")).click();
			}
			else if(z.equalsIgnoreCase("subtract"))
			{
				driver.findElement(By.xpath("//*[@content-desc='minus']")).click();
			}
			else if(z.equalsIgnoreCase("multiply"))
			{
				driver.findElement(By.xpath("//*[@content-desc='multiply']")).click();
			}
			else
			{
				driver.findElement(By.xpath("//*[@content-desc='divide']")).click();
			}
			//Enter input2
			for(int i=0;i<y.length();i++)
			{
				char d=y.charAt(i);
				if(d=='-')
				{
					driver.findElement(By.xpath("//*[@content-desc='minus']")).click();
				}
				else
				{
					driver.findElement(By.xpath("//*[@text='"+d+"']")).click();
				}
			}
			//Click '='
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@content-desc='equals']")));
			driver.findElement(By.xpath("//*[@content-desc='equals']")).click();
			//Get output
			String temp=driver.findElement(By.xpath("//*[@class='android.widget.EditText']")).getAttribute("text");
			if(temp.contains("minus"))
			{
				temp=temp.replace("minus","-");
			}
			//Validations
			int i1=Integer.parseInt(x);
			int i2=Integer.parseInt(y);
			int output=Integer.parseInt(temp);
			if(z.equalsIgnoreCase("add") && output==i1+i2)
			{
				System.out.println(output);
				System.out.println(i1+i2);
				System.out.println("Test Passed");
			}
			else if(z.equalsIgnoreCase("subtract") && output==i1-i2)
			{
				System.out.println(output);
				System.out.println(i1-i2);
				System.out.println("Test Passed");
			}
			else if(z.equalsIgnoreCase("multiply") && output==i1*i2)
			{
				System.out.println(output);
				System.out.println(i1*i2);
				System.out.println("Test Passed");
			}
			else if(z.equalsIgnoreCase("divide") && output==i1/i2)
			{
				System.out.println(output);
				System.out.println(i1/i2);
				System.out.println("Test Passed");
			}
			else
			{
				SimpleDateFormat sf=new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
				Date d=new Date();
				String fname=sf.format(d)+".png";
				File src=driver.getScreenshotAs(OutputType.FILE);
				File dest=new File(fname);
				FileHandler.copy(src,dest);
				System.out.println(z+" Test Failed");
			}
			//Close site
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

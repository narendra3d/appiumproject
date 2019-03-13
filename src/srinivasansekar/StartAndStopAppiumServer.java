package srinivasansekar;

public class StartAndStopAppiumServer 
{
	public static void main(String[] args) throws Exception
	{
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723\"");
		Thread.sleep(10000);
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}

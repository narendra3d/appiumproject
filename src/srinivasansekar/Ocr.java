package srinivasansekar;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

public class Ocr
{
	public static void main(String[] args) throws Exception
	{
		//Open image file to get text
		File f=new File("code.png");
		//Point tess4j 3.0.0 related to library folder
		File fo=LoadLibs.extractTessResources("tessdata");
		//Create object to apply OCR on an image
		Tesseract obj=new Tesseract();
		obj.setDatapath(fo.getAbsolutePath());
		String res=obj.doOCR(f);
		Thread.sleep(20000);
		System.out.println(res);
	}
}

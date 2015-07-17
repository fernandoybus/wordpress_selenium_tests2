package edu.ucsc.extension.wtest.support;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Util {
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		}catch(Exception e) {
			//ignore
		}
	}
	
	public static void takeScreenshot(WebDriver driver, String destFileName) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(destFileName);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

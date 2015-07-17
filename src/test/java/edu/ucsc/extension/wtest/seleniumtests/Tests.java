package edu.ucsc.extension.wtest.seleniumtests;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.glass.ui.Application;

import edu.ucsc.extension.wtest.support.Dashboard;
import edu.ucsc.extension.wtest.support.LoginPage;
import edu.ucsc.extension.wtest.support.NewPostForm;
import edu.ucsc.extension.wtest.support.PostDashboard;
import edu.ucsc.extension.wtest.support.Util;

import java.util.ArrayList;

public class Tests {
	
	private static final String username = "fernando.azevedo@y-bus.com";
	private static final String password = "Tests123!";
	
	
	// BEFORE START CHANGE POST NUMBER TO DELETE ON TASK 3 LINE 68

	@Test
	public void testPostCreation() {
		WebDriver driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		// You may want to add an implicit wait here
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		Dashboard dashboard = loginPage.login(username, password);
		PostDashboard postdashboard = dashboard.showPostDashboard();
		int postCount = postdashboard.getPostCount();
		System.out.println("There are " + postCount + " posts.");

		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////	
		
		// TASK 1 TO CREATE A NEW POST 
		NewPostForm newPostForm = postdashboard.openNewPostForm();
		List<String> scores = new ArrayList<String> ();
		scores.add("Bullet1");
		scores.add("Bullet2");
		scores.add("Bullet3");
		newPostForm.publish("Title for Post", scores );
		System.out.println("Task 1 completed");
		
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////	
		
		
		//EXTRA: GETTING POST NUMBER of task 1 SO WE CAN DELETE ON TASK 3
		String postURL = driver.getCurrentUrl();
		System.out.println(postURL);
		int positionleft = postURL.indexOf("post=");
		int positionright = postURL.indexOf("&action");
		String postNumberstr = postURL.substring( positionleft + 5,positionright);
		System.out.println(postNumberstr);
		int postNumber = Integer.parseInt(postNumberstr);
		
		
	
		// TASK 3 TO DELETE ONE POST 
		// Delete post that was created on task 1
		
		int delete = postdashboard.deletePostById(postNumber);
		System.out.println(delete);
		System.out.println("Task 3 completed");

		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////	
		
		// TASK 4 TO DELETE ALL POSTS 
		int deleteall = postdashboard.deleteAllPost();
		System.out.println(deleteall);
		System.out.println("Task 4 completed");
		
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////	
		
		//TASK 5
//		System.out.println("Task 5");
//				//STEP 1 CREATE POST
				NewPostForm newPostForm2 = postdashboard.openNewPostForm();
				newPostForm2.publish("New Title to be edited" );
//				
//				//STEP 2 Quick edit Post
				// Go to posts page
				String currentURL = driver.getCurrentUrl();
				System.out.println(currentURL);
				int position = currentURL.indexOf("/wp");
				currentURL = currentURL.substring(0, position);
				System.out.println(currentURL);
				currentURL = currentURL + "/wp-admin/edit.php";
				driver.navigate().to(currentURL);
				
				//Get 1st element of list
				System.out.println("Hover on 1st list item");
				List<WebElement> posts = driver.findElements(By.cssSelector("tbody#the-list tr"));
				System.out.println(posts.get(0).getText());

				WebElement element = posts.get(0);
				//STEP 2: Edit Title
				Actions action = new Actions(driver);
		        action.moveToElement(element).build().perform();
		        driver.findElement(By.className("editinline")).click();
		        WebElement titleBox = driver.findElement(By.className("ptitle"));
		        titleBox.clear();
		        //SAve Edit
		        titleBox.sendKeys("Title is edited");
		        WebElement save = driver.findElement(By.className("save"));
		        save.click();
	     
		        System.out.println("Saved post - title is edited");
				Util.wait(6);

				//STEP 3: Now delete Post
				System.out.println("Hover on 1st list item");
				List<WebElement> posts2 = driver.findElements(By.cssSelector("tbody#the-list tr"));
				System.out.println(posts2.get(0).getText());
				WebElement element2 = posts2.get(0);
				Actions action2 = new Actions(driver);
				action2.moveToElement(element2).build().perform();
		        driver.findElement(By.className("submitdelete")).click();
		        System.out.println("POST NOW DELETED");
				System.out.println("Task 5 completed");
		
				
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////	
				
		//TASK 6
		// project's folder
		String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);
		System.out.println("Current working directory : " + workingDir + "/car.jpg");
		
		//go to library
		System.out.println("TASK 6");
		//String currentURL = driver.getCurrentUrl(); //uncomment if needed
		System.out.println(currentURL);
		//int position = currentURL.indexOf("/wp"); //uncomment if needed
		currentURL = currentURL.substring(0, position);
		System.out.println(currentURL);
		String mediaURL = currentURL + "/wp-admin/media-new.php?browser-uploader";
		driver.navigate().to(mediaURL);
		
		//image 1
		System.out.println("uploading image 1");
		WebElement El = driver.findElement(By.id("async-upload"));
		El.sendKeys(workingDir + "/car.jpg");
		driver.findElement(By.id("html-upload")).click();
		Util.wait(5);
		
		//image 2	
		driver.navigate().to(mediaURL);
		Util.wait(3);
		System.out.println("uploading image 2");
		WebElement El2 = driver.findElement(By.id("async-upload"));
		El2.sendKeys(workingDir + "/car2.jpg");
		driver.findElement(By.id("html-upload")).click();
		Util.wait(5);
		
		//image 3
		driver.navigate().to(mediaURL);
		Util.wait(3);
		System.out.println("uploading image 3");
		WebElement El3 = driver.findElement(By.id("async-upload"));
		El3.sendKeys(workingDir + "/car3.jpg");
		driver.findElement(By.id("html-upload")).click();
		
		System.out.println("All images have been uploaded!");
		Util.wait(15);
		
		// Now Delete all images
		String deletemediaURL = currentURL + "/wp-admin/upload.php?mode=list";
		driver.navigate().to(deletemediaURL);
		driver.findElement(By.id("cb-select-all-1")).click();
		Select dropdown = new Select(driver.findElement(By.id("bulk-action-selector-top")));
		dropdown.selectByValue("delete");
		Util.wait(3);
		System.out.println("DELETING ALL MEDIA...");
		driver.findElement(By.id("doaction")).click();
		
		System.out.println("Task 6 completed");
		
		
		Util.wait(15);
		        
		        
		driver.quit();
	}

}

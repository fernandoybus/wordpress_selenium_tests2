package edu.ucsc.extension.wtest.support;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostDashboard {
	
	private WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, linkText = "Add New")
	private WebElement newPostButton;

	@FindBy(how = How.ID, id = "cb-select-all-1")
	private WebElement selectAllPosts;	
	
	
	
	public PostDashboard(WebDriver driver) {
		this.driver = driver;
		if(!driver.getCurrentUrl().endsWith("edit.php"))
			throw new IllegalStateException();
	}
	
	public NewPostForm openNewPostForm() {
		newPostButton.click();
		
		// Wait for the title to change
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.titleContains("Add New Post"));
		
		// Wait for few more elements
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("submitdiv")));
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("publish")));
		
		return PageFactory.initElements(driver, NewPostForm.class);
	}
	
	public int getPostCount() {
		try {
			List<WebElement> posts = driver.findElements(By.cssSelector("tbody#the-list tr"));
			System.out.println(posts.get(0).getText());
			String title = posts.get(0).getText();
			posts.get(0).click();
			
			
			// TASK 2 - no posts
			if (title.equals("No posts found.")) {
				return 0;
				
			}
			else{
				return posts.size();
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deletePostById(int postId){
		try {
			String currentURL = driver.getCurrentUrl();
			System.out.println(currentURL);
			int position = currentURL.indexOf("/wp");
			currentURL = currentURL.substring(0, position);
			System.out.println(currentURL);
			currentURL = currentURL + "/wp-admin/post.php?post=" +  postId + "&action=edit";
			//https://fernandoybustest.wordpress.com/wp-admin/post.php?post=26&action=edit
			driver.navigate().to(currentURL);
			driver.findElement(By.className("submitdelete")).click();
			
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public int deleteAllPost(){
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		int position = currentURL.indexOf("/wp");
		currentURL = currentURL.substring(0, position);
		System.out.println(currentURL);
		currentURL = currentURL + "/wp-admin/edit.php";
		driver.navigate().to(currentURL);
		
		driver.findElement(By.id("cb-select-all-1")).click();
		Util.wait(2);
		
		Select dropdown = new Select(driver.findElement(By.id("bulk-action-selector-top")));
		dropdown.selectByValue("trash");
		Util.wait(3);
		System.out.println("DELETING...");
		driver.findElement(By.id("doaction")).click();
		
		
		return 1;
	}
	
	

	
}

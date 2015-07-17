package edu.ucsc.extension.wtest.support;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewPostForm {
	
	private WebDriver driver;
	
	@FindBy(how = How.NAME, name = "post_title")
	private WebElement titleBox;
	

	@FindBy(how = How.ID, id = "content-html")
	private WebElement htmlButon;	
	
	
	
	@FindBy(how = How.ID, id = "publish")
	private WebElement publishButton;
	

	@FindBy(how = How.ID, id = "content")
	private WebElement textBox;
	


	public NewPostForm(WebDriver driver) {
		this.driver = driver;
	}
	
	public void publish(String title) {
		
		System.out.println("Creating new post...");
		
		
		System.out.println(title);
		titleBox.sendKeys(title);

		publishButton.click();
		
		Util.wait(15);
	}
	
	public void publish(String title, List<String> contentBullets) {
		
		System.out.println("Creating new post...");
		htmlButon.click();
		
		
		System.out.println(title);
		titleBox.sendKeys(title);

		
		
		String bullets = "";
		bullets = "<ol>";
		
		
		for (int i = 0; i < contentBullets.size(); i++) {
			System.out.println(contentBullets.get(i));
			bullets = bullets + "<li>" + contentBullets.get(i) + "</li>";
		}
		bullets = bullets + "</ol>";
		System.out.println(bullets);
	
		
		//this.driver.switchTo().frame("content_ifr");
		textBox = this.driver.findElement(By.id("content"));
		textBox.sendKeys(bullets);
		//this.driver.switchTo().defaultContent();
	
		Util.wait(1);
		System.out.println("publishing post task 1");
		publishButton.click();
		
		Util.wait(15);
	}
	
	
	


}

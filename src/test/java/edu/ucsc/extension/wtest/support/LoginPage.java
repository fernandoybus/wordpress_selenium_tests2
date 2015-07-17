package edu.ucsc.extension.wtest.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;
	
	@FindBy(how = How.ID, id="user_login")
	private WebElement usernameBox;
	
	@FindBy(how = How.ID, id="user_pass")	
	private WebElement passwordBox;
	
	@FindBy(how = How.ID, id = "wp-submit")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://jatin146.wordpress.com/wp-admin");
	}
	
	public Dashboard login(String username, String password) {
		usernameBox.sendKeys(username);
		passwordBox.sendKeys(password);
		loginButton.click();
		
		// Lets wait for the title to change
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.titleContains("Dashboard"));
		
		// Here we should wait for few more items just to be sure
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("postbox-container-1")));
		
		
		return PageFactory.initElements(driver, Dashboard.class);
	}

}

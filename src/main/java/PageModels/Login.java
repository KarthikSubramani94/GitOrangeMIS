package PageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	private By username = By.xpath("//input[@name='txtUsername']");
	private By password = By.xpath("//input[@name='txtPassword']");
	private By submit = By.xpath("//input[@name='Submit']");
	private By welcome = By.xpath("//a[@id='welcome']");
	private By logout = By.xpath("//a[contains(text(),'Logout')]");

	public WebDriver driver;

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public void username(String usrname) {
		driver.findElement(username).sendKeys(usrname);
	}

	public void password(String passwrd) {
		driver.findElement(password).sendKeys(passwrd);
	}

	public void submit() {
		driver.findElement(submit).submit();
	}
	
	public boolean welcome() {
		boolean isDisplayed=driver.findElement(welcome).isDisplayed();
		return isDisplayed;
	}
	
	public void welcomeClick() {
		driver.findElement(welcome).click();
	}
	
	public void logout() {
		driver.findElement(logout).click();
	}

}

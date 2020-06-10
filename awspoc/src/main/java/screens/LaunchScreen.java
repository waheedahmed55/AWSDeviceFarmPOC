package screens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LaunchScreen {

	AppiumDriver driver;

	// page constants
	@AndroidFindBy(accessibility = "test-Username")
	@iOSXCUITFindBy(accessibility = "test-Username")
	public MobileElement usernameEditText;

	@AndroidFindBy(accessibility = "test-Password")
	@iOSXCUITFindBy(accessibility = "test-Password")
	public MobileElement passwordEditText;

	@AndroidFindBy(accessibility = "test-LOGIN")
	@iOSXCUITFindBy(accessibility = "test-LOGIN")
	public MobileElement loginButton;
	
	
	// init elements constructor
	public LaunchScreen(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// page methods
	public void enterUserName(String uName) {
		usernameEditText.clear();
		usernameEditText.sendKeys(uName);
	}

	public void enterPassword(String pwd) {
		passwordEditText.clear();
		passwordEditText.sendKeys(pwd);
	}

	public void clickLoginButton() {
		loginButton.click();
	}

}

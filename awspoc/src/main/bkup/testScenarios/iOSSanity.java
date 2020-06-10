package testScenarios;

import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utils.Randomgenerator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import screens.LaunchScreen;


public class iOSSanity {
	
	public IOSDriver driver;
	public WebDriverWait wait;
	public LaunchScreen launchscreen;
	public Randomgenerator rand;
	
	@BeforeTest
	public void setUp() throws IOException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "iOS");
		caps.setCapability("platformVersion", "12.0");
		caps.setCapability("deviceName", "iPhone X");
		caps.setCapability("automationName", "XCUITest");
		caps.setCapability("nativeWebTap", true);
		caps.setCapability("bundleId", "org.reactjs.native.example.SwagLabsMobileApp");
		caps.setCapability("noReset", true);
		caps.setCapability("connectHardwareKeyboard", false);
		caps.setCapability("toggleSoftwarereKeyboard", true);
		caps.setCapability("isHeadless", false);
		caps.setCapability("noReset", false);

		driver = new IOSDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		launchscreen = new LaunchScreen(driver);
		rand = new Randomgenerator();
		
		
	}

	@AfterTest
	public void tearDown() {
		try {
			 //driver.quit();
		} catch (Exception ign) {
		}
	}
	
	
	@Test(priority=1, description="Validate app launched as expected")
	public void ValidateAppLaunch() {
		
		Assert.assertEquals(launchscreen.loginButton.isDisplayed(), true, "login button is missing after app launch");
		Assert.assertEquals(launchscreen.usernameEditText.isEnabled(), true, "login button is missing after app launch");
		Assert.assertEquals(launchscreen.passwordEditText.isEnabled(), true, "login button is missing after app launch");
	
	}
	
	
	@Test(priority=2, description="Login with invalid credentials validation")
	public void Login_withinvalidcredentials_validation() {
		
		launchscreen.enterUserName(rand.generateRandomName());
		launchscreen.enterPassword(rand.generateRandomPassword());
		launchscreen.clickLoginButton();
		
	}
	

}

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
import org.testng.asserts.SoftAssert;

import Utils.Randomgenerator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import screens.LaunchScreen;


public class AndroidSanity {

public AndroidDriver driver;
public WebDriverWait wait;
public LaunchScreen launchscreen;
public Randomgenerator rand;


@BeforeTest
public void setUp() throws IOException {
DesiredCapabilities caps = new DesiredCapabilities();
caps.setCapability("platformName", "Android");
caps.setCapability("deviceName", "R38MC00R9KJ");
caps.setCapability("automationName", "UiAutomator2");
caps.setCapability("appPackage", "com.swaglabsmobileapp");
caps.setCapability("appActivity", "MainActivity");
caps.setCapability("noReset", true);
caps.setCapability("newCommandTimeout", "2400");

driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps);
driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

launchscreen = new LaunchScreen(driver);
rand = new Randomgenerator();

}

/**
 * AWS will automatically handle the driver.quit thus in order to make it work in AWS. for local you can uncomment
 */
@AfterTest
public void tearDown() {
try {
//driver.quit();
} catch (Exception ign) {
}
}


@Test(description="validation app launch")
public void AppLaunch_Validation() {

Assert.assertEquals(launchscreen.loginButton.isDisplayed(), true, "login button is missing after app launch");
Assert.assertEquals(launchscreen.usernameEditText.isEnabled(), true, "login button is missing after app launch");
Assert.assertEquals(launchscreen.passwordEditText.isEnabled(), true, "login button is missing after app launch");


}

@Test(priority=2, description="Login with invalid credentials validation")
public void Login_withinvalidcredentials_validation() {

launchscreen.enterUserName(rand.generateRandomName());
launchscreen.enterPassword(rand.generateRandomPassword());
launchscreen.clickLoginButton();
Assert.assertEquals(driver.findElementByXPath("//android.widget.TextView[@text='Username and password do not match any user in this service.']").isDisplayed(), true, "login button is missing after app launch");


}

@Test(priority=3, description="Login with valid credentials validation")
public void Login_withvalidcredentials_validation() {

launchscreen.enterUserName("standard_user");
launchscreen.enterPassword("secret_sauce");
launchscreen.clickLoginButton();
Assert.assertEquals(driver.findElementByXPath("//android.widget.TextView[@text='PRODUCTS']").isDisplayed(), true, "login button is missing after app launch");


}


}
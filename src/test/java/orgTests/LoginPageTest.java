package orgTests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import Annexure.browserSelection;
import PageModels.Login;

public class LoginPageTest extends browserSelection {

	public Login login;
	private String dir = System.getProperty("user.dir");
	private static Logger log = LogManager.getLogger(LoginPageTest.class.getName());

	public LoginPageTest() throws IOException {
		driver = browserCombo();
		login = new Login(driver);
	}

	@Test
	public void loginP() throws IOException {
		System.setProperty("log4j.configurationFile", dir + "\\src\\main\\resources\\resource\\log4j2.xml");
		for (int i = 1; i < 3; i++) {
			String username = readData("Sheet4", "Value.xlsx", i, 0);
			String password = readData("Sheet4", "Value.xlsx", i, 1);
			login.username(username);
			log.info("User entered username");
			login.password(password);
			log.info("User entered password");
			login.submit();
			log.info("User submitted successfully");
			if (login.welcome()) {
				log.info("User logged into system");
				writeData("Sheet4", "Value.xlsx", i, 2, "Test Passed");
				login.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				log.error("User will click the welcome button");
				login.welcomeClick(); 
				log.debug("User will click the logout");
				login.logout();
				log.info("User loggedout from system successfully");
			} else {
				log.info("User is not logged into system");
				login.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				writeData("Sheet4", "Value.xlsx", i, 2, "Test Failed");
			}
		}
	}

	@AfterTest
	public void tearDown() {
		login.driver.close();
		login.driver = null;
	}
}

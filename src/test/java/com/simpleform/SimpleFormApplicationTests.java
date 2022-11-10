package com.simpleform;

//import org.junit.jupiter.api.Test;
import com.simpleform.model.UsersModel;
import com.simpleform.pages.*;
import com.simpleform.repository.UsersRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleFormApplicationTests {
	private static final String BASE_URL = "http://127.0.0.1";
	@LocalServerPort
	private int port;
	protected WebDriver driver;
	protected HomePage homePage;

	@Autowired
	private UsersRepository userRepository;


	@BeforeClass
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void beforeTest() {
		driver = new ChromeDriver();
		homePage = new HomePage(driver);
		driver.get(BASE_URL + ":" + port);
	}

	@After
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testSuccessfulRegister() {
		RegisterPage registerPage = homePage.clickRegistrationNavigation();
		String username = RandomStringUtils.random(8,true,true);
		registerPage.enterUserNameInputField(username);
		registerPage.enterPasswordInputField(RandomStringUtils.random(8,true,true));
		registerPage.enterEmailInputField(RandomStringUtils.random(6,true,true) + "@gmail.com");

		registerPage.clickRegisterButtonForSuccessfulRegister();
		assertEquals(driver.getTitle(), "Login Page");

		assertTrue("User is not added to the database",userRepository.findFirstByUsername(username) != null);
	}

	@Test
	public void testUnsuccessfulRegisterWithExistingUsername() {
		RegisterPage registerPage = homePage.clickRegistrationNavigation();
		registerPage.enterUserNameInputField("eyupbyr");
		registerPage.enterPasswordInputField(RandomStringUtils.random(8,true,true));
		registerPage.enterEmailInputField(RandomStringUtils.random(8,true,true) + "@gmail.com");

		registerPage.clickRegisterButtonForUnsuccessfulRegister();
		assertEquals(driver.getTitle(), "Error Page");
	}

	@Test
	public void testSuccessfulLogin() {
		LoginPage loginPage = homePage.clickLoginNavigation();
		loginPage.enterUserNameInputField("eyupbyr");
		loginPage.enterPasswordInputField("123456789Ab");

		Optional<UsersModel> user = userRepository.findFirstByUsername("eyupbyr");
		assertTrue("User not found", user != null);
		//assertTrue("Password is wrong",user.stream().findFirst().get().getPassword().equals("123456789Ab"));
		assertTrue("Password is wrong",user.get().getPassword().equals("123456789Ab"));


		PersonalPage personalPage = loginPage.clickLoginButtonForSuccesfulLogin();
		assertEquals("Welcome to the personal page eyupbyr", personalPage.getWelcomeText());
	}

	@Test
	public void testUnSuccessfulLoginWithWrongPassword() {
		LoginPage loginPage = homePage.clickLoginNavigation();
		loginPage.enterUserNameInputField("eyupbyr");
		loginPage.enterPasswordInputField("123456789aB");

		Optional<UsersModel> user = userRepository.findFirstByUsername("eyupbyr");
		//assertTrue("User not found", user != null);
		assertFalse("Password is not wrong",user.get().getPassword().equals("123456789aB"));

		ErrorPage errorPage = loginPage.clickLoginButtonForUnuccesfulLogin();
		assertTrue(errorPage.getErrorText().startsWith("You entered incorrect data!"));
	}


	/*
	protected WebDriver driver;
	protected HomePage homePage;

	@BeforeClass
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver","src/test/java/resources/chromedriver.exe");
		//driver = new ChromeDriver();

		homePage = new HomePage(driver);

		driver.get("https://localhost:8081/");
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}*/

}

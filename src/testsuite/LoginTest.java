package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //find signin link and click
        clickOnElement(By.linkText("Sign In"));
        //get text
        String expectedDisplay = "Welcome Back!";
        String actualDisplay = getTextFromElement(By.className("page__heading"));
        //validate message
        Assert.assertEquals("Not Matching", expectedDisplay, actualDisplay);
    }

    @Test
    public void verifyTheErrorMessage() {
        //click on element
        clickOnElement(By.linkText("Sign In"));
        //send text to element
        sendTextToElement(By.id("user[email]"), "123.com");
        sendTextToElement(By.id("user[password]"), "123");

        clickOnElement(By.xpath("//body/main[@id='main-content']/div[1]/div[1]/article[1]/form[1]/div[4]/input[1]"));

        String expectedDisplay = "Invalid email or password.";
        String actualDisplay = getTextFromElement(By.xpath("//li[contains(text(),'Invalid email or password.')]"));
        //validate message
        Assert.assertEquals("Not Matching", expectedDisplay, actualDisplay);

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

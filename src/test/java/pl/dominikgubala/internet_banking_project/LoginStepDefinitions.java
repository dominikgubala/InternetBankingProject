package pl.dominikgubala.internet_banking_project;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginStepDefinitions {
    WebDriver driver;

    public LoginStepDefinitions() {
        driver = new ChromeDriver();
    }

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get("https://demo.guru99.com/V4/");
    }

    @When("They enter a valid username and password")
    public void they_enter_valid_username_and_password() {
        driver.findElement(By.name("uid")).sendKeys("mngr646878");
        driver.findElement(By.name("password")).sendKeys("hyhAsAh");
    }

    @When("They enter a valid username and an invalid password")
    public void they_enter_valid_username_and_invalid_password() {
        driver.findElement(By.name("uid")).sendKeys("mngr646878");
        driver.findElement(By.name("password")).sendKeys("wrongpasswd");
    }

    @And("Click the {string} button")
    public void click_the_button(String buttonText) {
        driver.findElement(By.name("btnLogin")).click();
    }

    @Then("They are redirected to the main page")
    public void they_are_redirected_to_main_page() {
        String expectedText = "Welcome To Manager's Page of Guru99 Bank";
        Assert.assertTrue(driver.getPageSource().contains(expectedText),
                "Expected message not found on main page");
    }

    @Then("They see the message {string}")
    public void they_see_the_message(String expectedMessage) {
        Assert.assertTrue(driver.getPageSource().contains(expectedMessage),
                "Expected message not found on page: " + expectedMessage);
    }

    @Then("They see the error message {string}")
    public void they_see_the_error_message(String expectedAlertMessage) {
        try {
            Alert alert = driver.switchTo().alert();
            Assert.assertEquals(alert.getText(), expectedAlertMessage);
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.assertTrue(driver.getPageSource().contains(expectedAlertMessage),
                    "Expected message not found on page or alert: " + expectedAlertMessage);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

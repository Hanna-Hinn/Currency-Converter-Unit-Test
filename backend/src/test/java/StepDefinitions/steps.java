package StepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

public class steps {

    private WebDriver driver;
    JavascriptExecutor js;


    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
    }

    @Given("I am on the currency conversion website")
    public void i_am_on_the_currency_conversion_website() {
        // Write code here that turns the phrase above into concrete actions
        setUp();
        driver.get("http://localhost:3000/");

    }
    @When("I enter {string} in the amount field")
    public void i_enter_in_the_amount_field(String string) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id(":r1:")).click();
        driver.findElement(By.id(":r1:")).sendKeys(string);

    }
    @When("I select {string} as the source currency")
    public void i_select_as_the_source_currency() {
        // Write code here that turns the phrase above into concrete actions
        {
            WebElement element = driver.findElement(By.xpath("//div[@id=\'card\']/div[2]/div/div[2]/div/div"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(4)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(147)")).click();
    }
    @When("I select {string} as the target currency")
    public void i_select_as_the_target_currency() {
        // Write code here that turns the phrase above into concrete actions
        {
            WebElement element = driver.findElement(By.xpath("//div[@id=\'card\']/div[2]/div/div[3]/div/div"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(7)"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(62)")).click();
    }
    @When("I click the exchange button")
    public void i_click_the_exchange_button() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id(":r5:")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".MuiButton-contained"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".MuiButton-contained")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
    }

    @When("i choose option")
    public void i_choose_option() {
        // Write code here that turns the phrase above into concrete actions
        {
            WebElement element = driver.findElement(By.name("controlled-radio-buttons-group"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.name("controlled-radio-buttons-group")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".MuiButton-contained")).click();
    }

    @Then("I Should Quit the Website")
    public void i_should_quit_the_website() {
        // Write code here that turns the phrase above into concrete actions
       driver.quit();
    }

}

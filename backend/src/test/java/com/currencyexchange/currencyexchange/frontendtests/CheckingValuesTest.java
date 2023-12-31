package com.currencyexchange.currencyexchange.frontendtests;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class CheckingValuesTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void checkingthevalues() {
    driver.get("http://localhost:3000/");
    driver.manage().window().setSize(new Dimension(974, 1032));
    driver.findElement(By.id(":r1:")).click();
    driver.findElement(By.id(":r1:")).sendKeys("1000");
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
}

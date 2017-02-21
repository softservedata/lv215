package com.softserve.edu.schedule.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {

	//@Test
	public void first() throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver",
//				"C:\\Program Files (x86)\\Mozilla Firefox\\drivers\\geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//
		driver.get("http://schedule-lv215.herokuapp.com");
		driver.findElement(By.cssSelector("#ch1_chosen > a.chosen-single > span")).click();
		WebElement search = driver.findElement(By.cssSelector("div.chosen-drop > div.chosen-search > input"));
		search.sendKeys("Room 202"+Keys.ENTER);
		//search.click();
		//Thread.sleep(5000);
		driver.findElement(By.cssSelector("button.fc-prev-button.fc-button.fc-state-default.fc-corner-left")).click();
		//driver.findElement(By.xpath("//button[@class='fc-prev-button fc-button fc-state-default fc-corner-left']")).click();
		//
		Thread.sleep(2000);
		driver.quit();
	}

	@Test
	public void signin() throws InterruptedException {
//		System.setProperty("webdriver.gecko.driver",
//				"C:\\Program Files (x86)\\Mozilla Firefox\\drivers\\geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//
		driver.get("http://schedule-lv215.herokuapp.com");
		// Goto Sign in
		driver.findElement(By.xpath("//a[contains(@href,'/login')]")).click();
		//
		driver.findElement(By.id("username")).sendKeys("obutyter@gmail.com");
		Thread.sleep(2000);
		//WebElement login = driver.findElement(By.id("username"));
		//login.sendKeys("HAHAHAHAHAHAHA");
		//Thread.sleep(2000);
		//driver.navigate().refresh();
		//login.sendKeys("obutyter@gmail.com");
		//
		driver.findElement(By.id("password")).sendKeys("Qwerty!1");
		driver.findElement(By.id("password")).submit();
		//
		Thread.sleep(5000);
		driver.quit();
	}

}

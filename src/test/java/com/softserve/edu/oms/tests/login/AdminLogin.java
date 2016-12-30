package com.softserve.edu.oms.tests.login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLogin {
	public static final Logger logger = LoggerFactory.getLogger(AdminLogin.class);
	
	@Test
	public void testAdminLogin() throws Exception {
		logger.info("@Test testAdminLogin(...) Start");
		System.out.println("surefire.reports.directory: "
				+ System.getProperty("surefire.reports.directory"));
		System.out.println("surefire.webdriver.ci: "
				+ System.getProperty("surefire.webdriver.ci"));
		//
//		System.setProperty("webdriver.chrome.driver",
//				this.getClass().getResource("/drivers/chromedriver.exe").getPath().substring(1));
//		WebDriver driver = new ChromeDriver();
		//
		WebDriver driver = new HtmlUnitDriver(true);
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		//
//		System.setProperty("webdriver.chrome.driver",
//				this.getClass().getResource("/drivers/phantomjs.exe").getPath().substring(1));
//        WebDriver driver = new PhantomJSDriver();
        //
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.get("http://java.training.local:8080/OMS/");
		driver.get("http://ssu-oms.training.local:8180/OMS/");
		System.out.println("URL done");
		Thread.sleep(2000);
		//
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys("iva");
		System.out.println("login done");
		//
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("qwerty");
		System.out.println("password done");
		driver.findElement(By.name("submit")).click();
		System.out.println("submit done");
		Thread.sleep(2000);
		//
        //File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(scrFile, new File("./screenshot2.png"));
		Assert.assertEquals("ivanka", driver.findElement(
				By.xpath("//div[@id='content']/div/fieldset/table/tbody/tr/td[2]")).getText());
		Assert.assertEquals("horoshko", driver.findElement(
				By.xpath("//div[@id='content']/div/fieldset/table/tbody/tr[2]/td[2]")).getText());
		Assert.assertEquals("Administrator", driver.findElement(
				By.xpath("//div[@id='content']/div/fieldset/table/tbody/tr[4]/td[2]")).getText());
		driver.findElement(By.cssSelector("img[alt=\"logout\"]")).click();
		System.out.println("all Asserts done");
		Thread.sleep(2000);
		driver.quit();
		logger.info("@Test testAdminLogin(...) Done");
	}

}

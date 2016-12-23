package com.softserve.edu.oms.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.softserve.edu.oms.pages.LoginPage;

public class TestRunner {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    public void oneTimeSetUp() {

        System.out.println("before");
        final String driverPath = "src/test/resources/drivers/";
        final String loginPageUrl= System.getenv("oms_loginPageUrl");
        System.out.println("+++url = " + System.getenv("oms_loginPageUrl"));
        if (System.getProperty("surefire.webdriver.ci").equals("HtmlUnitDriver")) {
        	driver = new HtmlUnitDriver(true);
    		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
//    		System.setProperty("webdriver.chrome.driver",
//    				this.getClass().getResource("/drivers/phantomjs.exe").getPath().substring(1));
//    		driver = new PhantomJSDriver();
        } else {
	        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
	        driver = new ChromeDriver();
        }
        driver
                .manage()
                .timeouts()
                .implicitlyWait(30, TimeUnit.SECONDS);
        driver
                .manage()
                .window()
                .maximize();


        driver.get(loginPageUrl);
        loginPage = new LoginPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void oneTimeTearDown(){ 
        driver.quit();
    }
    
}


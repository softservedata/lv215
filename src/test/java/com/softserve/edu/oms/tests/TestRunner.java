package com.softserve.edu.oms.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.softserve.edu.oms.pages.LoginPage;
import java.util.concurrent.TimeUnit;

public class TestRunner {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    public void oneTimeSetUp() {

        System.out.println("before");
        final String driverPath = "src/test/resources/drivers/";
        final String loginPageUrl= System.getenv("oms_loginPageUrl");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");

        driver = new ChromeDriver();
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


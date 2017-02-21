package com.softserve.edu.schedule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage {

    // Fields
	private WebDriver driver;
	public final String SIGNIN_TEXT = "Sign in";
    //
    private WebElement signin;

	public StartPage(WebDriver driver) {
        this.driver = driver;
        this.signin = driver.findElement(By.xpath("//a[contains(@href,'/login')]"));
    }

	// get Data

	public WebElement getSigninLink() {
		return this.signin;
	}

	// Functional

	public String getSigninLinkText() {
		return getSigninLink().getText().trim();
	}

	// set Data

	public void clickSigninLink() {
		getSigninLink().click();
	}

	// Business Logic

	public LoginPage gotoLogin() {
		clickSigninLink();
		return new LoginPage(driver);
	}

}

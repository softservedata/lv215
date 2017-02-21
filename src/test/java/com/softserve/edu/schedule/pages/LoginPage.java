package com.softserve.edu.schedule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.schedule.data.IUser;

public class LoginPage {

	// Fields
	private WebDriver driver;
	//
	private WebElement username;
	private WebElement password;
	private WebElement signin;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.username = driver.findElement(By.id("username"));
		this.password = driver.findElement(By.id("password"));
		this.signin = driver.findElement(By.cssSelector("input.btn.btn-default"));
	}

	// get Data

	public WebElement getUsernameInput() {
		return this.username;
	}

	public WebElement getPasswordInput() {
		return this.password;
	}

	public WebElement getSignin() {
		return this.signin;
	}

	// Functional

	public String getUsernameInputText() {
		return getUsernameInput().getText();
	}

	public String getPasswordInputText() {
		return getPasswordInput().getText();
	}

	public String getSignintText() {
		return getSignin().getText().trim();
	}

	// set Data

	public void setUsernameInput(String username) {
		getUsernameInput().sendKeys(username);
	}

	public void setPasswordInput(String password) {
		getPasswordInput().sendKeys(password);
	}

	public void clearUsernameInput() {
		getUsernameInput().clear();
	}

	public void clearPasswordInput() {
		getPasswordInput().clear();
	}

	public void clickUsernameInput() {
		getUsernameInput().click();
	}

	public void clickPasswordInput() {
		getPasswordInput().click();
	}

	public void clickSignin() {
		getSignin().click();
	}

	// Business Logic

	public void setUsernameInputClear(String username) {
		clearUsernameInput();
		setUsernameInput(username);
	}

	public void setPasswordInputClear(String password) {
		clearPasswordInput();
		setPasswordInput(password);
	}

	// Functional Business Logic

	private void setLoginData(IUser user) {
		setUsernameInputClear(user.getUsername());
		setPasswordInputClear(user.getPassword());
		clickSignin();
	}

	public AdminHomePage successAdminLogin(IUser admin) {
		setLoginData(admin);
		// Return a new page object representing the destination.
		return new AdminHomePage(driver);
	}

	public CustomerHomePage successCustomerLogin(IUser customer) {
		setLoginData(customer);
		// Return a new page object representing the destination.
		return new CustomerHomePage(driver);
	}

	public LoginValidatorPage unsuccessfulLogin(IUser invalidUser) {
		setLoginData(invalidUser);
		return new LoginValidatorPage(driver); // return this;
	}

}

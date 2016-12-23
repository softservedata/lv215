package com.softserve.edu.oms.tests.olavr;

/**
 * Created by User on 13.12.2016.
 */
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.User;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.HomePage;
import com.softserve.edu.oms.tests.TestRunner;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import com.softserve.edu.oms.pages.UserInfoPage;
import com.softserve.edu.oms.pages.LoginPage;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;

public class RememberMeTest extends TestRunner {
    private SoftAssert softAssert = new SoftAssert();

    @Test
    public void rememberMeTest() {
        IUser user= UserRepository.get().adminUser();
        Assert.assertEquals(loginPage.getLoginnameInputText(), "");
        Assert.assertEquals(loginPage.getPasswordInputText(), "");
        loginPage.setLoginnameInput(user.getLoginname());
        loginPage.setPasswordInput(user.getPassword());
        loginPage.clickgetRememberMeCheckbox();
        loginPage.clickSubmitButton();
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getFirstnameText(), user.getFirstname());
        Assert.assertEquals(homePage.getLastnameText(), user.getLastname());
        homePage.clickLogoutButton();
        loginPage = new LoginPage(driver);
        softAssert.assertEquals(loginPage.getLoginnameInputText(),user.getLoginname());
        softAssert.assertEquals(loginPage.getPasswordInputText().length(),user.getPassword().length());
        softAssert.assertAll();

    }
}


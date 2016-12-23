package com.softserve.edu.oms.expages;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.HomePage;
import com.softserve.edu.oms.pages.LoginPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.softserve.edu.oms.enums.ErrorMessagesEnum.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPageTest extends TestRunner {

    private SoftAssert softAssert = new SoftAssert();

    /**
     * DataProvider for verification 'Reset' button functionality
     * @return user from UserRepository
     */
    @DataProvider
    public Object[][] someUser() {
        return new Object[][]{
                {UserRepository.get().invalidUser()}
        };
    }

    /**
     * Provides not register user login and password
     * @return user who is not registered in system from UserRepository
     */
    @DataProvider
    public Object[][] notExistUser() {
        return new Object[][] {
                {UserRepository.get().nonExistingUser() }
        };
    }


    @Test
    public void loginWithEmptyCredentials() {
        String currentErrorMessage = loginPage
                .loginWithEmptyCredentials()
                .getBadCredentialsErrorMessageText();

        assertThat(currentErrorMessage, CoreMatchers.equalTo(ERROR_MESSAGE.message));
    }

    /**
     * Test verifies that error message is shown when user tries to login without
     * being registered in the system
     *
     * Based on LVSETOMS-29 in Jira
     *
     * @author Dmytro Voropai
     * @param notExistUser {@link com.softserve.edu.oms.data.UserRepository}
     */
    @Test(dataProvider = "notExistUser", alwaysRun = true)
    public void verifyResetButtonFunctionalityForNonRegisteredUser(IUser notExistUser) {
//      Check if Object of String error message is not null.
        Assert.assertNotNull(loginPage.unsuccessfulLogin(notExistUser)
                .getBadCredentialsErrorMessageText());
//      Check if error message is the same as was expected
        Assert.assertEquals(loginPage.unsuccessfulLogin(notExistUser)
                .getBadCredentialsErrorMessageText(), EXPECTED_ERROR_MESSAGE_TC29.message);
    }

    /**
     * This test verifies that entered values in 'User' and 'Password' fields
     * are cleared by clicking on 'Reset' button
     *
     * Based on LVSETOMS-37 in Jira
     *
     * @author Iryna Kyselchuk
     * @since 16.12.16
     * @param someUser {@link com.softserve.edu.oms.data.UserRepository}
     */
    @Test(dataProvider = "someUser")
    public void verifyResetButtonFunctionality(IUser someUser) {

        loginPage.setLoginDataAndReset(someUser);
        Assert.assertTrue(loginPage
                .getLoginnameInputText()
                .isEmpty());
        Assert.assertTrue(loginPage
                .getPasswordInputText()
                .isEmpty());

    }
    /**
     * Verify that after check "Remember me" checkbox
     * entered login and password save in input fields
     * after logout
     *
     * @author Oleh Lavrynenko
     * @version 1.0
     * @since 16.12.16
     */
    @Test
    public void rememberMeTest() {
        IUser user = UserRepository.get().adminUser();

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

        softAssert.assertEquals(loginPage.getLoginnameInputText(), user.getLoginname());
        softAssert.assertEquals(loginPage.getPasswordInputText().length(), user.getPassword().length());
        softAssert.assertAll();

    }


}

package com.softserve.edu.oms.tests.createuser;

import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.softserve.edu.oms.enums.ErrorMessagesEnum.CONFIRM_PASSWORD_ERROR_MESSAGE;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This test verifies that error message is shown
 * when creating new user with not confirmed password
 *
 * Based on LVSETOMS-58 in Jira
 *
 * @author Iryna Kyselchuk
 * @since 16.12.16
 */
public class ErrorMsgConfirmPasswordTest extends TestRunner{

    private AdminHomePage adminHomePage;
    private AdministrationPage administrationPage;
    private CreateNewUserPage createNewUserPage;
    private DBUtils dbUtils;

    /**
     * Provides data for user login
     * @return badMemoryUser from UserRepository
     */
    @DataProvider
    public Object[][] badMemoryUser() {
        return new Object[][] {
                { UserRepository.get().badMemoryUser() }
        };
    }

    /**
     * Set preconditions for test:
     * login with Administrator role credentials
     * and navigate to Create New User page
     */
    @BeforeMethod
    public void loginAndGotoCreateUserPage() {
        IUser admin = UserRepository.get().adminUser();
        adminHomePage = loginPage.successAdminLogin(admin);
        administrationPage = adminHomePage.gotoAdministrationPage();
        createNewUserPage = administrationPage.gotoCreateNewUserPage();
    }

    /**
     * This method verifies that error message appears
     * when trying to create a new user with
     * correct values in 'Login Name', 'First Name', 'Last Name', 'Email Address' fields
     * and different values in 'Password' and 'Confirm Password' fields
     *
     * @param newUser {@link com.softserve.edu.oms.data.UserRepository}
     */
    @Test(dataProvider = "badMemoryUser")
    @Step("verifyErrorMsgUserWithNotConfirmedPassword")
    public void verifyErrorMsgUserWithNotConfirmedPassword(IUser newUser) {

        dbUtils = new DBUtils();

        // verify that user with chosen login does not exist
        assertThat(dbUtils.getUserByLogin(newUser.getLoginname()), CoreMatchers.equalTo(null));

        // set correct data for new user account
        // valid value in 'Confirm Password' but not the same as in 'Password' field
        createNewUserPage
                .setLoginInput(newUser.getLoginname())
                .setFirstNameInput(newUser.getFirstname())
                .setLastNameInput(newUser.getLastname())
                .setPasswordInput(newUser.getPassword())
                .setConfirmPasswordInput(newUser.getPassword().toUpperCase())
                .setEmailInput(newUser.getEmail())
                .clickCreateButton();
        createNewUserPage.acceptAlert();

        // verify that correct error message appears
        Assert.assertTrue(createNewUserPage.getConfirmPasswordErrorMessage().isDisplayed()
                && createNewUserPage.getConfirmPasswordErrorMessageText().equals(CONFIRM_PASSWORD_ERROR_MESSAGE.message));

        // verify that user with invalid confirm password is not created
        assertThat(dbUtils.getUserByLogin(newUser.getLoginname()), CoreMatchers.equalTo(null));
    }

    /**
     * Logout from current page
     */
    @AfterMethod
    public void returnToPreviousState() {
        createNewUserPage.logout();
    }
}

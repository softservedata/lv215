package com.softserve.edu.oms.expages;

import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.User;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.Region;
import com.softserve.edu.oms.enums.Role;
import com.softserve.edu.oms.enums.SQLQueries;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.tests.TestRunner;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Step;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.oms.enums.ErrorMessagesEnum.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateNewUserPageTest extends TestRunner {

    private AdminHomePage adminHomePage;
    private AdministrationPage administrationPage;
    private CreateNewUserPage createNewUserPage;
    private DBUtils dbUtils;
    private int pagesCount;
    private int numberUsers;

    final private IUser userWithLongCredentials= UserRepository.get().UserWithLongCredentials();



    @DataProvider
    public Object[][] validUser() {
        return new Object[][]{{UserRepository.get().userForDelete()},
        };
    }

    @DataProvider
    public Object[][] invalidUsers() {
        return new Object[][]{
                {UserRepository.get().invalidUser()}
        };
    }

    @DataProvider
    public Object[][] adminUser() {
        return new Object[][]{
                {UserRepository.get().adminUser()}
        };
    }

    /**
     * DataProvider for verification creating new user with not confirmed password
     * @return badMemoryUser from UserRepository
     */
    @DataProvider
    public Object[][] badMemoryUser() {
        return new Object[][]{
                {UserRepository.get().badMemoryUser()}
        };
    }

    @DataProvider
    public Object[][] nonExistingUser() {
        return new Object[][]{
                {UserRepository.get().nonExistingUser()}
        };
    }

    /**
     * Provides login and password of registered user
     * with an "Administrator" role
     * @return user from UserRepository
     */
    @DataProvider
    public Object[][] validUserAdministrator() {
        return new Object[][] {
                {UserRepository.get().adminUser()}
        };
    }

    /**
     * This test case verifies that error messages appear when trying to create
     * a new user with too long login, first /last name (longer than 13 characters)
     * and password (longer than 10 character
     *
     * Based on LVSETOMS-51 in Jira
     *
     * @author Dmytro Voropai
     * @param validUserAdministrator {@link com.softserve.edu.oms.data.UserRepository}
     */
    @Test(dataProvider = "validUserAdministrator",alwaysRun = true)
    public void verifyCreateNewUserWithTooLognData(IUser validUserAdministrator){
        AdminHomePage omsAdminHomePage = loginPage.successAdminLogin(validUserAdministrator);
        CreateNewUserPage omsСreateNewUserPage = omsAdminHomePage
                .gotoAdministrationPage()
                .gotoCreateNewUserPage()
                .setLoginData(userWithLongCredentials)
                .clickCreateButton()
                .acceptAlert();

//        Check if correct messages appeared for required fields
        Assert.assertEquals(omsСreateNewUserPage.getLoginErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_lOGIN_TC51.message);
        Assert.assertEquals(omsСreateNewUserPage.getFirstNameErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_FIRST_NAME_TC51.message);
        Assert.assertEquals(omsСreateNewUserPage.getLastNameErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_LAST_NAME_TC51.message);
        Assert.assertEquals(omsСreateNewUserPage.getPasswordErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_PASSWORD_TC51.message);

//        Check if user with too long credentials was not created in database
        Assert.assertNull(dbUtils.getUserByLogin(userWithLongCredentials.getLoginname()));
    }

    /**
     * Test verifies that validation on empty mandatory fields works while
     * creating new user
     *
     * Based on LVSETOMS-49 in Jira
     *
     * @author Dmytro Voropai
     * @param validUserAdministrator {@link com.softserve.edu.oms.data.UserRepository}
     */
    @Test(dataProvider = "validUserAdministrator", alwaysRun = true)
    public void verifyErrorMessagesDuringUserCreation(IUser validUserAdministrator) {
        AdminHomePage omsAdminHomePage = loginPage.successAdminLogin(validUserAdministrator);
        CreateNewUserPage omsСreateNewUserPage = omsAdminHomePage
                .gotoAdministrationPage()
                .gotoCreateNewUserPage()
                .clickCreateButton()
                .acceptAlert();

        //  Assert that messages appear on page for all mandatory fields
        Assert.assertNotNull(omsСreateNewUserPage.getLoginErrorMessageText());
        Assert.assertNotNull(omsСreateNewUserPage.getFirstNameErrorMessageText());
        Assert.assertNotNull(omsСreateNewUserPage.getLastNameErrorMessageText());
        Assert.assertNotNull(omsСreateNewUserPage.getPasswordErrorMessageText());
        Assert.assertNotNull(omsСreateNewUserPage.getEmailErrorMessageText());

        //  Compare existing messages with expected
        Assert.assertEquals(omsСreateNewUserPage.getLoginErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_lOGIN_TC49.message);
        Assert.assertEquals(omsСreateNewUserPage.getFirstNameErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_FIRST_NAME_TC49.message);
        Assert.assertEquals(omsСreateNewUserPage.getLastNameErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_LAST_NAME_TC49.message);
        Assert.assertEquals(omsСreateNewUserPage.getPasswordErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_PASSWORD_TC49.message);
        Assert.assertEquals(omsСreateNewUserPage.getEmailErrorMessageText(),
                EXPECTED_ERROR_MESSAGE_FOR_EMAIL_ADDRESS_TC49.message);
    }

    @Test(dataProvider = "invalidUsers")
    @Step("createInvalidNewUserTest")
    public void createInvalidNewUserTest(User user) {
        IUser admin = UserRepository.get().adminUser();
        AdministrationPage administrationPage = loginPage
                .successAdminLogin(admin)
                .gotoAdministrationPage();
        CreateNewUserPage createNewUserPage =
                administrationPage.gotoCreateNewUserPage();
        createNewUserPage.setLoginData(user);
        driver.switchTo().alert().accept();

        assertThat(createNewUserPage.getFirstNameErrorMessageText(),
                CoreMatchers.equalTo(FIRST_NAME_ERROR_MESSAGE.message));
        assertThat(createNewUserPage.getLastNameErrorMessageText(),
                CoreMatchers.equalTo(LAST_NAME_ERROR_MESSAGE.message));
        DBUtils dbUtils = new DBUtils();
        assertThat(dbUtils.getUserByLogin(user.getLoginname()), CoreMatchers.equalTo(null));
    }

    /**
     * Verify that a new user isn't created after
     * click "Cancel" button on Create New User Page
     *
     * @author Oleh Lavrynenko
     * @version 1.0
     * @since 16.12.16
     */
    @Test
    @Step("cancelCreateUserTest")
    public void cancelCreateUserTest() {
        IUser user = UserRepository.get().adminUser();
        AdminHomePage adminHomePage = new AdminHomePage(driver);
        AdministrationPage adminPage = new AdministrationPage(driver);
        DBUtils dbUtils = new DBUtils();

        Assert.assertEquals(loginPage.getLoginnameInputText(), "");
        Assert.assertEquals(loginPage.getPasswordInputText(), "");
        loginPage.setLoginnameInput(user.getLoginname());
        loginPage.setPasswordInput(user.getPassword());
        loginPage.clickSubmitButton();

        adminHomePage.clickAdministrationTab();
        CreateNewUserPage createPage = adminPage.gotoCreateNewUserPage();

        user = UserRepository.get().invalidUser();
        Assert.assertNull(dbUtils.getUserByLogin(user.getLoginname()));

        createPage.setLoginInput(user.getLoginname())
                .setFirstNameInput(user.getFirstname())
                .setLastNameInput(user.getLastname())
                .setEmailInput(user.getEmail())
                .setPasswordInput(user.getPassword())
                .setConfirmPasswordInput(user.getPassword())
                .clickCancelButton();
        Assert.assertNull(dbUtils.getUserByLogin(user.getLoginname()));
    }

    /**
     * just for login
     * @param admUser
     */
//    @Test(dataProvider = "adminUser")
//    public void PreconditionTest(IUser admUser) {
//
//        CreateNewUserPage adminHomePage = loginPage.successAdminLogin(admUser)
//                .clickAdministrationTab()
//                .gotoCreateNewUserPage();
//    }

    /**
     * <h1>Verify that Login field is case insensitive</h1>
     * This test goes to Create New User Page,
     * 1) executes SQL query to verify that nonExistingUser from UserRepository
     * really is not in DB
     * 2) If he, for some reason, exists - then generates new random Login
     * 3) Then the data for nonExistingUser are set in the form "Create new user"
     * 4) the fields "Role" and "Region" are not used, and leave as default
     * 5) the same Login, but in reverse case is set in Login field
     * 6) error message should appear - if so test passed
     * <p/>
     * <p>Note: there are two data providers and thus two tests, because
     * first we login as Admin and then trying to create New User</p>
     *
     * @param nonExistingUser
     * @author Viktoriia Bybel
     * @version 1.0
     * @see UserRepository
     * @since 15.12.16
     */

    @Test(dataProvider = "nonExistingUser", dependsOnMethods = "PreconditionTest")
    @Step("UniqueUserCreatingTest")
    public void UniqueUserCreatingTest(IUser nonExistingUser) {

        DBUtils dbUtils = new DBUtils();

        String nonExistingLogin = nonExistingUser.getLoginname();

        while (dbUtils.verifyThatUserIsInDB(nonExistingLogin)) {
            nonExistingLogin = RandomStringUtils.random(5, true, false).toLowerCase();
        }

        CreateNewUserPage newUserPage = new CreateNewUserPage(driver);
        newUserPage
                .setLoginInput(nonExistingLogin)
                .setFirstNameInput(nonExistingUser.getFirstname())
                .setLastNameInput(nonExistingUser.getLastname())
                .setPasswordInput(nonExistingUser.getPassword())
                .setConfirmPasswordInput(nonExistingUser.getPassword())
                .setEmailInput(nonExistingUser.getEmail())
                .clickCreateButton();

        if (!(dbUtils.verifyThatUserIsInDB(nonExistingLogin))) {

            CreateNewUserPage newUserPageAgain = new AdministrationPage(driver)
                    .gotoCreateNewUserPage().setLoginInput(nonExistingLogin.toUpperCase());

           // Assert.assertTrue(newUserPageAgain.getLoginErrorMessageText().contains("already in use"));

            dbUtils.deleteUsersFromDB(SQLQueries.DELETE_USER_BY_LOGIN.getQuery(),
                    nonExistingUser.getLoginname());
        }
    }

    /**
     * Set preconditions for tests: login as Administrator role
     * and navigate to Create New User page
     */
    @BeforeMethod
    public void setTestPreconditions() {
        IUser admin = UserRepository.get().adminUser();
        adminHomePage = loginPage.successAdminLogin(admin);
        administrationPage = adminHomePage.gotoAdministrationPage();
        createNewUserPage = administrationPage.gotoCreateNewUserPage();
    }

    /**
     * This test verifies that error message appears
     * when trying to create a new user with
     * different values in 'Password' and 'Confirm Password' fields
     *
     * Based on LVSETOMS-58 in Jira
     *
     * @author Iryna Kyselchuk
     * @since 16.12.16
     * @param newUser {@link com.softserve.edu.oms.data.UserRepository}
     */
    @Test(dataProvider = "badMemoryUser")
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


    /**
     * Verify that values in 'Number of found users' and 'Page#:' links
     * are properly updated after creation a new user.
     *
     * @author Roman Raba
     * @version 1.0
     * @since 16.12.16
     */
    @Test(dataProvider = "validUser")
    @Step("verifyChangePageNumber")
    public void verifyChangePageNumber(IUser user) {

        int newNumberOfUsers;
        int newPagesCount;

        verifyAndCreateUsers();

        CreateNewUserPage createNewUserPage =
                administrationPage.gotoCreateNewUserPage();

        administrationPage = createNewUserPage.successCreateNewUser(user);
        newNumberOfUsers = Integer.valueOf(administrationPage.getFoundUsersNumber());
        newPagesCount = Integer.valueOf(administrationPage.getPagesQuantity());
        Assert.assertEquals(numberUsers + 1, newNumberOfUsers);
        Assert.assertEquals(pagesCount + 1, newPagesCount);

        deleteUsersFromDB();
    }


    /**
     * Verify that number of active registered users is aliquot to 5.
     * And creates necessary number of users.
     *
     * @author Roman Raba
     * @version 1.0
     * @see CreateNewUserPageTest#verifyChangePageNumber(IUser)
     * @since 16.12.16
     */
    public void verifyAndCreateUsers() {
        int numberOfImems;

        IUser user = UserRepository.get().someUser();

        AdminHomePage adminHomePage =
                loginPage.successAdminLogin(UserRepository.get().adminUser());
        administrationPage =
                adminHomePage.gotoAdministrationPage();
        numberUsers = administrationPage.getFoundUsersNumber();
        pagesCount = administrationPage.getPagesQuantity();

        numberOfImems = administrationPage.getUsersPerPageNumber();

        if ((numberUsers % numberOfImems) != 0) {
            for (int i = 0; i < (numberOfImems - (numberUsers % numberOfImems)); i++) {
                CreateNewUserPage createNewUserPage =
                        administrationPage.gotoCreateNewUserPage();

                createNewUserPage.setLoginInput(user.getLoginname() + user.getLoginname().charAt(i)).
                        setFirstNameInput(user.getFirstname()).
                        setLastNameInput(user.getLastname()).
                        setPasswordInput(user.getPassword()).
                        setConfirmPasswordInput(user.getPassword()).
                        setEmailInput(user.getEmail()).
                        setSelectRegion(Region.getRegion(user.getRegion())).
                        setSelectRole(Role.valueOf(user.getRole().toUpperCase()));

                administrationPage = createNewUserPage.successCreateNewUser();
            }
            numberUsers = numberUsers + (numberOfImems - (numberUsers % numberOfImems));
        }
    }


    /**
     * Deletes users from DB after creation in test.
     *
     * @author Roman Raba
     * @version 1.0
     * @see CreateNewUserPageTest#verifyChangePageNumber(IUser)
     * @since 16.12.16
     */
    public void deleteUsersFromDB() {
        DBUtils dbUtils = new DBUtils();
        dbUtils.deleteUsersFromDB(SQLQueries.DELETE_USERS_BY_FIRSTNAME.getQuery(),
                UserRepository.get().someUser().getFirstname());
    }


}

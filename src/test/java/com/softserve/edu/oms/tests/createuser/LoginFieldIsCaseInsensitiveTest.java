    package com.softserve.edu.oms.tests.createuser;

    import com.softserve.edu.oms.data.DBUtils;
    import com.softserve.edu.oms.data.IUser;
    import com.softserve.edu.oms.data.UserRepository;
    import com.softserve.edu.oms.enums.SQLQueries;
    import com.softserve.edu.oms.pages.AdministrationPage;
    import com.softserve.edu.oms.pages.CreateNewUserPage;
    import com.softserve.edu.oms.tests.TestRunner;
    import org.apache.commons.lang3.RandomStringUtils;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;
    import ru.yandex.qatools.allure.annotations.Step;

    public class LoginFieldIsCaseInsensitiveTest extends TestRunner{

    @DataProvider
    public Object[][] admAndNonExistingUser() {
        return new Object[][] {
                { UserRepository.get().adminUser(), UserRepository.get().nonExistingUser() }
        };
    }

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
     *
     * <p>Note: there are two data providers and thus two tests, because
     * first we login as Admin and then trying to create New User</p>
     *
     * @author Viktoriia Bybel
     * @version 1.0
     * @since 15.12.16
     * @param nonExistingUser
     * @see UserRepository
     */

    @Test(dataProvider = "admAndNonExistingUser")
    @Step("LoginFieldIsCaseInsensitiveTest")
    public void LoginFieldIsCaseInsensitiveTest(IUser admUser, IUser nonExistingUser) {

        //login and go to addUser.html
        CreateNewUserPage adminHomePage = loginPage.successAdminLogin(admUser)
                .clickAdministrationTab()
                .gotoCreateNewUserPage();

        //verifying that user do not exist or generating a new one
        DBUtils dbUtils = new DBUtils();
        String nonExistingLogin = nonExistingUser.getLoginname();
        while (dbUtils.verifyThatUserIsInDB(nonExistingLogin)) {
            nonExistingLogin = RandomStringUtils.random(5, true, false).toLowerCase();
        }

        //set up a form and creating a new user
        CreateNewUserPage newUserPage = new CreateNewUserPage(driver);
        newUserPage
                .waitForLoad()
                .setLoginInput(nonExistingLogin)
                .setFirstNameInput(nonExistingUser.getFirstname())
                .setLastNameInput(nonExistingUser.getLastname())
                .setPasswordInput(nonExistingUser.getPassword())
                .setConfirmPasswordInput(nonExistingUser.getPassword())
                .setEmailInput(nonExistingUser.getEmail())
                .clickCreateButton()
                .acceptAlert();

        //entering the data to verify that error message will appear
        CreateNewUserPage newUserPageAgain = new AdministrationPage(driver)
                .gotoCreateNewUserPage()
                .setLoginInput(nonExistingLogin.toUpperCase())
                .setFirstNameInput(nonExistingUser.getFirstname())
                .setLastNameInput(nonExistingUser.getLastname())
                .setPasswordInput(nonExistingUser.getPassword())
                .setConfirmPasswordInput(nonExistingUser.getPassword())
                .setEmailInput(nonExistingUser.getEmail());

        Assert.assertTrue(newUserPageAgain.getLoginError());

        //delete created user from DB
        dbUtils.deleteUsersFromDB(SQLQueries.DELETE_USER_BY_LOGIN.getQuery(),
                nonExistingUser.getLoginname());

        }

    }

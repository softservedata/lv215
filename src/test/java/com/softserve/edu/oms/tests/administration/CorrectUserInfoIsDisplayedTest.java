package com.softserve.edu.oms.tests.administration;

import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.User;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

public class CorrectUserInfoIsDisplayedTest extends TestRunner{

    @DataProvider
    public Object[][] admUser() {
        return new Object[][] {
                { UserRepository.get().adminUser() }
        };
    }

    /**
     * <h1>Correct info on Administation Page is displayed</h1>
     * This test goes to Administration Page
     * 1) reads the quantity of found users,
     * 2) executes the SQL query for finding the quantity of user from DB
     * 3) compares these two parameters
     * 4) then reads all rows from the table on page
     * 5) executes the SQL query to read the same from DB
     * 6) compares the two lists created in 4) and 5)
     *
     *<p>Note: the quantity of rows is 5 not 10 rows</p>
     *
     * TC45
     *
     * @author Viktoriia Bybel
     * @version 1.0
     * @since 15.12.16
     * @param admUser
     */
    @Test(dataProvider = "admUser")
    @Step("CorrectUserInfoIsDisplayedTest")
    public void CorrectUserInfoIsDisplayedTest(IUser admUser) {

        //log in and go to users.html
        AdministrationPage administrationPage = loginPage
                .successAdminLogin(admUser)
                .gotoAdministrationPage();

        //read a number of found users from page
        int numberOfFoundUsersFromPage = administrationPage
                .waitForLoad()
                .getFoundUsersNumber();

        //read a number of found users from DB
        DBUtils dbUtils = new DBUtils();
        int numberOfFoundUsersFromDB = dbUtils.countAllUsers();

        //verify that they are equal
        Assert.assertEquals(numberOfFoundUsersFromDB, numberOfFoundUsersFromPage);

        //read all users data displayed on page
        List<User> usersFromPage = administrationPage
                .waitForLoad()
                .getUsersFromCurrentPage();

        //read top 5 users from DB
        List<User> usersFromDB = dbUtils.getTopFiveUsers();

        //verify that they are the same
        for(int i = 0; i<usersFromPage.size();i++){
            Assert.assertTrue(usersFromDB.get(i).CompareTo(usersFromPage.get(i)));
        }
    }
}

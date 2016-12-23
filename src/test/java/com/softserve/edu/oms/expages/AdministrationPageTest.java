package com.softserve.edu.oms.expages;

import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.User;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.UsersPerPage;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;


public class AdministrationPageTest extends TestRunner{

    private static final String SHOW_ITEMS_LINK_NAME = "Show 10 items";
    private AdminHomePage adminHomePage;
    private AdministrationPage administrationPage;

    /**
     * DataProvider for login and go to Administration page
     * @return valid user with Administrator role from UserRepository
     */
    @DataProvider
    public Object[][] adminUser() {
        return new Object[][] {
                { UserRepository.get().adminUser() }
        };
    }

    @DataProvider
    public Object[][] admUser() {
        return new Object[][] {
                { UserRepository.get().adminUser() }
        };
    }

    @AfterMethod
    public void returnToPreviousState() {
        administrationPage.logout();
    }

    @Test
    public void paginationTest() {
        IUser admin = UserRepository.get().adminUser();
        AdministrationPage administrationPage = loginPage
                .successAdminLogin(admin)
                .gotoAdministrationPage();

        assertThat(administrationPage.getQuantityOfUsersPerPage() ,
                CoreMatchers.equalTo(UsersPerPage.FIVE.getResultsPerPage()));
        assertTrue(administrationPage.showItemsLinkIsDisplayed());
        assertThat(administrationPage.getShowItemsLinkText(),
                CoreMatchers.equalTo(SHOW_ITEMS_LINK_NAME));

        double pagesNumber = administrationPage.getFoundUsersNumber() / (UsersPerPage.FIVE.getResultsPerPage() * 1.0);
        assertThat(administrationPage.getPagesQuantity(), CoreMatchers.equalTo((int)Math.ceil(pagesNumber)));

        administrationPage.changeQuantityOfUsersPerPage();

        assertThat(administrationPage.getQuantityOfUsersPerPage() ,
                CoreMatchers.equalTo(UsersPerPage.TEN.getResultsPerPage()));

        pagesNumber = administrationPage.getFoundUsersNumber() / (UsersPerPage.TEN.getResultsPerPage() * 1.0);
        assertThat(administrationPage.getPagesQuantity(), CoreMatchers.equalTo((int)Math.ceil(pagesNumber)));

    }

    /**
     * This test verifies that Administrator can navigate the User table
     * by 'First', 'Last', 'Forward' and 'Backward' buttons
     * and current page in the pagination label changes according to navigation
     *
     * Based on LVSETOMS-44 in Jira
     *
     * @author Iryna Kyselchuk
     * @since 16.12.16
     * @param admin {@link com.softserve.edu.oms.data.UserRepository}
     */
    @Test(dataProvider = "adminUser")
    public void verifyNavigationButtons(IUser admin) {

        // set preconditions
        adminHomePage = loginPage.successAdminLogin(admin);
        administrationPage = adminHomePage.gotoAdministrationPage();

        // determine the count of pages depending on count os users per page
        int numberUsersOnPage = administrationPage.getQuantityOfUsersPerPage();
        int numberOfFoundUsers = administrationPage.getFoundUsersNumber();
        int expectedPageCount = numberOfFoundUsers / numberUsersOnPage;
        if ((numberOfFoundUsers % numberUsersOnPage) != 0) {
            expectedPageCount += 1;
        }

        // verify that 'First' and 'Backward' buttons are disabled
        // current page is: 1# of x#
        Assert.assertFalse(administrationPage.isFirstButtonEnabled()
                && administrationPage.isBackwardButtonEnabled());
        Assert.assertTrue(administrationPage.isForwardButtonEnabled()
                && administrationPage.isLastButtonEnabled());
        Assert.assertTrue((administrationPage.getPagesQuantity() == expectedPageCount)
                && (administrationPage.getCurrentPageNumber() == 1));

        administrationPage.clickForwardButton();

        // verify that after clicking 'Forward' button:
        // 'First', 'Backward', 'Forward' and 'Last' buttons are enabled
        // current page is: 2# of x#
        Assert.assertTrue(administrationPage.isFirstButtonEnabled()
                && administrationPage.isBackwardButtonEnabled()
                && administrationPage.isForwardButtonEnabled()
                && administrationPage.isLastButtonEnabled());
        Assert.assertTrue(administrationPage.getCurrentPageNumber() == 2);

        administrationPage.clickLastButton();

        // verify that after clicking 'Last' button:
        // 'First' and 'Backward' buttons are enabled
        // 'Forward' and 'Last' buttons are disabled
        // current page is: x# of x#
        Assert.assertTrue(administrationPage.isFirstButtonEnabled()
                && administrationPage.isBackwardButtonEnabled());
        Assert.assertFalse(administrationPage.isForwardButtonEnabled()
                && administrationPage.isLastButtonEnabled());
        Assert.assertTrue(administrationPage.getCurrentPageNumber() == expectedPageCount);

        administrationPage.clickBackwardButton();

        // verify that after clicking 'Backward' button:
        // 'First', 'Backward', 'Forward' and 'Last' buttons are enabled
        // current page is: x#-1 of x#
        Assert.assertTrue(administrationPage.isFirstButtonEnabled()
                && administrationPage.isBackwardButtonEnabled()
                && administrationPage.isForwardButtonEnabled()
                && administrationPage.isLastButtonEnabled());
        Assert.assertTrue(administrationPage.getCurrentPageNumber() == (expectedPageCount-1));
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
    public void CorrectUserDisplayingTest(IUser admUser) {
        int numberOfFoundUsersFromDB;

        AdministrationPage administrationPage = loginPage.successAdminLogin(admUser)
                .clickAdministrationTab();

        int numberOfFoundUsersFromPage = administrationPage.getFoundUsersNumber();

        DBUtils dbUtils = new DBUtils();
        numberOfFoundUsersFromDB = dbUtils.countAllUsers();
        Assert.assertEquals(numberOfFoundUsersFromDB, numberOfFoundUsersFromPage);

        List<User> usersFromPage = administrationPage.getUsersFromCurrentPage();

        List<User> usersFromDB = dbUtils.getTopFiveUsers();

        for(int i = 0; i<usersFromPage.size();i++){
            Assert.assertTrue(usersFromDB.get(i).CompareTo(usersFromPage.get(i)));
        }
    }


}

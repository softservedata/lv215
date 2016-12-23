package com.softserve.edu.oms.tests.administration;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * This test verifies that Administrator can navigate
 * through table with users on 'Administrator' tab
 * by navigation buttons
 *
 * Based on LVSETOMS-44 in Jira
 *
 * @author Iryna Kyselchuk
 * @since 16.12.16
 */
public class NavigationButtonsTest extends TestRunner {

    private AdminHomePage adminHomePage;
    private AdministrationPage administrationPage;

    /**
     * Set preconditions for test:
     * login with Administrator role credentials
     * and go to Administration page
     */
    @BeforeMethod
    public void loginAndGotoAdministrationPage() {
        IUser admin = UserRepository.get().adminUser();
        adminHomePage = loginPage.successAdminLogin(admin);
        administrationPage = adminHomePage.gotoAdministrationPage();
    }

    /**
     * This method verifies that Administrator can navigate the User table
     * by 'First', 'Last', 'Forward' and 'Backward' buttons
     * and current page in the pagination label changes according to navigation
     * where count of pages is number of records divided by
     * number of records per page and
     * rounded to the bigger integer
     */
    @Test
    @Step("verifyNavigationButtons")
    public void verifyNavigationButtons() {
        // determine the count of pages depending on count os users per page
        int numberUsersOnPage = administrationPage.getQuantityOfUsersPerPage();
        int numberOfFoundUsers = administrationPage.getFoundUsersNumber();
        int expectedPageCount = numberOfFoundUsers / numberUsersOnPage;
        // round count of pages to the bigger integer
        if ((numberOfFoundUsers % numberUsersOnPage) != 0) {
            expectedPageCount += 1;
        }

        /* verify that 'First' and 'Backward' buttons are disabled
           current page is: 1# of x#  */
        Assert.assertFalse(administrationPage.isFirstButtonEnabled()
                && administrationPage.isBackwardButtonEnabled());
        Assert.assertTrue(administrationPage.isForwardButtonEnabled()
                && administrationPage.isLastButtonEnabled());
        Assert.assertTrue((administrationPage.getPagesQuantity() == expectedPageCount)
                && (administrationPage.getCurrentPageNumber() == 1));

        administrationPage.clickForwardButton();

        /* verify that after clicking 'Forward' button:
           'First', 'Backward', 'Forward' and 'Last' buttons are enabled
           current page is: 2# of x# */
        Assert.assertTrue(administrationPage.isFirstButtonEnabled()
                && administrationPage.isBackwardButtonEnabled()
                && administrationPage.isForwardButtonEnabled()
                && administrationPage.isLastButtonEnabled());
        Assert.assertTrue(administrationPage.getCurrentPageNumber() == 2);

        administrationPage.clickLastButton();

        /* verify that after clicking 'Last' button:
           'First' and 'Backward' buttons are enabled
           'Forward' and 'Last' buttons are disabled
           current page is: x# of x# */
        Assert.assertTrue(administrationPage.isFirstButtonEnabled()
                && administrationPage.isBackwardButtonEnabled());
        Assert.assertFalse(administrationPage.isForwardButtonEnabled()
                && administrationPage.isLastButtonEnabled());
        Assert.assertTrue(administrationPage.getCurrentPageNumber() == expectedPageCount);

        administrationPage.clickBackwardButton();

        /* verify that after clicking 'Backward' button:
           'First', 'Backward', 'Forward' and 'Last' buttons are enabled
           current page is: x#-1 of x# */
        Assert.assertTrue(administrationPage.isFirstButtonEnabled()
                && administrationPage.isBackwardButtonEnabled()
                && administrationPage.isForwardButtonEnabled()
                && administrationPage.isLastButtonEnabled());
        Assert.assertTrue(administrationPage.getCurrentPageNumber() == (expectedPageCount-1));
    }

    /**
     * Logout from current page
     */
    @AfterMethod
    public void returnToPreviousState() {
        administrationPage.logout();
    }
}

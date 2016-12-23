//package com.softserve.edu.oms.tests.administration;
//
//import com.softserve.edu.oms.enums.UsersPerPage;
//import org.hamcrest.CoreMatchers;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import com.softserve.edu.oms.data.DBUtils;
//import com.softserve.edu.oms.data.IUser;
//import com.softserve.edu.oms.data.UserRepository;
//import com.softserve.edu.oms.enums.Region;
//import com.softserve.edu.oms.enums.Role;
//import com.softserve.edu.oms.enums.SQLQueries;
//import com.softserve.edu.oms.pages.AdminHomePage;
//import com.softserve.edu.oms.pages.AdministrationPage;
//import com.softserve.edu.oms.pages.CreateNewUserPage;
//import com.softserve.edu.oms.tests.TestRunner;
//import ru.yandex.qatools.allure.annotations.Step;
//
//import static com.softserve.edu.oms.enums.LabelsNamesEnum.SHOW_10_ITEMS_LINK;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.testng.Assert.assertTrue;
//
///**
// * This test verifies that new user creation affects
// * 'Number of Found User' and pagination label on main
// * page of 'Administration' tab
// *
// * Based on LVSETOMS-53 in Jira
// *
// * @author Roman Raba
// * @since 16.12.16
// * @link http://ssu-jira.softserveinc.com/browse/LVSETOMS-53
// */
//public class PaginationTest extends TestRunner{
//
//    private AdministrationPage administrationPage;
//    private int pagesCount;
//    private int numberUsers;
//
//    @DataProvider
//    public Object[][] validUser() {
//        return new Object[][]{{UserRepository.get().userForDelete()},
//        };
//    }
//
//
//    /**
//     * Verify that number of active registered users is aliquot to 5.
//     * And creates necessary number of users.
//     */
//    @BeforeMethod
//    public void verifyAndCreateUsers() {
//        int numberOfImems;
//        IUser user = UserRepository.get().someUser();
//
//        //Go to Administration page
//        AdminHomePage adminHomePage =
//                loginPage.successAdminLogin(UserRepository.get().adminUser());
//        administrationPage =
//                adminHomePage.gotoAdministrationPage();
//
//        //Total number of users.
//        numberUsers = administrationPage.getFoundUsersNumber();
//
//        //Number of pages. (One page can displays 5 or 10 users
//        //which equivalent variable #numberOfImems)
//        pagesCount = administrationPage.getPagesQuantity();
//
//        //Number of users which are shown in a table on an Administration page
//        numberOfImems = administrationPage.getUsersPerPageNumber();
//
//        //Verify if number of active registered users is aliquot to 5.
//        //And creating some users if necessary.
//        if ((numberUsers % numberOfImems) != 0) {
//            for (int i = 0; i < (numberOfImems - (numberUsers % numberOfImems)); i++) {
//                CreateNewUserPage createNewUserPage =
//                        administrationPage.gotoCreateNewUserPage();
//
//                createNewUserPage.setLoginInput(user.getLoginname() + user.getLoginname().charAt(i)).
//                        setEmailInput(user.getEmail()).
//                        setFirstNameInput(user.getFirstname()).
//                        setLastNameInput(user.getLastname()).
//
//                        setPasswordInput(user.getPassword()).
//                        setConfirmPasswordInput(user.getPassword()).
//                        setSelectRegion(Region.getRegion(user.getRegion())).
//                        setSelectRole(Role.valueOf(user.getRole().toUpperCase()));
//
//                administrationPage = createNewUserPage.successCreateNewUser();
//            }
//            numberUsers = numberUsers + (numberOfImems - (numberUsers % numberOfImems));
//        }
//    }
//
//
//    /**
//     * Deletes users from DB after creation in test.
//     */
//    @AfterMethod
//    public void deleteUsersFromDB() {
//        DBUtils dbUtils = new DBUtils();
//        dbUtils.deleteUsersFromDB(SQLQueries.DELETE_USERS_BY_FIRSTNAME.getQuery(),
//                UserRepository.get().someUser().getFirstname());
//    }
//
//
//    /**
//     * Verify that values in 'Number of found users' and 'Page#:' links
//     * are properly updated after creation a new user.
//     * @param user - user which should be created
//     */
//    @Test(dataProvider = "validUser")
//    @Step("verifyChangePageNumber")
//    public void verifyChangePageNumber(IUser user) {
//
//        int newNumberOfUsers;
//        int newPagesCount;
//
//        //Go to Creation New User page
//        CreateNewUserPage createNewUserPage =
//                administrationPage.gotoCreateNewUserPage();
//
//        //Creation new user.
//        administrationPage = createNewUserPage.successCreateNewUser(user);
//
//        //Total number of users.
//        newNumberOfUsers =  administrationPage.getFoundUsersNumber();
//
//        //Number of pages. (One page can displays 5 or 10 users
//        //which equivalent variable #numberOfImems)
//        newPagesCount =  administrationPage.getPagesQuantity();
//
//        //Verify if number of users and number of pages are changed
//        //after creation a new user.
//        Assert.assertEquals(numberUsers + 1, newNumberOfUsers);
//        Assert.assertEquals(pagesCount + 1, newPagesCount);
//    }
//
//    /**
//     * @author Anastasiia Maidanska
//     * @version 1.0
//     * @since 20.12.16
//     */
//
//    public static class PaginationTest extends TestRunner{
//
//        /**
//         * Verify that correct number of records displays in a table on 'Administration' tab
//         */
//
//        @Test
//        @Step
//        public void paginationTest() {
//            // Get admin user from UserRepository
//            IUser admin = UserRepository.get().adminUser();
//
//            // Login and go to administration page
//            AdministrationPage administrationPage = loginPage
//                    .successAdminLogin(admin)
//                    .gotoAdministrationPage();
//
//
//             // Verification that 5 records are displayed in a table
//            assertThat(administrationPage.getQuantityOfUsersPerPage() ,
//                    CoreMatchers.equalTo(UsersPerPage.FIVE.getResultsPerPage()));
//
//            // Verification that 'Show 10 items' link is displayed
//            assertTrue(administrationPage.showItemsLinkIsDisplayed());
//
//            // Verification that 'Show 10 items' link has correct name
//            assertThat(administrationPage.getShowItemsLinkText(),
//                    CoreMatchers.equalTo(SHOW_10_ITEMS_LINK.name));
//
//            // Count number of pages
//            double pagesNumber = administrationPage.getFoundUsersNumber() / (UsersPerPage.FIVE.getResultsPerPage() * 1.0);
//
//            // Verification that correct number of pages displayed
//            assertThat(administrationPage.getPagesQuantity(), CoreMatchers.equalTo((int)Math.ceil(pagesNumber)));
//
//            administrationPage.changeQuantityOfUsersPerPage();
//
//            // Verification that 10 records are displayed in a table
//            assertThat(administrationPage.getQuantityOfUsersPerPage() ,
//                    CoreMatchers.equalTo(UsersPerPage.TEN.getResultsPerPage()));
//
//            // Count number of pages
//            pagesNumber = administrationPage.getFoundUsersNumber() / (UsersPerPage.TEN.getResultsPerPage() * 1.0);
//
//            // Verification that correct number of pages displayed
//            assertThat(administrationPage.getPagesQuantity(), CoreMatchers.equalTo((int)Math.ceil(pagesNumber)));
//
//        }
//    }
//}

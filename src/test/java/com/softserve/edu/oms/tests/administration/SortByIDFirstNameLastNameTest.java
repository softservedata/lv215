package com.softserve.edu.oms.tests.administration;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserve.edu.oms.enums.SQLQueries.*;


public class SortByIDFirstNameLastNameTest extends TestRunner {

    @DataProvider
    public Object[][] admUser() {
        return new Object[][] {
                { UserRepository.get().adminUser() }
        };
    }


    @Test(dataProvider = "admUser")
    public void assertSortById(IUser admUser) {
        Assert.assertTrue(loginPage.logout()
                .successAdminLogin(admUser)
                .gotoAdministrationPage()
                .waitForLoad()
                .showTenRows()
                .waitForLoad()
                .compareLogins(SORT_USERS_BY_ID_ASC.getQuery()));
    }



    @Test(dataProvider = "admUser")
    public void assertSortByFirstNameASC(IUser admUser) {
        Assert.assertTrue(loginPage.logout()
                .successAdminLogin(admUser)
                .gotoAdministrationPage()
                .waitForLoad()
                .showTenRows()
                .waitForLoad()
                .sortByFirstNameASC()
                .waitForLoad()
                .compareLogins(SORT_USERS_BY_FIRSTNAME_ASC.getQuery()));
    }

    @Test(dataProvider = "admUser")
    public void assertSortByFirstNameDESC(IUser admUser) {
        Assert.assertTrue(loginPage.logout()
                .successAdminLogin(admUser)
                .gotoAdministrationPage()
                .waitForLoad()
                .showTenRows()
                .waitForLoad()
                .sortByFirstNameDESC()
                .waitForLoad()
                .compareLogins(SORT_USERS_BY_FIRSTNAME_DESC.getQuery()));
    }

    @Test(dataProvider = "admUser")
    public void assertSortByLastNameASC(IUser admUser) {
        Assert.assertTrue(loginPage.logout()
                .successAdminLogin(admUser)
                .gotoAdministrationPage()
                .waitForLoad()
                .sortByLastNameASC()
                .waitForLoad()
                .compareLogins(SORT_USERS_BY_LASTNAME_ASC.getQuery()));
    }

    @Test(dataProvider = "admUser")
    public void assertSortByLastNameDESC(IUser admUser) {
        Assert.assertTrue(loginPage.logout()
                .successAdminLogin(admUser)
                .gotoAdministrationPage()
                .waitForLoad()
                .sortByLastNameDESC()
                .waitForLoad()
                .compareLogins(SORT_USERS_BY_LASTNAME_DESC.getQuery()));
    }





}




package com.softserve.edu.oms.tests.createuser;


import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.User;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.ConditionFilterDropdownList;
import com.softserve.edu.oms.enums.FieldFilterDropdownList;
import com.softserve.edu.oms.enums.SQLQueries;
import com.softserve.edu.oms.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class CreateNewUserTest extends TestRunner {

    @DataProvider
    public Object[][] Users() {
        return new Object[][]{
                {UserRepository.get().adminUser(), UserRepository.get().newUser()}
        };
    }

    @DataProvider
    public Object[][] newUser() {
        return new Object[][]{
                {UserRepository.get().newUser()}
        };
    }

    @Test(dataProvider = "newUser")
    public void assertNonExistence(IUser newUser) {
        DBUtils dbUtility = new DBUtils();
        Assert.assertFalse(dbUtility.verifyThatUserIsInDB(newUser.getLoginname()));
    }

    @Test(dataProvider = "Users")
    public void createNewUser(IUser admUser, IUser newUser) {
        List<User> users = loginPage.logout()
                .successAdminLogin(admUser)
                .gotoAdministrationPage()
                .waitForLoad()
                .gotoCreateNewUserPage()
                .waitForLoad()
                .setLoginData(newUser)
                .successCreateNewUser()
                .waitForLoad()
                .filterAndSearch(FieldFilterDropdownList.LOGIN, ConditionFilterDropdownList.EQUALS, newUser.getLoginname())
                .waitForLoad()
                .getAllUsers();

        if(users.size()>1)
            Assert.assertTrue(false);

        Assert.assertEquals(users.get(0).getLoginname(), newUser.getLoginname());
    }

    @Test(dataProvider = "newUser")
    public void deleteUser (IUser newUser){
        DBUtils dbUtility = new DBUtils();
        dbUtility.deleteUsersFromDB(SQLQueries.DELETE_USER_BY_LOGIN.getQuery(), newUser.getLoginname());
        Assert.assertFalse(dbUtility.verifyThatUserIsInDB(newUser.getLoginname()));
    }

}

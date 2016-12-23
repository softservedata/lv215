package com.softserve.edu.oms.tests.login;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginAsEveryUserTypeTest extends TestRunner {

    @DataProvider
    public Object[][] admUser() {
        return new Object[][] {
                { UserRepository.get().adminUser() }
        };
    }
    @DataProvider
    public Object[][] customerUser() {
        return new Object[][] {
                { UserRepository.get().customerUser() }
        };
    }
    @DataProvider
    public Object[][] merchandiserUser() {
        return new Object[][] {
                { UserRepository.get().merchandiserUser() }
        };
    }
    @DataProvider
    public Object[][] supervisorUser() {
        return new Object[][] {
                { UserRepository.get().supervisorUser() }
        };
    }
    @Test(dataProvider = "admUser")
    @Step
    public void assertAdministratorLogin(IUser admUser) {
        Assert.assertEquals(loginPage.logout()
                .successAdminLogin(admUser)
                .waitForLoad()
                .getRoleText(), admUser.getRole());
    }

    @Test(dataProvider = "customerUser")
    @Step("Check if user with customer type logs in successfully")
    public void assertCustomerLogin(IUser customerUser) {
        Assert.assertEquals(loginPage.logout()
                .successCustomerLogin(customerUser)
                .waitForLoad()
                .getRoleText(), customerUser.getRole());
    }

    @Test(dataProvider = "merchandiserUser")
    @Step
    public void assertMerchandiserLogin(IUser merchandiserUser) {
        Assert.assertEquals(loginPage.logout()
                .successMerchandiserLogin(merchandiserUser)
                .waitForLoad()
                .getRoleText(), merchandiserUser.getRole());
    }

    @Test(dataProvider = "supervisorUser")
    @Step
    public void assertSupervisorLogin(IUser supervisorUser) {
        Assert.assertEquals(loginPage.logout()
                .successSupervisorLogin(supervisorUser)
                .waitForLoad()
                .getRoleText(), supervisorUser.getRole());
    }



}

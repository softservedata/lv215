package com.softserve.edu.oms.tests.login;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.ErrorMessagesEnum;
import com.softserve.edu.oms.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Test verifies that error messages is shown when user tries to login without
 * being registered in the system
 *
 * Based on LVSETOMS-29 in Jira
 *
 * @author Dmytro Voropai
 */
public class ResetButtonErrorMessagesTest extends TestRunner {

//  Provides not register user login and password
    @DataProvider
    public Object[][] notExistUser() {
        return new Object[][] {
                {UserRepository.get().nonExistingUser() }
        };
    }

    @Test(dataProvider = "notExistUser", alwaysRun = true)
    @Step("Error messages verification for non registered user")
    public void verifyResetButtonErrorMessagesForNonRegisteredUser(IUser notExistUser){
//      Check if Object of String error message is not null.
        Assert.assertNotNull(loginPage.unsuccessfulLogin(notExistUser)
                .getBadCredentialsErrorMessageText());
//      Check if error message is the same as was expected
        Assert.assertEquals(loginPage.unsuccessfulLogin(notExistUser)
                .getBadCredentialsErrorMessageText(), ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_TC29.message);
    }
}

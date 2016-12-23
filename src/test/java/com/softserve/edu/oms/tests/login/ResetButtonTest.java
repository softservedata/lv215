package com.softserve.edu.oms.tests.login;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.tests.TestRunner;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.softserve.edu.oms.enums.ErrorMessagesEnum.ERROR_MESSAGE;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This test verifies that entered user credentials values
 * are cleared by clicking on 'Reset' button
 *
 * Based on LVSETOMS-37 in Jira
 *
 * @author Iryna Kyselchuk
 * @since 15.12.16
 */
public class ResetButtonTest extends TestRunner {

    /**
     * Provides data for user login
     *
     * @return user from UserRepository
     */
    @DataProvider
    public Object[][] someUser() {
        return new Object[][]{
                {UserRepository.get().invalidUser()}
        };
    }

    /**
     * This method verifies that after
     * entering user credentials in 'User' and 'Password' fields
     * and clicking 'Reset' button
     * both fields are empty
     *
     * @param someUser {@link com.softserve.edu.oms.data.UserRepository}
     */
    @Test(dataProvider = "someUser")
    @Step("verifyResetButtonFunctionality")
    public void verifyResetButtonFunctionality(IUser someUser) {

        loginPage.setLoginDataAndReset(someUser);
        Assert.assertTrue(loginPage
                .getLoginnameInputText()
                .isEmpty());
        Assert.assertTrue(loginPage
                .getPasswordInputText()
                .isEmpty());
    }
}



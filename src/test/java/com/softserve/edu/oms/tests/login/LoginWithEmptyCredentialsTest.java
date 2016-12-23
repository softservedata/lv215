package com.softserve.edu.oms.tests.login;

import com.softserve.edu.oms.tests.TestRunner;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.softserve.edu.oms.enums.ErrorMessagesEnum.ERROR_MESSAGE;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Anastasiia Maidanska
 * @version 1.0
 * @since 20.12.16
 */

public  class LoginWithEmptyCredentialsTest extends TestRunner {

    /**
     * Test verifies that error message is shown,
     * when user try to login with empty "Login" and "Password" fields.
     */

    @Test
    @Step
    public void loginWithEmptyCredentials (){

        // Click on 'Submit' button and get  error message
        String currentErrorMessage = loginPage
                .loginWithEmptyCredentials()
                .getBadCredentialsErrorMessageText();

        // Verify that error message is correct
        assertThat(currentErrorMessage, CoreMatchers.equalTo(ERROR_MESSAGE.message));
    }
}

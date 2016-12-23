    package com.softserve.edu.oms.tests.userinfo;

    import com.softserve.edu.oms.data.IUser;
    import com.softserve.edu.oms.data.UserRepository;
    import com.softserve.edu.oms.pages.AdminHomePage;
    import com.softserve.edu.oms.tests.TestRunner;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;
    import ru.yandex.qatools.allure.annotations.Step;


    public class SwitchingTabsFunctionalityTest extends TestRunner {

    @DataProvider
    public Object[][] admUser() {
        return new Object[][] {
            { UserRepository.get().adminUser() }
        };
    }
    /**
    * <h1>Switching tabs on UserInfo page for Admin user</h1>
    * This test validates that Admin has an ability to switch between
    * tabs on UserInfo page
    *
    * @author Viktoriia Bybel
     * @version 1.0
     * @since 15.12.16
     * @param admUser
     */

    @Test(dataProvider = "admUser")
    @Step("TabSwitchingTest")
    public void TabSwitchingTest(IUser admUser) {

        //log in
        AdminHomePage adminHomePage = loginPage.successAdminLogin(admUser);

        //verify that "User Info" tab is active
        Assert.assertTrue(adminHomePage
                .waitForLoad()
                .getUserInfoTab()
                .isEnabled());

        //verify that "Administration" tab is present
        Assert.assertTrue(adminHomePage
                .waitForLoad()
                .getAdministrationTab()
                .isDisplayed());

        //go to Admin page
        adminHomePage
                .gotoAdministrationPage()
                .waitForLoad();

        //verify that we are at Admin page
        Assert.assertTrue((driver.getCurrentUrl()).contains("users.htm"));

        //return to User Info page
        adminHomePage
                .waitForLoad()
                .clickUserInfoTab();

        //verify that we are at User Info page
        Assert.assertTrue((driver.getCurrentUrl()).contains("userInfo.htm"));
    }
    }

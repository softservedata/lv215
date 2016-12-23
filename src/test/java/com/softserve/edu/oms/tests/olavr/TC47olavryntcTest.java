package com.softserve.edu.oms.tests.olavr;

/**
 * Created by Oleh Lavrynenko on 13.12.2016.
 */
import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.ConditionFilterDropdownList;
import com.softserve.edu.oms.enums.FieldFilterDropdownList;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TC47olavryntcTest {
    WebDriver driver;
    private SoftAssert softAssert = new SoftAssert();
    private LoginPage omsLoginPage;
    private AdminHomePage omsAdminHomePage;
    private AdministrationPage omsAdministrationPage;

    private final String TOO_LONG_NAME="zxcvbnm asdfghjk qwertyuio pxmfjfn jvnvkh";
    private final String VALID_NAME="noneiva";
    @BeforeClass
    public void setUp() {
        final String driverPath = "src/test/resources/drivers/";
        final String logInPageUrl= "http://localhost:8080/OMS/login.htm";
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");

        driver = new ChromeDriver();
        driver
                .manage()
                .timeouts()
                .implicitlyWait(30, TimeUnit.SECONDS);
        driver
                .manage()
                .window()
                .maximize();

        driver.get(logInPageUrl);
        omsLoginPage = new LoginPage(driver);

    }

    @DataProvider // (parallel = true)
    public Object[][] validUsers() {
        return new Object[][]{{UserRepository.get().adminUser()},
                // { UserRepository.get().customerUser() }
        };
    }

    @Test(dataProvider = "validUsers")
    public void testOptionValues(IUser admin) {
        softAssert=new SoftAssert();
        omsAdminHomePage = omsLoginPage.successAdminLogin(admin);
        omsAdministrationPage = omsAdminHomePage.gotoAdministrationPage();
        softAssert.assertEquals(omsAdministrationPage.getSelectFieldDefaultValue(), FieldFilterDropdownList.FIRST_NAME.getFieldName());
        softAssert.assertEquals(omsAdministrationPage.getSelectFieldOptions(), new HashSet<>(Arrays.asList(FieldFilterDropdownList.values()))
                .stream()
                .map(p -> p.getFieldName().toLowerCase()).collect(Collectors.toSet()));
        softAssert.assertEquals(omsAdministrationPage.getSelectConditionDefaultValue(), ConditionFilterDropdownList.EQUALS.getNameOfConditionFilterField());
        softAssert.assertEquals(omsAdministrationPage.getSelectConditionOptions(),
                new HashSet<>(Arrays.asList(ConditionFilterDropdownList.values()))
                        .stream()
                        .map(condition-> condition.getNameOfConditionFilterField()).collect(Collectors.toSet()));
        softAssert.assertAll();
    }

    //@Test(dataProvider = "validUsers")
    public void verifySearchTooLongName(IUser admin) {
        softAssert=new SoftAssert();
        omsAdminHomePage = omsLoginPage.successAdminLogin(admin);
        omsAdministrationPage = omsAdminHomePage.gotoAdministrationPage();

        omsAdministrationPage.clickSearchButton();
        omsAdministrationPage.filterAndSearch(FieldFilterDropdownList.FIRST_NAME,ConditionFilterDropdownList.EQUALS,TOO_LONG_NAME);

        DBUtils dbUtils = new DBUtils();
        int numberOfUsers = dbUtils.getAllCells("","").size();

        softAssert.assertEquals(omsAdministrationPage.getAllUsers().size(),numberOfUsers );
        softAssert.assertAll();


    }

    @Test(dataProvider = "validUsers")
    public void verifySearchByEquals(IUser admin) {
        softAssert=new SoftAssert();
        omsAdminHomePage = omsLoginPage.successAdminLogin(admin);
        omsAdministrationPage = omsAdminHomePage.gotoAdministrationPage();

        omsAdministrationPage.clickSearchButton();
        omsAdministrationPage.filterAndSearch(FieldFilterDropdownList.LOGIN,ConditionFilterDropdownList.EQUALS,VALID_NAME);

        DBUtils dbUtils = new DBUtils();
        int numberOfUsers = dbUtils.getUserByLogin(VALID_NAME) == null ? 0 : 1;
        softAssert.assertEquals(omsAdministrationPage.getAllUsers().size(),numberOfUsers );
        softAssert.assertAll();
    }

    @Test(dataProvider = "validUsers")
    public void verifySearchByNotEquals(IUser admin) {
        softAssert=new SoftAssert();
        omsAdminHomePage = omsLoginPage.successAdminLogin(admin);
        omsAdministrationPage = omsAdminHomePage.gotoAdministrationPage();

        omsAdministrationPage.clickSearchButton();
        omsAdministrationPage.selectField(FieldFilterDropdownList.LOGIN);
        omsAdministrationPage.selectConditionByIndex(1);
        omsAdministrationPage.search(VALID_NAME);

        DBUtils dbUtils = new DBUtils();

        int numberOfUsersWithLogin = dbUtils.getUserByLogin(VALID_NAME) == null ? 0 : 1;
        int numberOfUsers = dbUtils.countAllUsers() - numberOfUsersWithLogin;
        softAssert.assertEquals(new AdministrationPage(driver).getAllUsers().size(),numberOfUsers);
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        omsAdministrationPage.logout();

    }
    @AfterClass
    public void afterClass() {
        if (driver!=null) driver.close();

    }
}

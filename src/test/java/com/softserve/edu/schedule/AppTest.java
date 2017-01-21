package com.softserve.edu.schedule;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testApp() {
        System.out.println("+++ surefire.reports.directory = "
                + System.getProperty("surefire.reports.directory"));
        Assert.assertTrue(true);
    }

}

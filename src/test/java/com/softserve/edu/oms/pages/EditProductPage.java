package com.softserve.edu.oms.pages;

import org.openqa.selenium.WebDriver;

/**
 * PageObject class that represents Edit Product page.
 *
 * @version 1.0
 * @since 15.12.16
 * @author Iryna Kyselchuk
 *
 * Class extends AddProductPage
 * @see AddProductPage
 */
public class EditProductPage extends AddProductPage {

    /** Class constructor */
    public EditProductPage(WebDriver driver) {
        super(driver);
    }
}

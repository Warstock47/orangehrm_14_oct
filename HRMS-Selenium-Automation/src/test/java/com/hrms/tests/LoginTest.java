package com.hrms.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.hrms.base.BaseTest;
import com.hrms.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("Admin", "admin123");

    }
}
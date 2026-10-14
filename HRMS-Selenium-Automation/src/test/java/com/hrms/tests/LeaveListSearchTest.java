package com.hrms.tests;

import org.testng.annotations.Test;

import com.hrms.base.BaseTest;
import com.hrms.pages.LoginPage;
import com.hrms.pages.LeavePage;

public class LeaveListSearchTest extends BaseTest {

    @Test
    public void leaveListSearchTest() throws InterruptedException {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Open Leave module
        LeavePage leavePage = new LeavePage(driver);
        leavePage.clickLeave();

        // Search Leave List
        leavePage.searchByEmployee("HARI");

        Thread.sleep(3000);

        System.out.println("Leave list search completed.");
    }
}
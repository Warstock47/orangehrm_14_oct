package com.hrms.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hrms.base.BaseTest;
import com.hrms.pages.LoginPage;
import com.hrms.pages.MyLeavePage;

public class MyLeaveVerificationTest extends BaseTest {

    @Test
    public void verifyAppliedLeave() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Open My Leave
        MyLeavePage myLeavePage = new MyLeavePage(driver);

        myLeavePage.clickLeave();
        myLeavePage.clickMyLeave();

        // Search the applied leave date
        myLeavePage.searchByDate(
                "2026-20-10",
                "2026-20-10"
        );

        // Verify the leave record
        boolean leaveFound =
                myLeavePage.isLeaveDisplayed(
                        "2026-20-10",
                        "CAN - Personal",
                        "Personal work"
                );

        Assert.assertTrue(
                leaveFound,
                "Applied leave was not found."
        );

        System.out.println(
                "PASS: Leave application verified successfully."
        );
    }
}
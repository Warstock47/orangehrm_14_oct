package com.hrms.tests;

import org.testng.annotations.Test;

import com.hrms.base.BaseTest;
import com.hrms.pages.ApplyLeavePage;
import com.hrms.pages.LoginPage;

public class ApplyLeaveTest extends BaseTest {

    @Test
    public void applyLeaveTest() throws InterruptedException {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Open Leave
        ApplyLeavePage applyLeavePage =
                new ApplyLeavePage(driver);

        applyLeavePage.clickLeave();
        
        
        // Open Apply Leave
        applyLeavePage.clickApply();

        // Select Leave Type
        applyLeavePage.selectLeaveType();

        // Enter dates
        applyLeavePage.enterDates(
                "2026-10-24",
                "2026-10-24"
        );

        // Enter comment
        applyLeavePage.enterComments(
                "Personal work"
        );

        // Apply
        applyLeavePage.clickApplyButton();

        Thread.sleep(3000);

        System.out.println("Leave application submitted.");
        
        
        applyLeavePage.enterDates(
        		 "2026-10-24",
                 "2026-10-24"
        	);
        
        applyLeavePage.enterComments("Personal work");
        
        applyLeavePage.clickApplyButton();
        
    }
}
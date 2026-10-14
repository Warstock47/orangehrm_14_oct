package com.hrms.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hrms.base.BaseTest;
import com.hrms.pages.EmployeeDetailsPage;
import com.hrms.pages.EmployeeListPage;
import com.hrms.pages.LoginPage;
import com.hrms.pages.PIMPage;

public class EditEmployeeTest extends BaseTest {

    @Test
    public void editEmployeeTest() {

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("Admin", "admin123");

        // Open PIM
        PIMPage pimPage = new PIMPage(driver);

        pimPage.clickPIM();

        // Search employee
        EmployeeListPage employeeListPage =
                new EmployeeListPage(driver);

        employeeListPage.searchEmployee("John");

        // Open employee
        employeeListPage.openEmployee("John");

        // Edit employee
        EmployeeDetailsPage employeeDetailsPage =
                new EmployeeDetailsPage(driver);

        employeeDetailsPage.editEmployee(
                "Johnny",
                "Automation"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("viewPersonalDetails"),
                "Employee details were not updated"
        );
    }
}
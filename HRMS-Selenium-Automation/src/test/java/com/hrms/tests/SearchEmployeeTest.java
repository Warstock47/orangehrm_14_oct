package com.hrms.tests;

import org.testng.annotations.Test;

import com.hrms.base.BaseTest;
import com.hrms.pages.EmployeeListPage;
import com.hrms.pages.LoginPage;
import com.hrms.pages.PIMPage;

public class SearchEmployeeTest extends BaseTest {

    @Test
    public void searchEmployeeTest() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        // Open PIM
        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickPIM();

        // Search Employee
        EmployeeListPage employeeListPage =
                new EmployeeListPage(driver);

        employeeListPage.searchEmployee("Arcane");

        System.out.println(
                "Employee search completed."
        );
    }
}
package com.hrms.tests;

import org.testng.annotations.Test;

import com.hrms.base.BaseTest;
import com.hrms.pages.AddEmployeePage;
import com.hrms.pages.LoginPage;
import com.hrms.pages.PIMPage;

public class AddEmployeeTest extends BaseTest {

    @Test
    public void addEmployeeTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        AddEmployeePage addEmployeePage =
                new AddEmployeePage(driver);

        addEmployeePage.addEmployee(
                "Arcane",
                "console",
                "microsoft"
        );

        System.out.println(
                "Employee saved successfully."
        );
    }
}
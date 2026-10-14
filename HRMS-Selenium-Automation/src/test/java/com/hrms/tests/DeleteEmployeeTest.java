package com.hrms.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.hrms.base.BaseTest;
import com.hrms.pages.EmployeeListPage;
import com.hrms.pages.LoginPage;
import com.hrms.pages.PIMPage;

public class DeleteEmployeeTest extends BaseTest {

    @Test
    public void deleteEmployeeTest() throws InterruptedException {
    	
    	 WebDriverWait wait =
    	           new WebDriverWait(driver, Duration.ofSeconds(15)); 
        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("Admin", "admin123");

        // Open PIM
        PIMPage pimPage = new PIMPage(driver);

        pimPage.clickPIM();

        // Search employee
        EmployeeListPage employeeListPage =
                new EmployeeListPage(driver);

        employeeListPage.searchEmployee("Arcane");

       
        
        // Select employee
        employeeListPage.selectEmployee("Arcane");

        
        
        // Delete employee
        employeeListPage.clickDelete();

        // Confirm delete
        employeeListPage.confirmDelete();

        System.out.println(
                "Employee deletion completed."
        );
        
    }
}
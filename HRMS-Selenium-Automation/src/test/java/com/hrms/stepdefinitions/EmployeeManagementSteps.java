
package com.hrms.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hrms.hooks.Hooks;
import com.hrms.pages.AddEmployeePage;
import com.hrms.pages.EmployeeListPage;
import com.hrms.pages.LoginPage;
import com.hrms.pages.PIMPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class EmployeeManagementSteps {

    private String createdEmployeeName;
    private boolean employeeDeleted;

    private WebDriver driver() {
        return Hooks.getDriver();
    }

    // Explicit wait helper method
    private WebDriverWait getWait() {
        return new WebDriverWait(driver(), Duration.ofSeconds(15));
    }

    // STEP 1: Login
    @Given("the user is logged in to OrangeHRM")
    public void userIsLoggedIn() {

        getWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.name("username")
            )
        );

        LoginPage loginPage = new LoginPage(driver());
        loginPage.login("Admin", "admin123");

        getWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[normalize-space()='Dashboard']")
            )
        );
    }

    // STEP 2: Open PIM
    @When("the user opens the PIM module")
    public void openPIM() {

        new PIMPage(driver()).clickPIM();

        getWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[normalize-space()='Add']")
            )
        );
    }

    // STEP 3: Create a new employee
    @When("the user creates a new employee")
    public void createNewEmployee() {

        employeeDeleted = false;

        getWait().until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Add']")
            )
        ).click();

        String firstName = "Cucumber";
        String lastName = "Test" + System.currentTimeMillis();

        createdEmployeeName = firstName + " " + lastName;

        AddEmployeePage addEmployeePage =
            new AddEmployeePage(driver());

        addEmployeePage.addEmployee(firstName, "", lastName);
    }

    // STEP 4: Verify employee creation
    
    @Then("the employee should be created successfully")
    public void verifyEmployeeCreated() {

        Assert.assertNotNull(
            createdEmployeeName,
            "Employee name was not generated"
        );

        boolean detailsDisplayed = driver().findElements(
            By.xpath("//h6[normalize-space()='Personal Details']")
        ).size() > 0;

        if (!detailsDisplayed) {
            String errors = driver().findElements(
                By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")
            ).stream()
             .map(e -> e.getText())
             .reduce("", (a, b) -> a + " | " + b);

            Assert.fail(
                "Employee creation failed. Validation errors: "
                    + errors
                    + ". Current URL: "
                    + driver().getCurrentUrl()
            );
        }

        System.out.println("Employee created: " + createdEmployeeName);
    }
    // STEP 5: Search for the newly created employee
    
    @When("the user searches for the newly created employee")
    public void searchNewEmployee() {

        new PIMPage(driver()).clickPIM();

        // Wait for the Employee List page to load
        getWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[normalize-space()='Employee Name']")
            )
        );

        EmployeeListPage employeeList =
            new EmployeeListPage(driver());

        employeeList.searchEmployee(createdEmployeeName);

        System.out.println(
            "Searching employee: " + createdEmployeeName
        );
    }

 
    // STEP 6: Verify employee appears in the results
    @Then("the employee should appear in the results")
    public void verifyEmployeeAppears() {

        Assert.assertNotNull(
            createdEmployeeName,
            "Created employee name is null"
        );

        EmployeeListPage employeeList =
            new EmployeeListPage(driver());

        try {
            getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='table']")
                )
            );

            boolean found = getWait().until(
                d -> employeeList.isEmployeeDisplayed(
                    createdEmployeeName
                )
            );

            Assert.assertTrue(
                found,
                "Employee not found: " + createdEmployeeName
            );

        } catch (org.openqa.selenium.TimeoutException e) {
            Assert.fail(
                "Employee was not found within 15 seconds: "
                    + createdEmployeeName,
                e
            );
        }
    }


    // STEP 7: Delete the newly created employee
    @When("the user deletes the newly created employee")
    public void deleteNewEmployee() {

        EmployeeListPage employeeList =
            new EmployeeListPage(driver());

        employeeList.searchEmployee(createdEmployeeName);
        employeeList.selectEmployee(createdEmployeeName);
        employeeList.clickDelete();
        employeeList.confirmDelete();

        employeeList.waitForEmployeeToDisappear(
            createdEmployeeName
        );

        employeeDeleted = true;

        System.out.println(
            "Deleted employee: " + createdEmployeeName
        );
    }

    // STEP 8: Verify deletion
    @Then("the employee should no longer appear in the results")
    public void verifyEmployeeDeleted() {

        Assert.assertTrue(
            employeeDeleted,
            "Employee deletion was not completed: "
                + createdEmployeeName
        );

        Assert.assertFalse(
            new EmployeeListPage(driver())
                .isEmployeeDisplayed(createdEmployeeName),
            "Employee still appears after deletion: "
                + createdEmployeeName
        );
    }
}

package com.hrms.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeListPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Employee Name field
    private By employeeName =
            By.xpath("//label[text()='Employee Name']/../..//input");

    // Search button
    private By searchButton =
            By.xpath("//button[@type='submit']");

    // Reset button
    private By resetButton =
            By.xpath("//button[@type='reset']");

    public EmployeeListPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    // Enter employee name

public void enterEmployeeName(String name) {

    WebElement field = wait.until(
        ExpectedConditions.elementToBeClickable(employeeName)
    );

    field.click();
    field.sendKeys(org.openqa.selenium.Keys.CONTROL, "a");
    field.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
    field.sendKeys(name);
}


    // Select employee from autocomplete suggestion

public void selectEmployeeFromSuggestion(String name) {
    WebDriverWait suggestionWait =
        new WebDriverWait(driver, Duration.ofSeconds(10));

    try {
        By suggestion = By.xpath(
            "//div[contains(@class,'oxd-autocomplete-option')]"
            + "[contains(normalize-space(.),'" + name + "')]"
        );

        suggestionWait.until(
            ExpectedConditions.elementToBeClickable(suggestion)
        ).click();

    } catch (TimeoutException e) {
        throw new TimeoutException(
            "No autocomplete suggestion found for employee: "
            + name
            + ". Check whether the employee exists and whether "
            + "the Employee Name field was populated correctly.",
            e
        );
    }
}


 // Click Search

public void clickSearch() {

    By formLoader = By.cssSelector(".oxd-form-loader");

    // Wait for any existing loading overlay to disappear
    wait.until(
        ExpectedConditions.invisibilityOfElementLocated(formLoader)
    );

    // Wait for Search to be clickable
    WebElement search = wait.until(
        ExpectedConditions.elementToBeClickable(searchButton)
    );

    search.click();

    // Wait for the search request to finish
    wait.until(
        ExpectedConditions.invisibilityOfElementLocated(formLoader)
    );
}


    // Search employee

public void searchEmployee(String name) {

    enterEmployeeName(name);
    selectEmployeeFromSuggestion(name);
    clickSearch();
}


    // Click Reset
    public void clickReset() {

        wait.until(
                ExpectedConditions.elementToBeClickable(resetButton)
        ).click();
    }

    // Check whether employee is displayed

public boolean isEmployeeDisplayed(String name) {

    String[] parts = name.split(" ", 2);

    if (parts.length != 2) {
        throw new IllegalArgumentException(
            "Expected first and last name: " + name
        );
    }

    By employeeRow = By.xpath(
        "//div[contains(@class,'oxd-table-card')]" +
        "[.//div[normalize-space()='" + parts[0] + "']]" +
        "[.//div[normalize-space()='" + parts[1] + "']]"
    );

    try {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(employeeRow)
        ).isDisplayed();

    } catch (TimeoutException e) {
        return false;
    }
}


    // Open employee details
    public void openEmployee(String name) {

        By employee = By.xpath(
                "//div[@role='row']" +
                "//div[contains(normalize-space(),'" + name + "')]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(employee)
        ).click();
    }

    // Select employee checkbox
    public void selectEmployee(String name) {

        By checkbox = By.xpath(
                "//div[contains(@class,'oxd-table-card')]" +
                "[.//div[contains(normalize-space(),'" + name + "')]]" +
                "//input[@type='checkbox']"
        );

        WebElement checkboxElement =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(checkbox)
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", checkboxElement);
    }

    // Click Delete Selected
    public void clickDelete() {

        By deleteButton = By.xpath(
                "//button[normalize-space()='Delete Selected']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(deleteButton)
        ).click();
    }

    // Confirm Delete
    public void confirmDelete() {

        By confirmButton = By.xpath(
                "//button[contains(normalize-space(),'Yes, Delete')]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(confirmButton)
        ).click();
    }
        

        // Wait until the deleted employee disappears from the results
        public void waitForEmployeeToDisappear(String name) {

            By employee = By.xpath(
                    "//div[@role='row']" +
                    "//div[contains(normalize-space(),'" + name + "')]"
            );

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(employee)
            );
        
    }
}
package com.hrms.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName =
            By.name("firstName");

    private By middleName =
            By.name("middleName");

    private By lastName =
            By.name("lastName");

    private By saveButton =
            By.xpath("//button[@type='submit']");

    private By formLoader =
            By.cssSelector(".oxd-form-loader");

    public AddEmployeePage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public void enterFirstName(String name) {

        wait.until(
            ExpectedConditions.elementToBeClickable(firstName)
        ).sendKeys(name);
    }

    public void enterMiddleName(String name) {

        wait.until(
            ExpectedConditions.elementToBeClickable(middleName)
        ).sendKeys(name);
    }

    public void enterLastName(String name) {

        wait.until(
            ExpectedConditions.elementToBeClickable(lastName)
        ).sendKeys(name);
    }

    public void clickSave() {

        // Click Save after the form is ready
        wait.until(
            ExpectedConditions.elementToBeClickable(saveButton)
        ).click();

        // Wait for either successful navigation or a validation error
        wait.until(driver ->
            driver.findElements(
                By.xpath("//h6[normalize-space()='Personal Details']")
            ).size() > 0
            ||
            driver.findElements(
                By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")
            ).size() > 0
        );
    }

    public void addEmployee(
            String first,
            String middle,
            String last) {

        enterFirstName(first);
        enterMiddleName(middle);
        enterLastName(last);

        clickSave();
    }
}
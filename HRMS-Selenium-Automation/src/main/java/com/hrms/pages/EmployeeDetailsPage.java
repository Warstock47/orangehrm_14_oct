package com.hrms.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName =
            By.name("firstName");

    private By lastName =
            By.name("lastName");

    private By saveButton =
            By.xpath("//button[@type='submit']");

    private By formLoader =
            By.cssSelector(".oxd-form-loader");

    public EmployeeDetailsPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public void changeFirstName(String name) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(firstName)
        );

        driver.findElement(firstName).clear();

        driver.findElement(firstName).sendKeys(name);
    }

    public void changeLastName(String name) {

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(lastName)
        );

        driver.findElement(lastName).clear();

        driver.findElement(lastName).sendKeys(name);
    }

    public void clickSave() {

        // Wait until OrangeHRM loader disappears
        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(formLoader)
        );

        // Wait until Save button can actually be clicked
        wait.until(
            ExpectedConditions.elementToBeClickable(saveButton)
        );

        driver.findElement(saveButton).click();
    }

    public void editEmployee(
            String firstName,
            String lastName) {

        changeFirstName(firstName);

        changeLastName(lastName);

        clickSave();
    }
}
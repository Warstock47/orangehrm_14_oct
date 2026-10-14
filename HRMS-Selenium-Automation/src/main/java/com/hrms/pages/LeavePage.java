package com.hrms.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeavePage {

    WebDriver driver;
    WebDriverWait wait;

    // Leave menu
    By leaveMenu = By.xpath("//span[text()='Leave']");

    // Leave List
    By leaveList = By.xpath("//a[text()='Leave List']");
    
    //Employee Name
    By employeeName = By.xpath("//label[text()='Employee Name']/following::input[1]");

    // Search button
    By searchButton = By.xpath("//button[@type='submit']");

    public LeavePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Click Leave module
    public void clickLeave() {

        wait.until(ExpectedConditions.elementToBeClickable(leaveMenu))
                .click();
    }

    // Open Leave List and search
    public void searchLeave() {

        wait.until(ExpectedConditions.elementToBeClickable(leaveList))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(searchButton))
                .click();
    }
    
    public void searchByEmployee(String employee) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName))
                .sendKeys(employee);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton))
                .click();
    }    
               
    }

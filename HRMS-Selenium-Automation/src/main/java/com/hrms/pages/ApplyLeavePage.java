package com.hrms.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplyLeavePage {

    WebDriver driver;
    WebDriverWait wait;

    // Leave menu
    By leaveMenu = By.xpath("//span[text()='Leave']");

    // Apply tab
    By applyTab = By.xpath("//a[text()='Apply']");

    // Leave Type dropdown
    By leaveType = By.xpath("//label[text()='Leave Type']/following::div[contains(@class,'oxd-select-text')][1]");

    // Leave Type option - we'll select CAN - Personal
    By personalLeave = By.xpath("//div[@role='option']//span[text()='CAN - Personal']");

    // From Date
    By fromDate = By.xpath("//label[text()='From Date']/following::input[1]");

    // To Date
    By toDate = By.xpath("//label[text()='To Date']/following::input[1]");

    // Comments
    By comments = By.xpath("//label[text()='Comments']/following::textarea[1]");

    // Apply button
    By applyButton = By.xpath("//button[@type='submit' and normalize-space()='Apply']");


    public ApplyLeavePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    public void clickLeave() {

        wait.until(ExpectedConditions.elementToBeClickable(leaveMenu))
                .click();
    }


    public void clickApply() {

        wait.until(ExpectedConditions.elementToBeClickable(applyTab))
                .click();
    }


    public void selectLeaveType() {

        wait.until(ExpectedConditions.elementToBeClickable(leaveType))
                .click();

        wait.until(ExpectedConditions.elementToBeClickable(personalLeave))
                .click();
    }


    public void enterDates(String from, String to) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(fromDate))
                .sendKeys(from);

        wait.until(ExpectedConditions.visibilityOfElementLocated(toDate))
                .sendKeys(to);
    }


    public void enterComments(String comment) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(comments))
                .sendKeys(comment);
    }


    public void clickApplyButton() {

        wait.until(ExpectedConditions.elementToBeClickable(applyButton))
                .click();
    }
}
package com.hrms.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyLeavePage {

    WebDriver driver;
    WebDriverWait wait;

    By leaveMenu = By.xpath("//span[text()='Leave']");

    By myLeaveTab = By.xpath("//a[text()='My Leave']");

    By fromDate = By.xpath(
            "//label[text()='From Date']/following::input[1]"
    );

    By toDate = By.xpath(
            "//label[text()='To Date']/following::input[1]"
    );

    By searchButton = By.xpath(
            "//button[normalize-space()='Search']"
    );

    By leaveTable = By.xpath(
            "//div[contains(@class,'oxd-table-body')]"
    );


    public MyLeavePage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }


    public void clickLeave() {

        wait.until(
                ExpectedConditions.elementToBeClickable(leaveMenu)
        ).click();
    }


    public void clickMyLeave() {

        wait.until(
                ExpectedConditions.elementToBeClickable(myLeaveTab)
        ).click();
    }


    public void searchByDate(String from, String to) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromDate)
        ).clear();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromDate)
        ).sendKeys(from);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(toDate)
        ).clear();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(toDate)
        ).sendKeys(to);

        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(leaveTable)
        );
    }


    public boolean isLeaveDisplayed(
            String date,
            String leaveType,
            String comment) {

        By leaveRecord = By.xpath(
                "//div[contains(@class,'oxd-table-body')]"
                + "//div[contains(@class,'oxd-table-row')]"
                + "[contains(.,'" + date + "')]"
                + "[contains(.,'" + leaveType + "')]"
                + "[contains(.,'" + comment + "')]"
        );

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            leaveRecord
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }
}
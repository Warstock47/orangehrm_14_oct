package com.hrms.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecruitmentPage {

    WebDriver driver;
    WebDriverWait wait;

    By recruitmentMenu =
            By.xpath("//span[text()='Recruitment']");

    By candidatesTab =
            By.xpath("//a[normalize-space()='Candidates']");

    By candidateName =
            By.xpath("//label[text()='Candidate Name']/following::input[1]");

    By searchButton =
            By.xpath("//button[normalize-space()='Search']");

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickRecruitment() {

        wait.until(
                ExpectedConditions.elementToBeClickable(recruitmentMenu)
        ).click();
    }

    public void clickCandidates() {

        wait.until(
                ExpectedConditions.elementToBeClickable(candidatesTab)
        ).click();
    }

    public void searchCandidate(String name) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(candidateName)
        ).sendKeys(name);

        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }
}
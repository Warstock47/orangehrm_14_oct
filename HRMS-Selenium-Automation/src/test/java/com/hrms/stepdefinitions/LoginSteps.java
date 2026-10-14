
package com.hrms.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hrms.hooks.Hooks;
import com.hrms.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {

    private WebDriver driver() {
        return Hooks.getDriver();
    }

    @Given("the user opens the OrangeHRM login page")
    public void openLoginPage() {

        new WebDriverWait(driver(), Duration.ofSeconds(15))
            .until(ExpectedConditions.visibilityOfElementLocated(
                By.name("username")));
    }

    @When("the user enters valid credentials")
    public void enterCredentials() {

        LoginPage loginPage = new LoginPage(driver());

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
    }

    @When("the user clicks the login button")
    public void clickLogin() {

        new LoginPage(driver()).clickLogin();
    }



@Then("the dashboard should be displayed")
public void verifyDashboard() {

    WebDriver driver = driver();

    WebDriverWait wait =
        new WebDriverWait(driver, Duration.ofSeconds(15));

    try {
        wait.until(ExpectedConditions.urlContains("/dashboard"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h6[normalize-space()='Dashboard']")
        ));

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/dashboard"),
            "Dashboard URL was not reached"
        );

    } catch (org.openqa.selenium.TimeoutException e) {
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());
        throw e;
    }
}

    }

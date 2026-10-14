package com.hrms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage {

    private WebDriver driver;

    private By pimMenu =
            By.xpath("//span[text()='PIM']");

    private By addEmployee =
            By.xpath("//a[text()='Add Employee']");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPIM() {
        driver.findElement(pimMenu).click();
    }

    public void clickAddEmployee() {
        driver.findElement(addEmployee).click();
    }
}
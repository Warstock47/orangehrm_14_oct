
package com.hrms.hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    @Before
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        DRIVER.set(driver);

        driver.manage().window().maximize();

        driver.manage().timeouts()
              .implicitlyWait(Duration.ZERO);

        driver.get(
            "https://opensource-demo.orangehrmlive.com/"
        );
    }

    @After
    public void tearDown() {
        WebDriver driver = DRIVER.get();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }
}

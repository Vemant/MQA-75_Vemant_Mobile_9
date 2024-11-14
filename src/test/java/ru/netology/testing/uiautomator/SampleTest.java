package ru.netology.testing.uiautomator;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {
    private AndroidDriver driver;
    private MobileObjects mobileObjects;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(
                "platformName",
                "Android"
        );
        caps.setCapability(
                "appium:deviceName",
                "Some name"
        );
        caps.setCapability(
                "appium:appPackage",
                "ru.netology.testing.uiautomator"
        );
        caps.setCapability(
                "appium:appActivity",
                "ru.netology.testing.uiautomator.MainActivity"
        );
        caps.setCapability(
                "appium:automationName",
                "uiautomator2"
        );
        caps.setCapability(
                "appium:ensureWebviewsHavePages",
                true
        );
        caps.setCapability(
                "appium:nativeWebScreenshot",
                true
        );
        caps.setCapability(
                "appium:newCommandTimeout",
                3600
        );
        caps.setCapability(
                "appium:connectHardwareKeyboard",
                true
        );

        driver = new AndroidDriver(this.getUrl(), caps);
        mobileObjects = new MobileObjects(driver);
    }

    @Test
    public void sampleTestEmptyLine() {
        mobileObjects.textToBeChanged.isDisplayed();
        String originalText = mobileObjects.textToBeChanged.getText();
        mobileObjects.userInput.isDisplayed();
        mobileObjects.userInput.click();
        mobileObjects.userInput.sendKeys(
                mobileObjects.emptyKey
        );
        mobileObjects.buttonChange.isDisplayed();
        mobileObjects.buttonChange.click();
        mobileObjects.textToBeChanged.isDisplayed();
        Assertions.assertEquals(
                originalText,
                mobileObjects.textToBeChanged.getText()
        );
    }

    @Test
    public void sampleTestAnotherActivity() {
        mobileObjects.userInput.isDisplayed();
        mobileObjects.userInput.click();
        mobileObjects.userInput.sendKeys(
                mobileObjects.activityKey
        );
        mobileObjects.buttonActivity.isDisplayed();
        mobileObjects.buttonActivity.click();
        mobileObjects.testOnNewPage.isDisplayed();
        Assertions.assertEquals(
                mobileObjects.activityKey,
                mobileObjects.testOnNewPage.getText()
        );
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}


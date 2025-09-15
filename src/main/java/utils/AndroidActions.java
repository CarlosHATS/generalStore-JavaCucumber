package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class AndroidActions {

    protected AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) {
        this.driver = driver;
    }

    public void scrollUntilVisible(By locator) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean canScrollMore = true;
        int scrollCount = 0;
        try {
            while (canScrollMore && scrollCount < 30) {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    break;
                }
                canScrollMore = (Boolean) js.executeScript("mobile: scrollGesture", Map.of(
                        "left", 0,
                        "top", 0,
                        "width", 1000,
                        "height", 1700,
                        "direction", "down",
                        "percent", 0.8
                ));
                scrollCount++;
            }
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    public WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean scrollDown() {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", Map.of(
                "left", 0,
                "top", 0,
                "width", 1000,
                "height", 1700,
                "direction", "down",
                "percent", 0.8
        ));
    }
}


package ecommerce.hooks;

import eccomerce.pages.CartPage;
import eccomerce.pages.FormPage;
import eccomerce.pages.ProductPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class Hooks {

    private AndroidDriver driver;
    private AppiumDriverLocalService service;
    private ProductPage productPage;
    private CartPage cartPage;
    private FormPage formPage;

    public AndroidDriver getDriver() {
        return driver;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage(driver);
        }
        return productPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }

    public FormPage getFormPage() {
        if (formPage == null) {
            formPage = new FormPage(driver);
        }
        return formPage;
    }

    @Before
    public void startAppium() {
        try {
            String userHome = System.getProperty("user.home");
            service = new AppiumServiceBuilder()
                    .withAppiumJS(new File(userHome + "\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                    .withIPAddress("127.0.0.1").usingPort(4723).build();
            service.start();

            String projectPath = System.getProperty("user.dir");
            String appPath = projectPath + "/src/test/resources/GeneralStore.apk";
//            String chromeDriverPath = projectPath + "/chromeDriver/chromedriver.exe";
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName("Pixel")
                    .setUdid("emulator-5554")
                    .setApp(appPath);
//                    .setChromedriverExecutable(chromeDriverPath);

            driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException("Failed to start Appium server or driver.", e);
        }
    }

    @After
    public void addScreenshot(Scenario scenario) {
        if (driver != null) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        closeAppium();
    }

    public void closeAppium() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}


package ecommerce.testNGTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
//	public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException, URISyntaxException {
		// Iniciar o appium server automaticamente
		String userHome = System.getProperty("user.home");
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(userHome+"\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		
		// Setar as capabilities
		UiAutomator2Options options = new UiAutomator2Options()
				.setDeviceName("Pixel")
				.setUdid("emulator-5554")
//				.setAppPackage("com.androidsample.generalstore")
//				.setAppActivity("com.androidsample.generalstore.MainActivity");
				.setApp(userHome+"\\Desktop\\udemy\\projetos\\Store\\src\\test\\java\\resources\\GeneralStore.apk")
				.setChromedriverExecutable("C:\\chromeDriver\\chromedriver.exe");

		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}
	
	public void swipeGesture(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
				"direction", "left",
				"percent", 0.3));
	}
	
	public void dragAndDrop(WebElement ele, int  ele1, int ele2) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", ele1,
			    "endY", ele2));
	}
	
	public void scrollUntilVisible(By locator) {
	    // Desativa o implicit wait temporariamente
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    boolean canScrollMore = true;
	    int maxScrolls = 30;
	    int scrollCount = 0;
	    try {
	        while (canScrollMore && scrollCount < maxScrolls) {
	            List<WebElement> elements = driver.findElements(locator);

	            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
	                System.out.println("Elemento visível: " + elements.get(0).getText());
	                break;}
	            canScrollMore = (Boolean) js.executeScript("mobile: scrollGesture", Map.of(
	                "left", 0,
	                "top", 0,
	                "width", 1000,
	                "height", 1700,
	                "direction", "down",
	                "percent", 0.8));

	            scrollCount++;}

	        if (scrollCount >= maxScrolls) {
	            System.out.println("Elemento não encontrado após " + maxScrolls + " tentativas de scroll.");
	        }

	    } finally {
	        // Reativa o implicit wait padrão
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    }
	    
	}
	
	public void explictWait(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera até 10 segundos
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		Assert.assertTrue(element.isDisplayed());
	}
	
	@AfterClass
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }

	    if (service != null && service.isRunning()) {
	        service.stop();
	    }
	    
	}


}





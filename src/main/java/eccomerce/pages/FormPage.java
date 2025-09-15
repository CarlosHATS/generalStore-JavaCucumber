package eccomerce.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

public class FormPage extends AndroidActions {

    public FormPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement dropCountry;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    private WebElement femaleGender;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    private WebElement maleGender;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    @AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
    private WebElement errorMessage;


    public void setNameField(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void selectGender(String gender) {
        if ("Female".equalsIgnoreCase(gender)) {
            femaleGender.click();
        } else {
            maleGender.click();
        }
    }

    public void chooseCountry(String country) {
        By nameField = AppiumBy.id("com.androidsample.generalstore:id/nameField");
        waitForElement(nameField);
        dropCountry.click();
        By element = AppiumBy.xpath(String.format("//android.widget.TextView[@text='%s']", country));
        scrollUntilVisible(element);
        driver.findElement(element).click();
    }

    public ProductPage PresShopButton() {
        shopButton.click();
        return new ProductPage(driver);
    }

    public String getErrorMessageText() {
        return errorMessage.getAttribute("name");
    }
}

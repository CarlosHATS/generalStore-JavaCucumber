package eccomerce.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends AndroidActions {

    public ProductPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath  ="//android.widget.TextView[@text='ADD TO CART']")
    public List<WebElement> addToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;

    public void addItemToCart(int index) throws InterruptedException {
        By firstAddToCartButton = AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']");
        waitForElement(firstAddToCartButton);

        for (int i = 0; i < index; i++) {
            addToCart.get(0).click();
            Thread.sleep(1000);
            scrollUntilVisible(firstAddToCartButton);
        }
    }

    private final List<String> selectedProductNames = new ArrayList<>();
    private final List<String> selectedProductPrices = new ArrayList<>();

    public void specificProduct(String element){
        String xpathElement = "//android.widget.TextView[@text='"+element+"']/following-sibling::android.widget.LinearLayout//android.widget.TextView[@text='ADD TO CART']";
        By productElement = AppiumBy.xpath(xpathElement);
        scrollUntilVisible(productElement);

        String productNameXPath = "//android.widget.TextView[@text='"+element+"']";
        String productPriceXPath = "//android.widget.TextView[@text='"+element+"']/following-sibling::android.widget.LinearLayout//android.widget.TextView[@index='0']";

        WebElement productNameElement = driver.findElement(AppiumBy.xpath(productNameXPath));
        WebElement productPriceElement = driver.findElement(AppiumBy.xpath(productPriceXPath));

        selectedProductNames.add(productNameElement.getText());
        selectedProductPrices.add(productPriceElement.getText());

        driver.findElement(AppiumBy.xpath(xpathElement)).click();
    }

    public List<String> getSelectedProductNames() {
        return selectedProductNames;
    }

    public List<Double> getSelectedProductPricesAsDoubles() {
        List<Double> priceList = new ArrayList<>();
        for (String priceString : this.selectedProductPrices) {
            String cleanPrice = priceString.replace("$", "").trim();
            priceList.add(Double.parseDouble(cleanPrice));
        }
        return priceList;
    }

    public CartPage clickCartButton(){
        cartButton.click();
        return new CartPage(driver);
    }

}


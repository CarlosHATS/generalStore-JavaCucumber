package eccomerce.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AndroidActions {

    public CartPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    private List<WebElement> productNamesInCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPricesInCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    public List<String> getProductNamesInCart() {
        List<String> names = new ArrayList<>();
        for (WebElement element : productNamesInCart) {
            names.add(element.getText());
        }
        return names;
    }

    public List<Double> getProductPricesInCartAsDoubles() {
        List<Double> doublePrices = new ArrayList<>();
        for (WebElement element : productPricesInCart) {
            String cleanPrice = element.getText().replace("$", "").trim();
            doublePrices.add(Double.parseDouble(cleanPrice));
        }
        return doublePrices;
    }

    public double getCalculatedTotalAmount() {
        double totalSum = 0;
        for (WebElement priceElement : productPricesInCart) {
            String cleanPrice = priceElement.getText().replace("$", "").trim();
            totalSum += Double.parseDouble(cleanPrice);
        }
        return totalSum;
    }

    public double displayTotalAmount() {
        String textTotalAmount = totalAmount.getText();
        String cleanPrice = textTotalAmount.replace("$", "").trim();
//        cleanPrice = cleanPrice + 1;
        return Double.parseDouble(cleanPrice);
    }

}

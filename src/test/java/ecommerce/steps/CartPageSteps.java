package ecommerce.steps;

import eccomerce.pages.CartPage;
import eccomerce.pages.ProductPage;
import ecommerce.hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class CartPageSteps {

    private final Hooks hooks;

    public CartPageSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    @Then("The products in the cart must match the selected items")
    public void the_products_in_the_cart_must_match_the_selected_items() {
        CartPage cartPage = hooks.getCartPage();
        List<String> namesInCart = cartPage.getProductNamesInCart();
        List<Double> pricesInCart = cartPage.getProductPricesInCartAsDoubles();

        ProductPage productPage = hooks.getProductPage();
        List<String> selectedNames = productPage.getSelectedProductNames();
        List<Double> selectedPrices = productPage.getSelectedProductPricesAsDoubles();

        System.out.println(selectedNames);
        System.out.println(namesInCart);

        Assert.assertEquals(namesInCart, selectedNames, "The product names in the cart do not match the selected items.");
        Assert.assertEquals(pricesInCart, selectedPrices, "The product prices in the cart do not match the selected items.");
    }

    @And("The system displays the total value of the products")
    public void the_system_displays_the_total_value_of_the_products(){
        Double getCalculatedTotalAmountProducts = hooks.getCartPage().getCalculatedTotalAmount();
        Double displayTotalAmountCart = hooks.getCartPage().displayTotalAmount();

        Assert.assertEquals(getCalculatedTotalAmountProducts, displayTotalAmountCart, "The product prices in the cart do not match the selected items.");
    }
}

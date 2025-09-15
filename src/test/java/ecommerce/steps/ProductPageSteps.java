package ecommerce.steps;

import ecommerce.hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class ProductPageSteps {

    private final Hooks hooks;

    public ProductPageSteps(Hooks hooks) {
        this.hooks = hooks;
    }


    @And("The user click on the cart button")
    public void And_The_user_click_on_the_cart_button(){
        hooks.getProductPage().clickCartButton();
    }

    @When("The user adds the following items to cart:")
    public void the_user_adds_the_following_items_to_cart(DataTable itemsTable) {
        List<Map<String, String>> itemsList = itemsTable.asMaps(String.class, String.class);

        for (Map<String, String> item : itemsList) {
            String itemName = item.get("item");

            hooks.getProductPage().specificProduct(itemName);
        }
    }


}


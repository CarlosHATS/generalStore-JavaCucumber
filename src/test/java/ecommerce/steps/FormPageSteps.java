package ecommerce.steps;


import eccomerce.pages.FormPage;
import eccomerce.pages.ProductPage;
import ecommerce.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class FormPageSteps {

    private final Hooks hooks;

    public FormPageSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    @Given("^The user select the country (.+)$")
    public void the_user_select_the_country(String country) {
        hooks.getFormPage().chooseCountry(country);
    }

    @When("^The user select de gender (.+)$")
    public void the_user_select_de_gender(String gender) {
        hooks.getFormPage().selectGender(gender);
    }

    @When("^The user input yor name (.+)$")
    public void the_user_input_yor_name(String name) {
        hooks.getFormPage().setNameField(name);
    }

    @Then("^The user click on the shopping button$")
    public void the_user_click_on_the_shopping_button() {
        ProductPage productPage = hooks.getFormPage().PresShopButton();
    }



    @Given("The user don't input yor name")
    public void the_user_dont_input_yor_name() {
    }

    @Then("The error message is visible")
    public void the_error_message_is_visible() {
        String errorMessageText = hooks.getFormPage().getErrorMessageText();
        System.out.println(errorMessageText);
        Assert.assertEquals(errorMessageText, "Please enter your name");
    }
}

package ecommerce.testNGTest;

import java.util.List;
import java.util.Set;

import eccomerce.pages.FormPage;
import eccomerce.pages.ProductPage;
import org.testng.annotations.Test;

public class eCommercefFillForm extends BaseTest{
    FormPage formPage = new FormPage(driver);
    ProductPage productPage = new ProductPage(driver);

	@Test
	public void fillForm() throws InterruptedException {
		formPage.chooseCountry("Brazil");
		formPage.setNameField("Jessy");
		formPage.selectGender("Female");
		formPage.PresShopButton();

		Thread.sleep(10000);
		}

}

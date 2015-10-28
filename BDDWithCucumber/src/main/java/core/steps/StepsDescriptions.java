package core.steps;

import core.factory.DriverFactory;
import core.pages.MainPage;
import core.pages.ProductsListPage;
import core.pages.SubcategoriesPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.HelperUtils;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;


public class StepsDescriptions{

    private WebDriver driver;

    private MainPage mainPage;
    private ProductsListPage productsListPage;
    private SubcategoriesPage subcategoriesPage;

    private String manufacturer;
    private String cheapestProductName;
    private int minPrice;
    private int maxPrice;
    private int mentionedQuantity;

    @Before
    public void setUp() {
        DriverFactory factory = new DriverFactory();
        driver = factory.getDriverInstance();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @And("^I select \"([^\"]*)\" as products subcategory$")
    public void I_select_as_products_subcategory(String subcategory) throws Throwable {
        productsListPage = subcategoriesPage.clickOnSubcategory(subcategory);
    }

    @And("^I set maximum price to \"([^\"]*)\"$")
    public void I_set_maximum_price_to(String price) throws Throwable {
        maxPrice = HelperUtils.parseToInt(price);
        productsListPage = productsListPage.selectMaxPrice(price);
    }

    @And("^I set minimum price to \"([^\"]*)\"$")
    public void I_set_minimum_price_to(String price) throws Throwable {
        minPrice = HelperUtils.parseToInt(price);
        productsListPage = productsListPage.selectMinPrice(price);
    }

    @And("^I select \"([^\"]*)\" as manufacturer$")
    public void I_choose(String manufacturer) throws Throwable {
        this.manufacturer = manufacturer;
        mentionedQuantity = HelperUtils.getNumberOfManufacturerProducts(productsListPage.getManufacturers(), productsListPage.getNumbers(), manufacturer);
        productsListPage = productsListPage.selectManufacturer(manufacturer);
    }

    @And("^I sort products by price$")
    public void I_sort_products_by_price() throws Throwable {
        productsListPage = productsListPage.sortByPrice();
    }

    @And("^I get the product name with cheapest price$")
    public void I_get_the_I_get_the_product_name_with_cheapest_price() throws Throwable {
        cheapestProductName = productsListPage.getProductsNames().get(0).getText();
    }

    @And("^I search that product by name$")
    public void I_search_that_product_by_name() throws Throwable {
        productsListPage = productsListPage.search(cheapestProductName);
        productsListPage = productsListPage.submitSearch();
    }

    @Then("^I see that product$")
    public void I_see_that_product() throws Throwable {
        assertThat(productsListPage.getProductsNames().get(0).getText(), is(equalTo(cheapestProductName)));
        assertThat(productsListPage.getProductsNames().size(), is(equalTo(1)));
    }

    @Then("^I see that products number equals to expected on the page$")
    public void I_see_that_products_number_equals_to_expected_on_the_page() throws Throwable {
        assertThat(mentionedQuantity, is(equalTo(productsListPage.getFoundItemsQuantity())));
    }

    @Given("^I am on the \"([^\"]*)\" site$")
    public void I_am_on_the_site(String url) throws Throwable {
        driver.get(url);
    }

    @When("^I select \"([^\"]*)\" as products category$")
    public void I_select_as_products_category(String category) throws Throwable {
        mainPage = new MainPage(driver);
        subcategoriesPage = mainPage.clickOnCategory(category);
    }

    @Then("^I see expected result$")
    public void I_see_expected_result() throws Throwable {
        List<WebElement> productsPrices = productsListPage.getProductsPrices();
        for (WebElement price : productsPrices) {
            int intPrice = HelperUtils.parseToInt(price.getText());
            assertTrue(intPrice >= minPrice && intPrice <= maxPrice);
        }
    }
}


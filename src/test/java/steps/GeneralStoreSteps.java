package steps;

import core.Initialize;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class GeneralStoreSteps extends Initialize {


    @Given("User is on General Store page")
    public void userIsOnGeneralStorePage() {
        Assert.assertTrue("User isn't redirect to the general store page",isElementDisplayed(generalStoreScreen.generalStorePage));
    }

    @When("^User select the country ([^\"]*)$")
    public void userSelectTheCountryCountry(String country) throws InterruptedException {
        generalStoreScreen.selectCountry(country);
    }

    @And("^User enters the name ([^\"]*)$")
    public void userEntersTheNameName(String name) {
        generalStoreScreen.nameInputBox.sendKeys(name);
        hideKeyboard();
    }

    @And("^User selects the gender ([^\"]*)$")
    public void userSelectsTheGenderGender(String gender) {
        generalStoreScreen.selectGender(gender);
    }

    @And("^User clicks on the let's Shop button$")
    public void userClicksOnTheLetSShopButton() {
        generalStoreScreen.shopBtn.click();
    }

    @Then("^User is able to redirects to the products page$")
    public void userIsAbleToRedirectsToTheProductsPage() {
        Assert.assertTrue("User isn't redirect to the products page",isElementDisplayed(generalStoreScreen.productsPage));
    }
}

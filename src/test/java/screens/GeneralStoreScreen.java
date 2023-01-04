package screens;

import core.ActionMethods;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

import java.util.List;

public class GeneralStoreScreen extends ActionMethods {

    @AndroidFindBy(xpath="//android.widget.TextView[@text='General Store']")
    public MobileElement generalStorePage;

    @AndroidFindBy(xpath="//android.widget.EditText[contains(@resource-id,'nameField')]")
    public MobileElement nameInputBox;

    @AndroidFindBy(xpath="//android.widget.Spinner[contains(@resource-id,'spinnerCountry')]")
    public MobileElement countryDropDownBox;

    @AndroidFindBy(xpath="//android.widget.Button[contains(@resource-id,'btnLetsShop')]")
    public MobileElement shopBtn;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Products']")
    public MobileElement productsPage;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='India']")
    public List<MobileElement> india;

    public void selectGender(String gender)
    {
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='"+gender+"']")).click();
    }

    public MobileElement countryElement(String country)
    {
       return driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']"));
    }

    public void selectCountry(String country){
        countryDropDownBox.click();
        scrollDownToElements(india,true).get(0).click();
    }

}

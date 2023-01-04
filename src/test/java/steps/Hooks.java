package steps;

import core.DeviceCapabilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks extends DeviceCapabilities {

    public static Scenario scenario;

    /**
     * This method will execute before each scenario and will set capabilities
     *
     * @param scenario
     * @throws Exception
     */
    @Before
    public void setUp(Scenario scenario) throws Exception {
        //service = startService();
        driver = SetCapabilities();
        Hooks.scenario = scenario;
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    /**
     * This method will run after each scenario and will take screenshot and will attach in report for
     * failed scenario
     *
     * @param scenario
     */
    @After
    public void tearDown(Scenario scenario) {
        try {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            if (scenario.isFailed()) {
                if (prop.getProperty("Platform").equals("remote-lambda")) {
                    ((JavascriptExecutor) driver).executeScript("lambda-hook: {\"action\": \"setTestStatus\",\"arguments\": {\"status\":\"failed\", \"remark\":\"This is a sample remark for failed test \"}} ");
                }
                scenario.log("this is my failure message");
                TakesScreenshot ts = driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
            } else {
                if (prop.getProperty("Platform").equals("remote-lambda")) {
                    ((JavascriptExecutor) driver).executeScript("lambda-hook: {\"action\": \"setTestStatus\",\"arguments\": {\"status\":\"passed\"}} ");
                }
            }
        } catch (Exception e) {
            driver.closeApp();
            e.printStackTrace();
        }
        driver.closeApp();
        driver.quit();
        System.out.println("Run ended");
    }
}

package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Properties;

public class DeviceCapabilities {

    public static AppiumDriver<MobileElement> driver;
    public static Properties prop;
    public static DesiredCapabilities capabilities;
    public static AppiumDriverLocalService service;


    /**
     * This method is initializing elements through page factory
     */
    public DeviceCapabilities() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Method to identify the platform
     *
     * @return
     * @throws Exception
     */
    public AppiumDriver<MobileElement> SetCapabilities() throws Exception {
        SetProperty();
        try {
            if (prop.getProperty("PlatformName").equalsIgnoreCase("Android")) {
                driver = initAndroid();
            } else if (prop.getProperty("PlatformName").equalsIgnoreCase("ios")) {
                driver = initIOS();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    /**
     * Method is used to set property
     *
     * @throws IOException
     */
    public void SetProperty() throws IOException {
        FileInputStream fis =
                new FileInputStream(
                        System.getProperty("user.dir") + "/src/test/resources/config/config.properties");
        prop = new Properties();
        prop.load(fis);
    }


    /**
     * Capabilities for android device
     *
     * @return
     * @throws Exception
     */
    public AppiumDriver<MobileElement> initAndroid() throws Exception {
        capabilities = new DesiredCapabilities();
        if (prop.getProperty("Platform").equalsIgnoreCase("remote-lambda")) {
            capabilities.setCapability("build","MobileAutomation(build-75) "+java.time.LocalDate.now());
            capabilities.setCapability("name", "Mobile Automation Test "+ LocalTime.now());
            capabilities.setCapability("deviceName", prop.getProperty("LambdaTestAndroidDeviceName"));
            capabilities.setCapability("platformVersion", prop.getProperty("LambdaTestAndroidPlatformVersion"));
            capabilities.setCapability("platformName", prop.getProperty("LambdaTestAndroidPlatformName"));
            capabilities.setCapability("isRealMobile", prop.getProperty("LambdaTestIsRealMobile"));
            capabilities.setCapability("app", prop.getProperty("LambdaTestAndroidApp"));
            capabilities.setCapability("deviceOrientation", prop.getProperty("LambdaTestDeviceOrientation"));
            capabilities.setCapability("console", prop.getProperty("LambdaTestConsole"));
            capabilities.setCapability("network", prop.getProperty("LambdaTestNetwork"));
            capabilities.setCapability("visual", prop.getProperty("LambdaTestVisual"));
            capabilities.setCapability("devicelog", prop.getProperty("LambdaTestDeviceLog"));
            String hub = "https://" + prop.getProperty("LambdaTestUserName") + ":" + prop.getProperty("LambdaTestAccessKey") + prop.getProperty("LambdaTestGridURL");
            return driver = new AppiumDriver(new URL(hub), capabilities);
        } else if (prop.getProperty("Platform").equalsIgnoreCase("local")) {
            System.out.println("Opening App");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("PlatformVersion"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("PlatformName"));
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("RealDeviceName"));
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + prop.getProperty("App"));
            capabilities.setCapability("appPackage", prop.getProperty("AppPackage"));
            capabilities.setCapability("appActivity", prop.getProperty("AppActivity"));
            capabilities.setCapability("noReset", "false");
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, false);
            capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, false);
            return new AppiumDriver<MobileElement>(new URL(prop.getProperty("AppiumUrl")), capabilities);
        } else {
            return null;
        }
    }

    /**
     * Capabilities for IOS device
     *
     * @return
     * @throws Exception
     */
    public IOSDriver<MobileElement> initIOS() throws Exception {
        capabilities = new DesiredCapabilities();
        if (prop.getProperty("Platform").equalsIgnoreCase("remote-lambda")) {
            capabilities.setCapability("build","Mobile Automation(ios build-75) "+java.time.LocalDate.now());
            capabilities.setCapability("name", "Mobile Automation Test "+ LocalTime.now());
            capabilities.setCapability("deviceName", prop.getProperty("LambdaTestiOSDeviceName"));
            capabilities.setCapability("platformVersion", prop.getProperty("LambdaTestiOSPlatformVersion"));
            capabilities.setCapability("platformName", prop.getProperty("LambdaTestiOSPlatformName"));
            capabilities.setCapability("isRealMobile", prop.getProperty("LambdaTestIsRealMobile"));
            capabilities.setCapability("app", prop.getProperty("LambdaTestiOSApp"));
            capabilities.setCapability("deviceOrientation", prop.getProperty("LambdaTestDeviceOrientation"));
            capabilities.setCapability("console", prop.getProperty("LambdaTestConsole"));
            capabilities.setCapability("network", prop.getProperty("LambdaTestNetwork"));
            capabilities.setCapability("visual", prop.getProperty("LambdaTestVisual"));
            capabilities.setCapability("devicelog", prop.getProperty("LambdaTestDeviceLog"));
            String hub = "https://" + prop.getProperty("LambdaTestUserName") + ":" + prop.getProperty("LambdaTestAccessKey") + prop.getProperty("LambdaTestGridURL");
            return new IOSDriver<MobileElement>(new URL(hub), capabilities);
        } else if (prop.getProperty("Platform").equalsIgnoreCase("local")) {
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("IosRealDevicePlatformVersion"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("IosRealDeviceName"));
            capabilities.setCapability("udid", prop.getProperty("IosRealDeviceUdid"));
            capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + prop.getProperty("IosApp"));
            capabilities.setCapability("appPackage", prop.getProperty("AppPackage"));
            capabilities.setCapability("appActivity", prop.getProperty("AppActivity"));
            capabilities.setCapability("noReset", "false");
            capabilities.setCapability("autoGrantPermissions", true);
            return new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } else {
            return null;
        }
    }

}

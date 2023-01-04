package core;

import constant.TestConstants;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

abstract class Element extends DeviceCapabilities{
    MobileElement element = null;

    /** This method is used to find element */
    public void findElement(String locator, String locatorValue) {

        switch (locator) {
            case TestConstants.ID:
                element = driver.findElement(MobileBy.id(locatorValue));
                break;
            case TestConstants.XPATH:
                element = driver.findElement(MobileBy.xpath(locatorValue));
                break;
            case TestConstants.UISELECTOR:
                element = driver.findElement(MobileBy.AndroidUIAutomator(locatorValue));
                break;
            case TestConstants.CLASSNAME:
                element = driver.findElement(MobileBy.className(locatorValue));
                break;
            case TestConstants.ACCESSIBILITYID:
                element = driver.findElement(MobileBy.AccessibilityId(locatorValue));
                break;
        }
    }
}

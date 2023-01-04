package core;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionMethods extends Element{

    public static WebDriverWait driverWait = new WebDriverWait(driver, 60);
    /**
     * This method is use for the element is displayed or not
     *
     * @param element
     * @return
     */
    public static boolean isElementDisplayed(MobileElement element) {
        boolean flag = false;
        try {
            if (driverWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    /**
     * This method is used for the  scroll down to the element
     *
     * @param element
     * @param isLongScroll
     * @return
     * @throws InterruptedException
     */
    public static MobileElement scrollDownToElement(MobileElement element, boolean isLongScroll) throws InterruptedException {
        int timeoutInSeconds = isLongScroll ? 60 : 20;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        long startTime = System.currentTimeMillis();
        while (!isElementPresent(element)) {
            scrollDown();
            long scrollTime = (System.currentTimeMillis() - startTime) / 1000;

            if (scrollTime > timeoutInSeconds) {
                break;
            }
        }

        if (isElementPresent(element)) {
            int height = driver.manage().window().getSize().getHeight();
            int x = element.getLocation().getY();
            if (x > height - 240) {
                scrollDown();
            }
        }
        return element;
    }

    public static boolean isElementPresent(MobileElement element) {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        boolean flag = false;
        try {
            if (element.isDisplayed()) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * This method used to scroll down to the screen end
     */
    public static void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.6);
        int endX = size.getWidth() / 2;
        int endY = (int) (size.getHeight() * 0.2);

        swipe(startX, startY, endX, endY, 800);
    }

    /**
     * This method is used to swipe with co-ordination
     *
     * @param startx
     * @param starty
     * @param endx
     * @param endy
     * @param duration
     */
    public static void swipe(int startx, int starty, int endx, int endy, int duration) {
        TouchAction touchAction = new TouchAction(driver);
        Duration swipeDuration = Duration.ofMillis(duration);

        touchAction
                .press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(swipeDuration))
                .moveTo(PointOption.point(endx, endy))
                .release();

        touchAction.perform();
    }

    /**
     * This method is used for hide the keyboard
     */
    public void hideKeyboard() {
        driver.hideKeyboard();
    }

    /**
     * This method is used to scroll down to the elements
     *
     * @param elements
     * @param isLongScroll
     * @return
     */
    public List<MobileElement> scrollDownToElements(List<MobileElement> elements, boolean isLongScroll) {
        int timeoutInSeconds = isLongScroll ? 160 : 60;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        long startTime = System.currentTimeMillis();

        while (elements.isEmpty()) {
            scrollDown();
            long scrollTime = (System.currentTimeMillis() - startTime) / 1000;

            if (scrollTime > timeoutInSeconds) {
                break;
            }
        }

        if (!elements.isEmpty()) {
            int height = driver.manage().window().getSize().getHeight();
            int x = elements.get(0).getLocation().getY();
            if (x > height - 240) {
                scrollDown();
            }
        }

        return elements;
    }

}

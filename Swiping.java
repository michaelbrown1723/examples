import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by EntreScan on 7/7/2016.
 */
@RunWith(Parameterized.class)
public class Swiping {

    AndroidDriver driver;

    boolean testTrue = true;

    String correctUser = "ab1022";
    String correctPass = "password105";
    String incorrectUser = "ab102";
    String incorrectPass = "password10";

    @Parameterized.Parameters
    public static List<Object[]> data() {

        return Arrays.asList(new Object[5][0]);
    }

    public Swiping() {

    }

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "qwdqwd");
        caps.setCapability  ("platformName","Android");
        caps.setCapability("app","/Users/michaelbrownjr/Downloads/app-betatest-release.apk");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4492/wd/hub"),caps);

    }

    @Test
    public void theTest() throws Exception {



        while (true) {
            try {
                driver.findElementByAndroidUIAutomator("new UiSelector().text(\"SIGN IN\")").click();
                break;

            } catch (Exception e) {
            }

        }


        AndroidElement el = (AndroidElement) driver.findElementsByClassName("android.widget.EditText").get(0);
        el.sendKeys("ab1022");
        AndroidElement el1 = (AndroidElement) driver.findElementsByClassName("android.widget.EditText").get(1);
        el1.sendKeys("password105");
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Sign In\")").click();

        for(int k = 1;k <= 150;k++) {
            driver.swipe(400,1000,400,100,200);
            Thread.sleep(1500);

        }


    }

    @After
    public void terminate() {

        driver.quit();

    }
}



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

import static org.junit.Assert.assertTrue;

/*

    This test case makes sure that the reset password alert is successfully activated when the reset password
    button is clicked on the sign in page.

 */

@RunWith(Parameterized.class)
public class ResetPassword {

    AndroidDriver driver;

    String correctUser = "ab1022";
    String correctPass = "password105";
    String incorrectUser = "ab102";
    String incorrectPass = "password10";


    ///////
    //////////
    //////////////
    int portNum = 4723;
    String appPath = "/Users/michaelbrownjr/Downloads/app-betatest-release.apk";
    String deviceName = "qwdqwd";
    String platformVersion = "Android";
    //////////////
    //////////
    ///////


    @Parameterized.Parameters
    public static List<Object[]> data() {

        return Arrays.asList(new Object[5][0]);
    }

    public ResetPassword() {

    }

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", deviceName);
        caps.setCapability  ("platformName",platformVersion);
        caps.setCapability("app",appPath);
        driver = new AndroidDriver(new URL("http://0.0.0.0:" + portNum + "/wd/hub"),caps);

    }

    @Test
    public void theTest() throws Exception {



        //continue looking for the sign in button forever
        while(true) {

            try{

                driver.findElementByAndroidUIAutomator("new UiSelector().text(\"SIGN IN\")").click();

                break;

            }
            catch(Exception e) {}

        }
        AndroidElement el = (AndroidElement) driver.findElementsByClassName("android.widget.EditText").get(0);

        el.sendKeys(correctUser);

        driver.hideKeyboard();

        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Click here to reset your password\")").click();

        int loopIndex = 0;

        //holds condition of whether the test failed or passed
        boolean passed = false;

        //only attempt to find the label 100 times...if more than that, then we can assume the app didn't try to reset password
        while(loopIndex < 100) {

            try {

                //keep track of how many times the loop has run
                loopIndex++;

                //find the check your email alert and do nothing with it (we just want to see that it comes up)
                driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Check your email!\")");

                //if it finds the email alert, then the test passed
                passed = true;

            }catch(Exception e) {}


        }

        //determine whether or not the test passed
        assertTrue(passed);

    }

    //to do after the test
    @After
    public void terminate() {

        //quit the driver to restart the app
        driver.quit();

    }
}

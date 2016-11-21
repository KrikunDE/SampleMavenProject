

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * Created by User on 17.11.2016.
 */
public class Driver {
    private static WebDriver driver;

    public static WebDriver get() {
        return driver;
    }


    public static void set(WebDriver driverInput) {
        driver = driverInput;
    }


    public static void init() {

        Properties properties = new Properties();
        FileInputStream propFile;
        try {
            propFile = new FileInputStream("test.properties");
            properties.load(propFile);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        @SuppressWarnings("unchecked")
        Enumeration<String> e = (Enumeration<String>) properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            System.setProperty(key, properties.getProperty(key));
            Reporter.log(key + " - " + properties.getProperty(key), 2, true);
        }

        WebDriver driverInput;

        String s = System.getProperty("test.browser");
        if (s.equals("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.HelperApps.alwaysAsk.force", false);
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", "C:\\tmp");
            profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
            profile.setPreference("browser.download.useDownloadDir", true);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/x-gzip, image/bmp");
            driverInput = new FirefoxDriver();


        } else if (s.equals("iexplore")) {
            System.getProperty("webdriver.ie.driver");
            driverInput = new InternetExplorerDriver();

        } else if (s.equals("chrome")) {
            System.getProperty("chrome.driver");
            driverInput = new ChromeDriver();

        } else if (s.equals("ghost")) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("phantomjs.binary.path", "C:\\tools\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
            driverInput = new PhantomJSDriver(desiredCapabilities);

        } else {
            throw new AssertionError("Unsupported browser: " + System.getProperty("test.browser"));
        }
        driverInput.manage().timeouts().implicitlyWait(
                Integer.parseInt(System.getProperty("test.timeout")),
                TimeUnit.SECONDS
        );
        Driver.set(driverInput);
    }

    public static void tearDown() {
        Driver.get().quit();
    }
}

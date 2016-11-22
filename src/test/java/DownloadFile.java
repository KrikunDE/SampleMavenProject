
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 17.11.2016.
 */
public class DownloadFile extends BaseTest {
    @Test
    public void download() {
        WebDriver driver = Driver.get();
        driver.get("http://www.seleniumhq.org/download/");
        driver.findElement(By.xpath(".//a[contains (.,'bit Windows IE')]")).click();
        FluentWait<File> waiter = new FluentWait<File>(new File("C:\\Users\\User\\Downloads\\IEDriverServer_Win32_2.53.1.zip"));
        waiter.withTimeout(30, TimeUnit.SECONDS);
        waiter.pollingEvery(1000, TimeUnit.MILLISECONDS);
        waiter.until(new Predicate<File>() {

            @Override
            public boolean apply(File input) {
                return input.exists() && input.length() > 0;
            }
        });

    }
}

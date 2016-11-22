
;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 17.11.2016.
 */
public class UploadFile extends BaseTest {

    @Test
    public void uploadfile() {
        WebDriver driver = Driver.get();
        driver.get("http://image.online-convert.com/convert-to-bmp");
        driver.findElement(By.xpath(".//*[@id='file']")).sendKeys("C:\\tmp\\logo-top.png");
        driver.findElement(By.xpath(".//*[@id='submit_button']")).click();
        FluentWait<File> waiter = new FluentWait<File>(new File("C:\\Users\\User\\Downloads\\logo-top.bmp"));
        waiter.withTimeout(30, TimeUnit.SECONDS);
        waiter.pollingEvery(1000, TimeUnit.MILLISECONDS);
        waiter.until(new Predicate<File>() {
            @Override
            public boolean apply(File input) {
                return input.exists() && input.length() > 0;
            }
        });
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='post_options']/h2")).getText(), "You now have the following options:",
                "Confirmation text is not as expected");

    }


}

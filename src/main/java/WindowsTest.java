


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by User on 17.11.2016.
 */
public class WindowsTest extends BaseTest {

    @Test
    public void WT (){

        WebDriver driver = Driver.get();
        driver.get("http://www.quackit.com/html/html_editors/scratchpad/preview.cfm?example=/html/codes/html_open_link_in_new_window");
        String initialHandle = driver.getWindowHandles().iterator().next();
        driver.findElement(By.xpath("html/body/a")).click();
        Set<String> newHandles = driver.getWindowHandles();
        Reporter.log(newHandles.size() + "",2, true);
        Assert.assertTrue(newHandles.remove(initialHandle), "Old hangle wasn't removed");
        driver.switchTo().window(newHandles.iterator().next());
        Assert.assertEquals(driver.findElements(By.linkText("HTML5")).size(), 2,
                "New window has wrong content");
        driver.close();
        driver.switchTo().window(initialHandle);
        Assert.assertEquals(driver.findElements(By.xpath("html/body/a")).size(), 1,
                "Old window has wrong content");
    }
}


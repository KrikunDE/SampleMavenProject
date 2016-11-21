

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;



public class JavaSimpleAlertTest  extends BaseTest {


    @Test
    public void alert() throws IOException {
        WebDriver driver = Driver.get();
        driver.get("http://javascript.info/tutorial/hello-world");
        driver.findElement(By.xpath(".//input [@type ='button']")).click();
        Alert alert = driver.switchTo().alert();
       // Assert.assertEquals(alert.getText(), ("Hello, World!"), "Alert is not as expected");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
        alert.accept();
    }



}

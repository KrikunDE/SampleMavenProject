

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 * Created by User on 17.11.2016.
 */
public class DragAndDrop extends BaseTest {

    @Test
    public void DragNDrop() {
        WebDriver driver = Driver.get();
        driver.get(" https://jqueryui.com/resources/demos/draggable/sortable.html");
        WebElement source = driver.findElement(By.xpath(".//*[@id='draggable']"));
        WebElement target = driver.findElement(By.xpath(".//*[@id='sortable']/li[4]"));
        Actions actions = new Actions(driver);
        Action action = actions.clickAndHold(source).moveByOffset(50, 0).release(target).build();
        action.perform();
    }
}


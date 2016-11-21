

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 17.11.2016.
 */
public class BaseTest {

    @BeforeTest
    public void init() {
        Driver.init();
    }

    @AfterTest
    public void cleanup() {
        Driver.tearDown();

    }


}

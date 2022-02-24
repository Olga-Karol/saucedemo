package mavenParams;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExternalPropertiesTest {

    @Test
    public void runTest(){
        String externalProperty = System.getProperty("environment");
        Assert.assertEquals(externalProperty, "staging");
    }
}

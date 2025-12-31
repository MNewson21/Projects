package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest  {

    public AppTest() {
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    public void testApp(){
        assertTrue(true);
    }

}

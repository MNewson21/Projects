package org.example;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AppTest extends ApplicationTest {
    static{
        System.setProperty("testfx.robot", "glass");
        System.setProperty("java.awt.headless", "true");
        System.setProperty("monocle.platform", "Headless");
//        System.setProperty("prism.order", "sw");
//        System.setProperty("prism.text", "t2k");
    }





    private App app;


    @Override
    public void start(Stage stage) throws Exception {
        app = new App();
        app.start(stage);
    }

    public AppTest() {
    }

    @BeforeAll
    public static  void headless(){
        System.setProperty("testfx.robot", "glass");
        System.setProperty("java.awt.headless", "true");
        System.setProperty("monocle.platform", "Headless");
        //System.setProperty("prism.order", "sw");
        //System.setProperty("prism.text", "t2k");

    }

    @Test
    public void testApp(){
        assertTrue(true);
    }

    @Test
    public void menuTest(){
        FxAssert.verifyThat("#APANE", Node::isVisible);
        sleep(200);
        clickOn("#StartButton");
        WaitForAsyncUtils.waitForFxEvents();
        sleep(200);
        FxAssert.verifyThat("#TimerText", Node::isVisible);
    }

    @Test
    public void MovementTest(){
        clickOn("#StartButton");
        WaitForAsyncUtils.waitForFxEvents();
        sleep(200);
        TurtleEntity turtle = app.getController().getGameController().getGame().getEntityController().getTurtle();

        double turtleX = turtle.x;

        press(KeyCode.A).release(KeyCode.A);
        sleep(50);
        press(KeyCode.A).release(KeyCode.A);
        sleep(50);
        press(KeyCode.A).release(KeyCode.A);
        sleep(50);


        assertNotEquals(turtleX, turtle.x);



    }




}

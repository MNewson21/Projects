package org.example;

import javafx.scene.image.Image;

import java.util.Objects;

public class ImageHolder {
    public int turtleidleframe = 0;

    public Image ImageGenerator(String filepath){
        return new Image(
                Objects.requireNonNull(

                        getClass().getResourceAsStream(filepath),
                        "Cannot find the file: " + filepath
                )
        );
    }

    public Image[] TurtleIdle = new Image[] {
        ImageGenerator("/Sprites/Turtle/sprite-1-1.png"),
        ImageGenerator("/Sprites/Turtle/sprite-1-2.png"),
        ImageGenerator("/Sprites/Turtle/sprite-1-3.png"),
        ImageGenerator("/Sprites/Turtle/sprite-1-4.png"),
        ImageGenerator("/Sprites/Turtle/sprite-1-5.png"),
        ImageGenerator("/Sprites/Turtle/sprite-1-6.png")

    };





//    new Image(Objects.requireNonNull(
//            getClass().getResource("/Sprites/Turtle/sprite-1-1.png"),
//            "Missing resource: /Sprites/Turtle/sprite-1-1.png"
//                    ).toExternalForm()
//    )
}

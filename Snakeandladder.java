package com.example.snakeandladder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Snakeandladder extends Application {
    public static Group root;
    @Override
    public void start(Stage stage) throws IOException {

        root=new Group();
        GamePage page=new GamePage();//to access the gamepage contents
        root.getChildren().add(page.root);//childern is for multiple pages

        Scene scene = new Scene(root, 800, 500);//pane size
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

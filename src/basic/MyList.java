package basic;

import javafx.stage.Stage;

// Basic Imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Layout Imports
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class MyList {
    public void MyListScreen (Stage primaryStage) {
        Button HomeMenu = new Button();
        HomeMenu.setText("Back");
        HomeMenu.setMaxSize(100, 50);
        HomeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hi");
            }
        });
        primaryStage.setScene(HomeMenu);
    }
}

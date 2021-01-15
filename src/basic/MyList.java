package basic;

// Basic Imports
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.*;
import java.util.*;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports

// Layout Imports
import javafx.scene.layout.VBox;

public class MyList {
    public static void MyListScreen (Stage primaryStage, ArrayList<Games> GamesList) {
        Button HomeMenu = new Button();
        HomeMenu.setText("Back");
        HomeMenu.setMaxSize(100, 50);
        HomeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage, GamesList);
            }
        });
        VBox MyListVBox = new VBox(HomeMenu);
        primaryStage.setScene(new Scene(MyListVBox));
    }
}

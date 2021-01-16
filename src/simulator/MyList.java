package simulator;

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
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class MyList {
    public static void MyListScreen (Stage primaryStage, ArrayList<Games> GamesList) {
        GridPane myListGrid = new GridPane();
        myListGrid.setVgap(10);
        myListGrid.setHgap(10);
        myListGrid.setGridLinesVisible(false);
        myListGrid.setPadding(new Insets(25, 25, 25, 25));

        Button homeMenu = new Button();
        myListGrid.add(homeMenu, 0, 0);
        homeMenu.setText("Back");
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage, GamesList);
            }
        });

        primaryStage.setWidth(601);
        primaryStage.setScene(new Scene(myListGrid));
        primaryStage.show();
    }
}

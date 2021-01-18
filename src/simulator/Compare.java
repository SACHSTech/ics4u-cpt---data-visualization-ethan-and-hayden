package simulator;

// Basic Imports
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.*;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Layout Imports
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class Compare {
    public static void CompareScreen (Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {
        GridPane compareGrid = new GridPane();
        compareGrid.setVgap(10);
        compareGrid.setHgap(10);
        compareGrid.setGridLinesVisible(false);
        compareGrid.setPadding(new Insets(25, 25, 25, 25));
        Font CompareFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        Button homeMenu = new Button();
        compareGrid.add(homeMenu, 0, 0);
        homeMenu.setText("Back");
        homeMenu.setFont(CompareFont);
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage, MangaList, UserList);
            }
        });

        primaryStage.setWidth(601);
        primaryStage.setScene(new Scene(compareGrid));
        primaryStage.show();
    }
}

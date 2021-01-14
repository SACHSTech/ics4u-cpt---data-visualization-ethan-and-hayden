package basic;

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

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MyGamesList");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);

        Font MenuFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Full Screen Button
        Button FullScreenBtn = new Button();
        FullScreenBtn.setText("Full Screen");
        FullScreenBtn.setFont(MenuFont);
        FullScreenBtn.setWrapText(true);
        FullScreenBtn.setMaxSize(100, 50);
        FullScreenBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setFullScreen(true);
            }
        });

        // My List Button
        Button MyListBtn = new Button();
        MyListBtn.setText("My List");
        MyListBtn.setFont(MenuFont);
        MyListBtn.setWrapText(true);
        MyListBtn.setMaxSize(100, 50);
        MyListBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                MyListScreen(primaryStage);
            }
        });

        // Database Button
        Button DatabaseBtn = new Button();
        DatabaseBtn.setText("Database");
        DatabaseBtn.setFont(MenuFont);
        DatabaseBtn.setWrapText(true);
        DatabaseBtn.setMaxSize(100, 50);
        DatabaseBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setFullScreen(true);
            }
        });

        // Game Comparison Button
        Button CompareBtn = new Button();
        CompareBtn.setText("Compare");
        CompareBtn.setFont(MenuFont);
        CompareBtn.setWrapText(true);
        CompareBtn.setMaxSize(100, 50);
        CompareBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setFullScreen(true);
            }
        });

        // Setting up Background of Menu
        VBox MenuVBox = new VBox(MyListBtn, DatabaseBtn, CompareBtn, FullScreenBtn);
        MenuVBox.setAlignment(Pos.CENTER);
        MenuVBox.setStyle("-fx-background-color: #336699;");
        MenuVBox.setSpacing(20);
        primaryStage.setScene(new Scene(MenuVBox));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
 

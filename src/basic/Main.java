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
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MyGamesList");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);

        Font MenuFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

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
        VBox MenuVBox = new VBox(FullScreenBtn);
        MenuVBox.setPadding(new Insets(15, 12, 15, 12));
        MenuVBox.setSpacing(10);
        MenuVBox.setStyle("-fx-background-color: #336699;");
        MenuVBox.setSpacing(100);
        primaryStage.setScene(new Scene(MenuVBox, 200, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
 

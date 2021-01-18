package simulator;

// Basic Libraries Import
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

// Layout Imports
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class Main extends Application {

    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("'My Manga List' by Ethan Lai");
        primaryStage.setHeight(500);
        primaryStage.setWidth(600);
        primaryStage.setResizable(false);
        String fileName = "src/simulator/AllManga.csv";
        ArrayList<Manga> MangaList = Manga.csvToObject(fileName);
        ArrayList<UserManga> UserList = new ArrayList<UserManga>();
        mainMenu(primaryStage, MangaList, UserList);
    }
    
    public static void mainMenu(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {
        
        // Refreshes stage
        primaryStage.setWidth(600);

        GridPane menuGrid = new GridPane();
        menuGrid.setVgap(10);
        menuGrid.setHgap(10);
        menuGrid.setGridLinesVisible(false);
        menuGrid.setPadding(new Insets(25, 25, 25, 25));
        Font MenuFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Title
        Text mainTitle = new Text("My Manga List");
        mainTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        menuGrid.add(mainTitle, 0, 0);

        // My List Button
        Button MyListBtn = new Button();
        menuGrid.add(MyListBtn, 0, 1);
        MyListBtn.setText("My List");
        MyListBtn.setFont(MenuFont);
        MyListBtn.setWrapText(true);
        MyListBtn.setMaxSize(100, 50);
        MyListBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                MyList.MyListScreen(primaryStage, MangaList, UserList);
            }
        });

        // Database Button
        Button DatabaseBtn = new Button();
        menuGrid.add(DatabaseBtn, 0, 2);
        DatabaseBtn.setText("Database");
        DatabaseBtn.setFont(MenuFont);
        DatabaseBtn.setWrapText(true);
        DatabaseBtn.setMaxSize(100, 50);
        DatabaseBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Database.DatabaseScreen(primaryStage, MangaList, UserList);
            }
        });

        // Game Comparison Button
        Button CompareBtn = new Button();
        menuGrid.add(CompareBtn, 0, 3);
        CompareBtn.setText("Compare");
        CompareBtn.setFont(MenuFont);
        CompareBtn.setWrapText(true);
        CompareBtn.setMaxSize(100, 50);
        CompareBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Compare.CompareScreen(primaryStage, MangaList, UserList);
            }
        });

        // Setting up Background of Menu
        primaryStage.setScene(new Scene(menuGrid));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }
}
 

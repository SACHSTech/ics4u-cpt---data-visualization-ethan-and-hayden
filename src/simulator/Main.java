package simulator;

// Importing other folders
import simulator.ObjectClasses.*;
import simulator.MethodsClasses.*;

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
        Account.signInScreen(primaryStage);
    }

    public static void mainMenuScreen(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Refreshes stage
        primaryStage.setWidth(601);

        // Setting up gridpane
        GridPane menuGrid = new GridPane();
        menuGrid.setVgap(10);
        menuGrid.setHgap(10);
        menuGrid.setGridLinesVisible(false);
        menuGrid.setPadding(new Insets(25, 25, 25, 25));
        Font menuFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Title
        Text mainTitle = new Text(currentAccount.getUsername() + "'s Manga List");
        mainTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        menuGrid.add(mainTitle, 0, 0);

        // My List Button
        Button myListBtn = new Button();
        menuGrid.add(myListBtn, 0, 1);
        myListBtn.setText("My List");
        myListBtn.setFont(menuFont);
        myListBtn.setWrapText(true);
        myListBtn.setMaxSize(100, 50);
        myListBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MyList.MyListScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Database Button
        Button databaseBtn = new Button();
        menuGrid.add(databaseBtn, 0, 2);
        databaseBtn.setText("Database");
        databaseBtn.setFont(menuFont);
        databaseBtn.setWrapText(true);
        databaseBtn.setMaxSize(100, 50);
        databaseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Database.DatabaseScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Game Comparison Button
        Button compareBtn = new Button();
        menuGrid.add(compareBtn, 0, 3);
        compareBtn.setText("Graphs");
        compareBtn.setFont(menuFont);
        compareBtn.setWrapText(true);
        compareBtn.setMaxSize(100, 50);
        compareBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Compare.CompareScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Button to log off
        Button logOffBtn = new Button();
        menuGrid.add(logOffBtn, 0, 4);
        logOffBtn.setText("Save & Quit");
        logOffBtn.setFont(menuFont);
        logOffBtn.setWrapText(true);
        logOffBtn.setMaxSize(100, 50);
        logOffBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Account.savingToAccount(primaryStage, "src/simulator/Accounts.txt", userList, currentAccount);
                    
                }catch (IOException e){
                    e.printStackTrace();
                }
                primaryStage.close();
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
 

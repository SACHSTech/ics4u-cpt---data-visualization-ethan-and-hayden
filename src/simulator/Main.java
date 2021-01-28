package simulator;

import simulator.ObjectClasses.*;
import simulator.MethodsClasses.*;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;



public class Main extends Application {

    /**
     * Start method. Runs when the application starts.
     * 
     * @param primaryStage
     */
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("'My Manga List' by Ethan Lai");
        primaryStage.setHeight(500);
        primaryStage.setWidth(600);
        primaryStage.setResizable(false);
        Account.signInScreen(primaryStage);
    }

    /**
     * Sets up a screen that displays the main menu
     * 
     * @param primaryStage
     * @param mangaList
     * @param userList
     * @param currentAccount
     */
    public static void mainMenuScreen(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Refreshes stage
        primaryStage.setWidth(601);

        // Setting up gridpane
        GridPane menuGrid = new GridPane();
        menuGrid.setVgap(40);
        menuGrid.setHgap(10);
        menuGrid.setGridLinesVisible(false);
        menuGrid.setAlignment(Pos.TOP_CENTER);
        menuGrid.setPadding(new Insets(25, 25, 25, 25));
        Font menuFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Title
        Label mainTitle = new Label(currentAccount.getUsername() + "'s Manga List");
        mainTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        menuGrid.add(mainTitle, 0, 0);
        GridPane.setHalignment(mainTitle, HPos.CENTER);

        // My List Button
        Button myListBtn = new Button();
        menuGrid.add(myListBtn, 0, 1);
        GridPane.setHalignment(myListBtn, HPos.CENTER);
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
        GridPane.setHalignment(databaseBtn, HPos.CENTER);
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
        GridPane.setHalignment(compareBtn, HPos.CENTER);
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
        GridPane.setHalignment(logOffBtn, HPos.CENTER);
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

    /**
     * Main method. Launches the application.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }
}
 

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

// Layout Imports
import javafx.scene.layout.VBox;

// Table Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Database {
    public static Parent createContent() {
        final ObservableList<Games> data = FXCollections.observableArrayList(
                new Games("Hi Lmao", false, 3, false, "", "", false, 0, 0, 0, "", "", false, 0, 0),
                new Games("Lol", false, 5, false, "", "", false, 0, 0, 0, "", "", false, 0, 0));
 
        TableColumn titleColumn = new TableColumn("strTitle");
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("strTitle"));

        TableColumn handheldColumn = new TableColumn<>("isHandheld");
        handheldColumn.setText("Handheld?");
        handheldColumn.setCellValueFactory(new PropertyValueFactory("isHandheld"));

        TableColumn maxplayersColumn = new TableColumn<>("intMaxPlayers");
        maxplayersColumn.setText("Max Players");
        // maxplayersColumn.setMinWidth(200);
        maxplayersColumn.setCellValueFactory(new PropertyValueFactory("intMaxPlayers"));

        TableColumn multiplayerColumn = new TableColumn();
        multiplayerColumn.setText("Multiplayer?");
        multiplayerColumn.setCellValueFactory(new PropertyValueFactory("isMultiplayer"));

        TableColumn genreColumn = new TableColumn();
        genreColumn.setText("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory("strGenre"));

        TableColumn publisherColumn = new TableColumn();
        publisherColumn.setText("Publisher");
        publisherColumn.setCellValueFactory(new PropertyValueFactory("strPublisher"));

        TableColumn sequelColumn = new TableColumn();
        sequelColumn.setText("Sequel?");
        sequelColumn.setCellValueFactory(new PropertyValueFactory("isSequel"));

        TableColumn scoreColumn = new TableColumn();
        scoreColumn.setText("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory("intScore"));

        TableColumn salesColumn = new TableColumn();
        salesColumn.setText("Sales");
        salesColumn.setCellValueFactory(new PropertyValueFactory("dblSales"));

        TableColumn priceColumn = new TableColumn();
        priceColumn.setText("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory("dblPrice"));

        TableColumn consoleColumn = new TableColumn();
        consoleColumn.setText("Console");
        consoleColumn.setCellValueFactory(new PropertyValueFactory("strConsole"));

        TableColumn ratingColumn = new TableColumn();
        ratingColumn.setText("Rating");
        ratingColumn.setCellValueFactory(new PropertyValueFactory("strRating"));

        TableColumn rereleaseColumn = new TableColumn();
        rereleaseColumn.setText("Re-release?");
        rereleaseColumn.setCellValueFactory(new PropertyValueFactory("isRereleased"));

        TableColumn yearColumn = new TableColumn();
        yearColumn.setText("Year Released");
        yearColumn.setCellValueFactory(new PropertyValueFactory("intYear"));

        TableColumn playtimeColumn = new TableColumn();
        playtimeColumn.setText("Play time");
        playtimeColumn.setCellValueFactory(new PropertyValueFactory("dblPlaytime"));

        final TableView tableView = new TableView();
        tableView.setItems(data);
        tableView.getColumns().addAll(titleColumn, handheldColumn, maxplayersColumn, multiplayerColumn, genreColumn, publisherColumn, sequelColumn, scoreColumn, salesColumn, priceColumn, consoleColumn, ratingColumn, rereleaseColumn, yearColumn, playtimeColumn);
        return tableView;
    }

    public static void DatabaseScreen (Stage primaryStage) {

        // Table
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();

        /*
        // Home Menu Button
        Button HomeMenu = new Button();
        HomeMenu.setText("Back");
        HomeMenu.setMaxSize(100, 50);
        HomeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage);
            }
        });
        VBox MyListVBox = new VBox(HomeMenu);
        primaryStage.setScene(new Scene(MyListVBox)); */
    }
}


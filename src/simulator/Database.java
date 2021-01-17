package simulator;

// Basic Imports
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Table Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

// Layout Imports
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Database {
    public static Parent createContent(ArrayList<Games> GamesList) {
        final ObservableList<Games> data = FXCollections.observableArrayList(GamesList);
 
        TableColumn titleColumn = new TableColumn("strTitle");
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("strTitle"));

        TableColumn maxplayersColumn = new TableColumn<>("intMaxPlayers");
        maxplayersColumn.setText("Max Players");
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

        TableColumn scoreColumn = new TableColumn();
        scoreColumn.setText("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory("intScore"));

        TableColumn salesColumn = new TableColumn();
        salesColumn.setText("Sales (in Millions of Dollars)");
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

        TableColumn yearColumn = new TableColumn();
        yearColumn.setText("Year Released");
        yearColumn.setCellValueFactory(new PropertyValueFactory("intYear"));

        TableColumn playtimeColumn = new TableColumn();
        playtimeColumn.setText("Play Time (Mean Average in Hours)");
        playtimeColumn.setCellValueFactory(new PropertyValueFactory("dblPlaytime"));

        final TableView tableView = new TableView();
        tableView.setEditable(false);
        tableView.setItems(data);
        tableView.getColumns().addAll(titleColumn, maxplayersColumn, multiplayerColumn, genreColumn, publisherColumn, scoreColumn, salesColumn, priceColumn, consoleColumn, ratingColumn, yearColumn, playtimeColumn);
        return tableView;
    }

    public static void DatabaseScreen (Stage primaryStage, ArrayList<Games> GamesList, ArrayList<Games> UserList) {
        
        GridPane databaseGrid = new GridPane();
        databaseGrid.setVgap(10);
        databaseGrid.setHgap(10);
        databaseGrid.setGridLinesVisible(false);
        databaseGrid.setPadding(new Insets(25, 25, 25, 25));
        
        // Table
        databaseGrid.add(createContent(GamesList), 0, 0);

        // Home Menu Button
        Button homeMenu = new Button();
        databaseGrid.add(homeMenu, 0, 1);
        homeMenu.setText("Back");
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage, GamesList, UserList);
            }
        });

        // Refreshes the stage so the table would not be glitched
        primaryStage.setScene(new Scene(databaseGrid));
        primaryStage.setWidth(601);
        primaryStage.show();
    }
}


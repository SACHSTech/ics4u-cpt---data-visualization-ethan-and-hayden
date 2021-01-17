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
    public static Parent createContent(ArrayList<Manga> MangaList) {
        final ObservableList<Manga> data = FXCollections.observableArrayList(MangaList);
 
        TableColumn titleColumn = new TableColumn();
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("strTitle"));

        TableColumn typeColumn = new TableColumn<>();
        typeColumn.setText("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory("strType"));

        TableColumn chaptersColumn = new TableColumn();
        chaptersColumn.setText("Chapters");
        chaptersColumn.setCellValueFactory(new PropertyValueFactory("strChapter"));

        TableColumn genreColumn = new TableColumn();
        genreColumn.setText("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory("strGenre"));

        TableColumn authorColumn = new TableColumn();
        authorColumn.setText("Publisher");
        authorColumn.setCellValueFactory(new PropertyValueFactory("strAuthor"));

        TableColumn serializationColumn = new TableColumn();
        serializationColumn.setText("Serialization");
        serializationColumn.setCellValueFactory(new PropertyValueFactory("strSerialization"));

        TableColumn scoreColumn = new TableColumn();
        scoreColumn.setText("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory("dblScore"));

        TableColumn rankedColumn = new TableColumn();
        rankedColumn.setText("Ranked");
        rankedColumn.setCellValueFactory(new PropertyValueFactory("intRank"));

        TableColumn popularityColumn = new TableColumn();
        popularityColumn.setText("Popularity");
        popularityColumn.setCellValueFactory(new PropertyValueFactory("intPopularity"));

        TableColumn scorenumbersColumn = new TableColumn();
        scorenumbersColumn.setText("Scores");
        scorenumbersColumn.setCellValueFactory(new PropertyValueFactory("intScoreNumbers"));

        final TableView tableView = new TableView();
        tableView.setEditable(false);
        tableView.setItems(data);
        tableView.getColumns().addAll(titleColumn, typeColumn, chaptersColumn, genreColumn, authorColumn, serializationColumn, scoreColumn, rankedColumn, popularityColumn, scorenumbersColumn);
        return tableView;
    }

    public static void DatabaseScreen (Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<Manga> UserList) {
        
        GridPane databaseGrid = new GridPane();
        databaseGrid.setVgap(10);
        databaseGrid.setHgap(10);
        databaseGrid.setGridLinesVisible(false);
        databaseGrid.setPadding(new Insets(25, 25, 25, 25));
        
        // Table
        databaseGrid.add(createContent(MangaList), 0, 0);

        // Home Menu Button
        Button homeMenu = new Button();
        databaseGrid.add(homeMenu, 0, 1);
        homeMenu.setText("Back");
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage, MangaList, UserList);
            }
        });

        // Refreshes the stage so the table would not be glitched
        primaryStage.setScene(new Scene(databaseGrid));
        primaryStage.setWidth(601);
        primaryStage.show();
    }
}


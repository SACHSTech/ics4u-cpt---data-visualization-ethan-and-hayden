package simulator;

// Basic Imports
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.io.*;
import java.util.*;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

// Layout Imports
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

// Property Imports 
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Table Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyList {
    private static Parent searchBox(ArrayList<Manga> MangaList, ArrayList<Manga> UserList, GridPane myListGrid) {    
        TextField searchText = new TextField();
        searchText.setPromptText("Enter game name here");
        searchText.setMaxSize(140, TextField.USE_COMPUTED_SIZE);
        searchText.setOnKeyReleased((KeyEvent currentKey) -> {
            if (currentKey.getCode() == KeyCode.ENTER) {
                String strKey = searchText.getText();
                AddingToList(MangaList, strKey, UserList);
                myListGrid.add(createMyList(UserList), 0, 1);
                searchText.clear();
            }
        });
        return searchText;
    }

    // Linear Search
    private static void AddingToList (ArrayList<Manga> MangaList, String strKey, ArrayList<Manga> UserList) {
        int intCheck = 0;
        boolean isItemInUserList = false;
        for (Manga Current : MangaList) {
            if (strKey.equalsIgnoreCase(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", ""))) {
                for (Manga CurrentUserList : UserList) {
                    if (((CurrentUserList.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "").equalsIgnoreCase(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", ""))) {
                        isItemInUserList = true;
                        break;
                    }else {
                        isItemInUserList = false;
                    }
                }
                if (isItemInUserList == true) {
                    System.out.println("Item is already in your list");
                }else if (isItemInUserList == false) {
                    UserList.add(Current);
                }
            }else {
                intCheck++;
                if (intCheck == MangaList.size()) {
                    System.out.println("Item is not in the database");
                }
            }
        }
    }

    public static Parent createMyList(ArrayList<Manga> UserList) {
        final ObservableList<Manga> data = FXCollections.observableArrayList(UserList);
 
        TableColumn titleColumn = new TableColumn("strTitle");
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("strTitle"));

        TableColumn scoreColumn = new TableColumn();
        scoreColumn.setText("Your Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory("dblScore"));

        TableColumn chaptersColumn = new TableColumn();
        chaptersColumn.setText("Read Chapters");
        chaptersColumn.setCellValueFactory(new PropertyValueFactory("strChapter"));

        final TableView tableView = new TableView();
        tableView.setEditable(true);
        tableView.setItems(data);
        tableView.getColumns().addAll(titleColumn, scoreColumn, chaptersColumn);
        return tableView;
    } 

    public static void MyListScreen (Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<Manga> UserList) {

        // Creating GridPane
        GridPane myListGrid = new GridPane();
        myListGrid.setVgap(10);
        myListGrid.setHgap(10);
        myListGrid.setGridLinesVisible(false);
        myListGrid.setPadding(new Insets(25, 25, 25, 25));

        // Creating home button
        Button homeMenu = new Button();
        myListGrid.add(homeMenu, 0, 2);
        homeMenu.setText("Back");
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage, MangaList, UserList);
            }
        });

        // Creating search box
        myListGrid.add(searchBox(MangaList, UserList, myListGrid), 0 ,0);

        // Creating User's List
        myListGrid.add(createMyList(UserList), 0, 1);

        primaryStage.setWidth(601);
        primaryStage.setScene(new Scene(myListGrid));
        primaryStage.show();
    }
}

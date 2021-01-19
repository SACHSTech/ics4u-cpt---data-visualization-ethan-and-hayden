package simulator;

// Basic Imports
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.util.*;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Layout Imports
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.geometry.Side;

// Table Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.CheckBoxTableCell;

public class MyList {
    private static Parent searchBox(ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, GridPane myListGrid, Text AddingItemText) { 
        
        // Context Box
        ContextMenu autoSuggest = new ContextMenu();
        
        TextField searchText = new TextField();
        searchText.setPromptText("Enter game name here");
        searchText.setMaxSize(140, TextField.USE_COMPUTED_SIZE);

        searchText.setOnKeyTyped((KeyEvent currentKeyChar) -> {
            String strPressedChar = searchText.getText();
            SearchingByChar(strPressedChar, MangaList, autoSuggest, searchText);
        });
        searchText.setOnKeyReleased((KeyEvent currentKey) -> {
            autoSuggest.show(searchText, Side.BOTTOM, 0, 0);
            if (currentKey.getCode() == KeyCode.ENTER) {
                String strKey = searchText.getText();
                AddingToList(MangaList, strKey, UserList, AddingItemText, myListGrid);
                myListGrid.add(createMyList(UserList), 0, 1);
                searchText.clear();
                autoSuggest.hide();
            }
        });
        return searchText;
    }

    private static void SearchingByChar(String strPressedChar, ArrayList<Manga> MangaList, ContextMenu autoSuggest, TextField searchText) {
        autoSuggest.getItems().clear();
        int intCheck = 0;
        autoSuggest.setOnAction(e -> searchText.setText(((MenuItem)e.getTarget()).getText()));
        for (Manga Current : MangaList) {
            if (((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "").length() >= strPressedChar.length()) {
                if (((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "").substring(0, strPressedChar.length()).equalsIgnoreCase(strPressedChar)) {
                    intCheck++;
                    if (intCheck == 5) {
                        autoSuggest.getItems().addAll(new MenuItem(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "")));
                        break;
                    }else if (intCheck < 5 && intCheck > 0) {
                        autoSuggest.getItems().addAll(new MenuItem(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "")));
                    }else if (intCheck <= 0 || intCheck > 5) {
                        autoSuggest.hide();
                        break;
                    }
                }
            }else {
                continue;
            }
        }
    }

    // Linear Search
    private static void AddingToList(ArrayList<Manga> MangaList, String strKey, ArrayList<UserManga> UserList, Text AddingItemText, GridPane myListGrid) {
        int intCheck = 0;
        boolean isItemInUserList = false;
        for (Manga Current : MangaList) {
            if (strKey.equalsIgnoreCase(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", ""))) {
                for (UserManga CurrentUserList : UserList) {
                    if (((CurrentUserList.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "").equalsIgnoreCase(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", ""))) {
                        isItemInUserList = true;
                        break;
                    }else {
                        isItemInUserList = false;
                    }
                }
                if (isItemInUserList == true) {
                    AddingItemText.setText("This is already in your list");
                }else if (isItemInUserList == false) {
                    UserList.add(UserManga.convertToUserManga(Current));
                    AddingItemText.setText("Added Manga");
                }
            }else {
                intCheck++;
                if (intCheck == MangaList.size()) {
                    AddingItemText.setText("This is not in the database");
                }
            }
        }
    }
    
    // Recursion and Linear Search
    private static void deleteFromList(ArrayList<UserManga> UserList, GridPane myListGrid) {
        int intCheck = 0;
        for (UserManga Current : UserList) {
            intCheck++;
            if (Boolean.parseBoolean((Current.isUserSelectedProperty()).toString().replace("BooleanProperty [value: ", "").replace("]", "")) == true) {
                UserList.remove(Current);
                break;
            }
        }
        // System.out.println("UserList size: "+ UserList.size());
        // System.out.println("intCheck: " + intCheck);
        if (intCheck == UserList.size()) {
            myListGrid.add(createMyList(UserList), 0, 1);
        }else {
            deleteFromList(UserList, myListGrid);
        }
    }
        
    private static int totalReading(ArrayList<UserManga> UserList) {
        int intTotalReading = 0;
        for(UserManga Current : UserList) {
            if (((Current.strUserStatusProperty()).toString().replace("StringProperty [value: ", "").replace("]", "")).equalsIgnoreCase("Reading")) {
                intTotalReading++;
            }
        }
        return intTotalReading;
    }

    private static int totalFinished(ArrayList<UserManga> UserList) {
        int intTotalFinished = 0;
        for(UserManga Current : UserList) {
            if (((Current.strUserStatusProperty()).toString().replace("StringProperty [value: ", "").replace("]", "")).equalsIgnoreCase("Finished")) {
                intTotalFinished++;
            }
        }
        return intTotalFinished;
    }

    private static int totalDropped(ArrayList<UserManga> UserList) {
        int intTotalDropped = 0;
        for(UserManga Current : UserList) {
            if (((Current.strUserStatusProperty()).toString().replace("StringProperty [value: ", "").replace("]", "")).equalsIgnoreCase("Dropped")) {
                intTotalDropped++;
            }
        }
        return intTotalDropped;
    }

    private static double meanScore(ArrayList<UserManga> UserList) {
        double dblTotalScore = 0.00;
        int intTotalManga = UserList.size();
        double dblMeanScore = 0.00;
        if (intTotalManga != 0) {
            for(UserManga Current : UserList) {
                dblTotalScore = dblTotalScore + Integer.parseInt((Current.intUserScoreProperty()).toString().replace("IntegerProperty [value: ", "").replace("]", ""));
            }
            dblMeanScore = dblTotalScore / intTotalManga;
            return Math.round(dblMeanScore * 100) / 100.00;
        }
        return 0;
    }

    private static void refreshSummary(ArrayList<UserManga> UserList, Text MeanScoreText, Text TotalReadingText, Text TotalFinishedText, Text TotalDroppedText) {
        MeanScoreText.setText("Mean Score: " + meanScore(UserList));
        TotalReadingText.setText("Reading: " + totalReading(UserList));
        TotalFinishedText.setText("Finished: " + totalFinished(UserList));
        TotalDroppedText.setText("Dropped: " + totalDropped(UserList));
    }

    public static Parent createMyList(ArrayList<UserManga> UserList) {

        final ObservableList<UserManga> data = FXCollections.observableArrayList(UserList);
        
        TableColumn selectedColumn = new TableColumn<>();
        selectedColumn.setText("");
        selectedColumn.setPrefWidth(50);
        selectedColumn.setCellValueFactory(new PropertyValueFactory("isUserSelected"));
        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectedColumn));

        TableColumn titleColumn = new TableColumn("strTitle");
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("strTitle"));
        titleColumn.setPrefWidth(150);

        TableColumn scoreColumn = new TableColumn();
        scoreColumn.setText("Your Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory("intUserScore"));
        scoreColumn.setCellFactory(ComboBoxTableCell.forTableColumn(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        scoreColumn.setPrefWidth(100);
        
        TableColumn statusColumn = new TableColumn();
        statusColumn.setText("Your Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory("strUserStatus"));
        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Reading", "Finished", "Dropped"));
        statusColumn.setPrefWidth(100);

        final TableView tableView = new TableView();
        tableView.setEditable(true);
        tableView.setItems(data);
        tableView.getColumns().addAll(selectedColumn, titleColumn, scoreColumn, statusColumn);
        return tableView;
    } 

    public static void MyListScreen (Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {

        // Creating GridPane
        GridPane myListGrid = new GridPane();
        myListGrid.setVgap(10);
        myListGrid.setHgap(10);
        myListGrid.setGridLinesVisible(false);
        myListGrid.setPadding(new Insets(25, 25, 25, 25));
        Font MyListFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Creating TextBox
        Text AddingItemText = new Text("Click on cells to edit");
        AddingItemText.setTextAlignment(TextAlignment.CENTER);
        AddingItemText.setWrappingWidth(250);
        AddingItemText.setFont(MyListFont);

        // Creating Summary TextBox
        Font UserSummaryFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        Text MeanScoreText = new Text("Mean Score: " + meanScore(UserList));
        MeanScoreText.setTextAlignment(TextAlignment.CENTER);
        MeanScoreText.setWrappingWidth(120);
        MeanScoreText.setFont(UserSummaryFont);

        Text TotalReadingText = new Text("Reading: " + totalReading(UserList));
        TotalReadingText.setTextAlignment(TextAlignment.CENTER);
        TotalReadingText.setWrappingWidth(120);
        TotalReadingText.setFont(UserSummaryFont);

        Text TotalFinishedText = new Text("Finished: " + totalFinished(UserList));
        TotalFinishedText.setTextAlignment(TextAlignment.CENTER);
        TotalFinishedText.setWrappingWidth(120);
        TotalFinishedText.setFont(UserSummaryFont);

        Text TotalDroppedText = new Text("Dropped: " + totalDropped(UserList));
        TotalDroppedText.setTextAlignment(TextAlignment.CENTER);
        TotalDroppedText.setWrappingWidth(120);
        TotalDroppedText.setFont(UserSummaryFont);

        // Creating Summary Refresh Button
        Button summaryRefresh = new Button();
        summaryRefresh.setText("Refresh Summary");
        summaryRefresh.setFont(MyListFont);
        summaryRefresh.setPrefSize(120, 50);
        summaryRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refreshSummary(UserList, MeanScoreText, TotalReadingText, TotalFinishedText, TotalDroppedText);
            }
        });

        VBox UserSummaryBox = new VBox(summaryRefresh, MeanScoreText, TotalReadingText, TotalFinishedText, TotalDroppedText);
        UserSummaryBox.setSpacing(10);
        myListGrid.add(UserSummaryBox, 1, 1);

        // Adding search box and text field to HBox
        HBox UserInputTextBox = new HBox(searchBox(MangaList, UserList, myListGrid, AddingItemText), AddingItemText);
        UserInputTextBox.setSpacing(10);
        myListGrid.add(UserInputTextBox, 0, 0);

        // Creating home button
        Button homeMenu = new Button();
        myListGrid.add(homeMenu, 0, 2);
        homeMenu.setText("Back");
        homeMenu.setFont(MyListFont);
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage, MangaList, UserList);
            }
        });

        // Creating delete button
        Button deleteButton = new Button();
        myListGrid.add(deleteButton, 1, 0);
        deleteButton.setText("Delete Selected");
        deleteButton.setFont(MyListFont);
        deleteButton.setPrefSize(120, 50);
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                deleteFromList(UserList, myListGrid);
            }
        });

        // Creating User's List
        myListGrid.add(createMyList(UserList), 0, 1);

        primaryStage.setWidth(601);
        primaryStage.setScene(new Scene(myListGrid));
        primaryStage.show();
    }
}

package simulator.MethodsClasses;

import simulator.*;
import simulator.ObjectClasses.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import java.util.*;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.geometry.Side;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.CheckBoxTableCell;

public class MyList {

    /**
     * Method that shows the screen for the user's list.
     * 
     * @param primaryStage - stage that displays all scenes.
     * @param mangaList - an arraylist filled with Manga objects. 
     * @param userList - an arraylist filled with UserManga objects.
     * @param currentAccount - an Account object.
     */
    public static void myListScreen (Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Refreshes the stage.
        primaryStage.setWidth(600);

        // Creating temporary observable list that is identical to the userList arraylist.
        final ObservableList<UserManga> data = FXCollections.observableArrayList(userList);

        // Creating GridPane to organize children.
        GridPane myListGrid = new GridPane();
        myListGrid.setVgap(10);
        myListGrid.setHgap(10);
        myListGrid.setGridLinesVisible(false);
        myListGrid.setPadding(new Insets(25, 25, 25, 25));
        Font MyListFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Running method that creates a tableview that represents the user's List.
        myListGrid.add(createMyList(userList, data), 0, 1);

        // Creating textbox that tells what is currently happening to the user list.
        Text AddingItemText = new Text("Click on cells to edit");
        AddingItemText.setTextAlignment(TextAlignment.CENTER);
        AddingItemText.setWrappingWidth(200);
        AddingItemText.setFont(MyListFont);

        // Creating the user's data summary.
        Font UserSummaryFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        Text MeanScoreText = new Text("Mean Score: " + meanScore(userList));
        MeanScoreText.setTextAlignment(TextAlignment.CENTER);
        MeanScoreText.setWrappingWidth(120);
        MeanScoreText.setFont(UserSummaryFont);

        Text TotalReadingText = new Text("Reading: " + totalReading(userList));
        TotalReadingText.setTextAlignment(TextAlignment.CENTER);
        TotalReadingText.setWrappingWidth(120);
        TotalReadingText.setFont(UserSummaryFont);

        Text TotalFinishedText = new Text("Finished: " + totalFinished(userList));
        TotalFinishedText.setTextAlignment(TextAlignment.CENTER);
        TotalFinishedText.setWrappingWidth(120);
        TotalFinishedText.setFont(UserSummaryFont);

        Text TotalDroppedText = new Text("Dropped: " + totalDropped(userList));
        TotalDroppedText.setTextAlignment(TextAlignment.CENTER);
        TotalDroppedText.setWrappingWidth(120);
        TotalDroppedText.setFont(UserSummaryFont);

        // Creating Summary Refresh Button.
        Button summaryRefreshBtn = new Button();
        summaryRefreshBtn.setText("Refresh Summary");
        summaryRefreshBtn.setFont(MyListFont);
        summaryRefreshBtn.setPrefSize(120, 50);
        summaryRefreshBtn.setOnAction(new EventHandler<ActionEvent>() {

            // Event method that runs another method that refreshes the user's summary.
            @Override
            public void handle(ActionEvent event) {
                refreshSummary(userList, MeanScoreText, TotalReadingText, TotalFinishedText, TotalDroppedText);
            }
        });

        // Creating VBox to put the summary texts in.
        VBox UserSummaryBox = new VBox(summaryRefreshBtn, MeanScoreText, TotalReadingText, TotalFinishedText, TotalDroppedText);
        UserSummaryBox.setSpacing(10);
        myListGrid.add(UserSummaryBox, 1, 1);

        // Adding search box and text field to HBox.
        HBox UserInputTextBox = new HBox(searchBox(mangaList, userList, myListGrid, AddingItemText, data), AddingItemText);
        UserInputTextBox.setSpacing(10);
        myListGrid.add(UserInputTextBox, 0, 0);

        // Creating home button.
        Button homeMenuBtn = new Button();
        myListGrid.add(homeMenuBtn, 0, 2);
        homeMenuBtn.setText("Back");
        homeMenuBtn.setFont(MyListFont);
        homeMenuBtn.setMaxSize(100, 50);
        homeMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event that returns the user to the main menu.
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenuScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Creating delete button.
        Button deleteBtn = new Button();
        myListGrid.add(deleteBtn, 1, 0);
        deleteBtn.setText("Delete Selected");
        deleteBtn.setFont(MyListFont);
        deleteBtn.setPrefSize(120, 50);
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event that runs a method that deletes every selected Manga from the user's list.
            @Override
            public void handle(ActionEvent event) {
                deleteFromList(userList, myListGrid, data);
            }
        });

        // Setting gridpane to the stage.
        primaryStage.setScene(new Scene(myListGrid));
        primaryStage.show();
    }

    /**
     * Method that returns a search box so the user can search and add their desired manga into their list.
     * 
     * @param mangaList - an arraylist filled with Manga objects.
     * @param userList - an arraylist filled with UserManga objects.
     * @param myListGrid - a gridpane from the myList screen.
     * @param addingItemText - The text that describes what is happening to the user's list.
     * @param data - temporary data that is identical to the userList arraylist. It can be modified without damaging anything.
     * @return
     */
    private static Parent searchBox(ArrayList<Manga> mangaList, ArrayList<UserManga> userList, GridPane myListGrid, Text addingItemText, ObservableList<UserManga> data) { 
        
        // Creating context box where autosuggestions will be displayed.
        ContextMenu autoSuggest = new ContextMenu();
        
        // Creating textfield where the user can typed their desired manga's title.
        TextField searchText = new TextField();
        searchText.setPromptText("Enter manga name here");
        searchText.setPrefWidth(200);
        searchText.setMaxSize(200, TextField.USE_COMPUTED_SIZE);

        // Checking when the user types a character then runs a method that autosuggests mangas based on characters the user typed.
        searchText.setOnKeyTyped((KeyEvent currentKeyChar) -> {
            String strPressedChar = searchText.getText();
            SearchingByChar(strPressedChar, mangaList, autoSuggest, searchText);
        });

        // Checking when the user types enter then runs a method that adds the desired manga to the user's list.
        searchText.setOnKeyReleased((KeyEvent currentKey) -> {
            autoSuggest.show(searchText, Side.BOTTOM, 0, 0);
            if (currentKey.getCode() == KeyCode.ENTER) {
                String strKey = searchText.getText();
                AddingToList(mangaList, strKey, userList, addingItemText, myListGrid);
                data.clear();
                data.addAll(userList);
                searchText.clear();
                autoSuggest.hide();
            }
        });
        return searchText;
    }

    /**
     * Method that adds automatic suggestions based on current characters in the search box.
     * 
     * @param strPressedChar - what string is currently in the search box.
     * @param mangaList - an arraylist of Manga objects.
     * @param autoSuggest - context menu where the automatic suggestions will be displayed.
     * @param searchText - the textfield where the user types their string.
     */
    private static void SearchingByChar(String strPressedChar, ArrayList<Manga> mangaList, ContextMenu autoSuggest, TextField searchText) {

        // Clears the context menu.
        autoSuggest.getItems().clear();
        int intCheck = 0;

        // Sets the searchText as the name of the item that the user clicked on.
        autoSuggest.setOnAction(e -> searchText.setText(((MenuItem)e.getTarget()).getText()));

        // Iterates through the manga database.
        for (Manga current : mangaList) {

            // Checking if the current manga's title's length is greater than the string that the user typed in the search box.
            if (current.getTitle().length() >= strPressedChar.length()) {

                // Checking if the current manga's substring is equals to the string that the user typed in the search box.
                if (current.getTitle().substring(0, strPressedChar.length()).equalsIgnoreCase(strPressedChar)) {
                    intCheck++;

                    // Limits the amount of autosuggestions to 5. Hides the context menu if no manga is found with the same subtring.
                    if (intCheck == 5) {
                        autoSuggest.getItems().addAll(new MenuItem(current.getTitle()));
                        break;     
                    }else if (intCheck < 5 && intCheck > 0) {
                        autoSuggest.getItems().addAll(new MenuItem(current.getTitle()));
                    }else if (intCheck <= 0 || intCheck > 5) {
                        autoSuggest.hide();
                        break;
                    }
                }
            
            // If the manga's title is less than the string that the user wrote in the search box, skips over the current manga in the for loop.
            }else {
                continue;
            }
        }
    }

    /**
     * Search method that uses linear search to find whether the user's inputted string in the search box matches with a manga's title in the database.
     * 
     * @param mangaList - an arraylist filled with Manga objects.
     * @param strKey - the current string that the user wrote in the textfield.
     * @param userList - an arraylist filled with UserManga objects.
     * @param addingItemText - Text object that describes what is happening to the user's list.
     * @param myListGrid - gridpane from the myList screen.
     */
    private static void AddingToList(ArrayList<Manga> mangaList, String strKey, ArrayList<UserManga> userList, Text addingItemText, GridPane myListGrid) {

        // Initializing variables.
        int intCheck = 0;
        boolean isItemInUserList = false;

        // Iterating through the Manga database.
        for (Manga current : mangaList) {

            // Checking if the user's string in the textfield matches the title of a manga in the database.
            if (strKey.equalsIgnoreCase(current.getTitle())) {

                // Iterating through the User's list.
                for (UserManga currentUserList : userList) {

                    // Checking if the current manga's title matches the title of a manga that is already in the user's list. If it is, sets boolean to true and breaks the loop.
                    if (currentUserList.getTitle().equalsIgnoreCase(current.getTitle())) {
                        isItemInUserList = true;
                        break;
                    }else {
                        isItemInUserList = false;
                    }
                }

                // If the manga is already in the user's list, sets text that explains that the manga is already in the user's list.
                if (isItemInUserList == true) {
                    addingItemText.setText("This is already in your list");
                
                // Otherwise, adds the manga to the user's list.
                }else if (isItemInUserList == false) {
                    userList.add(UserManga.convertToUserManga(current));
                    addingItemText.setText("Added Manga");
                }
            }else {
                intCheck++;

                // Checks if loop reaches the end of the database. If it reaches the end, sets text that explains that the manga is not part of the database.
                if (intCheck == mangaList.size()) {
                    addingItemText.setText("This is not in the database");
                }
            }
        }
    }
    
    /**
     * Method that uses recursion and linear search to remove selected UserManga from the User's list.
     * 
     * @param UserList
     * @param myListGrid
     * @param data
     */
    private static void deleteFromList(ArrayList<UserManga> userList, GridPane myListGrid, ObservableList<UserManga> data) {

        // Initializing variables.
        int intCheck = 0;

        // For loop that iterates through the user's list.
        for (UserManga current : userList) {
            intCheck++;

            // Checking if the current manga is selected.
            if (current.getUserSelected() == true) {

                // Removes current manga and breaks the for loop.
                userList.remove(current);
                break;
            }
        }

        // Base case. Checks if the loop reached the end of the user's list. If so, resets the data and adds the new list with the deleted manga to the data.
        if (intCheck == userList.size()) {
            data.clear();
            data.addAll(userList);
        
        // Recursive call. If the loop did not reach the end of the user's list, runs itself again.
        }else {
            deleteFromList(userList, myListGrid, data);
        }
    }
    
    /**
     * Method that counts how many manga that are currently being read by the user. Uses linear search.
     * 
     * @param userList - an arraylist filled with UserManga objects. 
     * @return intTotalReading - an integer count of the amount of manga that is currenting being read.
     */
    private static int totalReading(ArrayList<UserManga> userList) {

        // Initializing variables.
        int intTotalReading = 0;

        // For loop that iterates through the user's list. Then checks if the current manga's status is reading. If it is, adds 1 to the integer variable.
        for(UserManga current : userList) {
            if (current.getUserStatus().equalsIgnoreCase("Reading")) {
                intTotalReading++;
            }
        }
        return intTotalReading;
    }

    /**
     * Method that counts how many manga that are finished by the user. Uses linear search.
     * 
     * @param userList - an arraylist filled with UserManga objects.
     * @return intTotalFinished - an integer count of the amount of manga finished.
     */
    private static int totalFinished(ArrayList<UserManga> userList) {

        // Initializing variables.
        int intTotalFinished = 0;

        // For loop that iterates through the user's list. Then checks if the current manga's status is finished. If it is, adds 1 to the integer variable.
        for(UserManga current : userList) {
            if (current.getUserStatus().equalsIgnoreCase("Finished")) {
                intTotalFinished++;
            }
        }
        return intTotalFinished;
    }

    /**
     * Method that counts how many manga that are dropped by the user. Uses lienar search.
     * 
     * @param userList - an arraylist filled with UserManga objects.
     * @return intTotalDropped - an integer count of the amount of manga dropped.
     */
    private static int totalDropped(ArrayList<UserManga> userList) {

        // Initializing variables.
        int intTotalDropped = 0;

        // For loop that iterates through the user's list. Then checks if the current manga's status is dropped. If it is, adds 1 to the integer variable.
        for(UserManga current : userList) {
            if (current.getUserStatus().equalsIgnoreCase("Dropped")) {
                intTotalDropped++;
            }
        }
        return intTotalDropped;
    }

    /**
     * Method that counts the mean score of the User's list.
     * 
     * @param userList - an arraylist filled with UserManga objects.
     * @return dblMeanScore - a double that represents the mean of the user scores. Rounded to 2 decimals.
     */
    private static double meanScore(ArrayList<UserManga> userList) {

        // Initializing variables.
        double dblTotalScore = 0.00;
        int intTotalManga = userList.size();
        double dblMeanScore = 0.00;

        // As long as the total amount of manga in the user list is not 0, iterates through the user's list and add all scores together.
        if (intTotalManga != 0) {
            for(UserManga current : userList) {
                dblTotalScore = dblTotalScore + current.getUserScore();
            }

            // Divides total score by the amount of manga in the user's list. Then returns a rounded version of the mean score.
            dblMeanScore = dblTotalScore / intTotalManga;
            return Math.round(dblMeanScore * 100) / 100.00;
        }

        // Otherwise, returns a mean score of 0.
        return 0;
    }

    /**
     * Method that refreshes the summary of the User's list.
     * 
     * @param userList - an arraylist filled with UserManga objects.
     * @param MeanScoreText - a text object.
     * @param TotalReadingText - a text object.
     * @param TotalFinishedText - a text object.
     * @param TotalDroppedText - a text object.
     */
    private static void refreshSummary(ArrayList<UserManga> userList, Text MeanScoreText, Text TotalReadingText, Text TotalFinishedText, Text TotalDroppedText) {

        // Running the methods again to refresh the texts.
        MeanScoreText.setText("Mean Score: " + meanScore(userList));
        TotalReadingText.setText("Reading: " + totalReading(userList));
        TotalFinishedText.setText("Finished: " + totalFinished(userList));
        TotalDroppedText.setText("Dropped: " + totalDropped(userList));
    }

    /**
     * Controller that constructs a tableview of the user's list.
     * 
     * @param UserList
     * @param data
     * @return
     */
    public static Parent createMyList(ArrayList<UserManga> UserList, ObservableList<UserManga> data) {

        // Creating a column for UserManga specific parameters and the title.
        TableColumn<Object, Boolean> selectedColumn = new TableColumn<>();
        selectedColumn.setText("");
        selectedColumn.setPrefWidth(50);
        selectedColumn.setCellValueFactory(new PropertyValueFactory<Object, Boolean>("isUserSelected"));
        selectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectedColumn));

        TableColumn<Object, String> titleColumn = new TableColumn<>();
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strTitle"));
        titleColumn.setPrefWidth(154);

        TableColumn<Object, Integer> scoreColumn = new TableColumn<>();
        scoreColumn.setText("Your Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("intUserScore"));
        scoreColumn.setCellFactory(ComboBoxTableCell.forTableColumn(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        scoreColumn.setPrefWidth(100);
        
        TableColumn<Object, String> statusColumn = new TableColumn<>();
        statusColumn.setText("Your Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strUserStatus"));
        statusColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Reading", "Finished", "Dropped"));
        statusColumn.setPrefWidth(100);

        // Creating the tableview and adding all columns to the tableview.
        final TableView tableView = new TableView<>();
        tableView.setEditable(true);
        tableView.setItems(data);
        tableView.getColumns().addAll(selectedColumn, titleColumn, scoreColumn, statusColumn);
        return tableView;
    } 
}

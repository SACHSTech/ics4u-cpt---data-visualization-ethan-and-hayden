package simulator.MethodsClasses;

import simulator.*;
import simulator.ObjectClasses.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.geometry.Side;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ComboBox;

public class Database {

    /**
     * Screen that displays the main database.
     * 
     * @param primaryStage - stage where every scene is displayed on.
     * @param mangaList - an arraylist filled with Manga objects.
     * @param userList - an arraylist filled with UserManga objects.
     * @param currentAccount - an Account object.
     */
    public static void databaseScreen(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Creating observablelist object. Essentially a copy of the Arraylist, mangaList. However, the program could modify this object without interfering with mangaList.
        final ObservableList<Manga> data = FXCollections.observableArrayList(mangaList);

        // Changes the width of the stage. Essentially refreshes the stage.
        primaryStage.setWidth(600);

        // Creating gridpane to organize children.
        GridPane databaseGrid = new GridPane();
        databaseGrid.setVgap(10);
        databaseGrid.setHgap(10);
        databaseGrid.setGridLinesVisible(false);
        databaseGrid.setPadding(new Insets(25, 25, 25, 25));
        Font databaseFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Adding a method which returns a tableview to the gridpane.
        databaseGrid.add(createContent(mangaList, data), 0, 1);

        // Creating toolbars.
        ToolBar searchToolbar = new ToolBar();
        ToolBar filterToolbar = new ToolBar();
        filterToolbar.setOrientation(Orientation.VERTICAL);

        // Creating ListView to keep track of filters.
        final ListView<String> currentFilters = new ListView<String>(FXCollections.<String>observableArrayList());
        Text currentFiltersTitle = new Text();
        currentFiltersTitle.setText("Current Filters:");
        currentFiltersTitle.setFont(databaseFont);
        currentFilters.setPrefWidth(120);
        currentFilters.setPrefHeight(140);

        // Creating combobox for the genre filter.
        ComboBox<String> genreFilter = new ComboBox<>();
        Text genreFilterTitle = new Text();
        genreFilterTitle.setText("Filter by Genre:");
        genreFilterTitle.setFont(databaseFont);
        genreFilter.setPrefWidth(120);
        genreFilter.setPromptText("Genres");
        genreFilter.getItems().add("Action");
        genreFilter.getItems().add("Adventure");
        genreFilter.getItems().add("Horror");
        genreFilter.getItems().add("Fantasy");
        genreFilter.getItems().add("Psychological");
        genreFilter.getItems().add("Mystery");
        genreFilter.getItems().add("Comedy");
        genreFilter.getItems().add("Romance");
        genreFilter.getItems().add("Sci-Fi");
        genreFilter.getItems().add("School");
        genreFilter.getItems().add("Slice of Life");
        genreFilter.getItems().add("Super Powers");
        genreFilter.getItems().add("Music");
        genreFilter.getItems().add("Shounen");
        genreFilter.getItems().add("Seinen");
        genreFilter.getItems().add("Shoujo");
        genreFilter.getItems().add("Josei");

        // Creating event for when the user clicks on an item in the combobox.
        genreFilter.setOnAction((event) -> {

            // Creating string variable for getting the current item's name.
            String strSelection = genreFilter.getSelectionModel().getSelectedItem();

            // Adding name of item to currentFilters listview.
            currentFilters.getItems().add(strSelection);

            // Switch statement that runs a filter method depending on the name of the item clicked.
            switch (strSelection) {
                case "Action" :
                    sortGenre(mangaList, data, "Action");
                    break;
                case "Adventure" :
                    sortGenre(mangaList, data, "Adventure");
                    break;
                case "Horror" :
                    sortGenre(mangaList, data, "Horror");
                    break;
                case "Fantasy" :
                    sortGenre(mangaList, data, "Fantasy");
                    break;
                case "Psychological" :
                    sortGenre(mangaList, data, "Psychological");
                    break;
                case "Mystery" :
                    sortGenre(mangaList, data, "Mystery");
                    break;
                case "Comedy" :
                    sortGenre(mangaList, data, "Comedy");
                    break;
                case "Romance" :
                    sortGenre(mangaList, data, "Romance");
                    break;
                case "Sci-Fi" :
                    sortGenre(mangaList, data, "Sci-Fi");
                    break;
                case "School" :
                    sortGenre(mangaList, data, "School");
                    break;
                case "Slice of Life" :
                    sortGenre(mangaList, data, "Slice of Life");
                    break;
                case "Super Powers" :
                    sortGenre(mangaList, data, "Super");
                    break;
                case "Music" :
                    sortGenre(mangaList, data, "Music");
                    break;
                case "Shounen" :
                    sortGenre(mangaList, data, "Shounen");
                    break;
                case "Seinen" :
                    sortGenre(mangaList, data, "Seinen");
                    break;
                case "Shoujo" :
                    sortGenre(mangaList, data, "Shoujo");
                    break;
                case "Josei" :
                    sortGenre(mangaList, data, "Josei");
                    break;
            }
        });

        // Creating combobox for the year filter.
        ComboBox<String> yearFilter = new ComboBox<>();
        Text yearFilterTitle = new Text();
        yearFilterTitle.setText("Filter by Year:");
        yearFilterTitle.setFont(databaseFont);
        yearFilter.setPrefWidth(120);
        yearFilter.setPromptText("Year Ranges");
        yearFilter.getItems().add("< 1979");
        yearFilter.getItems().add("1980 - 1989");
        yearFilter.getItems().add("1990 - 1999");
        yearFilter.getItems().add("2000 - 2009");
        yearFilter.getItems().add("2010 - 2020");

        // Creating event for when the user clicks on an item in the combobox.
        yearFilter.setOnAction((event) -> {

            // Creating string variable for getting the current item's name.
            String strSelection = yearFilter.getSelectionModel().getSelectedItem();

            // Adding current item's name to currentFilters listview
            currentFilters.getItems().add(strSelection);

            // Switch statement that runs a filter method depending on the name of the item clicked.
            switch (strSelection) {
                case "< 1979" :
                    sortYear(mangaList, data, strSelection);
                    break;
                case "1980 - 1989" :
                    sortYear(mangaList, data, strSelection);
                    break;
                case "1990 - 1999" :
                    sortYear(mangaList, data, strSelection);
                    break;
                case "2000 - 2009" :
                    sortYear(mangaList, data, strSelection);
                    break;
                case "2010 - 2020" :
                    sortYear(mangaList, data, strSelection);
                    break;
            }
        });

        // Creating combobox for the category sorter.
        ComboBox<String> categorySort = new ComboBox<>();
        Text categorySortTitle = new Text();
        categorySortTitle.setText("Sort By:");
        categorySortTitle.setFont(databaseFont);
        categorySort.setPrefWidth(120);
        categorySort.setPromptText("Categories");
        categorySort.getItems().add("Title");
        categorySort.getItems().add("Type");
        categorySort.getItems().add("Chapters");
        categorySort.getItems().add("Status");
        categorySort.getItems().add("Publish Year");
        categorySort.getItems().add("Genres");
        categorySort.getItems().add("Author");
        categorySort.getItems().add("Serialization");
        categorySort.getItems().add("Score");
        categorySort.getItems().add("Rank");
        categorySort.getItems().add("Popularity");
        categorySort.getItems().add("Number of Scores");

        // Creating event for when the user clicks on an item in the combobox.
        categorySort.setOnAction((event) -> {

            // String variable that gets the name of the item that is clicked.
            String strSelection = categorySort.getSelectionModel().getSelectedItem();

            // Switch statement that runs a selection sort method depending on the name of the item clicked
            switch (strSelection) {
                case "Title" :
                    sortByCategory(data, "Title");
                    break;
                case "Type" :
                    sortByCategory(data, "Type");
                    break;
                case "Chapters" :
                    sortByCategory(data, "Chapters");
                    break;
                case "Status" :
                    sortByCategory(data, "Status");
                    break;
                case "Publish Year" :
                    sortByCategory(data, "Published");
                    break;
                case "Genres" :
                    sortByCategory(data, "Genres");
                    break;
                case "Author" :
                    sortByCategory(data, "Author");
                    break;
                case "Serialization" :
                    sortByCategory(data, "Serialization");
                    break;
                case "Score" :
                    sortByCategory(data, "Score");
                    break;
                case "Rank" :
                    sortByCategory(data, "Rank");
                    break;
                case "Popularity" :
                    sortByCategory(data, "Popularity");
                    break;
                case "Number of Scores" :
                    sortByCategory(data, "Number of Scores");
                    break;
            }
        });

        // Creating button that displays the database's summary.
        Button summaryInfoBtn = new Button("Summary");
        summaryInfoBtn.setFont(databaseFont);
        summaryInfoBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SummaryData.dataSummaryScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });
        
        // Creating button that resets all current filters on the database.
        Button resetFiltersBtn = new Button("Reset Filters");
        resetFiltersBtn.setFont(databaseFont);
        resetFiltersBtn.setPrefWidth(125);
        resetFiltersBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event method that resets the dataset and clears all filters.
            @Override
            public void handle(ActionEvent event) {
                data.clear();
                data.addAll(mangaList);
                categorySort.getSelectionModel().select("");
                genreFilter.getSelectionModel().select("");
                yearFilter.getSelectionModel().select("");
                currentFilters.getItems().clear();
            }
        });

        // Adding items to their specific toolbar.
        searchToolbar.getItems().add(searchBox(primaryStage, mangaList, userList, databaseGrid, currentAccount));
        searchToolbar.getItems().add(summaryInfoBtn);
        filterToolbar.getItems().add(categorySortTitle);
        filterToolbar.getItems().add(categorySort);
        filterToolbar.getItems().add(genreFilterTitle);
        filterToolbar.getItems().add(genreFilter);
        filterToolbar.getItems().add(yearFilterTitle);
        filterToolbar.getItems().add(yearFilter);
        filterToolbar.getItems().add(currentFiltersTitle);
        filterToolbar.getItems().add(currentFilters);

        // Adding items to the gridpane.
        databaseGrid.add(searchToolbar, 0, 0);
        databaseGrid.add(resetFiltersBtn, 1, 0);
        databaseGrid.add(filterToolbar, 1, 1);

        // Home Menu Button.
        Button homeMenuBtn = new Button();
        databaseGrid.add(homeMenuBtn, 0, 2);
        homeMenuBtn.setText("Back");
        homeMenuBtn.setFont(databaseFont);
        homeMenuBtn.setMaxSize(100, 50);
        homeMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

            // Event method that resets all filters and resets the database. Then, runs a method that returns the user to the mains screen.
            @Override
            public void handle(ActionEvent event) {                
                data.clear();
                data.addAll(mangaList);
                Main.mainMenuScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Setting gridpane to stage.
        primaryStage.setScene(new Scene(databaseGrid));
        primaryStage.show();
    }

    /**
     * Method that creates a search box for searching an individual manga.
     * 
     * @param primaryStage - stage that displays all scenes.
     * @param mangaList - an arraylist filled with Manga objects.
     * @param userList - an arraylist filled with UserManga objects.
     * @param databaseGrid - a gridpane from the database screen.
     * @param currentAccount - an Account object.
     * @return Parent - a javafx object.
     */
    private static Parent searchBox(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, GridPane databaseGrid, Account currentAccount) { 
        
        // Creating context menu.
        ContextMenu autoSuggest = new ContextMenu();
        
        // Creating textfield. 
        TextField searchText = new TextField();
        searchText.setPrefWidth(310);
        searchText.setPromptText("Enter manga title here");
        searchText.setMaxSize(310, TextField.USE_COMPUTED_SIZE);

        // Checks which character is in the textfield. Then runs a method that gives suggestions based on the characters.
        searchText.setOnKeyTyped((KeyEvent currentKeyChar) -> {
            String strPressedChar = searchText.getText();
            searchingByChar(strPressedChar, mangaList, autoSuggest, searchText);
        });

        // Checking when the user clicks enter on the textfield. Then runs a method that checks if the manga is in the database.
        searchText.setOnKeyReleased((KeyEvent currentKey) -> {
            autoSuggest.show(searchText, Side.BOTTOM, 0, 0);
            if (currentKey.getCode() == KeyCode.ENTER) {
                String strKey = searchText.getText();
                individualSelect(primaryStage, mangaList, userList, strKey, databaseGrid, currentAccount);
                searchText.clear();
                autoSuggest.hide();
            }
        });
        return searchText;
    }

    /**
     * Method that checks what manga is written in the textfield then accesses the manga's individual record.
     * 
     * @param primaryStage - stage that displays all scenes.
     * @param mangaList - an arraylist of Manga objects.
     * @param userList - an arraylist of UserManga objects.
     * @param strKey - the string that is typed in the textfield.
     * @param databaseGrid - gridpane from the database screen.
     * @param currentAccount - an Account object.
     */
    private static void individualSelect(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, String strKey, GridPane databaseGrid, Account currentAccount) {
        
        // For loop that iterates through the manga database.
        for (Manga current : mangaList) {

            // If statement that checks if strKey equals to a Manga title in the database.
            if (strKey.equalsIgnoreCase(current.getTitle())) {

                // Runs method that accesses the manga's individual record.
                IndividualManga.individualRecordScreen(primaryStage, mangaList, userList, current, currentAccount);
            }
        }
    }

    /**
     * Method that autosearches suggestions based on the characters the user typed in the textfield.
     * 
     * @param strPressedChar - What string is currently written in the text field.
     * @param mangaList - an arraylist filled with Manga objects.
     * @param autoSuggest - the contextmenu that shows the autosuggestions.
     * @param searchText - the textfield where the user types their string.
     */
    private static void searchingByChar(String strPressedChar, ArrayList<Manga> mangaList, ContextMenu autoSuggest, TextField searchText) {

        // Clears everything in the autosuggest menu.
        autoSuggest.getItems().clear();

        // Initializing variables.
        int intCheck = 0;

        // When the user clicks on an item in the context menu, sets the item's title in the search bar.
        autoSuggest.setOnAction(e -> searchText.setText(((MenuItem)e.getTarget()).getText()));

        // For loop that iterates through the manga database.
        for (Manga current : mangaList) {

            // If statement that checks if the current manga's title's length is greater or equal to what the user currently typed in the search bar.
            if (current.getTitle().length() >= strPressedChar.length()) {

                // If statement that checks if the current title's substring is equal to what the user currently typed in the search bar.
                if (current.getTitle().substring(0, strPressedChar.length()).equalsIgnoreCase(strPressedChar)) {
                    intCheck++;

                    // Limits autosearch to five manga's at max. If less than 5, hides the context menu.
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
            
            // If manga's title length is less than what the user currently typed, the for loop skips over that manga.
            }else {
                continue;
            }
        }
    }

    /**
     * Method that uses selection sort to sort the database.
     * 
     * @param data - Temporary dataset of every manga in the database.
     * @param strMethodName - The name of the item of the combobox that is selected.
     */
    private static void sortByCategory(ObservableList<Manga> data, String strMethodName) {

        // Variables
        int currentMinIndex;
        int sortedSide;
        int unsortedSide;

        // For loop to iterate through the data
        for (sortedSide = 0; sortedSide < data.size() - 1; sortedSide++) {
            currentMinIndex = sortedSide;

            // For loop to iterate through the data that has been unsorted
            for (unsortedSide = sortedSide + 1; unsortedSide < data.size(); unsortedSide++) {

                // Switch statement that uses the name of the item of the combobox selected to sort the specific category.
                switch (strMethodName) {

                    // Sorting by integer.
                    case "Published" :
                        if (data.get(unsortedSide).getPublished() < data.get(currentMinIndex).getPublished()) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Rank":
                        if (data.get(unsortedSide).getRank() < data.get(currentMinIndex).getRank()) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Popularity":
                        if (data.get(unsortedSide).getPopularity() < data.get(currentMinIndex).getPopularity()) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Number of Scores":
                        if (data.get(unsortedSide).getScoreNumbers() > data.get(currentMinIndex).getScoreNumbers()) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    
                    // Sorting by double
                    case "Score" :
                        if (data.get(unsortedSide).getScore() > data.get(currentMinIndex).getScore()) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    
                    // Sorting by string alphabetically.
                        case "Title":
                        if (data.get(unsortedSide).getTitle().compareToIgnoreCase(data.get(currentMinIndex).getTitle()) < 0) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Type":
                        if (data.get(unsortedSide).getType().compareToIgnoreCase(data.get(currentMinIndex).getType()) < 0) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Chapters":
                        if (data.get(unsortedSide).getChapter().compareToIgnoreCase(data.get(currentMinIndex).getChapter()) < 0) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Status":
                        if (data.get(unsortedSide).getStatus().compareToIgnoreCase(data.get(currentMinIndex).getStatus()) < 0) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Genres":
                        if (data.get(unsortedSide).getGenre().compareToIgnoreCase(data.get(currentMinIndex).getGenre()) < 0) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Author":
                        if (data.get(unsortedSide).getAuthor().compareToIgnoreCase(data.get(currentMinIndex).getAuthor()) < 0) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                    case "Serialization":
                        if (data.get(unsortedSide).getSerialization().compareToIgnoreCase(data.get(currentMinIndex).getSerialization()) < 0) {
                            currentMinIndex = unsortedSide;
                        }
                        break;
                }
            }

            // Swapping indexes when needed
            if (sortedSide != currentMinIndex) {
                Manga temp = data.get(currentMinIndex);
                data.set(currentMinIndex, data.get(sortedSide));
                data.set(sortedSide, temp);
            }
        }
    }

    /**
     * Method that filters the list by Genre.
     * 
     * @param mangaList - an arraylist filled with Manga objects.
     * @param data - temporary list of every Manga in the database.
     * @param strGenre - name of the item selected from the combobox.
     */
    private static void sortGenre(ArrayList<Manga> mangaList, ObservableList<Manga> data, String strGenre) {

        // Variables.
        int intCount;
        int intCurrentGenre;

        // For loop that iterates through the manga database.
        for (Manga current : mangaList) {

            // Setting up string array that is filled with all genres of an individual manga.
            String[] strMangaGenres = (current.getGenre()).split(" / ");
            intCount = 0;

            // For loop that iterates through the array filled with the genres of an individual manga.
            for (intCurrentGenre = 0; intCurrentGenre < strMangaGenres.length; intCurrentGenre++) {

                // Checking if the current genre is not equal to what genre the user wants to filter by.
                if (!strMangaGenres[intCurrentGenre].equalsIgnoreCase(strGenre)) {
                    intCount++;

                    // If loop reaches through the end of the array, removes the current manga from the temporary data.
                    if (intCount == strMangaGenres.length) {
                        data.remove(current);
                    }
                }
            }
        }
    }

    /**
     * Method that filters the list by Year.
     * 
     * @param mangaList - an arraylist of all Manga objects.
     * @param data - temporary list of the manga database.
     * @param strYearRange - Name of the item that the user clicked on in the combobox. 
     */
    private static void sortYear(ArrayList<Manga> mangaList, ObservableList<Manga> data, String strYearRange) {

        // Checking if the user clicked on the item, "< 1979".
        if (strYearRange.equalsIgnoreCase("< 1979")) {

            // Iterates through the manga database.
            for (Manga current : mangaList) {

                // Removing all manga with a publish date later than 1979.
                if (current.getPublished() > 1979){
                    data.remove(current);
                }
            }
        
        // Checking if the user clicked on every other item.
        }else {

            // Setting up string array to store the year range of the item the user clicked on.
            String[] strYearRangeValues = (strYearRange.split(" - "));

            // Iterating through the manga database.
            for (Manga current : mangaList) {

                // Checking if the current manga is outside of the range and removing it if it is.
                if (current.getPublished() < Integer.parseInt(strYearRangeValues[0]) || current.getPublished() > Integer.parseInt(strYearRangeValues[1])){
                    data.remove(current);
                }  
            }
        }
    }

    /**
     * Controller for the database tableview.
     * 
     * @param mangaList - an arraylist of Manga objects.
     * @param data - a temporary list of the manga database.
     * @return TableView<Manga> - a javafx object that displays a tableview.
     */
    private static TableView<Manga> createContent(ArrayList<Manga> mangaList, ObservableList<Manga> data) {

        // Creating columns for the tableview based on each parameter of the Manga object. Each column in unsortable to implement my own sorting algorithm.
        TableColumn<Object, String> titleColumn = new TableColumn<>();
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strTitle"));
        titleColumn.setSortable(false);

        TableColumn<Object, String> typeColumn = new TableColumn<>();
        typeColumn.setText("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strType"));
        typeColumn.setSortable(false);

        TableColumn<Object, String> chaptersColumn = new TableColumn<>();
        chaptersColumn.setText("Chapters");
        chaptersColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strChapter"));
        chaptersColumn.setSortable(false);

        TableColumn<Object, String> statusColumn = new TableColumn<>();
        statusColumn.setText("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strStatus"));
        statusColumn.setSortable(false);

        TableColumn<Object, Integer> publishDateColumn = new TableColumn<>();
        publishDateColumn.setText("Publish Year");
        publishDateColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("intPublished"));
        publishDateColumn.setSortable(false);

        TableColumn<Object, String> genreColumn = new TableColumn<>();
        genreColumn.setText("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strGenre"));
        genreColumn.setSortable(false);

        TableColumn<Object, String> authorColumn = new TableColumn<>();
        authorColumn.setText("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strAuthor"));
        authorColumn.setSortable(false);

        TableColumn<Object, String> serializationColumn = new TableColumn<>();
        serializationColumn.setText("Serialization");
        serializationColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("strSerialization"));
        serializationColumn.setSortable(false);

        TableColumn<Object, Double> scoreColumn = new TableColumn<>();
        scoreColumn.setText("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Object, Double>("dblScore"));
        scoreColumn.setSortable(false);

        TableColumn<Object, Integer> rankedColumn = new TableColumn<>();
        rankedColumn.setText("Rank");
        rankedColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("intRank"));
        rankedColumn.setSortable(false);

        TableColumn<Object, Integer> popularityColumn = new TableColumn<>();
        popularityColumn.setText("Popularity");
        popularityColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("intPopularity"));
        popularityColumn.setSortable(false);

        TableColumn<Object, Integer> scorenumbersColumn = new TableColumn<>();
        scorenumbersColumn.setText("Number of Scores");
        scorenumbersColumn.setCellValueFactory(new PropertyValueFactory<Object, Integer>("intScoreNumbers"));
        scorenumbersColumn.setSortable(false);

        // Adding columns to tableview and returning it.
        final TableView tableView = new TableView();
        tableView.setEditable(false);
        tableView.setItems(data);
        tableView.getColumns().addAll(titleColumn, typeColumn, chaptersColumn, statusColumn, publishDateColumn, genreColumn, authorColumn, serializationColumn, scoreColumn, rankedColumn, popularityColumn, scorenumbersColumn);
        return tableView;
    }
}


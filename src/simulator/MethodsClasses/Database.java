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
     * @param primaryStage
     * @param mangaList
     * @param userList
     * @param currentAccount
     */
    public static void DatabaseScreen(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Changes the width of the stage. Essentially refreshes the stage.
        primaryStage.setWidth(600);

        // Creating observablelist object. Essentially a copy of the Arraylist, mangaList. However, the program could modify this object without interfering with mangaList.
        final ObservableList<Manga> data = FXCollections.observableArrayList(mangaList);

        // Creating gridpane to organize children
        GridPane databaseGrid = new GridPane();
        databaseGrid.setVgap(10);
        databaseGrid.setHgap(10);
        databaseGrid.setGridLinesVisible(false);
        databaseGrid.setPadding(new Insets(25, 25, 25, 25));
        Font databaseFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Adding a method which returns a tableview to the gridpane
        databaseGrid.add(createContent(mangaList, data), 0, 1);

        // Creating toolbars
        ToolBar searchToolbar = new ToolBar();
        ToolBar filterToolbar = new ToolBar();
        filterToolbar.setOrientation(Orientation.VERTICAL);

        // Creating ListView to keep track of filters
        final ListView<String> currentFilters = new ListView<String>(FXCollections.<String>observableArrayList());
        Text currentFiltersTitle = new Text();
        currentFiltersTitle.setText("Current Filters:");
        currentFiltersTitle.setFont(databaseFont);
        currentFilters.setPrefWidth(120);
        currentFilters.setPrefHeight(140);

        // Creating combobox for the genre filter
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
        genreFilter.setOnAction((event) -> {
            String strSelection = genreFilter.getSelectionModel().getSelectedItem();
            currentFilters.getItems().add(strSelection);
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
        yearFilter.setOnAction((event) -> {
            String strSelection = yearFilter.getSelectionModel().getSelectedItem();
            currentFilters.getItems().add(strSelection);
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

        // Creating combobox for the category sorter
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
        categorySort.setOnAction((event) -> {
            String strSelection = categorySort.getSelectionModel().getSelectedItem();
            switch (strSelection) {
                case "Title" :
                    sortByString(data, "Title");
                    break;
                case "Type" :
                    sortByString(data, "Type");
                    break;
                case "Chapters" :
                    sortByString(data, "Chapters");
                    break;
                case "Status" :
                    sortByString(data, "Status");
                    break;
                case "Publish Year" :
                    sortByInteger(data, "Published");
                    break;
                case "Genres" :
                    sortByString(data, "Genres");
                    break;
                case "Author" :
                    sortByString(data, "Author");
                    break;
                case "Serialization" :
                    sortByString(data, "Serialization");
                    break;
                case "Score" :
                    sortByDouble(data);
                    break;
                case "Rank" :
                    sortByInteger(data, "Rank");
                    break;
                case "Popularity" :
                    sortByInteger(data, "Popularity");
                    break;
                case "Number of Scores" :
                    sortByInteger(data, "Number of Scores");
                    break;
            }
        });

        // Creating button that displays the database's summary
        Button summaryInfoBtn = new Button("Summary");
        summaryInfoBtn.setFont(databaseFont);
        summaryInfoBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SummaryData.dataSummaryScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });
        
        // Creating button that resets all current filters on the database
        Button resetFiltersBtn = new Button("Reset Filters");
        resetFiltersBtn.setFont(databaseFont);
        resetFiltersBtn.setPrefWidth(125);
        resetFiltersBtn.setOnAction(new EventHandler<ActionEvent>() {
 
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

        // Adding items to their specific toolbar
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

        // Adding items to the gridpane
        databaseGrid.add(searchToolbar, 0, 0);
        databaseGrid.add(resetFiltersBtn, 1, 0);
        databaseGrid.add(filterToolbar, 1, 1);

        // Home Menu Button
        Button homeMenuBtn = new Button();
        databaseGrid.add(homeMenuBtn, 0, 2);
        homeMenuBtn.setText("Back");
        homeMenuBtn.setFont(databaseFont);
        homeMenuBtn.setMaxSize(100, 50);
        homeMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                // Resets all filters and sorts when the user leaves the database menu
                data.clear();
                data.addAll(mangaList);
                Main.mainMenuScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Setting gridpane to stage
        primaryStage.setScene(new Scene(databaseGrid));
        primaryStage.show();
    }

    /**
     * Method that creates a search box for individual search
     * 
     * @param primaryStage
     * @param mangaList
     * @param userList
     * @param databaseGrid
     * @param currentAccount
     * @return
     */
    private static Parent searchBox(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, GridPane databaseGrid, Account currentAccount) { 
        
        // Creating context menu
        ContextMenu autoSuggest = new ContextMenu();
        
        // Creating searchtext 
        TextField searchText = new TextField();
        searchText.setPrefWidth(310);
        searchText.setPromptText("Enter manga title here");
        searchText.setMaxSize(310, TextField.USE_COMPUTED_SIZE);

        // Checking which character is in the textfield
        searchText.setOnKeyTyped((KeyEvent currentKeyChar) -> {
            String strPressedChar = searchText.getText();
            searchingByChar(strPressedChar, mangaList, autoSuggest, searchText);
        });

        // Checking when the user clicks enter on the textfield
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
     * Method that checks what manga title is written in the textfield then accesses the manga's individual record.
     * 
     * @param primaryStage
     * @param mangaList
     * @param userList
     * @param strKey
     * @param databaseGrid
     * @param currentAccount
     */
    private static void individualSelect(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, String strKey, GridPane databaseGrid, Account currentAccount) {
        for (Manga current : mangaList) {
            if (strKey.equalsIgnoreCase(((current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", ""))) {
                IndividualManga.individualRecordScreen(primaryStage, mangaList, userList, current, currentAccount);
            }
        }
    }

    /**
     * Method that autosearches suggestions based on the characters the user typed in the textfield
     * 
     * @param strPressedChar
     * @param MangaList
     * @param autoSuggest
     * @param searchText
     */
    private static void searchingByChar(String strPressedChar, ArrayList<Manga> mangaList, ContextMenu autoSuggest, TextField searchText) {
        autoSuggest.getItems().clear();
        int intCheck = 0;
        autoSuggest.setOnAction(e -> searchText.setText(((MenuItem)e.getTarget()).getText()));
        for (Manga current : mangaList) {
            if (((current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "").length() >= strPressedChar.length()) {
                if (((current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "").substring(0, strPressedChar.length()).equalsIgnoreCase(strPressedChar)) {
                    intCheck++;
                    if (intCheck == 5) {
                        autoSuggest.getItems().addAll(new MenuItem(((current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "")));
                        break;
                    }else if (intCheck < 5 && intCheck > 0) {
                        autoSuggest.getItems().addAll(new MenuItem(((current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "")));
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

    /**
     * Method that uses selection sort to sort the database by integers.
     * 
     * @param data
     * @param strMethodName
     */
    private static void sortByInteger(ObservableList<Manga> data, String strMethodName) {
        int currentMinIndex;
        for (int i = 0; i < data.size() - 1; i++) {
            currentMinIndex = i;
            for (int j = i + 1; j < data.size(); j++) {
                switch (strMethodName) {
                    case "Published" :
                        if (Integer.parseInt(data.get(j).intPublishedProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) < Integer.parseInt(data.get(currentMinIndex).intPublishedProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Rank":
                        if (Integer.parseInt(data.get(j).intRankProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) < Integer.parseInt(data.get(currentMinIndex).intRankProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Popularity":
                        if (Integer.parseInt(data.get(j).intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) < Integer.parseInt(data.get(currentMinIndex).intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Number of Scores":
                        if (Integer.parseInt(data.get(j).intScoreNumbersProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) > Integer.parseInt(data.get(currentMinIndex).intScoreNumbersProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))) {
                            currentMinIndex = j;
                        }
                        break;
                }
            }

            // swap numbers if needed
            if (i != currentMinIndex) {
                Manga temp = data.get(currentMinIndex);
                data.set(currentMinIndex, data.get(i));
                data.set(i, temp);
            }
        }
    }

    /**
     * Method that uses selection sort to sort the database by doubles.
     * 
     * @param data
     */
    private static void sortByDouble(ObservableList<Manga> data) {
        int currentMinIndex;
        for (int i = 0; i < data.size() - 1; i++) {
            currentMinIndex = i;
            for (int j = i + 1; j < data.size(); j++) {
                if (Double.parseDouble(data.get(j).dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")) > Double.parseDouble(data.get(currentMinIndex).dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", ""))) {
                    currentMinIndex = j;
                }
            }

            // swap numbers if needed
            if (i != currentMinIndex) {
                Manga temp = data.get(currentMinIndex);
                data.set(currentMinIndex, data.get(i));
                data.set(i, temp);
            }
        }
    }

    /**
     * Method that uses selection sort to sort the database by String. sorts the database alphabetically.
     * 
     * @param data
     * @param strMethodName
     */
    private static void sortByString(ObservableList<Manga> data, String strMethodName) {
        int currentMinIndex;
        for (int i = 0; i < data.size() - 1; i++) {
            currentMinIndex = i;
            for (int j = i + 1; j < data.size(); j++) {
                switch (strMethodName) {
                    case "Title":
                        if ((data.get(j).strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(data.get(currentMinIndex).strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Type":
                        if ((data.get(j).strTypeProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(data.get(currentMinIndex).strTypeProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Chapters":
                        if ((data.get(j).strChapterProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(data.get(currentMinIndex).strChapterProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Status":
                        if ((data.get(j).strStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(data.get(currentMinIndex).strStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Genres":
                        if ((data.get(j).strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(data.get(currentMinIndex).strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Author":
                        if ((data.get(j).strAuthorProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(data.get(currentMinIndex).strAuthorProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Serialization":
                        if ((data.get(j).strSerializationProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(data.get(currentMinIndex).strSerializationProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                }
            }

            // swap numbers if needed
            if (i != currentMinIndex) {
                Manga temp = data.get(currentMinIndex);
                data.set(currentMinIndex, data.get(i));
                data.set(i, temp);
            }
        }
    }

    /**
     * Method that filters the list by Genre.
     * 
     * @param MangaList
     * @param data
     * @param strGenre
     */
    private static void sortGenre(ArrayList<Manga> MangaList, ObservableList<Manga> data, String strGenre) {
        int intCount;
        for (Manga current : MangaList) {
            String[] strCurrentGenre = (current.strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).split(" / ");
            intCount = 0;
            for (int i = 0; i < strCurrentGenre.length; i++) {
                if (!strCurrentGenre[i].equalsIgnoreCase(strGenre)) {
                    intCount++;
                    if (intCount == strCurrentGenre.length) {
                        data.remove(current);
                    }
                }
            }
        }
    }

    /**
     * Method that filters the list by Year.
     * 
     * @param MangaList
     * @param data
     * @param strGenre
     */
    private static void sortYear(ArrayList<Manga> MangaList, ObservableList<Manga> data, String strYearRange) {
        if (strYearRange.equalsIgnoreCase("< 1979")) {
            for (Manga current : MangaList) {
                if (Integer.parseInt(current.intPublishedProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) > 1979){
                    data.remove(current);
                }
            }
        }else {
            String[] strYearRangeValues = (strYearRange.split(" - "));
            for (Manga current : MangaList) {
                if (Integer.parseInt(current.intPublishedProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) < Integer.parseInt(strYearRangeValues[0]) || Integer.parseInt(current.intPublishedProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) > Integer.parseInt(strYearRangeValues[1])){
                    data.remove(current);
                }  
            }
        }
    }

    /**
     * Constructor for the database TableView
     * 
     * @param MangaList
     * @param data
     * @return
     */
    private static TableView<Manga> createContent(ArrayList<Manga> MangaList, ObservableList<Manga> data) {

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

        final TableView tableView = new TableView();
        tableView.setEditable(false);
        tableView.setItems(data);
        tableView.getColumns().addAll(titleColumn, typeColumn, chaptersColumn, statusColumn, publishDateColumn, genreColumn, authorColumn, serializationColumn, scoreColumn, rankedColumn, popularityColumn, scorenumbersColumn);
        return tableView;
    }
}


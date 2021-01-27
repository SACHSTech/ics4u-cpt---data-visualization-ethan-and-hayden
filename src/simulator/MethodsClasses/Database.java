package simulator.MethodsClasses;

// Basic Imports
import simulator.*;
import simulator.ObjectClasses.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.Parent;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

// Table Imports
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

// Layout Imports
import javafx.scene.layout.GridPane;
import javafx.scene.control.ListView;

// Toolbox Imports
import javafx.scene.control.ToolBar;
import javafx.scene.control.ComboBox;

public class Database {

    private static Parent searchBox(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, GridPane databaseGrid, Account currentAccount) { 
        
        // Context Box
        ContextMenu autoSuggest = new ContextMenu();
        
        TextField searchText = new TextField();
        searchText.setPrefWidth(315);
        searchText.setPromptText("Enter manga title here");
        searchText.setMaxSize(315, TextField.USE_COMPUTED_SIZE);

        searchText.setOnKeyTyped((KeyEvent currentKeyChar) -> {
            String strPressedChar = searchText.getText();
            SearchingByChar(strPressedChar, MangaList, autoSuggest, searchText);
        });
        searchText.setOnKeyReleased((KeyEvent currentKey) -> {
            autoSuggest.show(searchText, Side.BOTTOM, 0, 0);
            if (currentKey.getCode() == KeyCode.ENTER) {
                String strKey = searchText.getText();
                individualSelect(primaryStage, MangaList, UserList, strKey, databaseGrid, currentAccount);
                searchText.clear();
                autoSuggest.hide();
            }
        });
        return searchText;
    }

    private static void individualSelect(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, String strKey, GridPane databaseGrid, Account currentAccount) {
        for (Manga Current : MangaList) {
            if (strKey.equalsIgnoreCase(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", ""))) {
                IndividualManga.individualRecord(primaryStage, MangaList, UserList, Current, currentAccount);
            }
        }
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

    // Selection Sort
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

    // Selection Sort
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

    public static void DatabaseScreen(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, Account currentAccount) {

        final ObservableList<Manga> data = FXCollections.observableArrayList(MangaList);

        GridPane databaseGrid = new GridPane();
        databaseGrid.setVgap(10);
        databaseGrid.setHgap(10);
        databaseGrid.setGridLinesVisible(false);
        databaseGrid.setPadding(new Insets(25, 25, 25, 25));
        Font DatabaseFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Table
        databaseGrid.add(createContent(MangaList, data), 0, 1);

        // Toolbox
        ToolBar searchToolbar = new ToolBar();
        ToolBar filterToolbar = new ToolBar();
        filterToolbar.setOrientation(Orientation.VERTICAL);

        // ListView to keep track of filters
        final ListView<String> currentFilters = new ListView<String>(FXCollections.<String>observableArrayList());
        Text currentFiltersTitle = new Text();
        currentFiltersTitle.setText("Current Filters:");
        currentFiltersTitle.setFont(DatabaseFont);
        currentFilters.setPrefWidth(120);
        currentFilters.setPrefHeight(140);

        // GenreFilter combobox
        ComboBox<String> genreFilter = new ComboBox<>();
        Text genreFilterTitle = new Text();
        genreFilterTitle.setText("Filter by Genre:");
        genreFilterTitle.setFont(DatabaseFont);
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
                    sortGenre(MangaList, data, "Action");
                    break;
                case "Adventure" :
                    sortGenre(MangaList, data, "Adventure");
                    break;
                case "Horror" :
                    sortGenre(MangaList, data, "Horror");
                    break;
                case "Fantasy" :
                    sortGenre(MangaList, data, "Fantasy");
                    break;
                case "Psychological" :
                    sortGenre(MangaList, data, "Psychological");
                    break;
                case "Mystery" :
                    sortGenre(MangaList, data, "Mystery");
                    break;
                case "Comedy" :
                    sortGenre(MangaList, data, "Comedy");
                    break;
                case "Romance" :
                    sortGenre(MangaList, data, "Romance");
                    break;
                case "Sci-Fi" :
                    sortGenre(MangaList, data, "Sci-Fi");
                    break;
                case "School" :
                    sortGenre(MangaList, data, "School");
                    break;
                case "Slice of Life" :
                    sortGenre(MangaList, data, "Slice of Life");
                    break;
                case "Super Powers" :
                    sortGenre(MangaList, data, "Super");
                    break;
                case "Music" :
                    sortGenre(MangaList, data, "Music");
                    break;
                case "Shounen" :
                    sortGenre(MangaList, data, "Shounen");
                    break;
                case "Seinen" :
                    sortGenre(MangaList, data, "Seinen");
                    break;
                case "Shoujo" :
                    sortGenre(MangaList, data, "Shoujo");
                    break;
                case "Josei" :
                    sortGenre(MangaList, data, "Josei");
                    break;
            }
        });

        ComboBox<String> yearFilter = new ComboBox<>();
        Text yearFilterTitle = new Text();
        yearFilterTitle.setText("Filter by Year:");
        yearFilterTitle.setFont(DatabaseFont);
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
                    sortYear(MangaList, data, strSelection);
                    break;
                case "1980 - 1989" :
                    sortYear(MangaList, data, strSelection);
                    break;
                case "1990 - 1999" :
                    sortYear(MangaList, data, strSelection);
                    break;
                case "2000 - 2009" :
                    sortYear(MangaList, data, strSelection);
                    break;
                case "2010 - 2020" :
                    sortYear(MangaList, data, strSelection);
                    break;
            }
        });

        // Sort By combobox
        ComboBox<String> categorySort = new ComboBox<>();
        Text categorySortTitle = new Text();
        categorySortTitle.setText("Sort By:");
        categorySortTitle.setFont(DatabaseFont);
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


        Button summaryInfo = new Button("Summary");
        summaryInfo.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                SummaryData.dataSummaryScreen(primaryStage, MangaList, UserList, currentAccount);
            }
        });
        
        Button resetFilters = new Button("Reset Filters");
        resetFilters.setPrefWidth(125);
        resetFilters.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                data.clear();
                data.addAll(MangaList);
                currentFilters.getItems().clear();
            }
        });

        searchToolbar.getItems().add(searchBox(primaryStage, MangaList, UserList, databaseGrid, currentAccount));
        searchToolbar.getItems().add(summaryInfo);
        filterToolbar.getItems().add(categorySortTitle);
        filterToolbar.getItems().add(categorySort);
        filterToolbar.getItems().add(genreFilterTitle);
        filterToolbar.getItems().add(genreFilter);
        filterToolbar.getItems().add(yearFilterTitle);
        filterToolbar.getItems().add(yearFilter);
        filterToolbar.getItems().add(currentFiltersTitle);
        filterToolbar.getItems().add(currentFilters);
        databaseGrid.add(searchToolbar, 0, 0);
        databaseGrid.add(resetFilters, 1, 0);
        databaseGrid.add(filterToolbar, 1, 1);

        // Home Menu Button
        Button homeMenu = new Button();
        databaseGrid.add(homeMenu, 0, 2);
        homeMenu.setText("Back");
        homeMenu.setFont(DatabaseFont);
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                data.clear();
                data.addAll(MangaList);
                Main.mainMenu(primaryStage, MangaList, UserList, currentAccount);
            }
        });

        // Refreshes the stage so the table would not be glitched
        primaryStage.setScene(new Scene(databaseGrid));
        primaryStage.setWidth(601);
        primaryStage.show();
    }
}


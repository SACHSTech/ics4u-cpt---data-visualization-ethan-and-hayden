package simulator;

// Basic Imports
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

// Table Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.geometry.Side;

// Layout Imports
import javafx.scene.layout.GridPane;

// Toolbox Imports
import javafx.scene.control.ToolBar;
import javafx.scene.control.ComboBox;

public class Database {

    private static Parent searchBox(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, GridPane databaseGrid) { 
        
        // Context Box
        ContextMenu autoSuggest = new ContextMenu();
        
        TextField searchText = new TextField();
        searchText.setPrefWidth(145);
        searchText.setPromptText("Enter manga name");
        searchText.setMaxSize(145, TextField.USE_COMPUTED_SIZE);

        searchText.setOnKeyTyped((KeyEvent currentKeyChar) -> {
            String strPressedChar = searchText.getText();
            SearchingByChar(strPressedChar, MangaList, autoSuggest, searchText);
        });
        searchText.setOnKeyReleased((KeyEvent currentKey) -> {
            autoSuggest.show(searchText, Side.BOTTOM, 0, 0);
            if (currentKey.getCode() == KeyCode.ENTER) {
                String strKey = searchText.getText();
                individualSelect(primaryStage, MangaList, UserList, strKey, databaseGrid);
                searchText.clear();
                autoSuggest.hide();
            }
        });
        return searchText;
    }

    private static void individualSelect(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, String strKey, GridPane databaseGrid) {
        for (Manga Current : MangaList) {
            if (strKey.equalsIgnoreCase(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", ""))) {
                IndividualManga.individualRecord(primaryStage, MangaList, UserList, Current);
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
    private static ArrayList<Manga> sortByInteger(ArrayList<Manga> MangaList, String strMethodName) {
        int currentMinIndex;
        for (int i = 0; i < MangaList.size() - 1; i++) {
            currentMinIndex = i;
            for (int j = i + 1; j < MangaList.size(); j++) {
                switch (strMethodName) {
                    case "Rank":
                        if (Integer.parseInt(MangaList.get(j).intRankProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) < Integer.parseInt(MangaList.get(currentMinIndex).intRankProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Popularity":
                        if (Integer.parseInt(MangaList.get(j).intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) < Integer.parseInt(MangaList.get(currentMinIndex).intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Number of Scores":
                        if (Integer.parseInt(MangaList.get(j).intScoreNumbersProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) > Integer.parseInt(MangaList.get(currentMinIndex).intScoreNumbersProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))) {
                            currentMinIndex = j;
                        }
                        break;
                }
            }

            // swap numbers if needed
            if (i != currentMinIndex) {
                Manga temp = MangaList.get(currentMinIndex);
                MangaList.set(currentMinIndex, MangaList.get(i));
                MangaList.set(i, temp);
            }
        }
        return MangaList;
    }

    // Selection Sort
    private static ArrayList<Manga> sortByDouble(ArrayList<Manga> MangaList) {
        int currentMinIndex;
        for (int i = 0; i < MangaList.size() - 1; i++) {
            currentMinIndex = i;
            for (int j = i + 1; j < MangaList.size(); j++) {
                if (Double.parseDouble(MangaList.get(j).dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")) > Double.parseDouble(MangaList.get(currentMinIndex).dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", ""))) {
                    currentMinIndex = j;
                }
            }

            // swap numbers if needed
            if (i != currentMinIndex) {
                Manga temp = MangaList.get(currentMinIndex);
                MangaList.set(currentMinIndex, MangaList.get(i));
                MangaList.set(i, temp);
            }
        }
        return MangaList;
    }

    private static ArrayList<Manga> sortByString(ArrayList<Manga> MangaList, String strMethodName) {
        int currentMinIndex;
        for (int i = 0; i < MangaList.size() - 1; i++) {
            currentMinIndex = i;
            for (int j = i + 1; j < MangaList.size(); j++) {
                switch (strMethodName) {
                    case "Title":
                        if ((MangaList.get(j).strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Type":
                        if ((MangaList.get(j).strTypeProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strTypeProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Chapters":
                        if ((MangaList.get(j).strChapterProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strChapterProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Status":
                        if ((MangaList.get(j).strStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Published":
                        if ((MangaList.get(j).strPublishedProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strPublishedProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Genres":
                        if ((MangaList.get(j).strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Author":
                        if ((MangaList.get(j).strAuthorProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strAuthorProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Serialization":
                        if ((MangaList.get(j).strSerializationProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strSerializationProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0) {
                            currentMinIndex = j;
                        }
                        break;
                }
            }

            // swap numbers if needed
            if (i != currentMinIndex) {
                Manga temp = MangaList.get(currentMinIndex);
                MangaList.set(currentMinIndex, MangaList.get(i));
                MangaList.set(i, temp);
            }
        }
        return MangaList;
    }

    private static void sortGenre(ArrayList<Manga> MangaList, ObservableList<Manga> data, String strGenre) {
        data.clear();
        for (Manga current : MangaList) {
            String[] strCurrentGenre = (current.strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).split(" / ");
            for (int i = 0; i < strCurrentGenre.length; i++) {
                if (strCurrentGenre[i].equalsIgnoreCase(strGenre)) {
                    data.add(current);
                }
            }
        }
    }

    private static TableView<Manga> createContent(ArrayList<Manga> MangaList, ObservableList<Manga> data) {

        TableColumn titleColumn = new TableColumn();
        titleColumn.setText("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory("strTitle"));
        titleColumn.setSortable(false);

        TableColumn typeColumn = new TableColumn<>();
        typeColumn.setText("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory("strType"));
        typeColumn.setSortable(false);

        TableColumn chaptersColumn = new TableColumn();
        chaptersColumn.setText("Chapters");
        chaptersColumn.setCellValueFactory(new PropertyValueFactory("strChapter"));
        chaptersColumn.setSortable(false);

        TableColumn statusColumn = new TableColumn();
        statusColumn.setText("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory("strStatus"));
        statusColumn.setSortable(false);

        TableColumn publishDateColumn = new TableColumn();
        publishDateColumn.setText("Publish Date");
        publishDateColumn.setCellValueFactory(new PropertyValueFactory("strPublished"));
        publishDateColumn.setSortable(false);

        TableColumn genreColumn = new TableColumn();
        genreColumn.setText("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory("strGenre"));
        genreColumn.setSortable(false);

        TableColumn authorColumn = new TableColumn();
        authorColumn.setText("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory("strAuthor"));
        authorColumn.setSortable(false);

        TableColumn serializationColumn = new TableColumn();
        serializationColumn.setText("Serialization");
        serializationColumn.setCellValueFactory(new PropertyValueFactory("strSerialization"));
        serializationColumn.setSortable(false);

        TableColumn scoreColumn = new TableColumn();
        scoreColumn.setText("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory("dblScore"));
        scoreColumn.setSortable(false);

        TableColumn rankedColumn = new TableColumn();
        rankedColumn.setText("Rank");
        rankedColumn.setCellValueFactory(new PropertyValueFactory("intRank"));
        rankedColumn.setSortable(false);

        TableColumn popularityColumn = new TableColumn();
        popularityColumn.setText("Popularity");
        popularityColumn.setCellValueFactory(new PropertyValueFactory("intPopularity"));
        popularityColumn.setSortable(false);

        TableColumn scorenumbersColumn = new TableColumn();
        scorenumbersColumn.setText("Number of Scores");
        scorenumbersColumn.setCellValueFactory(new PropertyValueFactory("intScoreNumbers"));
        scorenumbersColumn.setSortable(false);

        final TableView tableView = new TableView();
        tableView.setEditable(false);
        tableView.setItems(data);
        tableView.getColumns().addAll(titleColumn, typeColumn, chaptersColumn, statusColumn, publishDateColumn, genreColumn, authorColumn, serializationColumn, scoreColumn, rankedColumn, popularityColumn, scorenumbersColumn);
        return tableView;
    }

    public static void DatabaseScreen(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {

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
        ToolBar toolbar = new ToolBar();

        // GenreFilter combobox
        ComboBox<String> genreFilter = new ComboBox<>();
        genreFilter.setPromptText("Genre Filter");
        genreFilter.getItems().add("Genre Filter");
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
            int intSelectedIndex = genreFilter.getSelectionModel().getSelectedIndex();
            switch (intSelectedIndex) {
                case 0 :
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 1 :
                    sortGenre(MangaList, data, "Action");
                    break;
                case 2 :
                    sortGenre(MangaList, data, "Adventure");
                    break;
                case 3 :
                    sortGenre(MangaList, data, "Horror");
                    break;
                case 4 :
                    sortGenre(MangaList, data, "Fantasy");
                    break;
                case 5 :
                    sortGenre(MangaList, data, "Psychological");
                    break;
                case 6 :
                    sortGenre(MangaList, data, "Mystery");
                    break;
                case 7 :
                    sortGenre(MangaList, data, "Comedy");
                    break;
                case 8 :
                    sortGenre(MangaList, data, "Romance");
                    break;
                case 9 :
                    sortGenre(MangaList, data, "Sci-Fi");
                    break;
                case 10 :
                    sortGenre(MangaList, data, "School");
                    break;
                case 11 :
                    sortGenre(MangaList, data, "Slice of Life");
                    break;
                case 12 :
                    sortGenre(MangaList, data, "Super");
                    break;
                case 13 :
                    sortGenre(MangaList, data, "Music");
                    break;
                case 14 :
                    sortGenre(MangaList, data, "Shounen");
                    break;
                case 15 :
                    sortGenre(MangaList, data, "Seinen");
                    break;
                case 16 :
                    sortGenre(MangaList, data, "Shoujo");
                    break;
                case 17 :
                    sortGenre(MangaList, data, "Josei");
                    break;
            }
        });

        // Sort By combobox
        ComboBox<String> categorySort = new ComboBox<>();
        categorySort.setPromptText("Sort Category");
        categorySort.getItems().add("Sort by Title");
        categorySort.getItems().add("Sort by Type");
        categorySort.getItems().add("Sort by Chapters");
        categorySort.getItems().add("Sort by Status");
        categorySort.getItems().add("Sort by Publish Date");
        categorySort.getItems().add("Sort by Genres");
        categorySort.getItems().add("Sort by Author");
        categorySort.getItems().add("Sort by Serialization");
        categorySort.getItems().add("Sort by Score");
        categorySort.getItems().add("Sort by Rank");
        categorySort.getItems().add("Sort by Popularity");
        categorySort.getItems().add("Sort by Number of Scores");

        categorySort.setOnAction((event) -> {
            int intSelectedIndex = categorySort.getSelectionModel().getSelectedIndex();
            switch (intSelectedIndex) {
                case 0:
                    genreFilter.getSelectionModel().select(0);
                    sortByString(MangaList, "Title");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 1:
                    genreFilter.getSelectionModel().select(0);
                    sortByString(MangaList, "Type");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 2:
                    genreFilter.getSelectionModel().select(0);
                    sortByString(MangaList, "Chapters");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 3:
                    genreFilter.getSelectionModel().select(0);
                    sortByString(MangaList, "Status");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 4:
                    genreFilter.getSelectionModel().select(0);
                    sortByString(MangaList, "Published");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 5:
                    genreFilter.getSelectionModel().select(0);
                    sortByString(MangaList, "Genres");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 6:
                    genreFilter.getSelectionModel().select(0);
                    sortByString(MangaList, "Author");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 7:
                    genreFilter.getSelectionModel().select(0);
                    sortByString(MangaList, "Serialization");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 8:
                    genreFilter.getSelectionModel().select(0);
                    sortByDouble(MangaList);
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 9:
                    genreFilter.getSelectionModel().select(0);
                    sortByInteger(MangaList, "Rank");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 10:
                    genreFilter.getSelectionModel().select(0);
                    sortByInteger(MangaList, "Popularity");
                    data.clear();
                    data.addAll(MangaList);
                    break;
                case 11:
                    genreFilter.getSelectionModel().select(0);
                    sortByInteger(MangaList, "Number of Scores");
                    data.clear();
                    data.addAll(MangaList);
                    break;
            }
        });

        Button summaryInfo = new Button("Summary");
        summaryInfo.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                SummaryData.dataSummaryScreen(primaryStage, MangaList, UserList);
            }
        });
        
        toolbar.getItems().add(searchBox(primaryStage, MangaList, UserList, databaseGrid));
        toolbar.getItems().add(categorySort);
        toolbar.getItems().add(genreFilter);
        toolbar.getItems().add(summaryInfo);
        databaseGrid.add(toolbar, 0, 0);

        // Home Menu Button
        Button homeMenu = new Button();
        databaseGrid.add(homeMenu, 0, 2);
        homeMenu.setText("Back");
        homeMenu.setFont(DatabaseFont);
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                sortByInteger(MangaList, "Rank");
                data.clear();
                data.addAll(MangaList);
                Main.mainMenu(primaryStage, MangaList, UserList);
            }
        });

        // Refreshes the stage so the table would not be glitched
        primaryStage.setScene(new Scene(databaseGrid));
        primaryStage.setWidth(601);
        primaryStage.show();
    }
}


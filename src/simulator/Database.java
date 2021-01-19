package simulator;

// Basic Imports
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Table Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

// Layout Imports
import javafx.scene.layout.GridPane;

// Toolbox Imports
import javafx.scene.control.ToolBar;
import javafx.scene.control.ComboBox;

public class Database {

    // Selection Sort
    private static ArrayList<Manga> sortByInteger(ArrayList<Manga> MangaList, String strMethodName) {
        int currentMinIndex;
        for (int i = 0; i < MangaList.size() - 1; i++) {
            currentMinIndex = i;
            for (int j = i + 1; j < MangaList.size(); j++) {
                switch (strMethodName) {
                    case "Rank" :
                        if(Integer.parseInt(MangaList.get(j).intRankProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) < Integer.parseInt(MangaList.get(currentMinIndex).intRankProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Popularity" :
                        if(Integer.parseInt(MangaList.get(j).intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) < Integer.parseInt(MangaList.get(currentMinIndex).intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Number of Scores" :
                        if(Integer.parseInt(MangaList.get(j).intScoreNumbersProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) > Integer.parseInt(MangaList.get(currentMinIndex).intScoreNumbersProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) ) {
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
                if(Double.parseDouble(MangaList.get(j).dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")) > Double.parseDouble(MangaList.get(currentMinIndex).dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")) ) {
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
                    case "Title" :
                        if( (MangaList.get(j).strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0 ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Type" :
                        if( (MangaList.get(j).strTypeProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strTypeProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0 ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Chapters" :
                        if( (MangaList.get(j).strChapterProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strChapterProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0 ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Status" :
                        if( (MangaList.get(j).strStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0 ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Published" :
                        if( (MangaList.get(j).strPublishedProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strPublishedProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0 ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Genres" :
                        if( (MangaList.get(j).strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0 ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Author" :
                        if( (MangaList.get(j).strAuthorProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strAuthorProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0 ) {
                            currentMinIndex = j;
                        }
                        break;
                    case "Serialization" :
                        if( (MangaList.get(j).strSerializationProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).compareToIgnoreCase(MangaList.get(currentMinIndex).strSerializationProperty().toString().replace("StringProperty [value: ", "").replace("]", "")) < 0 ) {
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

    private static Parent createContent(ArrayList<Manga> MangaList) {
        final ObservableList<Manga> data = FXCollections.observableArrayList(MangaList);
 
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
        rankedColumn.setText("Ranked");
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
    
    public static void DatabaseScreen (Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {
        
        GridPane databaseGrid = new GridPane();
        databaseGrid.setVgap(10);
        databaseGrid.setHgap(10);
        databaseGrid.setGridLinesVisible(false);
        databaseGrid.setPadding(new Insets(25, 25, 25, 25));
        Font DatabaseFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);
        
        // Toolbox
        ToolBar toolbar = new ToolBar();

        // GenreFilter combobox
        ComboBox<String> genreFilter = new ComboBox<>();
        genreFilter.setPromptText("Genre Filter");
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
            int selectedIndex = categorySort.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {
                sortByString(MangaList, "Title");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 1) {
                sortByString(MangaList, "Type");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 2) {
                sortByString(MangaList, "Chapters");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 3) {
                sortByString(MangaList, "Status");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 4) {
                sortByString(MangaList, "Published");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 5) {
                sortByString(MangaList, "Genres");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 6) {
                sortByString(MangaList, "Author");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 7) {
                sortByString(MangaList, "Serialization");
                databaseGrid.add(createContent(MangaList), 0, 1);         
            }else if (selectedIndex == 8) {
                sortByDouble(MangaList);
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 9) {
                sortByInteger(MangaList, "Rank");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 10) {
                sortByInteger(MangaList, "Popularity");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }else if (selectedIndex == 11) {
                sortByInteger(MangaList, "Number of Scores");
                databaseGrid.add(createContent(MangaList), 0, 1);
            }
        });

        toolbar.getItems().add(genreFilter);
        toolbar.getItems().add(categorySort);
        toolbar.getItems().add(new Button("Help"));
        databaseGrid.add(toolbar, 0, 0);

        // Table
        databaseGrid.add(createContent(MangaList), 0, 1);

        // Home Menu Button
        Button homeMenu = new Button();
        databaseGrid.add(homeMenu, 0, 2);
        homeMenu.setText("Back");
        homeMenu.setFont(DatabaseFont);
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


package simulator.MethodsClasses;

// Basic Imports
import simulator.*;
import simulator.ObjectClasses.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.*;

// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// Layout Imports
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

// Graph Imports
import javafx.collections.FXCollections;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

public class Compare {

    public static void CompareScreen (Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, Account currentAccount) {
        GridPane compareGrid = new GridPane();
        compareGrid.setVgap(10);
        compareGrid.setHgap(10);
        compareGrid.setGridLinesVisible(false);
        compareGrid.setPadding(new Insets(25, 25, 25, 25));
        Font CompareFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        Button homeMenu = new Button();
        compareGrid.add(homeMenu, 0, 2);
        homeMenu.setText("Back");
        homeMenu.setFont(CompareFont);
        homeMenu.setMaxSize(100, 50);
        homeMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenu(primaryStage, MangaList, UserList, currentAccount);
            }
        });

        Button scoreVsPopularityButton = new Button();
        compareGrid.add(scoreVsPopularityButton, 0, 0);
        scoreVsPopularityButton.setText("Scores vs Popularity");
        scoreVsPopularityButton.setFont(CompareFont);
        scoreVsPopularityButton.setMaxSize(200, 50);
        scoreVsPopularityButton.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                scoreVsPopularityScreen(primaryStage, MangaList, UserList, currentAccount);
            }
        });

        Button genreVsTop500Button = new Button();
        compareGrid.add(genreVsTop500Button, 0, 1);
        genreVsTop500Button.setText("Genre vs Top 500");
        genreVsTop500Button.setFont(CompareFont);
        genreVsTop500Button.setMaxSize(200, 50);
        genreVsTop500Button.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                genreVsTop500Screen(primaryStage, MangaList, UserList, currentAccount);
            }
        });

        primaryStage.setWidth(601);
        primaryStage.setScene(new Scene(compareGrid));
        primaryStage.show();
    }

    // Graph Screens
    private static void scoreVsPopularityScreen(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, Account currentAccount) {
        GridPane scoreVsPopularityGrid = new GridPane();
        scoreVsPopularityGrid.setVgap(10);
        scoreVsPopularityGrid.setHgap(10);
        scoreVsPopularityGrid.setGridLinesVisible(false);
        scoreVsPopularityGrid.setPadding(new Insets(25, 25, 25, 25));
        Font CompareFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        scoreVsPopularityGrid.add(ScoreVsPopularityGraph(MangaList), 0, 0);

        Button compareMenu = new Button();
        scoreVsPopularityGrid.add(compareMenu, 0, 1);
        compareMenu.setText("Back");
        compareMenu.setFont(CompareFont);
        compareMenu.setMaxSize(100, 50);
        compareMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                CompareScreen(primaryStage, MangaList, UserList, currentAccount);
            }
        });

        primaryStage.setWidth(600);
        primaryStage.setScene(new Scene(scoreVsPopularityGrid));
        primaryStage.show();
    }

    private static void genreVsTop500Screen(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, Account currentAccount) {
        GridPane genreVsPopularityGrid = new GridPane();
        genreVsPopularityGrid.setVgap(10);
        genreVsPopularityGrid.setHgap(10);
        genreVsPopularityGrid.setGridLinesVisible(false);
        genreVsPopularityGrid.setPadding(new Insets(25, 25, 25, 25));
        Font CompareFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        genreVsPopularityGrid.add(GenreVsTop500Graph(MangaList), 0, 0);

        Button compareMenu = new Button();
        genreVsPopularityGrid.add(compareMenu, 0, 1);
        compareMenu.setText("Back");
        compareMenu.setFont(CompareFont);
        compareMenu.setMaxSize(100, 50);
        compareMenu.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                CompareScreen(primaryStage, MangaList, UserList, currentAccount);
            }
        });

        primaryStage.setWidth(600);
        primaryStage.setScene(new Scene(genreVsPopularityGrid));
        primaryStage.show();
    }

    // Setting up graphs methods
    private static ScatterChart<Double, Integer> ScoreVsPopularityGraph(ArrayList<Manga> MangaList) {
        NumberAxis xAxis = new NumberAxis("Score", 8.1, 9.35, 0.1);
        NumberAxis yAxis = new NumberAxis("Popularity Rank", 1, 100, 1);
        final Series<Double, Integer> AllManga = new Series<>();
        final Series<Double, Integer> Top10Manga = new Series<>();
        AllManga.setName("Top 11-100 in Popularity");
        Top10Manga.setName("Top 10 in Popularity");
        for (Manga Current : MangaList) {
            if (Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) <= 100 && Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) > 10) {
                AllManga.getData().add(new Data<>(Double.parseDouble(Current.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")), Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))));
            }else if (Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) <= 10) {
                Top10Manga.getData().add(new Data<>(Double.parseDouble(Current.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")), Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))));
            }
        }
        ScatterChart<Double, Integer> chart = new ScatterChart(xAxis, yAxis);
        chart.setTitle("Scores vs Popularity for the Top 100 Most Popular Manga");
        chart.getData().add(AllManga);
        chart.getData().add(Top10Manga);
        return chart;
    }

    private static BarChart<String, Integer> GenreVsTop500Graph(ArrayList<Manga> MangaList) {
        String[] genres = {"Act.", "Adv.", "Hor.", "Fan.", "Psych.", "Myst.", "Com.", "Rom.", "Sci.", "Sch.", "SOL", "Pow.", "Mus."};
        int intActionAdultCount = 0;
        int intAdventureAdultCount = 0;
        int intHorrorAdultCount = 0;
        int intFantasyAdultCount = 0;
        int intPsychologicalAdultCount = 0;
        int intMysteryAdultCount = 0;
        int intComedyAdultCount = 0;
        int intRomanceAdultCount = 0;
        int intSciFiAdultCount = 0;
        int intSchoolAdultCount = 0;
        int intSliceofLifeAdultCount = 0;
        int intSuperPowersAdultCount = 0;
        int intMusicAdultCount = 0;

        int intActionChildCount = 0;
        int intAdventureChildCount = 0;
        int intHorrorChildCount = 0;
        int intFantasyChildCount = 0;
        int intPsychologicalChildCount = 0;
        int intMysteryChildCount = 0;
        int intComedyChildCount = 0;
        int intRomanceChildCount = 0;
        int intSciFiChildCount = 0;
        int intSchoolChildCount = 0;
        int intSliceofLifeChildCount = 0;
        int intSuperPowersChildCount = 0;
        int intMusicChildCount = 0;

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(genres));
        NumberAxis yAxis = new NumberAxis("Number of Manga", 0, 120, 5);

        for (Manga current : MangaList) {
            String[] strCurrentGenre = (current.strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).split(" / ");
            for (int i = 0; i < strCurrentGenre.length; i++) {
                if(strCurrentGenre[i].equalsIgnoreCase("Seinen") || strCurrentGenre[i].equalsIgnoreCase("Josei")) {
                    for (int j = 0; j < strCurrentGenre.length; j++) {
                        switch(strCurrentGenre[j]) {
                            case "Action" :
                                intActionAdultCount++;
                                break;
                            case "Adventure" :
                                intAdventureAdultCount++;
                                break;
                            case "Horror" :
                                intHorrorAdultCount++;
                                break;
                            case "Fantasy" :
                                intFantasyAdultCount++;
                                break;
                            case "Psychological" :
                                intPsychologicalAdultCount++;
                                break;
                            case "Mystery" :
                                intMysteryAdultCount++;
                                break;
                            case "Comedy" :
                                intComedyAdultCount++;
                                break;
                            case "Romance" :  
                                intRomanceAdultCount++;
                                break;
                            case "Sci-Fi" :
                                intSciFiAdultCount++;
                                break;
                            case "School" :
                                intSchoolAdultCount++;
                                break;
                            case "Slice of Life" :
                                intSliceofLifeAdultCount++;
                                break;
                            case "Super" :
                                intSuperPowersAdultCount++;
                                break;
                            case "Music" :
                                intMusicAdultCount++;
                                break;
                        }
                    }
                }else if(strCurrentGenre[i].equalsIgnoreCase("Shounen") || strCurrentGenre[i].equalsIgnoreCase("Shoujo")) {
                    for (int j = 0; j < strCurrentGenre.length; j++) {
                        switch(strCurrentGenre[j]) {
                            case "Action" :
                                intActionChildCount++;
                                break;
                            case "Adventure" :
                                intAdventureChildCount++;
                                break;
                            case "Horror" :
                                intHorrorChildCount++;
                                break;
                            case "Fantasy" :
                                intFantasyChildCount++;
                                break;
                            case "Psychological" :
                                intPsychologicalChildCount++;
                                break;
                            case "Mystery" :
                                intMysteryChildCount++;
                                break;
                            case "Comedy" :
                                intComedyChildCount++;
                                break;
                            case "Romance" :  
                                intRomanceChildCount++;
                                break;
                            case "Sci-Fi" :
                                intSciFiChildCount++;
                                break;
                            case "School" :
                                intSchoolChildCount++;
                                break;
                            case "Slice of Life" :
                                intSliceofLifeChildCount++;
                                break;
                            case "Super" :
                                intSuperPowersChildCount++;
                                break;
                            case "Music" :
                                intMusicChildCount++;
                                break;
                        }
                    }
                }
            }   
        }

        XYChart.Series<String, Integer> AdultManga = new XYChart.Series<String, Integer>();
        AdultManga.setName("Older Demographic");

        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[0], intActionAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[1], intAdventureAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[2], intHorrorAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[3], intFantasyAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[4], intPsychologicalAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[5], intMysteryAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[6], intComedyAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[7], intRomanceAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[8], intSciFiAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[9], intSchoolAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[10], intSliceofLifeAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[11], intSuperPowersAdultCount));
        AdultManga.getData().add(new XYChart.Data<String, Integer>(genres[12], intMusicAdultCount));

        XYChart.Series<String, Integer> ChildManga = new XYChart.Series<String, Integer>();
        ChildManga.setName("Younger Demographic");

        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[0], intActionChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[1], intAdventureChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[2], intHorrorChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[3], intFantasyChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[4], intPsychologicalChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[5], intMysteryChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[6], intComedyChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[7], intRomanceChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[8], intSciFiChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[9], intSchoolChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[10], intSliceofLifeChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[11], intSuperPowersChildCount));
        ChildManga.getData().add(new XYChart.Data<String, Integer>(genres[12], intMusicChildCount));

        BarChart<String, Integer> chart = new BarChart(xAxis, yAxis);
        chart.setTitle("Genre vs Number of Manga in the Top 500");
        chart.getData().addAll(AdultManga, ChildManga);
        return chart;
    }
}

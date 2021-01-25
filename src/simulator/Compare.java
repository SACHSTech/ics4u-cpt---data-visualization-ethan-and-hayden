package simulator;

// Basic Imports
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

    public static void CompareScreen (Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {
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
                Main.mainMenu(primaryStage, MangaList, UserList);
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
                scoreVsPopularityScreen(primaryStage, MangaList, UserList);
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
                genreVsTop500Screen(primaryStage, MangaList, UserList);
            }
        });

        primaryStage.setWidth(601);
        primaryStage.setScene(new Scene(compareGrid));
        primaryStage.show();
    }

    // Graph Screens
    private static void scoreVsPopularityScreen(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {
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
                CompareScreen(primaryStage, MangaList, UserList);
            }
        });

        primaryStage.setWidth(600);
        primaryStage.setScene(new Scene(scoreVsPopularityGrid));
        primaryStage.show();
    }

    private static void genreVsTop500Screen(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {
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
                CompareScreen(primaryStage, MangaList, UserList);
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
        final Series<Double, Integer> series = new Series<>();
        series.setName("Manga");
        for (Manga Current : MangaList) {
            if (Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) <= 100) {
                series.getData().add(new Data<>(Double.parseDouble(Current.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")), Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))));
            }
        }
        ScatterChart<Double, Integer> chart = new ScatterChart(xAxis, yAxis);
        chart.setTitle("Scores vs Popularity for the Top 100 Most Popular Manga");
        chart.getData().add(series);
        return chart;
    }

    private static BarChart<String, Integer> GenreVsTop500Graph(ArrayList<Manga> MangaList) {
        String[] genres = {"Act.", "Adv.", "Hor.", "Fan.", "Psych.", "Myst.", "Com.", "Rom.", "Sci.", "Sch.", "SOL", "Pow.", "Mus."};
        int intActionCount = 0;
        int intAdventureCount = 0;
        int intHorrorCount = 0;
        int intFantasyCount = 0;
        int intPsychologicalCount = 0;
        int intMysteryCount = 0;
        int intComedyCount = 0;
        int intRomanceCount = 0;
        int intSciFiCount = 0;
        int intSchoolCount = 0;
        int intSliceofLifeCount = 0;
        int intSuperPowersCount = 0;
        int intMusicCount = 0;

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(genres));
        NumberAxis yAxis = new NumberAxis("Number of Manga", 0, 200, 5);

        for (Manga current : MangaList) {
            String[] strCurrentGenre = (current.strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "")).split(" / ");
            for (int i = 0; i < strCurrentGenre.length; i++) {
                switch(strCurrentGenre[i]) {
                    case "Action" :
                        intActionCount++;
                        break;
                    case "Adventure" :
                        intAdventureCount++;
                        break;
                    case "Horror" :
                        intHorrorCount++;
                        break;
                    case "Fantasy" :
                        intFantasyCount++;
                        break;
                    case "Psychological" :
                        intPsychologicalCount++;
                        break;
                    case "Mystery" :
                        intMysteryCount++;
                        break;
                    case "Comedy" :
                        intComedyCount++;
                        break;
                    case "Romance" :  
                        intRomanceCount++;
                        break;
                    case "Sci-Fi" :
                        intSciFiCount++;
                        break;
                    case "School" :
                        intSchoolCount++;
                        break;
                    case "Slice of Life" :
                        intSliceofLifeCount++;
                        break;
                    case "Super" :
                        intSuperPowersCount++;
                        break;
                    case "Music" :
                        intMusicCount++;
                        break;
                }
            }   
        }
        XYChart.Series<String, Integer> MangaSeries = new XYChart.Series<String, Integer>();
        MangaSeries.setName("Manga");

        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[0], intActionCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[1], intAdventureCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[2], intHorrorCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[3], intFantasyCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[4], intPsychologicalCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[5], intMysteryCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[6], intComedyCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[7], intRomanceCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[8], intSciFiCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[9], intSchoolCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[10], intSliceofLifeCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[11], intSuperPowersCount));
        MangaSeries.getData().add(new XYChart.Data<String, Integer>(genres[12], intMusicCount));

        BarChart<String, Integer> chart = new BarChart(xAxis, yAxis);
        chart.setTitle("Genre vs Number of Top 500 Manga");
        chart.getData().add(MangaSeries);
        return chart;
    }
}

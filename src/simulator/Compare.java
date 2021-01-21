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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class Compare {

    public static void CompareScreen (Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList) {
        GridPane compareGrid = new GridPane();
        compareGrid.setVgap(10);
        compareGrid.setHgap(10);
        compareGrid.setGridLinesVisible(false);
        compareGrid.setPadding(new Insets(25, 25, 25, 25));
        Font CompareFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        Button homeMenu = new Button();
        compareGrid.add(homeMenu, 0, 1);
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

        primaryStage.setWidth(601);
        primaryStage.setScene(new Scene(compareGrid));
        primaryStage.show();
    }
    
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

    private static ScatterChart<Double, Integer> ScoreVsPopularityGraph(ArrayList<Manga> MangaList) {
        NumberAxis xAxis = new NumberAxis("Score", 8.1, 9.35, 0.1);
        NumberAxis yAxis = new NumberAxis("Popularity Rank", 1, 100, 1);
        final Series<Double, Integer> series = new Series<>();
        series.setName("Manga");
        for (Manga Current : MangaList) {
            if (Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "")) <= 100) {
                series.getData().add(new Data (Double.parseDouble(Current.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")), Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))));
            }
        }
        ScatterChart<Double, Integer> chart = new ScatterChart(xAxis, yAxis);
        chart.setTitle("Scores vs Popularity for the Top 100 Most Popular Manga");
        chart.getData().add(series);
        return chart;
    }
}

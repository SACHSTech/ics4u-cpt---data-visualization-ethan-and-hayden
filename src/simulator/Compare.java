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
import javafx.scene.chart.XYChart;
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

        compareGrid.add(ScoreVsPopularityGraph(MangaList), 0, 0);

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

        primaryStage.setWidth(601);
        primaryStage.setScene(new Scene(compareGrid));
        primaryStage.show();
    }
 
    public static ScatterChart<Double, Integer> ScoreVsPopularityGraph(ArrayList<Manga> MangaList) {
        NumberAxis xAxis = new NumberAxis("Score", 8.1, 9.35, 0.1);
        NumberAxis yAxis = new NumberAxis("Popularity Rank", 1, 100, 1);
        final Series<Number, Number> series = new Series<>();
        series.setName("Top 100 Most Popular Manga");
        for (Manga Current : MangaList) {
            series.getData().add(new Data (Double.parseDouble(Current.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")), Integer.parseInt(Current.intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", ""))));
        }
        ScatterChart chart = new ScatterChart(xAxis, yAxis);
        chart.getData().add(series);
        return chart;
    }
}

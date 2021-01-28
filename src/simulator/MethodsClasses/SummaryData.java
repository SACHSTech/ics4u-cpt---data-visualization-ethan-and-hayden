package simulator.MethodsClasses;

import simulator.ObjectClasses.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SummaryData {

    /**
     * Method that sets up a summary screen of the database.
     * 
     * @param primaryStage
     * @param MangaList
     * @param UserList
     * @param currentAccount
     */
    public static void dataSummaryScreen(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, Account currentAccount) {
        
        // Refreshes stage
        primaryStage.setWidth(601);

        GridPane summaryGrid = new GridPane();
        summaryGrid.setVgap(10);
        summaryGrid.setHgap(10);
        summaryGrid.setGridLinesVisible(false);
        summaryGrid.setPadding(new Insets(25, 25, 25, 25));
        Font summaryFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Title
        Text summaryTitle = new Text("Summary of the graph, 'Top 500 Manga'");
        summaryTitle.setFont(summaryFont);
        summaryGrid.add(summaryTitle, 0, 0);

        // Summary Info
        Text mangaCountText = new Text ("Number of Manga: " + mangaListCount(MangaList));
        Text highestScoreText = new Text ("Manga with the Highest Score: " + mangaListMax(MangaList));
        Text lowestScoreText = new Text ("Manga with the Lowest Score: " + mangaListMin(MangaList));
        Text averageScoreText = new Text ("Average Score (Mean): " + meanMangaScore(MangaList));
        Text medianScoreText = new Text ("Average Score (Median): " + medianMangaScore(MangaList));
        Text standardDeviationText = new Text ("Standard Deviation: " + standardDeviation(meanMangaScore(MangaList), MangaList));

        VBox summaryInfoBox = new VBox(mangaCountText, highestScoreText, lowestScoreText, averageScoreText, medianScoreText, standardDeviationText);
        summaryInfoBox.setSpacing(10);
        summaryInfoBox.setPrefHeight(375);
        summaryGrid.add(summaryInfoBox, 0, 1);
        
        // Database Button
        Button DatabaseBtn = new Button();
        summaryGrid.add(DatabaseBtn, 0, 2);
        DatabaseBtn.setText("Back");
        DatabaseBtn.setFont(summaryFont);
        DatabaseBtn.setWrapText(true);
        DatabaseBtn.setMaxSize(100, 50);
        DatabaseBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Database.DatabaseScreen(primaryStage, MangaList, UserList, currentAccount);
            }
        });

        // Setting up Background of Menu
        primaryStage.setScene(new Scene(summaryGrid));
        primaryStage.show();
    }

    /**
     * method that returns the amount of items in the database.
     * 
     * @param MangaList
     * @return
     */
    private static int mangaListCount(ArrayList<Manga> MangaList) {
        return MangaList.size();
    }

    /**
     * Method that returns the title of the manga with the highest score.
     * 
     * @param MangaList
     * @return
     */
    private static String mangaListMax(ArrayList<Manga> MangaList) {
        Manga Highest = MangaList.get(0);
        for (Manga Current : MangaList) {
            if ( Double.parseDouble(Current.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")) > Double.parseDouble(Highest.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", ""))) {
                Highest = Current;
            }
        }
        return Highest.strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "");
    }

    /**
     * Method that returns the title of the manga with the lowest score.
     * 
     * @param MangaList
     * @return
     */
    private static String mangaListMin(ArrayList<Manga> MangaList) {
        Manga Lowest = MangaList.get(0);
        for (Manga Current : MangaList) {
            if ( Double.parseDouble(Current.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")) < Double.parseDouble(Lowest.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", ""))) {
                Lowest = Current;
            }
        }
        return Lowest.strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "");
    }

    /**
     * Method that returns the mean score of the database.
     * 
     * @param MangaList
     * @return
     */
    private static double meanMangaScore(ArrayList<Manga> MangaList) {
        double dblTotalScore = 0.00;
        int intTotalManga = MangaList.size();
        double dblMeanScore = 0.00;
        if (intTotalManga != 0) {
            for(Manga Current : MangaList) {
                dblTotalScore = dblTotalScore + Double.parseDouble((Current.dblScoreProperty()).toString().replace("DoubleProperty [value: ", "").replace("]", ""));
            }
            dblMeanScore = dblTotalScore / intTotalManga;
            return Math.round(dblMeanScore * 100) / 100.00;
        }
        return 0;
    }

    /**
     * Method that returns the median score of the database.
     * 
     * @param MangaList
     * @return
     */
    private static double medianMangaScore(ArrayList<Manga> MangaList) {
        return Double.parseDouble(MangaList.get(MangaList.size() / 2).dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", ""));
    }

    /**
     * Method that calculates the standard deviation of the database.
     * 
     * @param dblMean
     * @param MangaList
     * @return
     */
    private static double standardDeviation(double dblMean, ArrayList<Manga> MangaList) {
        double dblSum = 0;
        double dblStandardDeviation;
        for (Manga Current : MangaList) {
            dblSum = dblSum + Math.pow(Double.parseDouble(Current.dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "")) - dblMean, 2);
        }
        dblStandardDeviation = Math.sqrt(dblSum / MangaList.size());
        return Math.round(dblStandardDeviation * 100) / 100.00;
    }
}
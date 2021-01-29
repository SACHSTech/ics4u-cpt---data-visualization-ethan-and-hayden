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
     * @param primaryStage - stage that displays all scenes.
     * @param mangaList - an arraylist filled with Manga objects.
     * @param userList - an arraylist filled with UserManga objects.
     * @param currentAccount - an Account object.
     */
    public static void dataSummaryScreen(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {
        
        // Refreshes stage.
        primaryStage.setWidth(601);

        // Creating gridpane to organize children.
        GridPane summaryGrid = new GridPane();
        summaryGrid.setVgap(10);
        summaryGrid.setHgap(10);
        summaryGrid.setGridLinesVisible(false);
        summaryGrid.setPadding(new Insets(25, 25, 25, 25));
        Font summaryFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);
        Font dataFont = Font.font("Comic Sans MS", FontWeight.NORMAL, 12);

        // Creating text for title.
        Text summaryTitle = new Text("Summary of the graph, 'Top 500 Manga'");
        summaryTitle.setFont(summaryFont);
        summaryGrid.add(summaryTitle, 0, 0);

        // Creating summary info.
        Text mangaCountText = new Text ("Number of Manga: " + mangaListCount(mangaList));
        mangaCountText.setFont(dataFont);
        Text highestScoreText = new Text ("Manga with the Highest Score: " + mangaListMax(mangaList));
        highestScoreText.setFont(dataFont);
        Text lowestScoreText = new Text ("Manga with the Lowest Score: " + mangaListMin(mangaList));
        lowestScoreText.setFont(dataFont);
        Text averageScoreText = new Text ("Average Score (Mean): " + meanMangaScore(mangaList));
        averageScoreText.setFont(dataFont);
        Text medianScoreText = new Text ("Average Score (Median): " + medianMangaScore(mangaList));
        medianScoreText.setFont(dataFont);
        Text standardDeviationText = new Text ("Standard Deviation: " + standardDeviation(meanMangaScore(mangaList), mangaList));
        standardDeviationText.setFont(dataFont);

        // Creating VBox to organize summary info in.
        VBox summaryInfoBox = new VBox(mangaCountText, highestScoreText, lowestScoreText, averageScoreText, medianScoreText, standardDeviationText);
        summaryInfoBox.setSpacing(10);
        summaryInfoBox.setPrefHeight(375);
        summaryGrid.add(summaryInfoBox, 0, 1);
        
        // Creating database button.
        Button DatabaseBtn = new Button();
        summaryGrid.add(DatabaseBtn, 0, 2);
        DatabaseBtn.setText("Back");
        DatabaseBtn.setFont(summaryFont);
        DatabaseBtn.setWrapText(true);
        DatabaseBtn.setMaxSize(100, 50);
        DatabaseBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event method that returns the user back to the database.
            @Override
            public void handle(ActionEvent event) {
                Database.databaseScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Displaying the gridpane to the stage.
        primaryStage.setScene(new Scene(summaryGrid));
        primaryStage.show();
    }

    /**
     * method that returns the amount of items in the database.
     * 
     * @param mangaList - an arraylist of Manga objects.
     * @return the size of the mangaList as an integer.
     */
    private static int mangaListCount(ArrayList<Manga> mangaList) {
        return mangaList.size();
    }

    /**
     * Method that returns the title of the manga with the highest score.
     *  
     * @param mangaList - an arraylist of Manga objects.
     * @return the title of the manga with the highest score as a string.
     */
    private static String mangaListMax(ArrayList<Manga> mangaList) {

        // Sets highest manga as the manga at the front of the list.
        Manga highest = mangaList.get(0);

        // Iterates through the database.
        for (Manga current : mangaList) {

            // Checking if the current manga has a higher score that the current highest. If it is higher, sets the highest as the current manga.
            if (current.getScore() > highest.getScore()) {
                highest = current;
            }
        }

        // Returning the highest manga's title.
        return highest.getTitle();
    }

    /**
     * Method that returns the title of the manga with the lowest score.
     * 
     * @param mangaList - an arraylist filled with Manga objects.
     * @return the title of the manga with the lowest score as a string.
     */
    private static String mangaListMin(ArrayList<Manga> mangaList) {

        // Setting the manga with the lowest score as the manga at the front of the list.
        Manga lowest = mangaList.get(0);

        // Iterates through the database.
        for (Manga current : mangaList) {

            // Checking if the current manga has a lower score that the current lowest. If it is lowest, sets the lowest as the current manga.
            if (current.getScore() < lowest.getScore()) {
                lowest = current;
            }
        }

        // Returns the title of the lowest manga.
        return lowest.getTitle();
    }

    /**
     * Method that returns the mean score of the database.
     * 
     * @param mangaList - an arraylist filled with Manga objects.
     * @return the mean score as a double. It is rounded to two decimal places.
     */
    private static double meanMangaScore(ArrayList<Manga> mangaList) {

        // Initializing variables.
        double dblTotalScore = 0.00;
        int intTotalManga = mangaList.size();
        double dblMeanScore = 0.00;

        // If the amount of manga in the list is not 0, uses a for loop to add all scores in the list together. 
        if (intTotalManga != 0) {
            for (Manga current : mangaList) {
                dblTotalScore = dblTotalScore + current.getScore();
            }

            // Divides the sum of all scores by the amount of manga in the list. Then rounds it to two decimal places.
            dblMeanScore = dblTotalScore / intTotalManga;
            return Math.round(dblMeanScore * 100) / 100.00;
        }

        // Otherwise returns 0.
        return 0;
    }

    /**
     * Method that returns the median score of the database.
     * 
     * @param mangaList - an arraylist filled with Manga objects.
     * @return the median of the database as a double.
     */
    private static double medianMangaScore(ArrayList<Manga> mangaList) {

        // Divides the list by two and uses that number as the index. Gets the title of the manga with that index.
        return mangaList.get(mangaList.size() / 2).getScore();
    }

    /**
     * Method that calculates the standard deviation of the database.
     * 
     * @param dblMean - the mean score of the database as a double.
     * @param mangaList - an arraylist filled with Manga objects.
     * @return the standard deviation of the scores of the database as a double. Rounded to two decimal places.
     */
    private static double standardDeviation(double dblMean, ArrayList<Manga> mangaList) {

        // Initializing variables.
        double dblSum = 0;
        double dblStandardDeviation;

        // Iterating through the list and getting the sum of the square of the the difference of the mean with every score in the database.
        for (Manga current : mangaList) {
            dblSum = dblSum + Math.pow(current.getScore() - dblMean, 2);
        }

        // Square rooting the sum divided by the size of the database.
        dblStandardDeviation = Math.sqrt(dblSum / mangaList.size());

        // Rounding the standard deviation to two decimal places.
        return Math.round(dblStandardDeviation * 100) / 100.00;
    }
}

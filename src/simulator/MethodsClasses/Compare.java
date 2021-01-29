package simulator.MethodsClasses;

import simulator.*;
import simulator.ObjectClasses.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.*;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;

public class Compare {

    /**
     * Screen that shows all available graphs.
     * 
     * @param primaryStage - the stage where every scene is displayed on
     * @param mangaList - an arraylist filled with Manga objects. An arraylist of the entire dataset.
     * @param userList - an arraylist filled with UserManga objects. An arraylist of the user's entire list.
     * @param currentAccount - an account object.
     */
    public static void compareScreen (Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Changing the width of the stage. Essentially refreshes the stage.
        primaryStage.setWidth(600);

        // Creating gridpane to organize children.
        GridPane compareGrid = new GridPane();
        compareGrid.setVgap(10);
        compareGrid.setHgap(10);
        compareGrid.setGridLinesVisible(false);
        compareGrid.setPadding(new Insets(25, 25, 25, 25));
        Font CompareFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Creating button to allow the user to return back to the main menu.
        Button homeMenuBtn = new Button();
        compareGrid.add(homeMenuBtn, 0, 2);
        homeMenuBtn.setText("Back");
        homeMenuBtn.setFont(CompareFont);
        homeMenuBtn.setMaxSize(100, 50);
        homeMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event method that runs a method that returns back to the main menu.
            @Override
            public void handle(ActionEvent event) {
                Main.mainMenuScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Creating a button to allow the user to access the score vs popularity graph.
        Button scoreVsPopularityBtn = new Button();
        compareGrid.add(scoreVsPopularityBtn, 0, 0);
        scoreVsPopularityBtn.setText("Scores vs Popularity");
        scoreVsPopularityBtn.setFont(CompareFont);
        scoreVsPopularityBtn.setMaxSize(200, 50);
        scoreVsPopularityBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event method that runs a method that returns to the score vs popularity graph.
            @Override
            public void handle(ActionEvent event) {
                scoreVsPopularityScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Creating button to allow the user to access the genre vs top 500 graph.
        Button genreVsTop500Btn = new Button();
        compareGrid.add(genreVsTop500Btn, 0, 1);
        genreVsTop500Btn.setText("Genre vs Top 500");
        genreVsTop500Btn.setFont(CompareFont);
        genreVsTop500Btn.setMaxSize(200, 50);
        genreVsTop500Btn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event method that runs a method that returns to the genre vs top 500 graph.
            @Override
            public void handle(ActionEvent event) {
                genreVsTop500Screen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Creating VBox to place every button on.
        VBox graphVBox = new VBox();
        graphVBox.setPrefSize(200, 375);
        graphVBox.setSpacing(20);
        graphVBox.getChildren().addAll(scoreVsPopularityBtn,genreVsTop500Btn);

        // Adding vbox to gridpane.
        compareGrid.add(graphVBox, 0, 0); 

        // Setting the grid into the stage.
        primaryStage.setScene(new Scene(compareGrid));
        primaryStage.show();
    }

    /**
     * Method that displays the graph, "Score vs popularity" on the stage.
     * 
     * @param primaryStage - stage that every scene is displayed on
     * @param mangaList - an arraylist of Manga objects
     * @param userList - an arraylist of UserManga objects 
     * @param currentAccount - an account object
     */
    private static void scoreVsPopularityScreen(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Refreshes the stage.
        primaryStage.setWidth(601);

        // Creating gridpane to organize children.
        GridPane scoreVsPopularityGrid = new GridPane();
        scoreVsPopularityGrid.setVgap(10);
        scoreVsPopularityGrid.setHgap(10);
        scoreVsPopularityGrid.setGridLinesVisible(false);
        scoreVsPopularityGrid.setPadding(new Insets(25, 25, 25, 25));
        Font CompareFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Adding a method which returns a scatterplot to the grid.
        scoreVsPopularityGrid.add(ScoreVsPopularityGraph(mangaList, userList), 0, 0);

        // Creating button that allows the user to return to the compare menu.
        Button compareMenuBtn = new Button();
        scoreVsPopularityGrid.add(compareMenuBtn, 0, 1);
        compareMenuBtn.setText("Back");
        compareMenuBtn.setFont(CompareFont);
        compareMenuBtn.setMaxSize(100, 50);
        compareMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event method that runs a method that returns to the compare screen.
            @Override
            public void handle(ActionEvent event) {
                compareScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Setting the gridpane on the stage.
        primaryStage.setScene(new Scene(scoreVsPopularityGrid));
        primaryStage.show();
    }

    /**
     * Method that displays the graph, "genre vs top 500 manga" on the stage.
     * 
     * @param primaryStage - the stage where every scene is displayed on.
     * @param mangaList - an arraylist filled with Manga objects.
     * @param userList - an arraylist filled with UserManga objects.
     * @param currentAccount - an account object.
     */
    private static void genreVsTop500Screen(Stage primaryStage, ArrayList<Manga> mangaList, ArrayList<UserManga> userList, Account currentAccount) {

        // Refreshes the stage.
        primaryStage.setWidth(601);

        // Creating gridpane to organize children.
        GridPane genreVsPopularityGrid = new GridPane();
        genreVsPopularityGrid.setVgap(10);
        genreVsPopularityGrid.setHgap(10);
        genreVsPopularityGrid.setGridLinesVisible(false);
        genreVsPopularityGrid.setPadding(new Insets(25, 25, 25, 25));
        Font CompareFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Adding a method which returns a bar graph into the gridpane.
        genreVsPopularityGrid.add(GenreVsTop500Graph(mangaList), 0, 0);

        // Button that allows the user to return to the compare menu.
        Button compareMenuBtn = new Button();
        genreVsPopularityGrid.add(compareMenuBtn, 0, 1);
        compareMenuBtn.setText("Back");
        compareMenuBtn.setFont(CompareFont);
        compareMenuBtn.setMaxSize(100, 50);
        compareMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            // Event method that returns to the compare screen.
            @Override
            public void handle(ActionEvent event) {
                compareScreen(primaryStage, mangaList, userList, currentAccount);
            }
        });

        // Setting the gridpane onto the stage.
        primaryStage.setScene(new Scene(genreVsPopularityGrid));
        primaryStage.show();
    }

    /**
     * Method that sets up the graph, Score vs Popularity.
     * 
     * @param mangaList - an arraylist of Manga objects.
     * @param userList - an arraylist of UserManga objects.
     * @return ScatterChart<Double, Integer> - a javafx object that displays a scatterplot with an Xaxis for doubles and Yaxis for Integers
     */
    private static ScatterChart<Double, Integer> ScoreVsPopularityGraph(ArrayList<Manga> mangaList, ArrayList<UserManga> userList) {

        // Variables.
        int intCount;

        // Setting up y and x axis.
        NumberAxis xAxis = new NumberAxis("Score", 8.1, 9.35, 0.1);
        NumberAxis yAxis = new NumberAxis("Popularity Rank", 1, 100, 1);

        // Setting up series for the scatterplot.
        final Series<Double, Integer> MangaFromUserList = new Series<>();
        final Series<Double, Integer> MangaFromDatabase = new Series<>();
        MangaFromUserList.setName("Manga from your list");
        MangaFromDatabase.setName("Manga from Database");

        // For loop that iterates through the manga database. Then, places all manga with a popularity rank less than 100 in their appropriate series.
        for (Manga current : mangaList) {

            // Initializing count.
            intCount = 0;

            // If statement that checks if the current manga's popularity is less or equal to 100.
            if (current.getPopularity() <= 100) {

                // If statement that checks if userlist's size is 1.
                if (userList.size() >= 1) {

                    // Iterates through the user's list.
                    for (UserManga currentUserManga : userList) {
                        intCount++;

                        // If the current manga from database is equal to the current manga from the userlist, adds the current manga's data to "MangaFromUserList" series.
                        if (current.getTitle().equalsIgnoreCase(currentUserManga.getTitle())) {
                            MangaFromUserList.getData().add(new Data<>(current.getScore(), current.getPopularity()));
                            break;
                        
                        // Otherwise, if the for loop reaches the end of the userlist, adds the current manga's data to "MangaFromDatabase" series.
                        }else if (intCount == userList.size()) {
                            MangaFromDatabase.getData().add(new Data<>(current.getScore(), current.getPopularity()));
                        } 
                    }
                
                // Otherwise, just adds current manga's data to "MangaFromDatabase" Series.
                }else {
                    MangaFromDatabase.getData().add(new Data<>(current.getScore(), current.getPopularity()));
                }
            }
        }
        
        // Creating the scatterchart and returning it.
        ScatterChart<Double, Integer> chart = new ScatterChart(xAxis, yAxis);
        chart.setTitle("Scores vs Popularity for the Top 100 Most Popular Manga");
        chart.getData().add(MangaFromDatabase);
        chart.getData().add(MangaFromUserList);
        return chart;
    }

    /**
     * Method that sets up the graph, Genre vs Top 500 Manga.
     * 
     * @param MangaList - an arraylist filled with Manga objects.
     * @return BarChart<String, Integer> - a javafx object that displays a barchart with an Xaxis for Strings and a Yaxis for Integers.
     */ 
    private static BarChart<String, Integer> GenreVsTop500Graph(ArrayList<Manga> MangaList) {

        // Creating and initializing Variables
        String[] genres = {"Act.", "Adv.", "Hor.", "Fan.", "Psych.", "Myst.", "Com.", "Rom.", "Sci.", "Sch.", "SOL", "Pow.", "Mus."};
        String[] strGenreArray;
        int intCurrentGenre;
        int intCurrentGenre2;
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

        // Creating x and y axis.
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(genres));
        NumberAxis yAxis = new NumberAxis("Number of Manga", 0, 120, 5);

        // For loop that iterates through the Manga database and counts every Manga based on their genre.
        for (Manga current : MangaList) {

            // Putting each manga's genres into an array.
            strGenreArray = (current.getGenre()).split(" / ");

            // For loop that interates through the current Manga's genres. 
            for (intCurrentGenre = 0; intCurrentGenre < strGenreArray.length; intCurrentGenre++) {

                // Checks if there is "Seinen" or "Josei" in the current Manga's genres list. "Seinen" or "Josei" are manga for adults.
                if(strGenreArray[intCurrentGenre].equalsIgnoreCase("Seinen") || strGenreArray[intCurrentGenre].equalsIgnoreCase("Josei")) {

                    // For loop that iterates through the current Manga's genres list again.
                    for (intCurrentGenre2 = 0; intCurrentGenre2 < strGenreArray.length; intCurrentGenre2++) {

                        // Switch statement that checks what genres the current Manga has. Adds one point for each variable that represents the specific genre.
                        switch(strGenreArray[intCurrentGenre2]) {
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
                
                // Checks if the current Manga has the genre of "Shounen" or "Shoujo" instead. "Shounen" and "Shoujo" are manga for kids.
                }else if(strGenreArray[intCurrentGenre].equalsIgnoreCase("Shounen") || strGenreArray[intCurrentGenre].equalsIgnoreCase("Shoujo")) {

                    // For loop that iterates through the current Manga's genres again.
                    for (intCurrentGenre2 = 0; intCurrentGenre2 < strGenreArray.length; intCurrentGenre2++) {

                        // Switch statement that checks what genre the current manga has. Adds one for each variable that represents the specific genre.
                        switch(strGenreArray[intCurrentGenre2]) {
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

        // Creating adult series for the bar graph. Adding values to the series too.
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

        // Creating child series for the bar graph. Adding values to the series too.
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

        // Creating bar chart and returning it.
        BarChart<String, Integer> chart = new BarChart(xAxis, yAxis);
        chart.setTitle("Genre vs Number of Manga in the Top 500");
        chart.getData().addAll(AdultManga, ChildManga);
        return chart;
    }
}

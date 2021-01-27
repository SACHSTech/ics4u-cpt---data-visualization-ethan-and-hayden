package simulator.MethodsClasses;

// Basic Imports
import simulator.ObjectClasses.*;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

// Table Imports
import javafx.geometry.Insets;

// Layout Imports
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.VPos;
import javafx.geometry.HPos;

public class IndividualManga {

    public static void individualRecord(Stage primaryStage, ArrayList<Manga> MangaList, ArrayList<UserManga> UserList, Manga Current, Account currentAccount) {
        
        // Refreshes stage
        primaryStage.setWidth(600);

        GridPane individualGrid = new GridPane();
        individualGrid.setVgap(10);
        individualGrid.setHgap(10);
        individualGrid.setGridLinesVisible(false);
        individualGrid.setPadding(new Insets(25, 25, 25, 25));
        Font individualFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Title
        Text individualTitle = new Text(((Current.strTitleProperty()).toString()).replace("StringProperty [value: ", "").replace("]", ""));
        individualTitle.setFont(individualFont);
        individualGrid.add(individualTitle, 0, 0);

        // Synopsis
        Text individualSynopsis = new Text("Synopsis: \n\n" + ((Current.strSynopsisProperty()).toString()).replace("StringProperty [value: ", "").replace("]", "").replace("*", ","));
        individualSynopsis.setTextAlignment(TextAlignment.LEFT);
        individualSynopsis.setWrappingWidth(350);
        individualSynopsis.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 10));
        individualGrid.add(individualSynopsis, 0, 1);
        GridPane.setHalignment(individualSynopsis,HPos.LEFT);
        GridPane.setValignment(individualSynopsis, VPos.TOP);

        // Other Summary VBox
        Text individualScore = new Text("Score: " + (Current.dblScoreProperty().toString()).replace("DoubleProperty [value: ", "").replace("]", ""));
        individualScore.setFont(individualFont);
        individualGrid.add(individualScore, 1, 0);

        Text individualRank = new Text("Rank: " + (Current.intRankProperty().toString()).replace("IntegerProperty [value: ", "").replace("]", ""));
        individualRank.setWrappingWidth(180);
        individualRank.setFont(individualFont);
        
        Text individualPopularity = new Text("Popularity: " + (Current.intPopularityProperty().toString()).replace("IntegerProperty [value: ", "").replace("]", ""));
        individualPopularity.setWrappingWidth(180);
        individualPopularity.setFont(individualFont);

        Text individualScoreNumber = new Text("Number of Scores: " + (Current.intScoreNumbersProperty().toString()).replace("IntegerProperty [value: ", "").replace("]", ""));
        individualScoreNumber.setWrappingWidth(180);
        individualScoreNumber.setFont(individualFont);

        Text individualType = new Text("Type: " + (Current.strTypeProperty().toString()).replace("StringProperty [value: ", "").replace("]", ""));
        individualType.setWrappingWidth(180);
        individualType.setFont(individualFont);

        Text individualChapters = new Text("Chapters: " + (Current.strChapterProperty().toString()).replace("StringProperty [value: ", "").replace("]", ""));
        individualChapters.setWrappingWidth(180);
        individualChapters.setFont(individualFont);

        Text individualPublished = new Text("Publish Year: " + (Current.intPublishedProperty().toString()).replace("IntegerProperty [value: ", "").replace("]", ""));
        individualPublished.setWrappingWidth(180);
        individualPublished.setFont(individualFont);

        Text individualGenre = new Text("Genres: " + (Current.strGenreProperty().toString()).replace("StringProperty [value: ", "").replace("]", ""));
        individualGenre.setWrappingWidth(180);
        individualGenre.setFont(individualFont);

        Text individualAuthor = new Text("Author: " + (Current.strAuthorProperty().toString()).replace("StringProperty [value: ", "").replace("]", ""));
        individualAuthor.setWrappingWidth(180);
        individualAuthor.setFont(individualFont);

        Text individualSerialization = new Text("Serialization: " + (Current.strSerializationProperty().toString()).replace("StringProperty [value: ", "").replace("]", ""));
        individualSerialization.setWrappingWidth(180);
        individualSerialization.setFont(individualFont);

        VBox individualSummary = new VBox(individualRank, individualPopularity, individualScoreNumber, individualType, individualChapters, individualPublished, individualGenre, individualAuthor, individualSerialization);
        individualSummary.setSpacing(10);
        individualSummary.setPrefSize(180, 500);
        individualGrid.add(individualSummary, 1, 1);

        // Database Button
        Button DatabaseBtn = new Button();
        individualGrid.add(DatabaseBtn, 0, 2);
        DatabaseBtn.setText("Back");
        DatabaseBtn.setFont(individualFont);
        DatabaseBtn.setWrapText(true);
        DatabaseBtn.setMaxSize(100, 50);
        DatabaseBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Database.DatabaseScreen(primaryStage, MangaList, UserList, currentAccount);
            }
        });

        // Setting up Background of Menu
        primaryStage.setScene(new Scene(individualGrid));
        primaryStage.show();
    }
}

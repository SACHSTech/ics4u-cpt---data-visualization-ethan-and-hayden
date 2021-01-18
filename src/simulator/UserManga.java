package simulator;

// Property imports
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserManga extends Manga {
    private StringProperty strUserStatus;
    private IntegerProperty intUserScore;

    public UserManga(String strTitle, String strType, String strChapter, String strStatus, String strPublished, String strGenre, String strAuthor, String strSerialization, double dblScore, int intRank, int intPopularity, int intScoreNumbers, int intUserScore, String strUserStatus) {
        super(strTitle, strType, strChapter, strStatus, strPublished, strGenre, strAuthor, strSerialization, dblScore, intRank, intPopularity, intScoreNumbers);
        this.strUserStatus = new SimpleStringProperty(strUserStatus);
        this.intUserScore = new SimpleIntegerProperty(intUserScore);
    }
    
    public StringProperty strUserStatusProperty() {
        return strUserStatus;
    }

    public IntegerProperty intUserScoreProperty() {
        return intUserScore;
    }

    public void setUserChapter(String strUserStatus) {
        this.strUserStatus = new SimpleStringProperty(strUserStatus);
    }

    public void setUserScore(int intUserScore) {
        this.intUserScore = new SimpleIntegerProperty(intUserScore);
    }
    public static UserManga convertToUserManga(Manga manga) {
        return new UserManga((manga.strTitleProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strTypeProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strChapterProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strStatusProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strPublishedProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strGenreProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strAuthorProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strSerializationProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), Double.parseDouble((manga.dblScoreProperty()).toString().replace("DoubleProperty [value: ", "").replace("]", "")), Integer.parseInt((manga.intRankProperty()).toString().replace("IntegerProperty [value: ", "").replace("]", "")), Integer.parseInt((manga.intPopularityProperty()).toString().replace("IntegerProperty [value: ", "").replace("]", "")), Integer.parseInt((manga.intScoreNumbersProperty()).toString().replace("IntegerProperty [value: ", "").replace("]", "")), 0, "Reading");
    }
}
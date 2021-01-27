package simulator.ObjectClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class UserManga extends Manga {
    private StringProperty strUserStatus;
    private IntegerProperty intUserScore;
    private BooleanProperty isUserSelected;

    public UserManga(String strTitle, String strType, String strChapter, String strStatus, int intPublished, String strGenre, String strAuthor, String strSerialization, double dblScore, int intRank, int intPopularity, int intScoreNumbers, String strSynopsis, int intUserScore, String strUserStatus, boolean isUserSelected) {
        super(strTitle, strType, strChapter, strStatus, intPublished, strGenre, strAuthor, strSerialization, dblScore, intRank, intPopularity, intScoreNumbers, strSynopsis);
        this.strUserStatus = new SimpleStringProperty(strUserStatus);
        this.intUserScore = new SimpleIntegerProperty(intUserScore);
        this.isUserSelected = new SimpleBooleanProperty(isUserSelected);
    }
    
    public StringProperty strUserStatusProperty() {
        return strUserStatus;
    }

    public IntegerProperty intUserScoreProperty() {
        return intUserScore;
    }

    public BooleanProperty isUserSelectedProperty() {
        return isUserSelected;
    }

    public void setUserStatus(String strUserStatus) {
        this.strUserStatus = new SimpleStringProperty(strUserStatus);
    }

    public void setUserScore(int intUserScore) {
        this.intUserScore = new SimpleIntegerProperty(intUserScore);
    }

    public void setUserSelected(Boolean isUserSelected) {
        this.isUserSelected = new SimpleBooleanProperty(isUserSelected);
    }

    public static UserManga convertToUserManga(Manga manga) {
        return new UserManga((manga.strTitleProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strTypeProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strChapterProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strStatusProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), Integer.parseInt((manga.intPublishedProperty()).toString().replace("IntegerProperty [value: ", "").replace("]", "")), (manga.strGenreProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strAuthorProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), (manga.strSerializationProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), Double.parseDouble((manga.dblScoreProperty()).toString().replace("DoubleProperty [value: ", "").replace("]", "")), Integer.parseInt((manga.intRankProperty()).toString().replace("IntegerProperty [value: ", "").replace("]", "")), Integer.parseInt((manga.intPopularityProperty()).toString().replace("IntegerProperty [value: ", "").replace("]", "")), Integer.parseInt((manga.intScoreNumbersProperty()).toString().replace("IntegerProperty [value: ", "").replace("]", "")), (manga.strSynopsisProperty()).toString().replace("StringProperty [value: ", "").replace("]", ""), 0, "Reading", false);
    }

    public String toString() {
        return strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "") + "," + intUserScoreProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "") + "," + strUserStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "");
    }
}

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

    public String getUserStatus() {
        return strUserStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    public int getUserScore() {
        return Integer.parseInt(intUserScoreProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "").replace("*", ","));
    }

    public boolean getUserSelected() {
        return Boolean.parseBoolean(isUserSelectedProperty().toString().replace("BooleanProperty [value: ", "").replace("]", "").replace("*", ","));
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
        return new UserManga(manga.getTitle(), manga.getType(), manga.getChapter(), manga.getStatus(), manga.getPublished(), manga.getGenre(), manga.getAuthor(), manga.getSerialization(), manga.getScore(), manga.getRank(), manga.getPopularity(), manga.getScoreNumbers(), manga.getSynopsis(), 0, "Reading", false);
    }

    public String toString() {
        return getTitle() + "," + getUserScore() + "," + getUserStatus();
    }
}

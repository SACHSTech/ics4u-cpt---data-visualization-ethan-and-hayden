package simulator.ObjectClasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class UserManga extends Manga {

    // Private variables
    private StringProperty strUserStatus;
    private IntegerProperty intUserScore;
    private BooleanProperty isUserSelected;

    /**
     * Constructor for UserManga.
     * 
     * @param strTitle - the title of the User Manga.
     * @param strType - the type of the User Manga.
     * @param strChapter - the chapters of the User Manga.
     * @param strStatus - the status of the User Manga.
     * @param intPublished - when the User Manga was published.
     * @param strGenre - the genre of the User Manga.
     * @param strAuthor - the author of the User Manga.
     * @param strSerialization - the serialization of the User Manga.
     * @param dblScore - the score of the User Manga.
     * @param intRank - the rank of the User Manga.
     * @param intPopularity - the popularity of the User Manga.
     * @param intScoreNumbers - the number of scores of the User Manga.
     * @param strSynopsis - the synopsis of the User Manga.
     * @param intUserScore - the user score of the User Manga.
     * @param strUserStatus - the user status of the User Manga.
     * @param isUserSelected - the user selection of the User Manga.
     */
    public UserManga(String strTitle, String strType, String strChapter, String strStatus, int intPublished, String strGenre, String strAuthor, String strSerialization, double dblScore, int intRank, int intPopularity, int intScoreNumbers, String strSynopsis, int intUserScore, String strUserStatus, boolean isUserSelected) {
        super(strTitle, strType, strChapter, strStatus, intPublished, strGenre, strAuthor, strSerialization, dblScore, intRank, intPopularity, intScoreNumbers, strSynopsis);
        this.strUserStatus = new SimpleStringProperty(strUserStatus);
        this.intUserScore = new SimpleIntegerProperty(intUserScore);
        this.isUserSelected = new SimpleBooleanProperty(isUserSelected);
    }
    
    /**
     * Getter method for User Status
     * 
     * @return strUserStatus - a string property
     */
    public StringProperty strUserStatusProperty() {
        return strUserStatus;
    }

    /**
     * Getter method for User score
     * 
     * @return intUserScore - an integer property
     */
    public IntegerProperty intUserScoreProperty() {
        return intUserScore;
    }

    /**
     * Getter method for User selection
     * 
     * @return isUserSelected - a boolean property
     */
    public BooleanProperty isUserSelectedProperty() {
        return isUserSelected;
    }

    /**
     * Getter method for User Status
     * 
     * @return user status as a string
     */
    public String getUserStatus() {
        return strUserStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Getter method for User Score
     * 
     * @return User score as a string
     */
    public int getUserScore() {
        return Integer.parseInt(intUserScoreProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "").replace("*", ","));
    }

    /**
     * Getter method for User selected
     * 
     * @return user selection as a string
     */
    public boolean getUserSelected() {
        return Boolean.parseBoolean(isUserSelectedProperty().toString().replace("BooleanProperty [value: ", "").replace("]", "").replace("*", ","));
    }

    /**
     * Setter method for user status
     * 
     * @param strUserStatus - a string
     */
    public void setUserStatus(String strUserStatus) {
        this.strUserStatus = new SimpleStringProperty(strUserStatus);
    }

    /**
     * Setter method for user score
     * 
     * @param intUserScore - an integer
     */
    public void setUserScore(int intUserScore) {
        this.intUserScore = new SimpleIntegerProperty(intUserScore);
    }

    /**
     * Setter method for user selection
     * 
     * @param isUserSelected - a boolean
     */
    public void setUserSelected(Boolean isUserSelected) {
        this.isUserSelected = new SimpleBooleanProperty(isUserSelected);
    }

    /**
     * Method that converts Manga to UserManga
     * 
     * @param manga - a Manga object
     * @return a new UserManga object
     */
    public static UserManga convertToUserManga(Manga manga) {
        return new UserManga(manga.getTitle(), manga.getType(), manga.getChapter(), manga.getStatus(), manga.getPublished(), manga.getGenre(), manga.getAuthor(), manga.getSerialization(), manga.getScore(), manga.getRank(), manga.getPopularity(), manga.getScoreNumbers(), manga.getSynopsis(), 0, "Reading", false);
    }

    /**
     * A toString method for a UserList object.
     * 
     * @return a string version of a UserList object
     */
    public String toString() {
        return getTitle() + "," + getUserScore() + "," + getUserStatus();
    }
}

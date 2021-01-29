package simulator.ObjectClasses;

import java.io.*;
import java.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Manga {

    // Private variables.
    private StringProperty strTitle;
    private StringProperty strType;
    private StringProperty strChapter;
    private StringProperty strStatus;
    private IntegerProperty intPublished;
    private StringProperty strGenre;
    private StringProperty strAuthor;
    private StringProperty strSerialization;
    private DoubleProperty dblScore;
    private IntegerProperty intRank;
    private IntegerProperty intPopularity;
    private IntegerProperty intScoreNumbers;
    private StringProperty strSynopsis;

    /**
     * Manga constructor.
     * 
     * @param strTitle - the title of the manga.
     * @param strType - the type of the manga.
     * @param strChapter - the chapters of the manga.
     * @param strStatus - the status of the manga.
     * @param intPublished - the publish date of the manga.
     * @param strGenre - the genres of the manga.
     * @param strAuthor - the author of the manga.
     * @param strSerialization - the serialization of the manga.
     * @param dblScore - the score of the manga.
     * @param intRank - the rank of the manga.
     * @param intPopularity - the popularity rank of the manga.
     * @param intScoreNumbers - the amount of scores of the manga.
     * @param strSynopsis - the synopsis of the manga.
     */
    public Manga(String strTitle, String strType, String strChapter, String strStatus, int intPublished, String strGenre, String strAuthor, String strSerialization, double dblScore, int intRank, int intPopularity, int intScoreNumbers, String strSynopsis) {
        this.strTitle = new SimpleStringProperty(strTitle);
        this.strType = new SimpleStringProperty(strType);
        this.strChapter = new SimpleStringProperty(strChapter);
        this.strStatus = new SimpleStringProperty(strStatus);
        this.intPublished = new SimpleIntegerProperty(intPublished);
        this.strGenre = new SimpleStringProperty(strGenre);
        this.strAuthor = new SimpleStringProperty(strAuthor);
        this.strSerialization = new SimpleStringProperty(strSerialization);
        this.dblScore = new SimpleDoubleProperty(dblScore);
        this.intRank = new SimpleIntegerProperty(intRank);
        this.intPopularity = new SimpleIntegerProperty(intPopularity);
        this.intScoreNumbers = new SimpleIntegerProperty(intScoreNumbers);
        this.strSynopsis = new SimpleStringProperty(strSynopsis);
    }

    /**
     * Getter method for the title
     * 
     * @return strTitle - StringProperty
     */
    public StringProperty strTitleProperty() {
        return strTitle;
    }

    /**
     * Getter method for the type
     * 
     * @return strType - StringProperty
     */
    public StringProperty strTypeProperty() {
        return strType;
    }

    /**
     * Getter method for the chapters
     * 
     * @return strChapter - StringProperty
     */
    public StringProperty strChapterProperty() {
        return strChapter;
    }

    /**
     * Getter method for the status
     * 
     * @return strStatus - StringProperty
     */
    public StringProperty strStatusProperty() {
        return strStatus;
    }

    /**
     * Getter method for the publish year
     * 
     * @return intPublished - IntegerProperty
     */
    public IntegerProperty intPublishedProperty() {
        return intPublished;
    }

    /**
     * Getter method for the genres
     * 
     * @return strGenre - StringProperty
     */
    public StringProperty strGenreProperty() {
        return strGenre;
    }  

    /**
     * Getter method for the author
     * 
     * @return strAuthor - StringProperty
     */
    public StringProperty strAuthorProperty() {
        return strAuthor;
    }

    /**
     * Getter method for the serialization
     * 
     * @return strSerialization - StringProperty
     */
    public StringProperty strSerializationProperty() {
        return strSerialization;
    }

    /**
     * Getter method for the score
     * 
     * @return dblScore - DoubleProperty
     */
    public DoubleProperty dblScoreProperty() {
        return dblScore;
    }

    /**
     * Getter method for the rank
     * 
     * @return strRank - IntegerProperty
     */
    public IntegerProperty intRankProperty() {
        return intRank;
    }

    /**
     * Getter method for the popularity
     * 
     * @return intPopularity - IntegerProperty
     */
    public IntegerProperty intPopularityProperty() {
        return intPopularity;
    }

    /**
     * Getter method for the score numbers
     * 
     * @return intScoreNumbers - IntegerProperty
     */
    public IntegerProperty intScoreNumbersProperty() {
        return intScoreNumbers;
    }

    /**
     * Getter method for the synopsis
     * 
     * @return strSynopsis - StringProperty
     */
    public StringProperty strSynopsisProperty() {
        return strSynopsis;
    }

    /**
     * Getter method for the title
     * 
     * @return title as a string
     */
    public String getTitle() {
        return strTitleProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Getter method for the type
     * 
     * @return type as a string
     */
    public String getType() {
        return strTypeProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Getter method for the chapters
     * 
     * @return chapter as a string
     */
    public String getChapter() {
        return strChapterProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Getter method for the status
     * 
     * @return status as a string
     */
    public String getStatus() {
        return strStatusProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Getter method for the publish date
     * 
     * @return publish date as an integer
     */
    public int getPublished() {
        return Integer.parseInt(intPublishedProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "").replace("*", ","));
    }

    /**
     * Getter method for the genre
     * 
     * @return genre as a string
     */
    public String getGenre() {
        return strGenreProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Getter method for the author
     * 
     * @return author as a string
     */
    public String getAuthor() {
        return strAuthorProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Getter method for the serialization
     * 
     * @return serialization as a string
     */
    public String getSerialization() {
        return strSerializationProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Getter method for the score
     * 
     * @return score as a double
     */
    public double getScore() {
        return Double.parseDouble(dblScoreProperty().toString().replace("DoubleProperty [value: ", "").replace("]", "").replace("*", ","));
    }

    /**
     * Getter method for the rank
     * 
     * @return rank as an integer
     */
    public int getRank() {
        return Integer.parseInt(intRankProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "").replace("*", ","));
    }

    /**
     * Getter method for the popularity
     * 
     * @return popularity as an integer
     */
    public int getPopularity() {
        return Integer.parseInt(intPopularityProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "").replace("*", ","));
    }

    /**
     * Getter method for the score numbers
     * 
     * @return score numbers as an integer
     */
    public int getScoreNumbers() {
        return Integer.parseInt(intScoreNumbersProperty().toString().replace("IntegerProperty [value: ", "").replace("]", "").replace("*", ","));
    }

    /**
     * Getter method for the synopsis
     * 
     * @return synopsis as a string
     */
    public String getSynopsis() {
        return strSynopsisProperty().toString().replace("StringProperty [value: ", "").replace("]", "").replace("*", ",");
    }

    /**
     * Setter method for the title
     * 
     * @param strTitle
     */
    public void setTitle(String strTitle) {
        this.strTitle = new SimpleStringProperty(strTitle);
    }

    /**
     * Setter method for the type
     * 
     * @param strType
     */
    public void setType(String strType) {
        this.strType = new SimpleStringProperty(strType);
    }

    /**
     * Setter method for the chapter
     * 
     * @param strChapter
     */
    public void setChapter(String strChapter) {
        this.strChapter = new SimpleStringProperty(strChapter);
    }

    /**
     * Setter method for the status
     * 
     * @param strStatus
     */
    public void setStatus(String strStatus) {
        this.strStatus = new SimpleStringProperty(strStatus);
    }

    /**
     * Setter method for the publish date
     * 
     * @param intPublished
     */
    public void setPublished(int intPublished) {
        this.intPublished = new SimpleIntegerProperty(intPublished);
    }

    /**
     * Setter method for the genre
     * 
     * @param strGenre
     */
    public void setGenre(String strGenre) {
        this.strGenre = new SimpleStringProperty(strGenre);
    }

    /**
     * Setter method for the author
     * 
     * @param strAuthor
     */
    public void setAuthor(String strAuthor) {
        this.strAuthor = new SimpleStringProperty(strAuthor);
    }

    /**
     * Setter method for the serialization
     * 
     * @param strSerialization
     */
    public void setSerialization(String strSerialization) {
        this.strSerialization = new SimpleStringProperty(strSerialization);
    }

    /**
     * Setter method for the score
     * 
     * @param dblScore
     */
    public void setScore(double dblScore) {
        this.dblScore = new SimpleDoubleProperty(dblScore);
    }

    /**
     * Setter method for the rank
     * 
     * @param intRank
     */
    public void setRank(int intRank) {
        this.intRank = new SimpleIntegerProperty(intRank);
    }

    /**
     * Setter method for the popularity
     * 
     * @param intPopularity
     */
    public void setPopularity(int intPopularity) {
        this.intPopularity = new SimpleIntegerProperty(intPopularity);
    }

    /**
     * Setter method for the score numbers
     * 
     * @param intScoreNumbers
     */
    public void setScoreNumbers(int intScoreNumbers) {
        this.intScoreNumbers = new SimpleIntegerProperty(intScoreNumbers);
    }

    /**
     * Setter method for the synopsis
     * 
     * @param strSynopsis
     */
    public void setSynopsis(String strSynopsis) {
        this.strSynopsis = new SimpleStringProperty(strSynopsis);
    }

    /**
     * Converts every line in the .csv file into an object.
     * 
     * @param fileName - path to the .csv file
     * @return ArrayList<Manga>
     * @throws IOException
     */
    public static ArrayList<Manga> csvToObject(String fileName) throws IOException {

        // Setting up buffered reader to read .csv file.
        BufferedReader CSVFile = new BufferedReader(new FileReader(fileName));  

        // Creating new arraylist.
        ArrayList<Manga> MangaList = new ArrayList<>();
        String strLine;

        // while loop to read end of .csv file.
        while ((strLine = CSVFile.readLine()) != null) {
            
            // Creating array filled with Strings that are taken from a line in the .csv file. Each string is separated by a comma.
            String[] MangaCsv = strLine.split(",");   

            // Creating manga object to store values.  
            Manga MangaObj = new Manga("", "", "", "", 0, "", "", "", 0, 0, 0, 0, "");  

            // Adding values from .csv to manga object  
            MangaObj.setTitle(MangaCsv[0]);   
            MangaObj.setType(MangaCsv[1]);  
            MangaObj.setChapter(MangaCsv[2]); 
            MangaObj.setStatus(MangaCsv[3]);
            MangaObj.setPublished(Integer.parseInt(MangaCsv[4]));
            MangaObj.setGenre(MangaCsv[5]);
            MangaObj.setAuthor(MangaCsv[6]);
            MangaObj.setSerialization(MangaCsv[7]);
            MangaObj.setScore(Double.parseDouble(MangaCsv[8]));
            MangaObj.setRank(Integer.parseInt(MangaCsv[9]));
            MangaObj.setPopularity(Integer.parseInt(MangaCsv[10]));
            MangaObj.setScoreNumbers(Integer.parseInt(MangaCsv[11]));
            MangaObj.setSynopsis(MangaCsv[12]);

            // adding objects to the arraylist  
            MangaList.add(MangaObj);         
        }
        CSVFile.close();
        return MangaList;
    }
}

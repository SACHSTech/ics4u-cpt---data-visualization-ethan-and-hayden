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

    public StringProperty strTitleProperty() {
        return strTitle;
    }

    public StringProperty strTypeProperty() {
        return strType;
    }

    public StringProperty strChapterProperty() {
        return strChapter;
    }

    public StringProperty strStatusProperty() {
        return strStatus;
    }

    public IntegerProperty intPublishedProperty() {
        return intPublished;
    }

    public StringProperty strGenreProperty() {
        return strGenre;
    }

    public StringProperty strAuthorProperty() {
        return strAuthor;
    }

    public StringProperty strSerializationProperty() {
        return strSerialization;
    }

    public DoubleProperty dblScoreProperty() {
        return dblScore;
    }

    public IntegerProperty intRankProperty() {
        return intRank;
    }

    public IntegerProperty intPopularityProperty() {
        return intPopularity;
    }

    public IntegerProperty intScoreNumbersProperty() {
        return intScoreNumbers;
    }

    public StringProperty strSynopsisProperty() {
        return strSynopsis;
    }

    public void setTitle(String strTitle) {
        this.strTitle = new SimpleStringProperty(strTitle);
    }

    public void setType(String strType) {
        this.strType = new SimpleStringProperty(strType);
    }

    public void setChapter(String strChapter) {
        this.strChapter = new SimpleStringProperty(strChapter);
    }

    public void setStatus(String strStatus) {
        this.strStatus = new SimpleStringProperty(strStatus);
    }

    public void setPublished(int intPublished) {
        this.intPublished = new SimpleIntegerProperty(intPublished);
    }

    public void setGenre(String strGenre) {
        this.strGenre = new SimpleStringProperty(strGenre);
    }

    public void setAuthor(String strAuthor) {
        this.strAuthor = new SimpleStringProperty(strAuthor);
    }

    public void setSerialization(String strSerialization) {
        this.strSerialization = new SimpleStringProperty(strSerialization);
    }

    public void setScore(double dblScore) {
        this.dblScore = new SimpleDoubleProperty(dblScore);
    }

    public void setRank(int intRank) {
        this.intRank = new SimpleIntegerProperty(intRank);
    }

    public void setPopularity(int intPopularity) {
        this.intPopularity = new SimpleIntegerProperty(intPopularity);
    }

    public void setScoreNumbers(int intScoreNumbers) {
        this.intScoreNumbers = new SimpleIntegerProperty(intScoreNumbers);
    }

    public void setSynopsis(String strSynopsis) {
        this.strSynopsis = new SimpleStringProperty(strSynopsis);
    }

    public static ArrayList<Manga> csvToObject(String fileName) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(fileName));  
        ArrayList<Manga> MangaList = new ArrayList<>();
        String strLine;
        while ((strLine = CSVFile.readLine()) != null) {
            
            // split on comma(',')
            String[] MangaCsv = strLine.split(",");   

            // create car object to store values  
            Manga MangaObj = new Manga("", "", "", "", 0, "", "", "", 0, 0, 0, 0, "");  

            // add values from csv to car object  
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

            // adding objects to a list  
            MangaList.add(MangaObj);         
        }
        CSVFile.close();
        return MangaList;
    }
}

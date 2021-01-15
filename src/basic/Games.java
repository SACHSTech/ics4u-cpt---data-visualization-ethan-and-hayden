package basic;

import java.io.*;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Games {
    private StringProperty strTitle;
    private BooleanProperty isHandheld;
    private IntegerProperty intMaxPlayers;
    private BooleanProperty isMultiplayer;
    private StringProperty strGenre;
    private StringProperty strPublisher;
    private BooleanProperty isSequel;
    private IntegerProperty intScore; 
    private DoubleProperty dblSales;
    private DoubleProperty dblPrice;
    private StringProperty strConsole;
    private StringProperty strRating;
    private BooleanProperty isRereleased;
    private IntegerProperty intYear;
    private DoubleProperty dblPlaytime;

    public Games(String strTitle, boolean isHandheld, int intMaxPlayers, boolean isMultiplayer, String strGenre, String strPublisher, boolean isSequel, int intScore, double dblSales, double dblPrice, String strConsole, String strRating, boolean isRereleased, int intYear, double dblPlaytime) {
        this.strTitle = new SimpleStringProperty(strTitle);
        this.isHandheld = new SimpleBooleanProperty(isHandheld);
        this.intMaxPlayers = new SimpleIntegerProperty(intMaxPlayers);
        this.isMultiplayer = new SimpleBooleanProperty(isMultiplayer);
        this.strGenre = new SimpleStringProperty(strGenre);
        this.strPublisher = new SimpleStringProperty(strPublisher);
        this.isSequel = new SimpleBooleanProperty(isSequel);
        this.intScore = new SimpleIntegerProperty(intScore); 
        this.dblSales = new SimpleDoubleProperty(dblSales);
        this.dblPrice = new SimpleDoubleProperty(dblPrice);
        this.strConsole = new SimpleStringProperty(strConsole);
        this.strRating = new SimpleStringProperty(strRating);
        this.isRereleased = new SimpleBooleanProperty(isRereleased);
        this.intYear = new SimpleIntegerProperty(intYear);
        this.dblPlaytime = new SimpleDoubleProperty(dblPlaytime);    
    }

    public StringProperty strTitleProperty() {
        return strTitle;
    }

    public BooleanProperty isHandheldProperty() {
        return isHandheld;
    }

    public IntegerProperty intMaxPlayersProperty() {
        return intMaxPlayers;
    }

    public BooleanProperty isMultiplayerProperty() {
        return isMultiplayer;
    }

    public StringProperty strGenreProperty() {
        return strGenre;
    }

    public StringProperty strPublisherProperty() {
        return strPublisher;
    }

    public BooleanProperty isSequelProperty() {
        return isSequel;
    }

    public IntegerProperty intScoreProperty() {
        return intScore;
    }

    public DoubleProperty dblSalesProperty() {
        return dblSales;
    }

    public DoubleProperty dblPriceProperty() {
        return dblPrice;
    }

    public StringProperty strConsoleProperty() {
        return strConsole;
    }

    public StringProperty strRatingProperty() {
        return strRating;
    }

    public BooleanProperty isRereleasedProperty() {
        return isRereleased;
    }

    public IntegerProperty intYearProperty() {
        return intYear;
    }

    public DoubleProperty dblPlaytimeProperty() {
        return dblPlaytime;
    }

    public void setTitle(String strTitle) {
        this.strTitle = new SimpleStringProperty(strTitle);
    }

    public void setHandheld(boolean isHandheld) {
        this.isHandheld = new SimpleBooleanProperty(isHandheld);
    }

    public void setMaxPlayers(int intMaxPlayers) {
        this.intMaxPlayers = new SimpleIntegerProperty(intMaxPlayers);
    }

    public void setMultiplayer(boolean isMultiplayer) {
        this.isMultiplayer = new SimpleBooleanProperty(isMultiplayer);
    }

    public void setGenre(String strGenre) {
        this.strGenre = new SimpleStringProperty(strGenre);
    }

    public void setPublisher(String strPublisher) {
        this.strPublisher = new SimpleStringProperty(strPublisher);
    }

    public void setSequel(boolean isSequel) {
        this.isSequel = new SimpleBooleanProperty(isSequel);
    }

    public void setScore(int intScore) {
        this.intScore = new SimpleIntegerProperty(intScore); 
    }

    public void setSales(double dblSales) {
        this.dblSales = new SimpleDoubleProperty(dblSales);
    }

    public void setPrice(double dblPrice) {
        this.dblPrice = new SimpleDoubleProperty(dblPrice);
    }

    public void setConsole(String strConsole) {
        this.strConsole = new SimpleStringProperty(strConsole);
    }

    public void setRating(String strRating) {
        this.strRating = new SimpleStringProperty(strRating);
    }

    public void setRereleased(boolean isRereleased) {
        this.isRereleased = new SimpleBooleanProperty(isRereleased);
    }

    public void setYear(int intYear) {
        this.intYear = new SimpleIntegerProperty(intYear);
    }

    public void setPlaytime(double dblPlaytime) {
        this.dblPlaytime = new SimpleDoubleProperty(dblPlaytime);
    }

    public static List<Games> csvToObject(String fileName) throws IOException {
        BufferedReader CSVFile = new BufferedReader(new FileReader(fileName));  
        List<Games> GamesList = new ArrayList<>();

        while (CSVFile.readLine() != null) {
            
            // split on comma(',')  
            String[] gamesCsv = CSVFile.readLine().split(",");   

            // create car object to store values  
            Games gamesObj = new Games("", false, 0, false, "", "", false, 0, 0, 0, "", "", false, 0, 0);  

            // add values from csv to car object  
            gamesObj.setTitle(gamesCsv[0]);  
            gamesObj.setHandheld(Boolean.parseBoolean(gamesCsv[1]));  
            gamesObj.setMaxPlayers(Integer.parseInt(gamesCsv[2]));  
            gamesObj.setMultiplayer(Boolean.parseBoolean(gamesCsv[3])); 
            gamesObj.setGenre(gamesCsv[4]);
            gamesObj.setPublisher(gamesCsv[5]);
            gamesObj.setSequel(Boolean.parseBoolean(gamesCsv[6]));
            gamesObj.setScore(Integer.parseInt(gamesCsv[7]));
            gamesObj.setSales(Double.parseDouble(gamesCsv[8]));
            gamesObj.setPrice(Double.parseDouble(gamesCsv[9]));
            gamesObj.setConsole(gamesCsv[10]);
            gamesObj.setRating(gamesCsv[11]);
            gamesObj.setRereleased(Boolean.parseBoolean(gamesCsv[12]));
            gamesObj.setYear(Integer.parseInt(gamesCsv[13]));
            gamesObj.setPlaytime(Double.parseDouble(gamesCsv[14]));

            // adding objects to a list  
            GamesList.add(gamesObj);         
        }
        CSVFile.close();
        return GamesList;
    }
}

package basic;

import java.io.*;
import java.util.*;

public class Games {
    private String strTitle;
    private boolean isHandheld;
    private int intMaxPlayers;
    private boolean isMultiplayer;
    private String strGenre;
    private String strPublisher;
    private boolean isSequel;
    private int intScore; 
    private double dblSales;
    private double dblPrice;
    private String strConsole;
    private String strRating;
    private boolean isRereleased;
    private int intYear;
    private double dblPlaytime;

    public Games(String strTitle, boolean isHandheld, int intMaxPlayers, boolean isMultiplayer, String strGenre, String strPublisher, boolean isSequel, int intScore, double dblSales, double dblPrice, String strConsole, String strRating, boolean isRereleased, int intYear, double dblPlaytime) {
        this.strTitle = strTitle;
        this.isHandheld = isHandheld;
        this.intMaxPlayers = intMaxPlayers;
        this.isMultiplayer = isMultiplayer;
        this.strGenre = strGenre;
        this.strPublisher = strPublisher;
        this.isSequel = isSequel;
        this.intScore = intScore; 
        this.dblSales = dblSales;
        this.dblPrice = dblPrice;
        this.strConsole = strConsole;
        this.strRating = strRating;
        this.isRereleased = isRereleased;
        this.intYear = intYear;
        this.dblPlaytime = dblPlaytime;    
    }

    public String getTitle() {
        return strTitle;
    }

    public boolean getHandheld() {
        return isHandheld;
    }

    public int getMaxPlayers() {
        return intMaxPlayers;
    }

    public boolean getMultiplayer() {
        return isMultiplayer;
    }

    public String getGenre() {
        return strGenre;
    }

    public String getPublisher() {
        return strPublisher;
    }

    public boolean getSequel() {
        return isSequel;
    }

    public int getScore() {
        return intScore;
    }

    public double getSales() {
        return dblSales;
    }

    public double getPrice() {
        return dblPrice;
    }

    public String getConsole() {
        return strConsole;
    }

    public String getRating() {
        return strRating;
    }

    public boolean getRereleased() {
        return isRereleased;
    }

    public int getYear() {
        return intYear;
    }

    public double getPlaytime() {
        return dblPlaytime;
    }

    public void setTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public void setHandheld(boolean isHandheld) {
        this.isHandheld = isHandheld;
    }

    public void setMaxPlayers(int intMaxPlayers) {
        this.intMaxPlayers = intMaxPlayers;
    }

    public void setMultiplayer(boolean isMultiplayer) {
        this.isMultiplayer = isMultiplayer;
    }

    public void setGenre(String strGenre) {
        this.strGenre = strGenre;
    }

    public void setPublisher(String strPublisher) {
        this.strPublisher = strPublisher;
    }

    public void setSequel(boolean isSequel) {
        this.isSequel = isSequel;
    }

    public void setScore(int intScore) {
        this.intScore = intScore;
    }

    public void setSales(double dblSales) {
        this.dblSales = dblSales;
    }

    public void setPrice(double dblPrice) {
        this.dblPrice = dblPrice;
    }

    public void setConsole(String strConsole) {
        this.strConsole = strConsole;
    }

    public void setRating(String strRating) {
        this.strRating = strRating;
    }

    public void setRereleased(boolean isRereleased) {
        this.isRereleased = isRereleased;
    }

    public void setYear(int intYear) {
        this.intYear = intYear;
    }

    public void setPlaytime(double dblPlaytime) {
        this.dblPlaytime = dblPlaytime;
    }

    public static void csvToObject(String fileName) throws IOException {
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

            // adding car objects to a list  
            GamesList.add(gamesObj);         
        }
        CSVFile.close();
    }
}

package simulator.ObjectClasses;

import simulator.*;
import java.io.*;
import java.util.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class Account {
    private String strUsername;
    private String strPassword;
    private ArrayList<UserManga> userList;

    public Account(String strUsername, String strPassword, ArrayList<UserManga> userList) {
        this.strUsername = strUsername;
        this.strPassword = strPassword;
        this.userList = userList;
    }

    public String getUsername() {
        return strUsername;
    }

    public String getPassword() {
        return strPassword;
    }

    public ArrayList<UserManga> getUserlist() {
        return userList;
    }

    public void setUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public void setPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public void setUserlist(ArrayList<UserManga> userList) {
        this.userList = userList;
    }

    ///////////////////// Static methods /////////////////////

    /**
     * Method that checks if the user's inputted password and username correspond with an account in the text file, Accounts.txt.
     * 
     * @param primaryStage
     * @param fileName
     * @param strTypedUsername
     * @param strTypedPassword
     * @throws IOException
     */
    private static void ifAccountSaved(Stage primaryStage, String fileName, String strTypedUsername, String strTypedPassword, Text errorText) throws IOException {

        // Variables for this method
        String[][] strAccount;
        String[] userList;
        String[] singleObject;
        int intNumberofAccounts;
        int intNumberofLines;
        int intCount;

        // Initializing variables
        intNumberofLines = 0;

        // Finding total number of lines in the text file
        BufferedReader TXTFile = new BufferedReader(new FileReader(fileName));
        while (TXTFile.readLine() != null) {
            intNumberofLines++;
        }
        TXTFile.close();

        // Finding number of accounts in the text file
        intNumberofAccounts = intNumberofLines / 3;

        // Creating array size to hold all accounts
        strAccount = new String[intNumberofAccounts][3];

        // Pushing every line in the text file into the array
        TXTFile = new BufferedReader(new FileReader(fileName));
        for (intCount = 0; intCount < intNumberofAccounts; intCount++) {
            strAccount[intCount][0] = TXTFile.readLine();
            strAccount[intCount][1] = TXTFile.readLine();
            strAccount[intCount][2] = TXTFile.readLine();

            // Checking if the username and password matches an account in the text file
            if (strTypedUsername.equalsIgnoreCase(strAccount[intCount][0]) && strTypedPassword.equalsIgnoreCase(strAccount[intCount][1])) {

                // Creating an arraylist of all mangas in the database. Based from the csv file.
                ArrayList<Manga> MangaList = Manga.csvToObject("src/simulator/AllManga.csv");

                // Creating an arraylist of all mangas in the user's list. Based from the text file.
                ArrayList<UserManga> UserList = new ArrayList<UserManga>();
                if (!strAccount[intCount][2].equalsIgnoreCase("")) {
                    userList = strAccount[intCount][2].split(" / ");
                    for (int i = 0; i < userList.length; i++) {
                        singleObject = userList[i].split(",");
                        UserManga UserMangaObj = new UserManga("", "", "", "", 0, "", "", "", 0, 0, 0, 0, "", 0, "", false);
                        UserMangaObj.setTitle(singleObject[0]);
                        UserMangaObj.setUserScore(Integer.parseInt(singleObject[1]));
                        UserMangaObj.setUserStatus(singleObject[2]);
                        UserList.add(UserMangaObj);
                    }
                }

                // Creating account object
                Account currentAccount = new Account(strTypedUsername, strTypedPassword, UserList);
                Main.mainMenuScreen(primaryStage, MangaList, UserList, currentAccount);
                break;
            
            // If the for loop reads the end of the text file, returns that the account is not found
            }else if (intCount == intNumberofAccounts - 1) {
                errorText.setText("Password or Username may be wrong");
            }
        }
        TXTFile.close();
    }

    /**
     * Method that allows the user's list to be saved into the text file, Accounts.txt.
     * 
     * @param primaryStage
     * @param fileName
     * @param UserList
     * @param currentAccount
     * @throws IOException
     */
    public static void savingToAccount(Stage primaryStage, String fileName, ArrayList<UserManga> UserList, Account currentAccount) throws IOException {

        // Variables
        String[][] strAccount;
        int intNumberofAccounts;
        int intNumberofLines;
        int intCount;

        // Initializing variables
        intNumberofLines = 0;

        // While loop that counts how many lines are in the text file
        BufferedReader TXTFile = new BufferedReader(new FileReader(fileName));
        while (TXTFile.readLine() != null) {
            intNumberofLines++;
        }
        TXTFile.close();

        // Finding number of accounts in the text file and setting up the size of an account array
        intNumberofAccounts = intNumberofLines / 3;
        strAccount = new String[intNumberofAccounts][3];

        TXTFile = new BufferedReader(new FileReader(fileName));
        for (intCount = 0; intCount < intNumberofAccounts; intCount++) {
            strAccount[intCount][0] = TXTFile.readLine();
            strAccount[intCount][1] = TXTFile.readLine();
            strAccount[intCount][2] = TXTFile.readLine();
            if (currentAccount.getUsername().equalsIgnoreCase(strAccount[intCount][0])
                    && currentAccount.getPassword().equalsIgnoreCase(strAccount[intCount][1])) {
                strAccount[intCount][2] = "";
                if (UserList.size() >= 1) {
                    for (UserManga current : UserList) {
                        strAccount[intCount][2] = strAccount[intCount][2] + current.toString() + " / ";
                    }
                } else if (UserList.size() == 0) {
                    strAccount[intCount][2] = "";
                }
            }
        }
        TXTFile.close();
        PrintWriter TXTFileWrite = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        for (intCount = 0; intCount < intNumberofAccounts; intCount++) {
            TXTFileWrite.println(strAccount[intCount][0]);
            TXTFileWrite.println(strAccount[intCount][1]);
            TXTFileWrite.println(strAccount[intCount][2]);
        }
        TXTFileWrite.close();
    }

    /**
     * Method that allows the user to create a new account with a new password and username. This account will be saved into the Accounts.txt folder.
     * 
     * @param strNewPass
     * @param strNewUser
     * @param fileName
     * @throws IOException
     */
    private static void creatingNewAccount(String strNewPass, String strNewUser, String fileName, Text errorText) throws IOException{
        String[][] strAccount;
        int intNumberofAccounts;
        int intNumberofLines;

        intNumberofLines = 0;

        BufferedReader TXTFile = new BufferedReader(new FileReader(fileName));
        while (TXTFile.readLine() != null) {
            intNumberofLines++;
        }
        TXTFile.close();
        intNumberofAccounts = intNumberofLines / 3;
        strAccount = new String[intNumberofAccounts][3];
        TXTFile = new BufferedReader(new FileReader(fileName));
        PrintWriter TXTFileWrite = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        for (int intCount = 0; intCount < intNumberofAccounts; intCount++) {
            strAccount[intCount][0] = TXTFile.readLine();
            strAccount[intCount][1] = TXTFile.readLine();
            strAccount[intCount][2] = TXTFile.readLine();
            if (strNewUser.equalsIgnoreCase(strAccount[intCount][0])) {
                errorText.setText("Account username is taken");
                break;
            }else if (intCount == intNumberofAccounts - 1) {
                TXTFileWrite.println(strNewUser);
                TXTFileWrite.println(strNewPass);
                TXTFileWrite.println("");
            }
        }
        TXTFile.close();
        TXTFileWrite.close();
    }

    ///////////////////// Screens /////////////////////

    /**
     * Screen for creating a new account. Allows the user to input a new password and username
     * 
     * @param primaryStage
     * @throws IOException
     */
    public static void newAccountScreen(Stage primaryStage) throws IOException {
        // Refreshes stage
        primaryStage.setWidth(601);

        // Creating gridpane to organize stage
        GridPane newAccountGrid = new GridPane();
        newAccountGrid.setVgap(10);
        newAccountGrid.setHgap(10);
        newAccountGrid.setGridLinesVisible(false);
        newAccountGrid.setPadding(new Insets(25, 25, 25, 25));
        Font newAccountFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Title
        Text newAccountTitle = new Text("Creating New Account");
        newAccountTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

        // Username
        TextField usernameText = new TextField();
        Label usernameLabel = new Label("Username: ");
        HBox usernameHbox = new HBox();
        usernameHbox.getChildren().addAll(usernameLabel, usernameText);
        usernameHbox.setSpacing(10);
        usernameLabel.setFont(newAccountFont);
        usernameText.setPrefWidth(200);

        // Password
        PasswordField passwordText = new PasswordField();
        Label passwordLabel = new Label("Password: ");
        HBox passwordHbox = new HBox();
        passwordHbox.getChildren().addAll(passwordLabel, passwordText);
        passwordHbox.setSpacing(13);
        passwordLabel.setFont(newAccountFont);
        passwordText.setPrefWidth(200);

        // Password
        PasswordField reTypePasswordText = new PasswordField();
        Label reTypePasswordLabel = new Label("Confirm Password: ");
        HBox reTypePasswordHbox = new HBox();
        reTypePasswordHbox.getChildren().addAll(reTypePasswordLabel, reTypePasswordText);
        reTypePasswordHbox.setSpacing(13);
        reTypePasswordLabel.setFont(newAccountFont);
        reTypePasswordText.setPrefWidth(150);

        // Error Box
        Text errorText = new Text();
        errorText.setFont(newAccountFont);
        errorText.setFill(Color.TOMATO);

        // Creating VBox 
        VBox newAccountVbox = new VBox();
        newAccountVbox.setPrefSize(300, 100);
        newAccountVbox.setSpacing(20);
        newAccountVbox.getChildren().addAll(usernameHbox, passwordHbox, reTypePasswordHbox, errorText);

        // create account Button
        Button newAccountBtn = new Button();
        newAccountBtn.setText("Create Account");
        newAccountBtn.setFont(newAccountFont);
        newAccountBtn.setMaxSize(300, 50);
        newAccountBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (passwordText.getText().equals(reTypePasswordText.getText())) {
                    try {
                        creatingNewAccount(passwordText.getText(), usernameText.getText(), "src/simulator/Accounts.txt", errorText);
                        errorText.setText("Created Account");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    errorText.setText("Confirm Password does not match");
                }
            }
        });

        // Return to Sign in Button
        Button signInBtn = new Button();
        signInBtn.setText("Back");
        signInBtn.setFont(newAccountFont);
        signInBtn.setMaxSize(300, 50);
        signInBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    signInScreen(primaryStage);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        // Adding everything to gridpane
        newAccountGrid.setAlignment(Pos.TOP_CENTER);
        newAccountGrid.add(newAccountTitle, 0, 0);
        newAccountGrid.add(newAccountVbox, 0, 1);
        newAccountGrid.add(newAccountBtn, 0, 2);
        newAccountGrid.add(signInBtn, 0, 3);

        // Setting up Background of Menu
        primaryStage.setScene(new Scene(newAccountGrid));
        primaryStage.show();
    }

    /**
     * A screen for the user to sign in with their account.
     * 
     * @param primaryStage
     * @throws IOException
     */
    public static void signInScreen(Stage primaryStage) throws IOException {
        
        // Refreshes stage
        primaryStage.setWidth(600);

        // Creating gridpane to organize stage
        GridPane signInGrid = new GridPane();
        signInGrid.setVgap(10);
        signInGrid.setHgap(10);
        signInGrid.setGridLinesVisible(false);
        signInGrid.setPadding(new Insets(25, 25, 25, 25));
        Font signInFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Title
        Text signInTitle = new Text("My Manga List");
        signInTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

        // Username
        TextField usernameText = new TextField();
        Label usernameLabel = new Label("Username: ");
        HBox usernameHbox = new HBox();
        usernameHbox.getChildren().addAll(usernameLabel, usernameText);
        usernameHbox.setSpacing(10);
        usernameLabel.setFont(signInFont);
        usernameText.setPrefWidth(200);

        // Password
        PasswordField passwordText = new PasswordField();
        Label passwordLabel = new Label("Password: ");
        HBox passwordHbox = new HBox();
        passwordHbox.getChildren().addAll(passwordLabel, passwordText);
        passwordHbox.setSpacing(13);
        passwordLabel.setFont(signInFont);
        passwordText.setPrefWidth(200);

        Text errorText = new Text();
        errorText.setFont(signInFont);
        errorText.setFill(Color.TOMATO);

        // Creating VBox 
        VBox signInVbox = new VBox();
        signInVbox.setPrefSize(300, 100);
        signInVbox.setSpacing(20);
        signInVbox.getChildren().addAll(usernameHbox, passwordHbox, errorText);

        // Sign in Button
        Button signInBtn = new Button();
        signInBtn.setText("Sign In");
        signInBtn.setFont(signInFont);
        signInBtn.setMaxSize(300, 50);
        signInBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                try {
                    ifAccountSaved(primaryStage, "src/simulator/Accounts.txt", usernameText.getText(), passwordText.getText(), errorText);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        // Create New Account Button
        Button createNewBtn = new Button();
        createNewBtn.setText("Create New Account");
        createNewBtn.setFont(signInFont);
        createNewBtn.setMaxSize(300, 50);
        createNewBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                try {
                    newAccountScreen(primaryStage);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        // Adding everything to gridpane
        signInGrid.setAlignment(Pos.TOP_CENTER);
        signInGrid.add(signInTitle, 0, 0);
        signInGrid.add(signInVbox, 0, 1);
        signInGrid.add(signInBtn, 0, 2);
        signInGrid.add(createNewBtn, 0, 3);

        // Setting up Background of Menu
        primaryStage.setScene(new Scene(signInGrid));
        primaryStage.show();
    }
}

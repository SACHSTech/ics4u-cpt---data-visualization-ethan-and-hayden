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
import javafx.geometry.HPos;
import javafx.geometry.Insets;

public class Account {

    // Private variables
    private String strUsername;
    private String strPassword;
    private ArrayList<UserManga> userList;

    /**
     * Account constructor.
     * 
     * @param strUsername - the username of the account.
     * @param strPassword - the password of the account.
     * @param userList - an arraylist filled with UserManga objects. Every account has their own userList.
     */
    public Account(String strUsername, String strPassword, ArrayList<UserManga> userList) {
        this.strUsername = strUsername;
        this.strPassword = strPassword;
        this.userList = userList;
    }

    /**
     * Getter method for the account's username.
     * 
     * @return strUsername - the username of the account as a string.
     */
    public String getUsername() {
        return strUsername;
    }

    /**
     * Getter method for the account's password.
     * 
     * @return strPassword - the password of the account as a string.
     */
    public String getPassword() {
        return strPassword;
    }

    /**
     * Getter method for the account's userList.
     * 
     * @return userList - an arraylist filled with UserManga objects.
     */
    public ArrayList<UserManga> getUserlist() {
        return userList;
    }

    /**
     * setter method for the account's username.
     * 
     * @param strUsername - the username of the account.
     */
    public void setUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    /**
     * setter method for the account's password.
     * 
     * @param strPassword - the password of the account.
     */
    public void setPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    /**
     * setter method for the account's userlist.
     * 
     * @param userList - an arraylist filled with UserManga objects.
     */
    public void setUserlist(ArrayList<UserManga> userList) {
        this.userList = userList;
    }

    /**
     * Screen for creating a new account. Allows the user to input a new password and username.
     * 
     * @param primaryStage - the stage that displays all scenes.
     * @throws IOException
     */
    public static void newAccountScreen(Stage primaryStage) throws IOException {

        // Refreshes stage
        primaryStage.setWidth(601);

        // Creating gridpane to organize stage.
        GridPane newAccountGrid = new GridPane();
        newAccountGrid.setVgap(10);
        newAccountGrid.setHgap(10);
        newAccountGrid.setGridLinesVisible(false);
        newAccountGrid.setPadding(new Insets(25, 25, 25, 25));
        Font newAccountFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Creating label for the title.
        Label newAccountTitle = new Label("Creating New Account");
        newAccountTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        GridPane.setHalignment(newAccountTitle, HPos.CENTER);

        // Textfield to allow the user to create a new username.
        TextField usernameText = new TextField();
        Label usernameLabel = new Label("Username: ");
        HBox usernameHbox = new HBox();
        usernameHbox.getChildren().addAll(usernameLabel, usernameText);
        usernameHbox.setSpacing(10);
        usernameLabel.setFont(newAccountFont);
        usernameText.setPrefWidth(200);

        // PasswordField to allow the user to create a new password.
        PasswordField passwordText = new PasswordField();
        Label passwordLabel = new Label("Password: ");
        HBox passwordHbox = new HBox();
        passwordHbox.getChildren().addAll(passwordLabel, passwordText);
        passwordHbox.setSpacing(13);
        passwordLabel.setFont(newAccountFont);
        passwordText.setPrefWidth(200);

        // PasswordFiled to allow the user to retype their password.
        PasswordField reTypePasswordText = new PasswordField();
        Label reTypePasswordLabel = new Label("Confirm Password: ");
        HBox reTypePasswordHbox = new HBox();
        reTypePasswordHbox.getChildren().addAll(reTypePasswordLabel, reTypePasswordText);
        reTypePasswordHbox.setSpacing(13);
        reTypePasswordLabel.setFont(newAccountFont);
        reTypePasswordText.setPrefWidth(150);

        // Text object to display why the user cannot create their account.
        Text errorText = new Text();
        errorText.setFont(newAccountFont);
        errorText.setFill(Color.TOMATO);

        // Creating VBox to organize textfields. 
        VBox newAccountVbox = new VBox();
        newAccountVbox.setPrefSize(300, 100);
        newAccountVbox.setSpacing(20);
        newAccountVbox.getChildren().addAll(usernameHbox, passwordHbox, reTypePasswordHbox, errorText);

        // Creating account Button
        Button newAccountBtn = new Button();
        newAccountBtn.setText("Create Account");
        newAccountBtn.setFont(newAccountFont);
        newAccountBtn.setMaxSize(300, 50);
        newAccountBtn.setOnAction(new EventHandler<ActionEvent>() {

            // Event method to create the user's account under specific conditions.
            @Override
            public void handle(ActionEvent event) {

                // If the textfields are empty, sets an error text.
                if (usernameText.getText().equals("") || passwordText.getText().equals("")) {
                    errorText.setText("Some fields are empty");
                }else {

                    // Checks if the password and confirm password is equal.
                    if (passwordText.getText().equals(reTypePasswordText.getText())) {

                        // Try and catch statements to read a text file.
                        try {

                            // Method that creates a new account.
                            creatingNewAccount(passwordText.getText(), usernameText.getText(), "src/simulator/Accounts.txt", errorText);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    
                    // Otherwise, sets error text that says that passwords do not match.
                    }else {
                        errorText.setText("Passwords do not match");
                    }
                }
            }
        });

        // Creating return to sign in button.
        Button signInBtn = new Button();
        signInBtn.setText("Back");
        signInBtn.setFont(newAccountFont);
        signInBtn.setMaxSize(300, 50);
        signInBtn.setOnAction(new EventHandler<ActionEvent>() {

            // Event that returns to the sign in screen.
            @Override
            public void handle(ActionEvent event) {

                // Try and catch method to read text file.
                try {
                    signInScreen(primaryStage);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        // Adding everything to gridpane.
        newAccountGrid.setAlignment(Pos.TOP_CENTER);
        newAccountGrid.add(newAccountTitle, 0, 0);
        newAccountGrid.add(newAccountVbox, 0, 1);
        newAccountGrid.add(newAccountBtn, 0, 2);
        newAccountGrid.add(signInBtn, 0, 3);

        // Displaying gridpane on stage.
        primaryStage.setScene(new Scene(newAccountGrid));
        primaryStage.show();
    }

    /**
     * A screen for the user to sign in with their account.
     * 
     * @param primaryStage - the stage where scenes are displayed.
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
        Label signInTitle = new Label("My Manga List");
        signInTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        GridPane.setHalignment(signInTitle, HPos.CENTER);

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

        // Creating new account button
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
        signInGrid.setAlignment(Pos.TOP_CENTER);;
        signInGrid.add(signInTitle, 0, 0);
        signInGrid.add(signInVbox, 0, 1);
        signInGrid.add(signInBtn, 0, 2);
        signInGrid.add(createNewBtn, 0, 3);

        // Setting up Background of Menu
        primaryStage.setScene(new Scene(signInGrid));
        primaryStage.show();
    }

    /**
     * Method that checks if the user's inputted password and username correspond with an account in the text file, Accounts.txt.
     * 
     * @param primaryStage - stage that displays all scenes.
     * @param fileName - path to the file asa string.
     * @param strTypedUsername - The string that the user typed in the username textfield.
     * @param strTypedPassword - the string that the user typed in the password textfield.
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
            if (strTypedUsername.equalsIgnoreCase(strAccount[intCount][0]) && strTypedPassword.equals(strAccount[intCount][1])) {

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
     * @param primaryStage - stage that displays all scenes.
     * @param fileName - the path to the text file.
     * @param UserList - Arraylist filled with UserManga objects.
     * @param currentAccount - an Account object.
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


        // Opening textfield
        TXTFile = new BufferedReader(new FileReader(fileName));

        // For loop that iterates through the account text file depending on how many accounts are in the text file.
        for (intCount = 0; intCount < intNumberofAccounts; intCount++) {

            // Sets each array cell as a line.
            strAccount[intCount][0] = TXTFile.readLine();
            strAccount[intCount][1] = TXTFile.readLine();
            strAccount[intCount][2] = TXTFile.readLine();

            // Checks which array cell matches the current accounts username and password.
            if (currentAccount.getUsername().equalsIgnoreCase(strAccount[intCount][0]) && currentAccount.getPassword().equalsIgnoreCase(strAccount[intCount][1])) {
                
                // Sets the array cell that represents the userlist as a blank line.
                strAccount[intCount][2] = "";

                // If userlist is greater or equal to one, iterates through the list and adds each UserManga to the array cell. Each usermanga is seperated by a " / ".
                if (UserList.size() >= 1) {
                    for (UserManga current : UserList) {
                        strAccount[intCount][2] = strAccount[intCount][2] + current.toString() + " / ";
                    }

                // If userList is equal to 0, keeps the array cell as a blank.  
                }else if (UserList.size() == 0) {
                    strAccount[intCount][2] = "";
                }
            }
        }
        TXTFile.close();

        // Writing all array cells into the text file. Essentially rewriting every account back into the text file.
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
     * @param strNewPass - String that the user typed in the passwordfield.
     * @param strNewUser - string that the user typed in the Username textfiled.
     * @param fileName - path to the text field.
     * @throws IOException
     */
    private static void creatingNewAccount(String strNewPass, String strNewUser, String fileName, Text errorText) throws IOException{

        // Intializing and creating variables.
        String[][] strAccount;
        int intNumberofAccounts;
        int intNumberofLines;
        int intCount;
        intNumberofLines = 0;

        // Counting how many lines are in the text file.
        BufferedReader TXTFile = new BufferedReader(new FileReader(fileName));
        while (TXTFile.readLine() != null) {
            intNumberofLines++;
        }
        TXTFile.close();

        // Counting how many accounts are in the text file.
        intNumberofAccounts = intNumberofLines / 3;

        // Setting up the size of the array.
        strAccount = new String[intNumberofAccounts][3];

        // For loop that sets every cell in the array as a line in the text file.
        TXTFile = new BufferedReader(new FileReader(fileName));
        PrintWriter TXTFileWrite = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
        for (intCount = 0; intCount < intNumberofAccounts; intCount++) {
            strAccount[intCount][0] = TXTFile.readLine();
            strAccount[intCount][1] = TXTFile.readLine();
            strAccount[intCount][2] = TXTFile.readLine();

            // Checks if the new username is equal to a username already in the text file. If there is, breaks the loop.
            if (strNewUser.equalsIgnoreCase(strAccount[intCount][0])) {
                errorText.setText("Account username is taken");
                break;
            
            // Checking if the for loop iterated to the end of the text file. Then writes the new account username and password at the end of the text file.
            }else if (intCount == intNumberofAccounts - 1) {
                errorText.setText("Created Account");
                TXTFileWrite.println(strNewUser);
                TXTFileWrite.println(strNewPass);
                TXTFileWrite.println("");
                TXTFileWrite.close();
            }
        }
        TXTFile.close();
    }
}

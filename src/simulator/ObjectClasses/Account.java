package simulator.ObjectClasses;

// Importing other folders
import simulator.*;
import simulator.MethodsClasses.*;

// Basic Libraries Import
import java.io.*;
import java.util.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
// Button Imports
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Text Imports
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

// Layout Imports
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
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

    private static void ifAccountSaved(Stage primaryStage, String fileName, String strTypedUsername, String strTypedPassword) throws IOException {
        String[][] strAccount;
        String[] userList;
        String[] singleObject;
        int intNumberofAccounts;
        int intNumberofLines = 0;
        BufferedReader TXTFile = new BufferedReader(new FileReader(fileName));
        while (TXTFile.readLine() != null) {
            intNumberofLines++;
        }
        TXTFile.close();
        intNumberofAccounts = intNumberofLines / 3;
        strAccount = new String[intNumberofAccounts][3];
        TXTFile = new BufferedReader(new FileReader(fileName));
        
        for (int intCount = 0; intCount < intNumberofAccounts; intCount++) {
            strAccount[intCount][0] = TXTFile.readLine();
            strAccount[intCount][1] = TXTFile.readLine();
            strAccount[intCount][2] = TXTFile.readLine();
            if (strTypedUsername.equalsIgnoreCase(strAccount[intCount][0]) && strTypedPassword.equalsIgnoreCase(strAccount[intCount][1])) {
                ArrayList<Manga> MangaList = Manga.csvToObject("src/simulator/AllManga.csv");
                ArrayList<UserManga> UserList = new ArrayList<UserManga>();
                if(!strAccount[intCount][2].equalsIgnoreCase("")) {
                    userList = strAccount[intCount][2].split(" / ");
                    for (int i = 0; i < userList.length; i++) {
                        singleObject = userList[i].split(",");
                        UserManga UserMangaObj = new UserManga("", "", "", "", 0, "", "", "", 0, 0, 0, 0, "", 0, "", false);
                        UserMangaObj.setTitle(singleObject[0]);   
                        UserMangaObj.setUserScore(Integer.parseInt(singleObject[1]));
                        UserMangaObj.setUserChapter(singleObject[2]);
    
                        UserList.add(UserMangaObj); 
                    }
                }
                Account currentAccount = new Account(strTypedUsername, strTypedPassword, UserList);
                Main.mainMenu(primaryStage, MangaList, UserList, currentAccount);
                break;
            }else {
                System.out.println("Account is not found");
            }
        }
        TXTFile.close();
    }

    public static void savingToAccount(Stage primaryStage, String fileName, ArrayList<UserManga> UserList, Account currentAccount) throws IOException {
        String[][] strAccount;
        int intNumberofAccounts;
        int intNumberofLines = 0;
        BufferedReader TXTFile = new BufferedReader(new FileReader(fileName));
        while (TXTFile.readLine() != null) {
            intNumberofLines++;
        }

        TXTFile.close();
        intNumberofAccounts = intNumberofLines / 3;
        strAccount = new String[intNumberofAccounts][3];
        TXTFile = new BufferedReader(new FileReader(fileName));
        
        for (int intCount = 0; intCount < intNumberofAccounts; intCount++) {
            strAccount[intCount][0] = TXTFile.readLine();
            strAccount[intCount][1] = TXTFile.readLine();
            strAccount[intCount][2] = TXTFile.readLine();
            if (currentAccount.getUsername().equalsIgnoreCase(strAccount[intCount][0]) && currentAccount.getPassword().equalsIgnoreCase(strAccount[intCount][1])) {
                strAccount[intCount][2] = "";
                if (UserList.size() >= 1) {
                    for (UserManga current : UserList) {
                        strAccount[intCount][2] = strAccount[intCount][2] + current.toString() + " / ";
                    }
                }else if (UserList.size() == 0) {
                    strAccount[intCount][2] = "";
                }
            }
        }
        TXTFile.close();
        PrintWriter TXTFileWrite = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        for(int intCount = 0; intCount < intNumberofAccounts; intCount++){
			TXTFileWrite.println(strAccount[intCount][0]);
            TXTFileWrite.println(strAccount[intCount][1]);
            TXTFileWrite.println(strAccount[intCount][2]);
        }
        TXTFileWrite.close();
    }

    public static void signInMenu(Stage primaryStage) throws IOException {
        
        // Refreshes stage
        primaryStage.setWidth(600);

        GridPane signInGrid = new GridPane();
        signInGrid.setVgap(10);
        signInGrid.setHgap(10);
        signInGrid.setGridLinesVisible(false);
        signInGrid.setPadding(new Insets(25, 25, 25, 25));
        Font signInFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);

        // Title
        Text signInTitle = new Text("My Manga List");
        signInTitle.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        signInGrid.add(signInTitle, 0, 0);

        // Username
        TextField usernameText = new TextField();
        signInGrid.add(usernameText, 0, 1);
        usernameText.setPromptText("Username");
        usernameText.setPrefWidth(200);
        usernameText.setMaxSize(200, TextField.USE_COMPUTED_SIZE);

        // Password
        TextField passwordText = new TextField();
        signInGrid.add(passwordText, 0, 2);
        passwordText.setPromptText("Password");
        passwordText.setPrefWidth(200);
        passwordText.setMaxSize(200, TextField.USE_COMPUTED_SIZE);

        // Sign in Button
        Button signInBtn = new Button();
        signInGrid.add(signInBtn, 0, 3);
        signInBtn.setText("Sign In");
        signInBtn.setFont(signInFont);
        signInBtn.setMaxSize(100, 50);
        signInBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                try {
                    ifAccountSaved(primaryStage, "src/simulator/Accounts.txt", usernameText.getText(), passwordText.getText());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        // Setting up Background of Menu
        primaryStage.setScene(new Scene(signInGrid));
        primaryStage.show();
    }
}

Main.java

package sample;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.stage.Stage;


public class Main extends Application {

@Override

public void start(Stage primaryStage) throws Exception{

Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));

primaryStage.setTitle("Магазин обуви");

primaryStage.setScene(new Scene(root, 700, 394));

primaryStage.show();

}

public static void main(String[] args) {

launch(args);

}

}



AuthorizationController.java

package sample;

import java.io.IOException; import java.net.URL; import java.sql.ResultSet; import java.sql.SQLException; import java.util.ResourceBundle; import javafx.fxml.FXML; import javafx.fxml.FXMLLoader; import javafx.scene.Parent; import javafx.scene.Scene; import javafx.scene.control.Button; import javafx.scene.control.PasswordField; import javafx.scene.control.TextField; import javafx.stage.Stage; import sample.animation.Shake; public class ControllerAuthorization { @FXML private ResourceBundle resources; @FXML private URL location; @FXML private TextField email; @FXML private PasswordField password;

@FXML private Button enter; @FXML void initialize() { enter.setOnAction(event -> { enter.getScene().getWindow().hide(); FXMLLoader loader = new FXMLLoader(); loader.setLocation(getClass().getResource("/sample/MainPage.fxml")); try { loader.load(); } catch (IOException e) { e.printStackTrace(); } Parent root = loader.getRoot(); Stage stage = new Stage(); stage.setScene(new Scene(root)); stage.showAndWait(); }); } }

Controller.java import java.io.IOException; import java.net.URL; import java.util.ResourceBundle; import javafx.fxml.FXML; import javafx.fxml.FXMLLoader; import javafx.scene.Parent; import javafx.scene.Scene; import javafx.scene.control.Button; import javafx.scene.control.PasswordField; import javafx.scene.control.TextField; import javafx.stage.Stage; public class Controller { @FXML private ResourceBundle resources; @FXML private URL location; @FXML private Button authorization;

@FXML private TextField name; @FXML private TextField surname; @FXML private TextField email; @FXML private PasswordField password; @FXML private Button applyReg; @FXML private TextField notify; @FXML void initialize() { authorization.setOnAction(event -> { authorization.getScene().getWindow().hide(); FXMLLoader loader = new FXMLLoader(); loader.setLocation(getClass().getResource("/sample/Authorization.fxml")); try { loader.load(); } catch (IOException e) { e.printStackTrace(); } Parent root = loader.getRoot(); Stage stage = new Stage(); stage.setScene(new Scene(root)); stage.showAndWait(); }); applyReg.setOnAction(event -> { applyReg.getScene().getWindow().hide(); FXMLLoader loader = new FXMLLoader();

loader.setLocation(getClass().getResource("/sample/Authorization.fxml")); try { loader.load(); } catch (IOException e) { e.printStackTrace(); } Parent root = loader.getRoot(); Stage stage = new Stage(); stage.setScene(new Scene(root)); stage.showAndWait(); }); applyReg.setOnAction(event1 -> { signUpNewUser(); }); } private void signUpNewUser() { applyReg.setText("Аккаунт успешно создан!"); DatabaseHandler dbHandler = new DatabaseHandler(); String Name = name.getText(); String Surname = surname.getText(); String Email = email.getText(); String Password = password.getText(); User user = new User(Name, Surname, Email, Password); dbHandler.signUpUser(user);




ControllerAssortment.java

package sample;

import java.io.IOException; import java.net.URL; import java.util.ResourceBundle; import javafx.fxml.FXML; import javafx.fxml.FXMLLoader; import javafx.scene.Parent; import javafx.scene.Scene; import javafx.scene.control.Button; import javafx.stage.Stage;

public class ControllerAssortment { @FXML private ResourceBundle resources; @FXML private URL location; @FXML private Button Tovar1; @FXML private Button Tovar2; @FXML private Button Tovar3; @FXML void initialize() { Tovar1.setOnAction(event -> { Tovar1.getScene().getWindow().hide(); FXMLLoader loader = new FXMLLoader(); loader.setLocation(getClass().getResource("/sample/Tovar1.fxml")); try { loader.load(); } catch (IOException e) { e.printStackTrace(); } Parent root = loader.getRoot(); Stage stage = new Stage(); stage.setScene(new Scene(root)); stage.showAndWait(); }); Tovar2.setOnAction(event -> { Tovar2.getScene().getWindow().hide(); FXMLLoader loader = new FXMLLoader(); loader.setLocation(getClass().getResource("/sample/Tovar2.fxml")); try { loader.load(); } catch (IOException e) { e.printStackTrace(); } Parent root = loader.getRoot(); Stage stage = new Stage(); stage.setScene(new Scene(root)); stage.showAndWait(); }); Tovar3.setOnAction(event -> { Tovar3.getScene().getWindow().hide(); FXMLLoader loader = new FXMLLoader(); loader.setLocation(getClass().getResource("/sample/Tovar3.fxml")); try { loader.load(); } catch (IOException e) {

e.printStackTrace(); } Parent root = loader.getRoot(); Stage stage = new Stage(); stage.setScene(new Scene(root)); stage.showAndWait(); }); } }




ControllerLastPage.java

package sample; import java.io.IOException; import java.net.URL; import java.util.ResourceBundle; import javafx.fxml.FXML; import javafx.fxml.FXMLLoader; import javafx.scene.Parent; import javafx.scene.Scene; import javafx.scene.control.Button; import javafx.stage.Stage; public class ControllerLastPage { @FXML private ResourceBundle resources; @FXML private URL location; @FXML private Button Assortment; @FXML void initialize() { Assortment.setOnAction(event -> { Assortment.getScene().getWindow().hide(); FXMLLoader loader = new FXMLLoader(); loader.setLocation(getClass().getResource("/sample/Assortment.fxml")); try { loader.load(); } catch (IOException e) { e.printStackTrace(); } Parent root = loader.getRoot(); Stage stage = new Stage(); stage.setScene(new Scene(root)); stage.showAndWait(); }); } }




DatabaseHandler.java

package sample; import java.sql.Connection; import java.sql.DriverManager; import java.sql.PreparedStatement;

import java.sql.SQLException; import java.sql.ResultSet; public class DatabaseHandler extends Configs { Connection dbConnection; public Connection getDbConnection() throws ClassNotFoundException, SQLException { String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName; Class.forName("com.mysql.cj.jdbc.Driver"); dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass); return dbConnection; } public void signUpUser(User user) { String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + "," + Const.USER_SURNAME + "," + Const.USER_EMAIL + "," + Const.USER_PASSWORD + ")" + "VALUES(?,?,?,?)"; Connection con = null; try { con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shoe?user=root&password=Warface12345678&serverTimezone=UTC"); PreparedStatement prSt = getDbConnection().prepareStatement(insert); prSt.setString(1, user.getName()); prSt.setString(2, user.getSurname()); prSt.setString(3, user.getEmail()); prSt.setString(4, user.getPassword()); prSt.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); } catch (ClassNotFoundException e) { e.printStackTrace(); } } public ResultSet getUser (User user){ ResultSet resSet = null; String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_EMAIL + "=? AND " + Const.USER_PASSWORD + "=?"; try { PreparedStatement prSt = getDbConnection().prepareStatement(select); prSt.setString(1, user.getEmail()); prSt.setString(2, user.getPassword()); resSet = prSt.executeQuery(); } catch (SQLException e) { e.printStackTrace(); } catch (ClassNotFoundException e) { e.printStackTrace(); }

return resSet; } }

User.java

package sample; public class User { private String name; private String surname; private String email; private String password; public User(String name, String surname, String email, String password) { this.name = name; this.surname = surname; this.email = email; this.password = password; } public User() { } public String getName() { return name; } public void setName(String name) { this.name = name; } public String getSurname() { return surname; } public void setSurname(String surname) { this.surname = surname; } public String getEmail() { return email; } public void setEmail(String email) { this.email = email; } public String getPassword() { return password; } public void setPassword(String password) { this.password = password; } }

Configs.java

package sample;

public class Configs {

protected String dbHost = "127.0.0.1";

protected String dbPort = "3306";

protected String dbUser = "root";

protected String dbPass = "Warface12345678";

protected String dbName = "shoe";

}

Const.java

package sample; public class Const { public static final String USER_TABLE = "users"; public static final String USERS_ID = "idusers"; public static final String USER_NAME = "name"; public static final String USER_SURNAME = "surname"; public static final String USER_EMAIL = "email"; public static final String USER_PASSWORD = "password"; }

animations / Shake.java

package animations;

import javafx.animation.TranslateTransition;

import javafx.scene.Node;

import javafx.util.Duration;

public class Shake {

private TranslateTransition tt;

public Shake(Node node){

tt = new TranslateTransition(Duration.millis(120), node);

tt.setFromX(0f);

tt.setByX(9f);

tt.setCycleCount(4);

tt.setAutoReverse(true);

}

public void playAnimation(){

tt.playFromStart();

}

}

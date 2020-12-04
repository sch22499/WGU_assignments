package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DAO.DBConnection;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DBConnection.makeConnection();

        Parent root = FXMLLoader.load(getClass().getResource("ViewController/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();

        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}

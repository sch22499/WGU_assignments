package sample.ViewController;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class Home {

    @FXML
    private Button viewScheduleButton;

    @FXML
    private Button viewCustomersButton;

    @FXML
    private Button viewReportsButton;

    @FXML
    private Button logOutButton;



    @FXML
    private void logOut(Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/viewController/login.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);


    }

    @FXML
    private void openReportsPage(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/viewController/reports.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }



    @FXML
    private void openSchedulePage(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/viewController/schedule.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

    @FXML
    private void openCustomerPage(Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/viewController/customer.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

}

package sample.ViewController;



import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DAO.AppointmentDAOImpl;
import sample.DAO.UserDAOImpl;
import sample.Main;


import javafx.scene.control.TextField;
import sample.Model.User;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.beans.binding.Bindings.when;

public class Login implements Initializable{

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;

    @FXML
    private Button loginButton;

    String fileName = "/sample/util/log.txt";

    private String path;
    private boolean shouldAppendToFile = false;





    private  ObservableList<User> users;
    private boolean isLoginValid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        ResourceBundle userLanguage;
//        Locale current = getLocale();
//        userLanguage = ResourceBundle.getBundle("ryanhildebrantsoftware2/Nat", current);
//
//
//        welcomeMessageLabel.setText(userLanguage.getString("welcome"));
//        usernameLabel.setText(userLanguage.getString("username"));
//        passwordLabel.setText(userLanguage.getString("password"));

    }


    public Login(){
        //default constructor
    }

    private void validateUserLogin() throws Exception {
        isLoginValid = false;

        User user = UserDAOImpl.getUser(usernameInput.getText());
        String password = passwordInput.getText();
        System.out.println(user.getPassword());

        try {

            if(user != null && user.getPassword().equals(password)){
                System.out.println("sarah ");
                isLoginValid = true;

                ReadFile file =new ReadFile(fileName);

            }
        }catch(Exception e){
            e.getMessage();
        }

    }

    @FXML
    private void onLoginButtonPressed(Event event) throws Exception {
        System.out.println(AppointmentDAOImpl.getAllAppointments());

        validateUserLogin();
        if(isLoginValid == true) {
            onSuccessfulLogin(event);
        }else{
            System.out.println("hoooo");
        }
    }



    private void onSuccessfulLogin(Event event) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/viewController/home.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }





}

package sample.ViewController;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DAO.AppointmentDAOImpl;
import sample.Main;
import sample.Model.Appointment;
import sample.Model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Schedule implements Initializable {


    private User currentUser;

    private static ObservableList<Appointment> allAppointments;

    private  static Appointment selectedAppointment;

    @FXML
    private ChoiceBox<String> monthDropdown;



    @FXML
    private SplitMenuButton weekDropdown;

    @FXML
    private TableView<Appointment> table;

    @FXML
    private TableColumn<Appointment, String> dateColumn;

    @FXML
    private TableColumn<Appointment, String> timeColumn;

    private static int selectedMonth;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private  TableColumn<Appointment, String> customerColumn;

    @FXML
    private TableColumn<Appointment, String> durationColumn;

    @FXML
    private Button homeButton;


    public static Appointment getSelectedAppointment(){
        return selectedAppointment;
    }

    public void setSelectedAppointment(Appointment selectedAppointment){
        this.selectedAppointment = selectedAppointment;

    }




    @FXML
    private void onHomeButtonSelect(Event event)throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/viewController/home.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }



    private void populateTable(ObservableList<Appointment> appointments) throws Exception {


        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime().toString()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime().toString()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        customerColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomerID()));
        durationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndTime().toString()));
        table.setItems(appointments);


    }

    //TODO how should we format this data for the thingy
    public static ObservableList<Appointment> getAppointmentsByMonth() throws Exception {

        ObservableList<Appointment> appointmentsByMonth = FXCollections.observableArrayList();


        allAppointments.forEach((Appointment appointment) -> {
            if (appointment.getStartTime().get(Calendar.MONTH) == selectedMonth) {
                appointmentsByMonth.add(appointment);
            }
        });
        return appointmentsByMonth;

    }

    private void getSelectedMonth(ChoiceBox<String> comboBox) throws Exception {
        String thisSelectedMonth = comboBox.getValue();

        switch(thisSelectedMonth){

            case "January":
                selectedMonth = 0;

            case "February":
                selectedMonth = 1;

            case "March":
                selectedMonth = 2;

            case "April":
                selectedMonth = 3;

            case "May":
                selectedMonth = 4;

            case "June":
                selectedMonth = 5;

            case "July":
                selectedMonth = 6;

            case "August":
                selectedMonth = 7;

            case "September":
                selectedMonth = 8;

            case "October":
                selectedMonth = 9;

            case "November":
                selectedMonth = 10;

            case "December":
                selectedMonth = 11;
        }
    }

    //TODO idk what is going on with the try catch here
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedMonth = 11;
        try {
            allAppointments = AppointmentDAOImpl.getAllAppointments();//AppointmentDAOImpl.getAppointmentsForUser(currentUser.getUserid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            populateTable(allAppointments);
        } catch (Exception e) {
            e.printStackTrace();
        }

        monthDropdown.getItems().addAll("January", "February","March","April","May","June","July","August","September","October","November","December");

        //TODO lambda
        monthDropdown.setOnAction(e -> {
            try {
                getSelectedMonth(monthDropdown);
                populateTable(getAppointmentsByMonth());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });



        setSelectedAppointment(null);
    }
}

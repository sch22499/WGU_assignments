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
import sample.DAO.CustomerDAOImpl;
import sample.Main;
import sample.Model.Appointment;
import sample.Model.Customer;
import sample.Model.User;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Schedule implements Initializable {


    private User currentUser;

    private static ObservableList<Appointment> allAppointments;

    private static ObservableList<Customer> allCustomers;

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

    public String getCustomerName(int customerID){

       String name = new String();

        for (Customer customer : allCustomers) {
            if(customer.getCustomerId() == customerID)
                name = customer.getCustomerName();

        }
       return name;
    }



    private void populateTable(ObservableList<Appointment> appointments) throws Exception {

//todo LAMBDA TO POPULATE TABLES

        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime().toString()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime().toString()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        customerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getCustomerName(cellData.getValue().getCustomerID())));
        durationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndTime().toString()));
        table.setItems(appointments);


    }

    public String getDayOfWeek(int value){
        String day = "";
        switch(value){
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }


    public String getDate(Calendar date, boolean isDate){

        String dayOfWeek = "";


        Date date1 = date.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        String dateString = "";
        dateString = format.format(date1);
        System.out.println(dateString);
        dayOfWeek = getDayOfWeek(date.get(Calendar.DAY_OF_WEEK));
        System.out.println(dayOfWeek);
        if(isDate == true){
            return dateString;
        }else {

            return dayOfWeek;
        }}

    //TODO how should we format this data for the thingy
    public  ObservableList<Appointment> getAppointmentsByMonth() throws Exception {

        ObservableList<Appointment> appointmentsByMonth = FXCollections.observableArrayList();


        allAppointments.forEach((Appointment appointment) -> {
            getDate(appointment.getStartTime(), true);
            if (appointment.getStartTime().get(Calendar.MONTH) == selectedMonth) {
                appointmentsByMonth.add(appointment);
            }

        });
        return appointmentsByMonth;

    }

    public ObservableList<Appointment> getAppointmnetsByWeekNumber(int weekNum){
        ObservableList<Appointment> appointmentsByWeekNum = FXCollections.observableArrayList();

        allAppointments.forEach((Appointment appointment) ->{
            int appWeekNum = appointment.getStartTime().get(Calendar.WEEK_OF_MONTH);
            if(appWeekNum == weekNum){
                appointmentsByWeekNum.add(appointment);
            }
        });

        return appointmentsByWeekNum;
    }

    private void getSelectedWeek(ChoiceBox<String> comboBox){

        
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
            allCustomers = CustomerDAOImpl.getAllCustomers();

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

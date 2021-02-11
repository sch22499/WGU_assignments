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

    //1000 milliseconds * 60 seconds * 60 minutes * 24 hours.
    private int dayInMilliseconds = 1000*60*60*24;

    @FXML
    private Button homeButton;



    //TODO split this class up?
    //Table thingies for month view
    @FXML
    private TableView<Appointment> tableMonthView;

    @FXML
    private TableColumn<Appointment, String> dateColumnMonthView;

    @FXML
    private ChoiceBox<String> dropdownMonthView;

    @FXML
    private TableColumn<Appointment, String> timeColumnMonthView;

    @FXML
    private TableColumn<Appointment, String> typeColumnMonthView;

    @FXML
    private  TableColumn<Appointment, String> customerColumnMonthView;

    @FXML
    private TableColumn<Appointment, String> durationColumnMonthView;

    private static int selectedMonth;


    //Thiniges for weekView
    @FXML
    private SplitMenuButton weekDropdown;

    @FXML
    private TableView<Appointment> tableWeekView;

    @FXML
    private TableColumn<Appointment, String> dateColumnWeekView;

    @FXML
    private ChoiceBox<String> dropdownWeekView;

    @FXML
    private TableColumn<Appointment, String> timeColumnWeekView;

    @FXML
    private TableColumn<Appointment, String> typeColumnWeekView;

    @FXML
    private  TableColumn<Appointment, String> customerColumnWeekView;

    @FXML
    private TableColumn<Appointment, String> durationColumnWeekView;

    private static int selectedWeek;


    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public void setSelectedAppointment(Appointment selectedAppointment){
        this.selectedAppointment = selectedAppointment;

    }

    public ChoiceBox<String> getMonthDropdown() {
        return dropdownMonthView;
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

        dateColumnMonthView.setCellValueFactory(cellData -> new SimpleStringProperty(getDate(cellData.getValue().getStartTime(), true)));
        timeColumnMonthView.setCellValueFactory(cellData -> new SimpleStringProperty(getTime(cellData.getValue().getStartTime())));
        typeColumnMonthView.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        customerColumnMonthView.setCellValueFactory(cellData -> new SimpleStringProperty(getCustomerName(cellData.getValue().getCustomerID())));
        durationColumnMonthView.setCellValueFactory(cellData -> new SimpleStringProperty(getDuration(cellData.getValue().getStartTime(), cellData.getValue().getEndTime())));
        tableMonthView.setItems(appointments);


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

    private int getMondayForLastWeek(int timeInMilliseconds){

        //TODO clean up

        int currentDayOfWeek;
        int sevenDaysAgo = timeInMilliseconds - (dayInMilliseconds * 7);
        int lastMonday = sevenDaysAgo - ((dayInMilliseconds) * timeInMilliseconds);

        return 1;
    }

    private int getMondayForNextWeek(int timeInMilliseconds){

        int currentDayOfWeek = 0 ;
        int sevenDaysFromNow = timeInMilliseconds + (1000 * 60 * 60 * 24 * 7);
        int nextMonday = sevenDaysFromNow - ((1000*60*60*24) * currentDayOfWeek);


        return 0;
    }
    private int getMondayForThisWeek(int timeInMilliseconds){


        int currentDayOfWeek = 0; //from time in milliseconds.
        int monday = timeInMilliseconds - (dayInMilliseconds * currentDayOfWeek);
        return 0;
    }

    private String getDuration(Calendar startTime, Calendar endTime){


        long duration = (endTime.getTimeInMillis() - startTime.getTimeInMillis())/60;

        return String.valueOf(duration);

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


        private String getTime(Calendar date){

            String time = "";

            Date date1 = date.getTime();

            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            time = format.format(date1);
            return time;
        }

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
        dropdownMonthView.getItems().addAll(
                "January", "February","March",
                "April", "May","June","July","August",
                "September","October","November","December");

        //TODO lambda
        dropdownMonthView.setOnAction(e -> {
            try {
                getSelectedMonth(dropdownMonthView);
                populateTable(getAppointmentsByMonth());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });



        setSelectedAppointment(null);
    }
}

package sample.ViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Path;
import sample.DAO.AppointmentDAOImpl;
import sample.DAO.CustomerDAOImpl;
import sample.DAO.UserDAOImpl;
import sample.Model.Appointment;
import sample.Model.User;
import sample.Model.Customer;
import sample.util.TimeFiles;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Reports implements Initializable{

    @FXML
    private TextArea textArea;

    @FXML
    private SplitMenuButton menuButton = new SplitMenuButton();

    @FXML
    private MenuItem appointmentTypesOption;

    @FXML
    private MenuItem scheduleOption;

    @FXML
    private MenuItem loginRecordOption;

    @FXML
    private Button homeButton;

    private static String parsedSchedule;

    static {
        try {
            parsedSchedule = AppointmentDAOImpl.parseAppointmentTypes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<Appointment> appointments;

    static {
        try {
            appointments = AppointmentDAOImpl.getAllAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<User> users;

    static {
        try {
            users = UserDAOImpl.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<Customer> customers;

    static {
        try {
            customers = CustomerDAOImpl.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Reports(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textArea.setText("report data will appear here");

    }



    @FXML
    private void onSelectConsultantScheduleOption() throws Exception {
        textArea.clear();
        textArea.setText(generateScheduleReport());

    }


    private String generateScheduleReport() throws Exception {
        //TODO add per user

        String text = "";

        for(User user : users) {
            String appointmentText = "";
            List userIds = new ArrayList();
            for (Appointment appointment:
                 appointments) {
                if (appointment.getUserID() == user.getUserid()) {
                        int customerId = appointment.getCustomerID();
                    String customerName = CustomerDAOImpl.getCustomer(customerId).getCustomerName(); //BY ID
                    appointmentText += "\t" + appointment.getType() + " From: " +
                                    TimeFiles.calendarToString(appointment.getStartTime()) + " To: " +
                                    TimeFiles.calendarToString(appointment.getEndTime()) + " With: " + customerName + "\n";
                    userIds.add(user.getUserid());
//                    for (Customer customer :
//                            customers) {
//                        if (appointment.getCustomerID() == customer.getCustomerId()) {
//                            String customerName = customer.getCustomerName();
//                            appointmentText += "\t" + appointment.getType() + " From: " +
//                                    TimeFiles.calendarToString(appointment.getStartTime()) + " To: " +
//                                    TimeFiles.calendarToString(appointment.getEndTime()) + " With: " + customerName + "\n";
//                        }
//                    }

                }

            }
            if(!userIds.contains(user.getUserid())){
                appointmentText = "\t No Appointments to Show \n";
            }

            text += user.getUserName() +": \n" + appointmentText;
        }
            return text;
    }

    @FXML //TODO add month
    private void onSelectAppointmentTypeOption() throws SQLException {
        textArea.clear();
        textArea.setText(AppointmentDAOImpl.parseAppointmentTypes());
    }

    public static String readFileAsString(String fileName){
        String text = "";
        try{
            text = new String (Files.readAllBytes((Paths.get(fileName))));
        }catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }

    @FXML
    private void onSelectLoginRecordOption(){
        textArea.clear();


        textArea.setText(readFileAsString("fileName.txt"));

    }


    @FXML
    private void onSelectMenu(){
        textArea.clear();
        textArea.setText("report data will appear here");

    }



}

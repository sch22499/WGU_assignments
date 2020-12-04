package sample.ViewController;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.DAO.AppointmentDAOImpl;
import sample.Model.Appointment;
import sample.Model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class Schedule implements Initializable {


    private User currentUser;

    private  static Appointment modifiedAppointment;

    @FXML
    private TableView<Appointment> table;

    @FXML
    private TableColumn<Appointment, String> dateColumn;

    @FXML
    private TableColumn<Appointment, String> timeColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private  TableColumn<Appointment, String> customerColumn;

    @FXML
    private TableColumn<Appointment, String> durationColumn;

    public static Appointment getModifiedAppointment(){
        return modifiedAppointment;
    }

    public void setModifiedAppointment(Appointment modifiedAppointment){
        this.modifiedAppointment = modifiedAppointment;

    }



    private void populateTable() throws Exception {


        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime().toString()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime().toString()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
       // customerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndTime().toString()));
        durationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndTime().toString()));
        table.setItems(AppointmentDAOImpl.getAppointmentsForUser(currentUser.getUserid()));


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setModifiedAppointment(null);
        try {
            populateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
